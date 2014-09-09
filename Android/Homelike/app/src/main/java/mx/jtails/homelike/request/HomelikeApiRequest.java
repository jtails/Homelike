package mx.jtails.homelike.request;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

/**
 * Created by GrzegorzFeathers on 9/1/14.
 */
public abstract class HomelikeApiRequest {

    protected static final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();
    protected static final JsonFactory JSON_FACTORY = new AndroidJsonFactory();

    protected HomelikeResponseHandler mReponseHandler;
    protected AbstractGoogleJsonClient mEndpoint;

    private HomelikeApiAsyncTask mAsyncTask;

    public HomelikeApiRequest(HomelikeResponseHandler handler){
        this.mReponseHandler = handler;
    }

    abstract protected Object doRequest() throws Exception;

    abstract protected void notifyResponseHandler(Object o);

    public void executeAsync(){
        this.mAsyncTask = new HomelikeApiAsyncTask();
        this.mAsyncTask.execute();
    }

    public void cancelRequest(){
        if(this.mAsyncTask != null && !this.mAsyncTask.isCancelled()){
            this.mAsyncTask.cancel(true);
            this.mAsyncTask = null;
        }
    }

    private class HomelikeApiAsyncTask extends AsyncTask<String, Integer, Object> {

        @Override
        protected Object doInBackground(String... param) {
            try {
                return doRequest();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            notifyResponseHandler(o);
        }
    }

    protected interface HomelikeResponseHandler {}

}
