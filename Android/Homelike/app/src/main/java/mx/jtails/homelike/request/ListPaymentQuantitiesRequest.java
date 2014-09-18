package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.endpoint.cantidadpagoendpoint.Cantidadpagoendpoint;
import mx.jtails.homelike.api.model.CantidadPago;
import mx.jtails.homelike.api.model.CollectionResponseCantidadPago;

/**
 * Created by GrzegorzFeathers on 9/17/14.
 */
public class ListPaymentQuantitiesRequest extends HomelikeApiRequest {

    public ListPaymentQuantitiesRequest(HomelikeResponseHandler handler) {
        super(handler);
        this.mEndpoint = new Cantidadpagoendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
    }

    @Override
    protected Object doRequest() throws Exception {
        CollectionResponseCantidadPago paymentQuantities =
                ((Cantidadpagoendpoint) this.mEndpoint).listCantidadPago().execute();
        return paymentQuantities.getItems();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        if(this.mReponseHandler != null){
            ((OnListPaymentQuantitiesResponseHandler) this.mReponseHandler)
                    .onListPaymentQuantities(o == null ? new ArrayList<CantidadPago>() :
                            (List<CantidadPago>) o);
        }
    }

    public interface OnListPaymentQuantitiesResponseHandler extends HomelikeResponseHandler {
        public void onListPaymentQuantities(List<CantidadPago> paymentQuantities);
    }
}
