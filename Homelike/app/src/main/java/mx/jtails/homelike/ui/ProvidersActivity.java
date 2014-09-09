package mx.jtails.homelike.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.ListProvidersRequest;
import mx.jtails.homelike.ui.adapter.ProvidersAdapter;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class ProvidersActivity extends ActionBarActivity
    implements ListProvidersRequest.ListProvidersResponseHandler {

    public static final String ARG_ADDRESS_ID = "arg_address_id";
    public static final String ARG_SERVICE_ID = "arg_service_id";

    private int mAddressId;
    private int mServiceId;
    private Direccion mAddress;
    private List<Proveedor> mProviders = new ArrayList<Proveedor>();
    private ProvidersAdapter mProvidersAdapter;

    private View mLayoutContent;
    private AbsListView mListView;
    private ProgressBar mProgress;
    private TextView mLblEmpty;

    private ListProvidersRequest mProvidersRequest;

    private enum ContentDisplayMode {
        LOAD, CONTENT, CONTENT_EMPTY;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_providers);

        if(savedInstanceState == null){
            this.loadAddress(this.getIntent().getExtras());
        } else {
            this.loadAddress(savedInstanceState);
        }

        this.setupActionBar();

        this.mLayoutContent = this.findViewById(R.id.layout_providers_content);
        this.mListView = (AbsListView) this.findViewById(R.id.list_providers);
        this.mProgress = (ProgressBar) this.findViewById(R.id.progress_providers);
        this.mLblEmpty = (TextView) this.findViewById(R.id.lbl_empty);
        this.mProvidersAdapter = new ProvidersAdapter(this, this.mProviders);

        this.mListView.setAdapter(this.mProvidersAdapter);
        this.displayContentMode(ContentDisplayMode.LOAD);
    }

    private void loadAddress(Bundle args){
        this.mAddressId = args.getInt(ARG_ADDRESS_ID);
        this.mServiceId = args.getInt(ARG_SERVICE_ID);
        this.mAddress = HomelikeDBManager.getDBManager().getAddress(this.mAddressId);
        if(this.mAddress == null){
            Toast.makeText(this, "Failed to recover address with id: " + this.mAddressId,
                    Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mProvidersRequest = new ListProvidersRequest(this,
                this.mServiceId, this.mAddress);
        this.mProvidersRequest.executeAsync();
    }

    private void setupActionBar(){
        ActionBar ab = this.getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setTitle("Homelike");
        ab.setSubtitle("Providers");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_ADDRESS_ID, this.mAddressId);
        outState.putInt(ARG_SERVICE_ID, this.mServiceId);
        super.onSaveInstanceState(outState);
    }

    private void displayContentMode(ContentDisplayMode displayMode){
        switch (displayMode) {
            case LOAD: {
                this.mLayoutContent.setVisibility(View.GONE);
                this.mProgress.setVisibility(View.VISIBLE);
                break;
            }
            case CONTENT: {
                this.mProgress.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLblEmpty.setVisibility(View.GONE);
                this.mListView.setVisibility(View.VISIBLE);

                this.mProvidersAdapter.updateContent(this.mProviders);
                break;
            }
            case CONTENT_EMPTY: {
                this.mProgress.setVisibility(View.GONE);
                this.mLayoutContent.setVisibility(View.VISIBLE);
                this.mLblEmpty.setVisibility(View.VISIBLE);
                this.mListView.setVisibility(View.GONE);
                break;
            }
        }
    }

    @Override
    public void onListProvidersResponse(List<Proveedor> providers) {
        this.mProvidersRequest = null;
        this.mProviders = providers;

        if(this.mProviders.size() > 0){
            this.displayContentMode(ContentDisplayMode.CONTENT);
        } else {
            this.displayContentMode(ContentDisplayMode.CONTENT_EMPTY);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(this.mProvidersRequest != null){
            this.mProvidersRequest.cancelRequest();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
