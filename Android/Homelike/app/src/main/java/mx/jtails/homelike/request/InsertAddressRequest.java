package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mx.jtails.homelike.api.model.Cuenta;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Dispositivo;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 9/18/14.
 */
public class InsertAddressRequest extends HomelikeApiRequest {

    private Direccion mAddress;

    public InsertAddressRequest(HomelikeResponseHandler handler, Direccion address) {
        super(handler);
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

        account.setDirecciones(new ArrayList<Direccion>(Arrays.asList(this.mAddress)));
        account.setDispositivos(new ArrayList<Dispositivo>(Arrays.asList(device)));

        return new InsertAccountRequest(null, account).executeOnThread();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        if(this.mReponseHandler != null){
            ((OnInsertAddressResponseHandler) this.mReponseHandler).onInsertAddressResponse(
                o == null || ((Cuenta) o).getDirecciones() == null ?
                null : ((Cuenta) o).getDirecciones());
        }
    }

    public interface OnInsertAddressResponseHandler extends HomelikeResponseHandler {
        public void onInsertAddressResponse(List<Direccion> addresses);
    }
}
