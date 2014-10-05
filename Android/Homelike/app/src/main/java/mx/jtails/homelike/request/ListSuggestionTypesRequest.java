package mx.jtails.homelike.request;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.endpoint.tsugerenciaendpoint.Tsugerenciaendpoint;
import mx.jtails.homelike.api.model.Tsugerencia;

/**
 * Created by GrzegorzFeathers on 10/5/14.
 */
public class ListSuggestionTypesRequest extends HomelikeApiRequest {

    public ListSuggestionTypesRequest(OnListSuggestionTypesResponseHandler handler) {
        super(handler);
        this.mEndpoint = new Tsugerenciaendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();
    }

    @Override
    protected Object doRequest() throws Exception {
        return ((Tsugerenciaendpoint) this.mEndpoint).listTsugerencia().execute().getItems();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        List<Tsugerencia> suggestionTypes = o == null ?
                new ArrayList<Tsugerencia>() : (List<Tsugerencia>) o;
        ((OnListSuggestionTypesResponseHandler) this.mReponseHandler)
                .onListSuggestionTypesResponse(suggestionTypes);
    }

    public interface OnListSuggestionTypesResponseHandler extends HomelikeResponseHandler {
        public void onListSuggestionTypesResponse(List<Tsugerencia> suggestionTypes);
    }
}
