package mx.jtails.provider.homelike.request;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.provider.homelike.api.endpoint.pedidoendpoint.Pedidoendpoint;
import mx.jtails.provider.homelike.api.model.Pedido;
import mx.jtails.provider.homelike.api.model.PedidoCollection;

/**
 * Created by GrzegorzFeathers on 11/25/14.
 */
public class ListOrdersRequest extends HomelikeApiRequest<List<Pedido>> {

    private int mProviderId;

    public ListOrdersRequest(HomelikeApiResponseHandler handler, int providerId) {
        super(handler, new Pedidoendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build());
        this.mProviderId = providerId;
    }

    @Override
    protected AbstractGoogleJsonClientRequest getRequest() throws Exception {
        return ((Pedidoendpoint) this.mEndpoint).listPedidosByProveedor()
                .set("idProveedor", this.mProviderId);
    }

    @Override
    protected List<Pedido> preNotificationProcess(Object o) {
        if(o == null){
            return new ArrayList<Pedido>();
        } else {
            return ((PedidoCollection) o).getItems();
        }
    }

}
