package mx.jtails.homelike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;

import mx.jtails.homelike.R;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class SplashActivity extends ActionBarActivity
    implements View.OnClickListener {

    private boolean mSignInClicked;

    private ConnectionResult mConnectionResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);

        this.findViewById(R.id.btn_sign_in).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        HomelikePreferences.saveString(HomelikePreferences.SESSION_COOKIE,
                "cookie");
        this.startActivity(new Intent(this, HomeActivity.class));
        this.finish();
    }

}
