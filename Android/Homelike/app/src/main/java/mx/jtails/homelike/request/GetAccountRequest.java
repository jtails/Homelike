package mx.jtails.homelike.request;

import mx.jtails.homelike.api.endpoint.cuentaendpoint.Cuentaendpoint;
import mx.jtails.homelike.api.model.Cuenta;

/**
 * Created by GrzegorzFeathers on 9/15/14.
 */
public class GetAccountRequest extends HomelikeApiRequest {

    private int mAccountId;
    private String mEmail;
    private boolean mByEmail = false;

    public GetAccountRequest(GetAccountResponseHandler handler, int accountId){
        super(handler);
        this.mAccountId = accountId;
        this.mEndpoint = new Cuentaendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
    }

    public GetAccountRequest(GetAccountResponseHandler handler, String mobile){
        super(handler);
        this.mEmail = mobile;
        this.mByEmail = true;
        this.mEndpoint = new Cuentaendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
    }

    @Override
    protected Object doRequest() throws Exception {
        if(this.mByEmail){
            return this.doRequestByEmail();
        } else {
            return this.doRequestByAccountId();
        }
    }

    private Cuenta doRequestByEmail() throws Exception {
        Cuentaendpoint.GetCuentaByUser request = ((Cuentaendpoint) this.mEndpoint)
                .getCuentaByUser();
        request.put("usuario", this.mEmail);

        Cuenta response = request.execute();

        return response;
    }

    private Cuenta doRequestByAccountId() throws Exception {
        return ((Cuentaendpoint) this.mEndpoint)
                .getCuenta((long) this.mAccountId).execute();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        if(this.mReponseHandler != null){
            ((GetAccountResponseHandler) this.mReponseHandler).onGetAccountResponse(
                    o == null ? null : (Cuenta) o);
        }
    }

    public interface GetAccountResponseHandler extends HomelikeResponseHandler {
        public void onGetAccountResponse(Cuenta account);
    }

}
