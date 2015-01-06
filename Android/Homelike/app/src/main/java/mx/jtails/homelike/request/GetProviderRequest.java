package mx.jtails.homelike.request;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;

import mx.jtails.homelike.api.endpoint.proveedorendpoint.Proveedorendpoint;
import mx.jtails.homelike.api.model.Proveedor;

/**
 * Created by GrzegorzFeathers on 9/15/14.
 */
public class GetProviderRequest extends ApiRequest<Proveedor> {

    private int mAccountId;
    private String mEmail;
    private boolean mByEmail = false;

    public GetProviderRequest(ApiResponseHandler<Proveedor> handler, int accountId){
        super(handler, new Proveedorendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build());
        this.mAccountId = accountId;
    }

    public GetProviderRequest(ApiResponseHandler<Proveedor> handler, String email){
        super(handler, new Proveedorendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build());
        this.mEmail = email;
        this.mByEmail = true;
    }

    @Override
    protected AbstractGoogleJsonClientRequest<Proveedor> getRequest() throws Exception {
        if(this.mByEmail){
            return this.getRequestByEmail();
        } else {
            return this.getRequestByAccountId();
        }
    }

    private AbstractGoogleJsonClientRequest<Proveedor> getRequestByEmail() throws Exception {
        Proveedorendpoint.GetProveedorByUser request = ((Proveedorendpoint) this.mEndpoint)
                .getProveedorByUser(new Proveedor().setUsuario(this.mEmail));
        return request;
    }

    private AbstractGoogleJsonClientRequest<Proveedor> getRequestByAccountId() throws Exception {
        return ((Proveedorendpoint) this.mEndpoint)
                .getProveedor((long) this.mAccountId);
    }

}
