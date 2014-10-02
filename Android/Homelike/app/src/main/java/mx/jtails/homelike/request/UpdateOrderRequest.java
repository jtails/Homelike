package mx.jtails.homelike.request;

import mx.jtails.homelike.api.endpoint.pedidoendpoint.Pedidoendpoint;
import mx.jtails.homelike.api.model.Pedido;

/**
 * Created by GrzegorzFeathers on 9/16/14.
 */
public class UpdateOrderRequest extends HomelikeApiRequest {

    private Pedido mOrder;

    public UpdateOrderRequest(HomelikeResponseHandler handler, Pedido order) {
        super(handler);
        this.mEndpoint = new Pedidoendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();

        this.mOrder = order;
    }

    @Override
    protected Object doRequest() throws Exception {
        return ((Pedidoendpoint) this.mEndpoint).insertPedido(this.mOrder).execute();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        if(this.mReponseHandler != null){
            ((OnUpdateOrderResponseHandler) this.mReponseHandler).onUpdateOrderResponse(
                    o == null ? null : (Pedido) o);
        }
    }

    public interface OnUpdateOrderResponseHandler extends HomelikeResponseHandler {
        public void onUpdateOrderResponse(Pedido order);
    }
}
