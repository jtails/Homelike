package mx.jtails.homelike.request;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public abstract class ApiRequest<T> {

    protected static final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();
    protected static final JsonFactory JSON_FACTORY = new AndroidJsonFactory();

    protected ApiResponseHandler<T> mResponseHandler;
    protected AbstractGoogleJsonClient mEndpoint;

    private HomelikeApiAsyncTask mAsyncTask;

    public ApiRequest(ApiResponseHandler<T> handler,
                      AbstractGoogleJsonClient endpoint){
        this.mResponseHandler = handler;
        this.mEndpoint = endpoint;
    }

    abstract protected AbstractGoogleJsonClientRequest<T> getRequest() throws Exception;

    protected T preNotificationProcess(Object o){
        return o == null ? null : (T) o;
    }

    protected void notifyResponseHandler(Object response) {
        if(this.mResponseHandler != null){
            this.mResponseHandler.onResponse(this.preNotificationProcess(response));
        }
    }

    protected String getTag(){
        return this.getClass().getSimpleName();
    }

    public void executeAsync(){
        Log.d(this.getTag(), "Starting request asynchronously: " + this.getTag());
        this.mAsyncTask = new HomelikeApiAsyncTask();
        this.mAsyncTask.execute();
    }

    public T executeOnThread(){
        Log.d(this.getTag(), "Starting request synchronously: " + this.getTag());
        return this.doRequest();
    }

    private T doRequest() {
        try {
            AbstractGoogleJsonClientRequest<T> request = this.getRequest();

            this.dumpRequest(request);
            T response = request.execute();
            this.dumpResponse(response);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void dumpRequest(AbstractGoogleJsonClientRequest request){
        try {
            Log.d(this.getTag(), "Requesting: ");
            String uriTemplate = request.getUriTemplate();
            String jsonContent = request.getJsonContent() != null ?
                    request.getJsonContent().toString() : "No JSON content";
            Log.d(this.getTag(), "URI: " + uriTemplate);
            Log.d(this.getTag(), "Content: " + jsonContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dumpResponse(T response) {
        try {
            Log.d(this.getTag(), "Response received: ");
            Log.d(this.getTag(), response == null ? "Null Response" : response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelRequest(){
        if(this.mAsyncTask != null && !this.mAsyncTask.isCancelled()){
            this.mAsyncTask.cancel(true);
            this.mAsyncTask = null;
        }
    }

    private class HomelikeApiAsyncTask extends AsyncTask<String, Integer, T> {

        @Override
        protected T doInBackground(String... param) {
            return doRequest();
        }

        @Override
        protected void onPostExecute(T o) {
            notifyResponseHandler(o);
        }
    }

}

