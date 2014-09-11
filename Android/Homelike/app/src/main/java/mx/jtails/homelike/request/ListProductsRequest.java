package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.endpoint.productoendpoint.Productoendpoint;
import mx.jtails.homelike.api.model.Producto;
import mx.jtails.homelike.api.model.ProductoCollection;

/**
 * Created by GrzegorzFeathers on 9/10/14.
 */
public class ListProductsRequest extends HomelikeApiRequest {

    private int mProviderId;

    public ListProductsRequest(ListProductsResponseHandler handler,
                                int providerId){
        super(handler);
        this.mEndpoint = new Productoendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
        this.mProviderId = providerId;
    }

    protected Object doRequest() throws Exception {
        Productoendpoint.ListProductosByProveedor tempList = ((Productoendpoint)
                this.mEndpoint).listProductosByProveedor();
        tempList.put("idProveedor", this.mProviderId);

        ProductoCollection products = tempList.execute();

        return products.getItems();
    }

    protected void notifyResponseHandler(Object o){
        List<Producto> products = o == null ?
                new ArrayList<Producto>() : (List<Producto>) o;
        ((ListProductsResponseHandler) this.mReponseHandler)
                .onListProductsResponse(products);
    }

    public interface ListProductsResponseHandler extends HomelikeResponseHandler {
        public void onListProductsResponse(List<Producto> products);
    }

}
