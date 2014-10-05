package mx.jtails.homelike.request;

import mx.jtails.homelike.api.endpoint.sugerenciascendpoint.Sugerenciascendpoint;
import mx.jtails.homelike.api.model.Cuenta;
import mx.jtails.homelike.api.model.Sugerenciasc;
import mx.jtails.homelike.api.model.Tsugerencia;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 10/5/14.
 */
public class InsertSuggestionRequest extends HomelikeApiRequest {

    private String mSuggestionString;
    private Tsugerencia mSuggestionType;

    public InsertSuggestionRequest(OnInsertSuggestionResponseHandler handler,
        Tsugerencia suggestionType, String suggestionString) {
        super(handler);
        this.mEndpoint = new Sugerenciascendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
        this.mSuggestionType = suggestionType;
        this.mSuggestionString = suggestionString;
    }

    @Override
    protected Object doRequest() throws Exception {
        Cuenta account = (Cuenta) new GetAccountRequest(null,
                HomelikePreferences.loadInt(HomelikePreferences.ACCOUNT_ID, -1)).executeOnThread();

        Sugerenciasc suggestion = new Sugerenciasc();
        suggestion.setSugerencia(this.mSuggestionString);
        suggestion.setTsugerencia(this.mSuggestionType);
        suggestion.setCuenta(account);
        return ((Sugerenciascendpoint) this.mEndpoint).insertSugerenciasc(suggestion).execute();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        ((OnInsertSuggestionResponseHandler) this.mReponseHandler)
                .onInsertSuggestionResponse(o != null);
    }

    public interface OnInsertSuggestionResponseHandler extends HomelikeResponseHandler {
        public void onInsertSuggestionResponse(boolean inserted);
    }

}
