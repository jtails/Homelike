package mx.jtails.homelike.request;

import mx.jtails.homelike.api.endpoint.cuentaendpoint.Cuentaendpoint;
import mx.jtails.homelike.api.model.Direccion;

/**
 * Created by GrzegorzFeathers on 11/6/14.
 */
public class DeleteAddressRequest extends HomelikeApiRequest {

    private Direccion mAddress;

    public DeleteAddressRequest(HomelikeResponseHandler handler, Direccion address) {
        super(handler);
        this.mEndpoint = new Cuentaendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
        this.mAddress = address;
    }

    @Override
    protected Object doRequest() throws Exception {
        return ((Cuentaendpoint) this.mEndpoint)
                .deleteDireccion(this.mAddress.getIdDireccion()).execute();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        if(this.mReponseHandler != null){
            ((OndeleteAddressResponseHandler) this.mReponseHandler).onDeleteAddressResponse();
        }
    }

    public interface OndeleteAddressResponseHandler extends HomelikeResponseHandler {
        public void onDeleteAddressResponse();
    }

}
