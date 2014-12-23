package mx.jtails.homelike.request;

import android.content.Context;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;

import mx.jtails.homelike.api.endpoint.cuentaendpoint.Cuentaendpoint;
import mx.jtails.homelike.api.model.Cuenta;
import mx.jtails.homelike.api.model.Dispositivo;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 12/22/14.
 */
public class InsertClientDeviceRequest extends ApiRequest<Dispositivo> {

    private Cuenta mAccount;
    private Context mContext;

    public InsertClientDeviceRequest(
            ApiResponseHandler<Dispositivo> handler,
            Cuenta account, Context ctx) {
        super(handler, new Cuentaendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build());
        this.mAccount = account;
        this.mContext = ctx;
    }

    @Override
    protected AbstractGoogleJsonClientRequest<Dispositivo> getRequest() throws Exception {
        return ((Cuentaendpoint) this.mEndpoint).insertDispositivo(
                HomelikeUtils.newApiDeviceInstance(this.mContext).setCuenta(this.mAccount));
    }
}
