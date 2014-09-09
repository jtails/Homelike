package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.endpoint.proveedorendpoint.Proveedorendpoint;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.api.model.ProveedorCollection;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class ListProvidersRequest extends HomelikeApiRequest {

    private int mServiceId;
    private Direccion mAddress;

    public ListProvidersRequest(ListProvidersResponseHandler handler,
        int serviceId, Direccion address){
        super(handler);
        this.mEndpoint = new Proveedorendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
        this.mServiceId = serviceId;
        this.mAddress = address;
    }

    protected Object doRequest() throws Exception {
        ProveedorCollection providers =
                ((Proveedorendpoint) this.mEndpoint).listProveedoresinRange(
                this.mServiceId, this.mAddress.getLatitud(), this.mAddress.getLongitud()).execute();
        List<Proveedor> providersList = providers.getItems();
        return providersList;
    }

    protected void notifyResponseHandler(Object o){
        List<Proveedor> locatedProviders = o == null ?
                new ArrayList<Proveedor>() : (List<Proveedor>) o;
        ((ListProvidersResponseHandler) this.mReponseHandler)
                .onListProvidersResponse(locatedProviders);
    }

    public interface ListProvidersResponseHandler extends HomelikeResponseHandler {
        public void onListProvidersResponse(List<Proveedor> providers);
    }

}
