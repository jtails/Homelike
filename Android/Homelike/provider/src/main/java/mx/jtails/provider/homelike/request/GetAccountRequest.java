package mx.jtails.provider.homelike.request;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;

import mx.jtails.provider.homelike.api.endpoint.proveedorendpoint.Proveedorendpoint;
import mx.jtails.provider.homelike.api.model.Proveedor;

/**
 * Created by GrzegorzFeathers on 9/15/14.
 */
public class GetAccountRequest extends HomelikeApiRequest<Proveedor> {

    private int mAccountId;
    private String mEmail;
    private boolean mByEmail = false;

    public GetAccountRequest(HomelikeApiResponseHandler<Proveedor> handler, int accountId){
        super(handler);
        this.mAccountId = accountId;
        this.mEndpoint = new Proveedorendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
    }

    public GetAccountRequest(HomelikeApiResponseHandler<Proveedor> handler, String email){
        super(handler);
        this.mEmail = email;
        this.mByEmail = true;
        this.mEndpoint = new Proveedorendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
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
