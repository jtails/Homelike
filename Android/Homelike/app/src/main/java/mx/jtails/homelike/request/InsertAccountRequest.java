package mx.jtails.homelike.request;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

import mx.jtails.homelike.api.endpoint.cuentaendpoint.Cuentaendpoint;
import mx.jtails.homelike.api.model.Cuenta;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Dispositivo;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 9/15/14.
 */
public class InsertAccountRequest extends HomelikeApiRequest {

    private Context mContext;
    private String mEmail;
    private String mMobile;

    public InsertAccountRequest(OnInsertAccountResponseHandler handler,
            Context context, String email, String mobile) {
        super(handler);
        this.mEndpoint = new Cuentaendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
        this.mContext = context;
        this.mEmail = email;
        this.mMobile = mobile;
    }

    @Override
    protected Object doRequest() throws Exception {
        Dispositivo device = HomelikeUtils.newApiDeviceInstance(this.mContext);

        Cuenta account = new Cuenta();
        account.setTelefono(this.mMobile);
        account.setUsuario(this.mEmail);
        account.setDirecciones(new ArrayList<Direccion>());
        account.setDispositivos(new ArrayList<Dispositivo>(Arrays.asList(device)));
        return ((Cuentaendpoint) this.mEndpoint).insertCuenta(account).execute();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        if(this.mReponseHandler != null){
            ((OnInsertAccountResponseHandler) this.mReponseHandler).onInsertAccountResponse(
                    o == null ? null : (Cuenta) o);
        }
    }

    public interface OnInsertAccountResponseHandler extends HomelikeResponseHandler {
        public void onInsertAccountResponse(Cuenta account);
    }

}
