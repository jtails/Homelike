package mx.jtails.provider.homelike.request;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;

import mx.jtails.provider.homelike.api.endpoint.pedidoendpoint.Pedidoendpoint;
import mx.jtails.provider.homelike.api.model.Pedido;

/**
 * Created by GrzegorzFeathers on 11/26/14.
 */
public class UpdateOrderRequest extends HomelikeApiRequest<Pedido> {

    private Pedido mOrder;

    public UpdateOrderRequest(HomelikeApiResponseHandler<Pedido> handler, Pedido order) {
        super(handler, new Pedidoendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build());
        this.mOrder = order;
    }

    @Override
    protected AbstractGoogleJsonClientRequest<Pedido> getRequest() throws Exception {
        return ((Pedidoendpoint) this.mEndpoint).insertPedido(this.mOrder);
    }

}
