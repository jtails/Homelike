package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.model.Cuenta;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 9/17/14.
 */
public class ListAddressesRequest extends HomelikeApiRequest {

    public ListAddressesRequest(HomelikeResponseHandler handler) {
        super(handler);
    }

    @Override
    protected Object doRequest() throws Exception {
        return new GetAccountRequest(null, HomelikePreferences.loadInt(
                HomelikePreferences.ACCOUNT_ID, -1)).executeOnThread();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        if(this.mReponseHandler != null){
            ((OnListAddressesResponseHandler) this.mReponseHandler)
                    .onListAddressesResponse(o == null || ((Cuenta) o).getDirecciones() == null ?
                                    new ArrayList<Direccion>() : ((Cuenta) o).getDirecciones());
        }
    }

    public interface OnListAddressesResponseHandler extends HomelikeResponseHandler {
        public void onListAddressesResponse(List<Direccion> addresses);
    }
}
