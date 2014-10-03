package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.endpoint.pedidoendpoint.Pedidoendpoint;
import mx.jtails.homelike.api.model.Pedido;

/**
 * Created by GrzegorzFeathers on 10/2/14.
 */
public class ListOrderCommentsByProviderRequest extends HomelikeApiRequest {

    private int mProviderId;

    public ListOrderCommentsByProviderRequest(OnListCommentsResponseHandler handler, int providerId) {
        super(handler);
        this.mEndpoint = new Pedidoendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
        this.mProviderId = providerId;
    }

    @Override
    protected Object doRequest() throws Exception {
        Pedidoendpoint.GetComentariosPedidosByProveedor tmpRequest =
                ((Pedidoendpoint) this.mEndpoint).getComentariosPedidosByProveedor();
        tmpRequest.put("idProveedor", this.mProviderId);
        return tmpRequest.execute().getItems();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        List<Pedido> orders = o == null ?
                new ArrayList<Pedido>() : (List<Pedido>) o;
        ((OnListCommentsResponseHandler) this.mReponseHandler)
                .onListCommentsResponse(orders);
    }

    public interface OnListCommentsResponseHandler extends HomelikeResponseHandler {
        public void onListCommentsResponse(List<Pedido> orders);
    }
}
