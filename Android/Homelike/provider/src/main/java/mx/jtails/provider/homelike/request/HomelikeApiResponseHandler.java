package mx.jtails.provider.homelike.request;

/**
 * Created by GrzegorzFeathers on 11/24/14.
 */
public interface HomelikeApiResponseHandler<T> {
    public void onResponse(T response);
}
