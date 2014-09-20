package mx.jtails.homelike.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.Window;

import mx.jtails.homelike.R;
import mx.jtails.homelike.ui.fragment.MyAddressesFragment;

public class MyAddressesActivity extends ActionBarActivity {

    public static final String ARG_REQUESTED_SERVICE_ID = "requested_service_id";

    private int mServiceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        this.setContentView(R.layout.activity_my_addresses);

        this.setupActionBar();

        if (savedInstanceState == null) {
            this.loadService(this.getIntent().getExtras());
        } else {
            this.loadService(savedInstanceState);
        }

        MyAddressesFragment contentFragment = MyAddressesFragment.getInstance(this.mServiceId);
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_my_addresses, contentFragment).commit();
    }

    private void loadService(Bundle args) {
        this.mServiceId = args.getInt(ARG_REQUESTED_SERVICE_ID, -1);
    }

    private void setupActionBar() {
        ActionBar ab = this.getSupportActionBar();

        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_REQUESTED_SERVICE_ID, this.mServiceId);
        super.onSaveInstanceState(outState);
    }

}