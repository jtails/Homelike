package mx.jtails.homelike.ui.adapter;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.endpoint.pedidoendpoint.Pedidoendpoint;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.api.model.PedidoCollection;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.request.ApiRequest;
import mx.jtails.homelike.request.ApiResponseHandler;

/**
 * Created by GrzegorzFeathers on 11/25/14.
 */
public class ListProviderOrdersRequest extends ApiRequest<List<Pedido>> {

    private int mProviderId;

    public ListProviderOrdersRequest(ApiResponseHandler handler, int providerId) {
        super(handler, new Pedidoendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build());
        this.mProviderId = providerId;
    }

    @Override
    protected AbstractGoogleJsonClientRequest getRequest() throws Exception {
        return ((Pedidoendpoint) this.mEndpoint).listPedidosByProveedor(
                new Proveedor().setIdProveedor(this.mProviderId));
    }

    @Override
    protected List<Pedido> preNotificationProcess(Object o) {
        if(o == null || ((PedidoCollection) o).getItems() == null){
            return new ArrayList<Pedido>();
        } else {
            return ((PedidoCollection) o).getItems();
        }
    }

}
