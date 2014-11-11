package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.Arrays;

import mx.jtails.homelike.api.endpoint.cuentaendpoint.Cuentaendpoint;
import mx.jtails.homelike.api.model.Cuenta;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Dispositivo;
import mx.jtails.homelike.util.HomelikePreferences;

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
        Cuenta account = new Cuenta();
        account.setIdCuenta(HomelikePreferences.loadInt(
                HomelikePreferences.ACCOUNT_ID, -1));

        Dispositivo device = new Dispositivo();
        device.setIdDispositivo(HomelikePreferences.loadInt(
                HomelikePreferences.DEVICE_ID, -1));

        account.setDirecciones(new ArrayList<Direccion>(Arrays.asList(
                new Direccion().setIdDireccion(this.mAddress.getIdDireccion()))));
        //account.setDispositivos(new ArrayList<Dispositivo>(Arrays.asList(device)));

        Cuentaendpoint.DeleteDireccion request =
                ((Cuentaendpoint) this.mEndpoint).deleteDireccion(this.mAddress.getIdDireccion());
        //String jsonString = request.getJsonContent().toString();
        System.out.println(request.toString());
        //System.out.println(jsonString);
        System.out.println(request.buildHttpRequestUrl());

        return request.execute();
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
