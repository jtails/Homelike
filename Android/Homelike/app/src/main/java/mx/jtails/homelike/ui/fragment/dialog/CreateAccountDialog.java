package mx.jtails.homelike.ui.fragment.dialog;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;

/**
 * Created by GrzegorzFeathers on 9/16/14.
 */
public class CreateAccountDialog extends DialogFragment {

    private View mRootView;
    private List<CharSequence> mAccounts;

    private Spinner mSpinnerAccount;
    private EditText mEditMobile;

    private boolean mValidated = false;

    private CreateAccountDialogCallbacks mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mListener = (CreateAccountDialogCallbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Account[] accounts = AccountManager.get(this.getActivity()).getAccountsByType("com.google");
        this.mAccounts = new ArrayList<CharSequence>();
        for(Account account : accounts){
            this.mAccounts.add(account.name);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());
        this.mRootView = inflater.inflate(R.layout.dialog_create_account, null);

        this.mSpinnerAccount = (Spinner) this.mRootView.findViewById(R.id.spinner_accounts);
        this.mEditMobile = (EditText) this.mRootView.findViewById(R.id.edit_mobile);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this.getActivity(), android.R.layout.simple_spinner_item, this.mAccounts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mSpinnerAccount.setAdapter(adapter);

        TelephonyManager tMgr =(TelephonyManager) this.getActivity()
                .getSystemService(Context.TELEPHONY_SERVICE);
        this.mEditMobile.setText(tMgr.getLine1Number());

        return new AlertDialog.Builder(this.getActivity())
            .setTitle("Register Account")
            .setView(this.mRootView)
            .setCancelable(false)
            .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onRegisterClicked();
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onCancelClicked();
                }
            }).create();
    }

    private void onCancelClicked(){
        if(this.mListener != null){
            this.mListener.onCancelRegistration();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (!mValidated) {
            onCancelClicked();
        }
    }

    private void onRegisterClicked(){
        String email = this.mSpinnerAccount.getSelectedItem().toString();
        String mobile = this.mEditMobile.getText().toString();

        if(email.isEmpty()){
            this.notifyError("Missing email");
            return;
        }

        if(mobile.isEmpty()){
            this.notifyError("Missing mobile");
            return;
        }

        this.mValidated = true;
        if(this.mListener != null){
            this.mListener.onRegisterAccount(email, mobile);
        }
    }

    private void notifyError(String message){
        new AlertDialog.Builder(this.getActivity())
                .setTitle("Wrong Field")
                .setMessage(message)
                .setPositiveButton("Ok", null).show();
    }

    public interface CreateAccountDialogCallbacks {
        public void onRegisterAccount(String email, String mobile);
        public void onCancelRegistration();
    }
}
