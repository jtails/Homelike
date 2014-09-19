package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.endpoint.pedidoendpoint.Pedidoendpoint;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.api.model.PedidoCollection;

/**
 * Created by GrzegorzFeathers on 9/1/14.
 */
public class ListOrdersRequest extends HomelikeApiRequest {

    private int mAccountId;

    public ListOrdersRequest(ListOrdersResponseHandler handler, int accountId){
        super(handler);
        this.mEndpoint = new Pedidoendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
        this.mAccountId = accountId;
    }

    protected Object doRequest() throws Exception {
        Pedidoendpoint.ListPedidosByCuenta request =
                ((Pedidoendpoint) this.mEndpoint).listPedidosByCuenta();
        request.put("idCuenta", this.mAccountId);
        PedidoCollection pedidoCollection = request.execute();
        return pedidoCollection.getItems();
    }

    protected void notifyResponseHandler(Object o){
        if(this.mReponseHandler != null){
            List<Pedido> orders = o == null ? new ArrayList<Pedido>() : (List<Pedido>) o;
            ((ListOrdersResponseHandler) this.mReponseHandler)
                    .onListOrdersResponse(orders);
        }
    }

    public interface ListOrdersResponseHandler extends HomelikeResponseHandler {
        public void onListOrdersResponse(List<Pedido> orders);
    }

}
