package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.InsertAddressRequest;
import mx.jtails.homelike.ui.HomeActivity;

/**
 * Created by GrzegorzFeathers on 9/4/14.
 */
public class AddressDetailsFragment extends Fragment
    implements InsertAddressRequest.OnInsertAddressResponseHandler {

    private Button mBtnFinish;
    private EditText mEditAlias;
    private EditText mEditStreet;
    private EditText mEditStreetNumber;
    private EditText mEditInterior;
    private EditText mEditColony;
    private EditText mEditZipCode;
    private EditText mEditCity;
    private EditText mEditState;
    private EditText mEditCountry;
    private EditText mEditReference;

    private Direccion mAddress;

    private ProgressDialog mInsertingDialog;

    private boolean mEditMode = false;

    public static AddressDetailsFragment newInstance(Direccion address, boolean editMode){
        AddressDetailsFragment fragment = new AddressDetailsFragment();
        fragment.mAddress = address;
        fragment.mEditMode = editMode;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_details, container, false);

        this.mBtnFinish = (Button) view.findViewById(R.id.btn_finish);
        this.mEditAlias = (EditText) view.findViewById(R.id.edit_alias);
        this.mEditStreet = (EditText) view.findViewById(R.id.edit_street);
        this.mEditStreetNumber = (EditText) view.findViewById(R.id.edit_street_number);
        this.mEditInterior = (EditText) view.findViewById(R.id.edit_interior);
        this.mEditColony = (EditText) view.findViewById(R.id.edit_colony);
        this.mEditZipCode = (EditText) view.findViewById(R.id.edit_zip_code);
        this.mEditCity = (EditText) view.findViewById(R.id.edit_city);
        this.mEditState = (EditText) view.findViewById(R.id.edit_state);
        this.mEditCountry = (EditText) view.findViewById(R.id.edit_country);
        this.mEditReference = (EditText) view.findViewById(R.id.edit_reference);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinishClicked();
            }
        });

        this.mEditAlias.setText(this.mAddress.getAlias());
        this.mEditStreet.setText(this.mAddress.getCalle());
        this.mEditStreetNumber.setText(this.mAddress.getNexterior());
        this.mEditInterior.setText(this.mAddress.getNinterior());
        this.mEditColony.setText(this.mAddress.getColonia());
        this.mEditZipCode.setText(this.mAddress.getCp());
        this.mEditCity.setText(this.mAddress.getDelegacion());
        this.mEditState.setText(this.mAddress.getEstado());
        this.mEditCountry.setText(this.mAddress.getPais());
        this.mEditReference.setText(this.mAddress.getReferencia1());
    }

    private void onFinishClicked() {
        if(this.validateFields()) {
            this.mInsertingDialog = ProgressDialog.show(this.getActivity(),
                    null, this.getString(R.string.saving_address), false, false);

            this.mAddress.setAlias(this.mEditAlias.getText().toString());
            this.mAddress.setCalle(this.mEditStreet.getText().toString());
            this.mAddress.setNexterior(this.mEditStreetNumber.getText().toString());
            this.mAddress.setNinterior(this.mEditInterior.getText().toString());
            this.mAddress.setColonia(this.mEditColony.getText().toString());
            this.mAddress.setCp(this.mEditZipCode.getText().toString());
            this.mAddress.setDelegacion(this.mEditCity.getText().toString());
            this.mAddress.setEstado(this.mEditState.getText().toString());
            this.mAddress.setPais(this.mEditCountry.getText().toString());
            this.mAddress.setReferencia1(this.mEditReference.getText().toString());

            if(!HomelikeDBManager.getDBManager().hasFavoriteAddress()) {
                this.mAddress.setEsDefault(1);
            } else {
                this.mAddress.setEsDefault(0);
            }

            new InsertAddressRequest(this, this.mAddress).executeAsync();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(!this.mEditMode){
            inflater.inflate(R.menu.address_details, menu);
        }
    }

    public void setEditMode(){
        this.mEditMode = true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_map:
                ((HomeActivity) this.getActivity()).singlePop();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean validateFields() {
        List<String> empties = this.validateEmpties();
        if(!empties.isEmpty()){
            this.notifyErrors(R.string.required_fields,
                    R.string.error_field_validation, empties);
            return false;
        } else {
            return true;
        }
    }

    private void notifyErrors(int title, int validationRes, List<String> errors){
        String errorFields = "\n";

        for(int i = 0 ; i < errors.size() ; i++) {
            errorFields += errors.get(i);
            if(i == errors.size() - 1){
                errorFields += ".\n";
            } else {
                errorFields += ", ";
            }
        }

        new AlertDialog.Builder(this.getActivity())
                .setCancelable(false)
                .setTitle(title)
                .setMessage(this.getString(validationRes) + errorFields)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    private List<String> validateEmpties(){
        List<String> errors = new ArrayList<String>();

        if(this.mEditAlias.getText().toString().isEmpty()){ errors.add(
                this.getString(R.string.field_alias)); }
        if(this.mEditStreet.getText().toString().isEmpty()){ errors.add(
                this.getString(R.string.field_street)); }
        if(this.mEditStreetNumber.getText().toString().isEmpty()){ errors.add(
                this.getString(R.string.field_street_number)); }
        if(this.mEditColony.getText().toString().isEmpty()){ errors.add(
                this.getString(R.string.field_colony)); }
        if(this.mEditZipCode.getText().toString().isEmpty()){ errors.add(
                this.getString(R.string.field_zip_code)); }
        if(this.mEditCity.getText().toString().isEmpty()){ errors.add(
                this.getString(R.string.field_city)); }
        if(this.mEditState.getText().toString().isEmpty()){ errors.add(
                this.getString(R.string.field_state)); }
        if(this.mEditCountry.getText().toString().isEmpty()) { errors.add(
                this.getString(R.string.field_country)); }
        if(this.mEditReference.getText().toString().isEmpty()){ errors.add(
                this.getString(R.string.field_reference)); }

        return errors;
    }

    @Override
    public void onInsertAddressResponse(List<Direccion> addresses) {
        if(this.mInsertingDialog != null){ this.mInsertingDialog.dismiss(); }
        if(addresses != null){
            HomelikeDBManager.getDBManager().saveAddresses(addresses);
            ((HomeActivity) this.getActivity()).singlePop();
            if(!this.mEditMode){
                ((HomeActivity) this.getActivity()).singlePop();
            }
        } else {
            new AlertDialog.Builder(this.getActivity())
                    .setCancelable(false)
                    .setTitle(R.string.error)
                    .setMessage(R.string.error_register_address)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        }
    }
}
