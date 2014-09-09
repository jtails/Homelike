package mx.jtails.homelike.ui.fragment;

import android.app.AlertDialog;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.ui.AddressActivity;

/**
 * Created by GrzegorzFeathers on 9/4/14.
 */
public class AddressDetailsFragment extends Fragment {

    private String mAddressMode = AddressActivity.MODE_REGISTER_ADDRESS;

    private Button mBtnFinish;
    private EditText mEditAlias;
    private EditText mEditStreet;
    private EditText mEditStreetNumber;
    private EditText mEditInterior;
    private EditText mEditColony;
    private EditText mEditZipCode;
    private EditText mEditCity;
    private EditText mEditState;
    private EditText mEditReference;

    private Direccion mAddress;

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
    }

    public void reloadFormContent(String addresMode, Direccion address, String alias) {
        this.mAddress = address;
        this.mEditAlias.setText(alias);

        this.mEditStreet.setText(address.getCalle());
        this.mEditStreetNumber.setText(address.getNexterior());
        this.mEditInterior.setText(address.getNinterior());
        this.mEditColony.setText(address.getColonia());
        this.mEditZipCode.setText(address.getCp());
        this.mEditCity.setText(address.getDelegacion());
        this.mEditState.setText(address.getEstado());
        this.mEditReference.setText(address.getReferencia1());
    }

    public void reloadFormContent(String addresMode, Direccion address) {
        this.reloadFormContent(addresMode, address, "");
    }

    private void onFinishClicked() {
        if(this.validateFields()) {

            this.mAddress.setCalle(this.mEditStreet.getText().toString());
            this.mAddress.setNexterior(this.mEditStreetNumber.getText().toString());
            this.mAddress.setNinterior(this.mEditInterior.getText().toString());
            this.mAddress.setColonia(this.mEditColony.getText().toString());
            this.mAddress.setCp(this.mEditZipCode.getText().toString());
            this.mAddress.setDelegacion(this.mEditCity.getText().toString());
            this.mAddress.setEstado(this.mEditState.getText().toString());
            this.mAddress.setReferencia1(this.mEditReference.getText().toString());

            if(!HomelikeDBManager.getDBManager().hasFavoriteAddress()) {
                this.mAddress.setEsDefault(1);
            } else {
                this.mAddress.setEsDefault(0);
            }

            int affectedRows = HomelikeDBManager.getDBManager().registerAddress(
                    this.mEditAlias.getText().toString(), this.mAddress);
            Toast.makeText(this.getActivity(), "Dirección guardada", Toast.LENGTH_SHORT).show();
            this.getActivity().finish();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.address_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_map:
                ((AddressActivity) this.getActivity()).backToMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean validateFields() {
        List<String> empties = this.validateEmpties();
        if(!empties.isEmpty()){
            this.notifyErrors("Campos necesarios",
                    "Los siguientes campos son necesarios: \n", empties);
            return false;
        } else {
            return true;
        }
    }

    private void notifyErrors(String title, String validation, List<String> errors){
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
                .setMessage(validation + errorFields)
                .setPositiveButton("Ok", null)
                .show();
    }

    private List<String> validateEmpties(){
        List<String> errors = new ArrayList<String>();

        if(this.mEditAlias.getText().toString().isEmpty()){ errors.add("Alias"); }
        if(this.mEditStreet.getText().toString().isEmpty()){ errors.add("Calle"); }
        if(this.mEditStreetNumber.getText().toString().isEmpty()){ errors.add("Exterior"); }
        if(this.mEditColony.getText().toString().isEmpty()){ errors.add("Colonia"); }
        if(this.mEditZipCode.getText().toString().isEmpty()){ errors.add("Código Postal"); }
        if(this.mEditCity.getText().toString().isEmpty()){ errors.add("Delegación"); }
        if(this.mEditState.getText().toString().isEmpty()){ errors.add("Estado"); }
        if(this.mEditReference.getText().toString().isEmpty()){ errors.add("Referencia"); }

        return errors;
    }

}
