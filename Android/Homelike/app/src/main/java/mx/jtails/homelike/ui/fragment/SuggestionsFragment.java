package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Tsugerencia;
import mx.jtails.homelike.request.InsertSuggestionRequest;
import mx.jtails.homelike.request.ListSuggestionTypesRequest;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.util.HomeMenuSection;

/**
 * Created by GrzegorzFeathers on 10/5/14.
 */
public class SuggestionsFragment extends Fragment
    implements ListSuggestionTypesRequest.OnListSuggestionTypesResponseHandler,
        InsertSuggestionRequest.OnInsertSuggestionResponseHandler {

    private EditText mEditComments;
    private RadioGroup mGroupSuggestions;
    private RadioButton[] mRadioSuggestions;
    private View mLayoutLoading;

    private List<Tsugerencia> mSuggestionTypes = new ArrayList<Tsugerencia>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar ab = ((ActionBarActivity) this.getActivity()).getSupportActionBar();
        ab.setSubtitle(HomeMenuSection.SUGGESTIONS.getSubtitleRes());
        ((ActionBarActivity) this.getActivity())
                .setSupportProgressBarIndeterminateVisibility(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_suggestions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mEditComments = (EditText) view.findViewById(R.id.edit_suggestion);
        this.mGroupSuggestions = (RadioGroup) view.findViewById(R.id.group_suggestions);
        this.mLayoutLoading = view.findViewById(R.id.layout_loading);
        this.mLayoutLoading.setVisibility(View.VISIBLE);
        view.findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendCommentClicked();
            }
        });
        new ListSuggestionTypesRequest(this).executeAsync();
    }

    private void onSendCommentClicked(){
        if(this.mLayoutLoading.getVisibility() == View.VISIBLE){ return; }
        if(!this.validateFields()){ return; }

        int id = this.mGroupSuggestions.getCheckedRadioButtonId();
        View selected = this.mGroupSuggestions.findViewById(id);
        int index = this.mGroupSuggestions.indexOfChild(selected);
        Tsugerencia suggestionType = this.mSuggestionTypes.get(index);
        String suggestion = this.mEditComments.getText().toString();

        this.mLayoutLoading.setVisibility(View.VISIBLE);
        this.mGroupSuggestions.setVisibility(View.GONE);

        new InsertSuggestionRequest(this, suggestionType, suggestion).executeAsync();
    }

    @Override
    public void onListSuggestionTypesResponse(List<Tsugerencia> suggestionTypes) {
        this.mLayoutLoading.setVisibility(View.GONE);
        this.mGroupSuggestions.setVisibility(View.VISIBLE);
        if(suggestionTypes.isEmpty()){
            //Toast.makeText(this.getActivity(), R.string.error_loading_options, Toast.LENGTH_SHORT).show();
            ((HomeActivity) this.getActivity()).onHomeMenuOptionSelected(HomeMenuSection.SERVICES);
        } else {
            this.mSuggestionTypes = suggestionTypes;
            this.mRadioSuggestions = new RadioButton[this.mSuggestionTypes.size()];
            for(int i = 0 ; i < this.mSuggestionTypes.size() ; i++){
                RadioButton radio = new RadioButton(this.getActivity());
                Tsugerencia suggestion = this.mSuggestionTypes.get(i);
                this.mRadioSuggestions[i] = radio;
                radio.setText(suggestion.getTipoSugerencia());
                radio.setTextColor(Color.WHITE);
                radio.setId(i + 100);
                this.mGroupSuggestions.addView(radio);
            }
        }
    }

    private boolean validateFields() {
        if(!this.validateEmpties()){
            this.notifyErrors(R.string.missing_fields);
            return false;
        } else {
            return true;
        }
    }

    private void notifyErrors(int title){

        new AlertDialog.Builder(this.getActivity())
                .setCancelable(false)
                .setTitle(title)
                .setMessage(R.string.error_empty_suggestion)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    private boolean validateEmpties(){
        if(this.mEditComments.getText().toString().isEmpty()){
            return false;
        }

        int id = this.mGroupSuggestions.getCheckedRadioButtonId();
        if(id < 0){
            return false;
        }

        return true;
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
        ((HomeActivity) this.getActivity()).onHomeMenuOptionSelected(HomeMenuSection.SERVICES);
    }
}
