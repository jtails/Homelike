package mx.jtails.homelike.ui.fragment.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Tsugerencia;
import mx.jtails.homelike.request.InsertSuggestionRequest;
import mx.jtails.homelike.request.ListSuggestionTypesRequest;

/**
 * Created by GrzegorzFeathers on 10/5/14.
 */
public class CommentsAndSuggestionsDialog extends DialogFragment
    implements ListSuggestionTypesRequest.OnListSuggestionTypesResponseHandler,
        InsertSuggestionRequest.OnInsertSuggestionResponseHandler {

    private EditText mEditComments;
    private RadioGroup mGroupSuggestions;
    private RadioButton[] mRadioSuggestions;
    private ProgressBar mProgressLoading;
    private TextView mLblError;

    private List<Tsugerencia> mSuggestionTypes = new ArrayList<Tsugerencia>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_comments_and_suggestions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.mEditComments = (EditText) view.findViewById(R.id.edit_suggestion);
        this.mGroupSuggestions = (RadioGroup) view.findViewById(R.id.group_suggestions);
        this.mProgressLoading = (ProgressBar) view.findViewById(R.id.progress_suggestions);
        this.mLblError = (TextView) view.findViewById(R.id.lbl_error);
        this.mProgressLoading.setVisibility(View.VISIBLE);
        view.findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendCommentClicked();
            }
        });
        new ListSuggestionTypesRequest(this).executeAsync();
    }

    private void onSendCommentClicked(){
        if(this.mProgressLoading.getVisibility() == View.VISIBLE){
            return;
        }

        if(this.mEditComments.getText().toString().isEmpty()){
            this.mLblError.setVisibility(View.VISIBLE);
            this.mLblError.setText(R.string.error_empty_suggestion);
            return;
        }

        int id = this.mGroupSuggestions.getCheckedRadioButtonId();
        if(id < 0){
            this.mLblError.setVisibility(View.VISIBLE);
            this.mLblError.setText(R.string.error_suggestion_type_not_selected);
            return;
        }

        View selected = this.mGroupSuggestions.findViewById(id);
        int index = this.mGroupSuggestions.indexOfChild(selected);
        Tsugerencia suggestionType = this.mSuggestionTypes.get(index);
        String suggestion = this.mEditComments.getText().toString();

        this.mProgressLoading.setVisibility(View.VISIBLE);
        this.mGroupSuggestions.setVisibility(View.GONE);
        this.mLblError.setVisibility(View.GONE);

        new InsertSuggestionRequest(this, suggestionType, suggestion).executeAsync();
    }

    @Override
    public void onListSuggestionTypesResponse(List<Tsugerencia> suggestionTypes) {
        this.mProgressLoading.setVisibility(View.GONE);
        this.mGroupSuggestions.setVisibility(View.VISIBLE);
        if(suggestionTypes.isEmpty()){
            Toast.makeText(this.getActivity(), R.string.error_loading_options, Toast.LENGTH_SHORT).show();
            this.dismiss();
        } else {
            this.mSuggestionTypes = suggestionTypes;
            this.mRadioSuggestions = new RadioButton[this.mSuggestionTypes.size()];
            for(int i = 0 ; i < this.mSuggestionTypes.size() ; i++){
                RadioButton radio = new RadioButton(this.getActivity());
                Tsugerencia suggestion = this.mSuggestionTypes.get(i);
                this.mRadioSuggestions[i] = radio;
                radio.setText(suggestion.getTipoSugerencia());
                radio.setId(i + 100);
                this.mGroupSuggestions.addView(radio);
            }
        }
    }

    @Override
    public void onInsertSuggestionResponse(boolean inserted) {
        if(inserted){
            Toast.makeText(this.getActivity(), R.string.gratitue_message,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getActivity(), R.string.error_send_suggestion,
                    Toast.LENGTH_SHORT).show();
        }
        this.dismiss();
    }
}
