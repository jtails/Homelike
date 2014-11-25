package mx.jtails.provider.homelike.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import mx.jtails.provider.homelike.R;
import mx.jtails.provider.homelike.api.model.Proveedor;
import mx.jtails.provider.homelike.request.GetAccountRequest;
import mx.jtails.provider.homelike.request.HomelikeApiResponseHandler;
import mx.jtails.provider.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public class SplashActivity extends ActionBarActivity
        implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        HomelikeApiResponseHandler<Proveedor> {

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
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .build();
    }

    @Override
    public void onClick(View v) {
        this.mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        this.mSigningInDialog = ProgressDialog.show(this, null,
                this.getString(R.string.signing_in), false, false);

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

    private void goToHome(Proveedor account){
        Person person = Plus.PeopleApi.getCurrentPerson(this.mGoogleApiClient);
        if(person != null){
            HomelikePreferences.saveString(HomelikePreferences.USER_NAME,
                    person.getDisplayName());
            HomelikePreferences.saveString(HomelikePreferences.USER_IMG,
                    person.getImage().getUrl());
        }
        HomelikePreferences.saveInt(HomelikePreferences.ACCOUNT_ID,
                account.getIdProveedor());
        this.startActivity(new Intent(this, HomeActivity.class));
        this.finish();
    }

    @Override
    public void onResponse(Proveedor provider) {
        this.mSigningInDialog.dismiss();
        if(provider == null){
            // TODO: User is not registered
        } else if(provider.getStatus().equals(0)) {
            // TODO: User in process of registration
        } else {
            this.goToHome(provider);
        }
    }
}
