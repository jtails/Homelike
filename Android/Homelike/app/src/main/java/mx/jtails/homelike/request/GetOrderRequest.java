package mx.jtails.homelike.request;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;

import mx.jtails.homelike.api.endpoint.pedidoendpoint.Pedidoendpoint;
import mx.jtails.homelike.api.model.Pedido;

/**
 * Created by GrzegorzFeathers on 12/20/14.
 */
public class GetOrderRequest extends ApiRequest<Pedido> {

    private long mOderId;

    public GetOrderRequest(ApiResponseHandler<Pedido> handler, long orderId) {
        super(handler, new Pedidoendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build());
        this.mOderId = orderId;
    }

    @Override
    protected AbstractGoogleJsonClientRequest<Pedido> getRequest() throws Exception {
        return ((Pedidoendpoint) this.mEndpoint).getPedido(this.mOderId);
    }
}
