package mx.jtails.homelike.request;

import android.content.Context;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;

import mx.jtails.homelike.api.endpoint.proveedorendpoint.Proveedorendpoint;
import mx.jtails.homelike.api.model.Dispositivop;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 12/10/14.
 */
public class InsertDeviceIdRequest extends ApiRequest<Dispositivop> {

    private Proveedor mProvider;
    private Context mContext;

    public InsertDeviceIdRequest(ApiResponseHandler<Dispositivop> handler,
        Proveedor provider, Context ctx) {
        super(handler, new Proveedorendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build());
        this.mProvider = provider;
        this.mContext = ctx;
    }

    @Override
    protected AbstractGoogleJsonClientRequest<Dispositivop> getRequest() throws Exception {
        return ((Proveedorendpoint) this.mEndpoint).insertDispositivo(
                new Dispositivop().setProveedor(this.mProvider)
                        .setGcmid(HomelikeUtils.getGCMId(this.mContext)));
    }
}
