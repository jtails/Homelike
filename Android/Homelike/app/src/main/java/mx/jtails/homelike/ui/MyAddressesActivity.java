package mx.jtails.homelike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Servicio;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.ui.adapter.MyAddressesAdapter;
import mx.jtails.homelike.ui.widget.TrackableScrollView;

public class MyAddressesActivity extends ActionBarActivity
    implements AdapterView.OnItemClickListener, TrackableScrollView.Callbacks {

    public static final String ARG_REQUESTED_SERVICE_ID = "requested_service_id";

    private int mServiceId;
    private Servicio mService;

    private TrackableScrollView mScrollView;
    private View mHeader;
    private ListView mListViewAddresses;
    private MyAddressesAdapter mMyAddressesAdapter;

    private int mPhotoHeight;

    private List<Direccion> mAddresses = HomelikeDBManager.getDBManager().loadAddresses();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_my_addresses);

        this.setupActionBar();

        if(savedInstanceState == null){
            this.loadService(this.getIntent().getExtras());
        } else {
            this.loadService(savedInstanceState);
        }

        this.mScrollView = (TrackableScrollView) this.findViewById(R.id.scroll_view);
        this.mHeader = this.findViewById(R.id.layout_header);
        this.mListViewAddresses = (ListView) this.findViewById(R.id.list_addresses);
        this.mMyAddressesAdapter = new MyAddressesAdapter(this.getApplicationContext(),
            this.mAddresses);

        this.mScrollView.addCallbacks(this);
        this.mListViewAddresses.setOnItemClickListener(this);
        this.mListViewAddresses.setAdapter(this.mMyAddressesAdapter);
        this.onScrollChanged(0, 0);

        this.mPhotoHeight = this.mHeader.getHeight();
    }

    private void loadService(Bundle args){
        this.mServiceId = args.getInt(ARG_REQUESTED_SERVICE_ID);
        this.mService = HomelikeDBManager.getDBManager().getService(this.mServiceId);
        if(this.mService == null){
            Toast.makeText(this, "Failed to recover address with id: " + this.mServiceId,
                    Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    private void setupActionBar(){
        ActionBar ab = this.getSupportActionBar();

        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setSubtitle("My Addresses");
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
    protected void onResume() {
        super.onResume();
        this.mAddresses = HomelikeDBManager.getDBManager().loadAddresses();
        this.mMyAddressesAdapter.updateContent(this.mAddresses);
        this.mMyAddressesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle args = new Bundle();
        if(position == this.mMyAddressesAdapter.getCount() - 1) {
            Intent intent = new Intent(this, AddressActivity.class);
            args.putString(AddressActivity.ARG_ADDRESS_MODE, AddressActivity.MODE_REGISTER_ADDRESS);
            intent.putExtras(args);
            this.startActivity(intent);
        } else {
            Direccion address = this.mMyAddressesAdapter.getItem(position);
            Intent intent = new Intent(this, ProvidersActivity.class);

            args.putInt(ProvidersActivity.ARG_ADDRESS_ID, address.getIdDireccion());
            args.putInt(ProvidersActivity.ARG_SERVICE_ID, this.mServiceId);
            intent.putExtras(args);
            this.startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_REQUESTED_SERVICE_ID, this.mServiceId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onScrollChanged(int deltaX, int deltaY) {
        int scrollY = this.mScrollView.getScrollY();
        this.mHeader.setTranslationY(scrollY * 0.5f);

    }
}
