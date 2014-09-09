package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.endpoint.servicioendpoint.Servicioendpoint;
import mx.jtails.homelike.api.model.CollectionResponseServicio;
import mx.jtails.homelike.api.model.Servicio;

/**
 * Created by GrzegorzFeathers on 9/1/14.
 */
public class ListServicesRequest extends HomelikeApiRequest {

    public ListServicesRequest(ListServicesResponseHandler handler){
        super(handler);
        this.mEndpoint = new Servicioendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
    }

    protected Object doRequest() throws Exception {
        CollectionResponseServicio serviceCollection =
                ((Servicioendpoint) this.mEndpoint).listServicio().execute();
        return serviceCollection.getItems();
    }

    protected void notifyResponseHandler(Object o){
        List<Servicio> locatedServices = o == null ?
                new ArrayList<Servicio>() : (List<Servicio>) o;
        ((ListServicesResponseHandler) this.mReponseHandler)
                .onListServicesResponse(locatedServices);
    }

    public interface ListServicesResponseHandler extends HomelikeResponseHandler {
        public void onListServicesResponse(List<Servicio> services);
    }

}
