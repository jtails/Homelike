package mx.jtails.homelike.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Cuenta;
import mx.jtails.homelike.request.GetAccountRequest;
import mx.jtails.homelike.request.InsertAccountRequest;
import mx.jtails.homelike.ui.fragment.dialog.CreateAccountDialog;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class SplashActivity extends ActionBarActivity
    implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        InsertAccountRequest.OnInsertAccountResponseHandler,
        CreateAccountDialog.CreateAccountDialogCallbacks,
        GetAccountRequest.GetAccountResponseHandler {

    private static final int RC_SIGN_IN = 0;

    private GoogleApiClient mGoogleApiClient;

    private boolean mIntentInProgress = false;

    private ProgressDialog mSigningInDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);

        this.findViewById(R.id.btn_sign_in).setOnClickListener(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    @Override
    public void onClick(View v) {
        this.mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        this.mSigningInDialog = ProgressDialog.show(this, null,
                "Signing in...", false, false);

        new GetAccountRequest(this,
                Plus.AccountApi.getAccountName(this.mGoogleApiClient)).executeAsync();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!mIntentInProgress && result.hasResolution()) {
            try {
                mIntentInProgress = true;
                startIntentSenderForResult(result.getResolution().getIntentSender(),
                        RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }


    @Override
    public void onRegisterAccount(String email, String mobile) {
        this.mSigningInDialog.setMessage("Registering account...");
        this.mSigningInDialog.show();
        new InsertAccountRequest(this, this, email, mobile).executeAsync();
    }

    @Override
    public void onCancelRegistration() {
        this.mGoogleApiClient.disconnect();
    }


    @Override
    public void onInsertAccountResponse(Cuenta account) {
        this.mSigningInDialog.dismiss();
        if(account == null){
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Couldn't register your account, please try again later or contact us for support")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
        } else {
            HomelikePreferences.saveInt(HomelikePreferences.ACCOUNT_ID,
                    account.getIdCuenta());
            HomelikePreferences.saveInt(HomelikePreferences.DEVICE_ID,
                    account.getDispositivos().get(0).getIdDispositivo());

            this.startActivity(new Intent(this, HomeActivity.class));
            this.finish();
        }
    }

    @Override
    public void onGetAccountResponse(Cuenta account) {
        this.mSigningInDialog.dismiss();
        if(account == null){
            new CreateAccountDialog().show(this.getSupportFragmentManager(), null);
        } else {
            // TODO save account data
            HomelikePreferences.saveInt(HomelikePreferences.ACCOUNT_ID,
                account.getIdCuenta());
            HomelikePreferences.saveInt(HomelikePreferences.DEVICE_ID,
                account.getDispositivos().get(0).getIdDispositivo());

            this.startActivity(new Intent(this, HomeActivity.class));
            this.finish();
        }
    }
}
