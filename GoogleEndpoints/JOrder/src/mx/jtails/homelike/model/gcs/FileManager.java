/*
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mx.jtails.homelike.model.gcs;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * A main method to show how to use the GCS client locally.
 *
 */
public class FileManager {

  /**
   * This is the service from which all requests are initiated.
   * The retry and exponential backoff settings are configured here.
   */
  private final GcsService gcsService =
      GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());

  /**
   * Writes the provided object to the specified file using Java serialization. One could use
   * this same technique to write many objects, or with another format such as Json or XML or just a
   * DataOutputStream.
   *
   * Notice at the end closing the ObjectOutputStream is not done in a finally block.
   * See below for why.
   */
  public void writeObjectToFile(GcsFilename fileName, Object content) throws IOException {
    GcsOutputChannel outputChannel =
        gcsService.createOrReplace(fileName, GcsFileOptions.getDefaultInstance());
    @SuppressWarnings("resource")
    ObjectOutputStream oout =
        new ObjectOutputStream(Channels.newOutputStream(outputChannel));
    oout.writeObject(content);
    oout.close();
  }

  /**
   * Writes the byte array to the specified file. Note that the close at the end is not in a
   * finally.This is intentional. Because the file only exists for reading if close is called, if
   * there is an exception thrown while writing the file won't ever exist. (This way there is no
   * need to worry about cleaning up partly written files)
   */
  public void writeToFile(GcsFilename fileName, byte[] content) throws IOException {
    @SuppressWarnings("resource")
    GcsOutputChannel outputChannel =
        gcsService.createOrReplace(fileName, GcsFileOptions.getDefaultInstance());
    outputChannel.write(ByteBuffer.wrap(content));
    outputChannel.close();
  }

  /**
   * Reads an object from the specified file using Java serialization. One could use this same
   * technique to read many objects, or with another format such as Json or XML or just a
   * DataInputStream.
   *
   * The final parameter to openPrefetchingReadChannel is a buffer size. It will attempt to buffer
   * the input by at least this many bytes. (This must be at least 1kb and less than 10mb) If
   * buffering is undesirable openReadChannel could be called instead, which is totally unbuffered.
   */
  public Object readObjectFromFile(GcsFilename fileName)
      throws IOException, ClassNotFoundException {
    GcsInputChannel readChannel = gcsService.openPrefetchingReadChannel(fileName, 0, 1024 * 1024);
    try (ObjectInputStream oin = new ObjectInputStream(Channels.newInputStream(readChannel))) {
      return oin.readObject();
    }
  }

  /**
   * Reads the contents of an entire file and returns it as a byte array. This works by first
   * requesting the length, and then fetching the whole file in a single call. (Because it calls
   * openReadChannel instead of openPrefetchingReadChannel there is no buffering, and thus there is
   * no need to wrap the read call in a loop)
   *
   * This is really only a good idea for small files. Large files should be streamed out using the
   * prefetchingReadChannel and processed incrementally.
   */
  public byte[] readFromFile(GcsFilename fileName) throws IOException {
    int fileSize = (int) gcsService.getMetadata(fileName).getLength();
    ByteBuffer result = ByteBuffer.allocate(fileSize);
    try (GcsInputChannel readChannel = gcsService.openReadChannel(fileName, 0)) {
      readChannel.read(result);
    }
    return result.array();
  }

  /**
   * Writes a map to GCS and then reads it back printing the result to standard out.
   * Then does the same for a byte array.
   * (You may wish to suppress stderr as there is a lot of noise)
   */
  public static void main(String[] args) throws IOException, ClassNotFoundException {
	    try {
			FileManager fileManager=new FileManager();
			GcsFilename filename = new GcsFilename("steam-form-673.appspot.com", "report.csv");
		    fileManager.writeToFile(filename, "uno,dos,tres".getBytes());
			System.out.println(" read: " + fileManager.readFromFile(filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la generacion del archivo");
		}
  }
}
