package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.model.Cuenta;
import mx.jtails.homelike.api.model.Direccion;
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
        Object objAccount = new GetAccountRequest(null, HomelikePreferences.loadInt(
                HomelikePreferences.ACCOUNT_ID, -1)).executeOnThread();
        if(objAccount == null){ return null; }

        Cuenta account = (Cuenta) objAccount;
        List<Direccion> addresses = new ArrayList<Direccion>();
        addresses.add(this.mAddress);
        account.setDirecciones(addresses);

        return new InsertAccountRequest(null, account).executeOnThread();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        if(this.mReponseHandler != null){
            ((OnInsertAddressResponseHandler) this.mReponseHandler).onInsertAddressResponse(
                o != null);
        }
    }

    public interface OnInsertAddressResponseHandler extends HomelikeResponseHandler {
        public void onInsertAddressResponse(boolean addressUpdated);
    }
}
