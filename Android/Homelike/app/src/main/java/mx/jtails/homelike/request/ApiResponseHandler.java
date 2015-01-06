package mx.jtails.homelike.request;

/**
 * Created by GrzegorzFeathers on 11/24/14.
 */
public interface ApiResponseHandler<T> {
    public void onResponse(T response);
}
