/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.jtails.homelike.model.gcm;

import mx.jtails.homelike.model.endpoints.Constants;
import mx.jtails.homelike.model.gcm.Message;
//import mx.jtails.homelike.model.gcm.MulticastResult;
import mx.jtails.homelike.model.gcm.Result;
import mx.jtails.homelike.model.gcm.Sender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendMessage{

  private static final int MULTICAST_SIZE = 1000;
  private static final Logger logger = Logger.getLogger(SendMessage.class.getName());
  
	public void sendSingle(List<String> devices, Message message) {
		String status;
		if (devices.isEmpty()) {
			status = "Message ignored as there is no device registered!";
		} else {
			// send a multicast message using JSON
			// must split in chunks of 1000 devices (GCM limit)
			int total = devices.size();
			List<String> partialDevices = new ArrayList<String>(total);
			int counter = 0;
			int tasks = 0;
			for (String device : devices) {
				counter++;
				partialDevices.add(device);
				int partialSize = partialDevices.size();
				if (partialSize == MULTICAST_SIZE || counter == total) {
					partialSend(partialDevices, message);
					partialDevices.clear();
					tasks++;
				}
			}
			status = "Asynchronously sending " + tasks
					+ " multicast messages to " + total + " devices";
		}
		logger.warning(status);
	}
  
  
	private void partialSend(List<String> partialDevices, Message message) {
		// make a copy
		final List<String> devices = new ArrayList<String>(partialDevices);
		MulticastResult multicastResult;
		try {
			Sender sender = new Sender(Constants.SERVER_KEY);
			multicastResult = sender.send(message, devices, 5);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error posting messages", e);
			return;
		}
		List<Result> results = multicastResult.getResults();
		// analyze the results
		for (int i = 0; i < devices.size(); i++) {
			String regId = devices.get(i);
			Result result = results.get(i);
			String messageId = result.getMessageId();
			if (messageId != null) {
				logger.warning("Succesfully sent message to device: " + regId
						+ "; messageId = " + messageId);
				String canonicalRegId = result.getCanonicalRegistrationId();
				if (canonicalRegId != null) {
					// same device has more than on registration id: update it
					logger.warning("canonicalRegId " + canonicalRegId);
					// Datastore.updateRegistration(regId, canonicalRegId);
				}
			} else {
				String error = result.getErrorCodeName();
				if (error
						.equals(mx.jtails.homelike.model.gcm.Constants.ERROR_NOT_REGISTERED)) {
					// application has been removed from device - unregister it
					logger.warning("Unregistered device: " + regId);
					// Datastore.unregister(regId);
				} else {
					logger.warning("Error sending message to " + regId + ": "
							+ error);
				}
			}
		}
		logger.warning("Total: "+multicastResult.getTotal()+", Fallidos: "+multicastResult.getFailure()+", Exitosos: "+multicastResult.getSuccess());
	}
 

}
