package mx.jtails.homelike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Servicio;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.request.HomelikeApiRequest;
import mx.jtails.homelike.request.ListAddressesRequest;
import mx.jtails.homelike.ui.widget.TrackableScrollView;

public class MyAddressesActivity extends ActionBarActivity
    implements TrackableScrollView.Callbacks, ListAddressesRequest.OnListAddressesResponseHandler {

    public static final String ARG_REQUESTED_SERVICE_ID = "requested_service_id";

    private int mServiceId;
    private Servicio mService;

    private TrackableScrollView mScrollView;
    private View mHeader;
    private ViewGroup mLayoutAddresses;

    private HomelikeApiRequest mListAddressesRequest;

    private List<Direccion> mAddresses = HomelikeDBManager.getDBManager().loadAddresses();

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

        this.mScrollView = (TrackableScrollView) this.findViewById(R.id.scroll_view);
        this.mHeader = this.findViewById(R.id.layout_header);
        this.mLayoutAddresses = (ViewGroup) this.findViewById(R.id.layout_addresses);

        this.mScrollView.addCallbacks(this);
        this.onScrollChanged(0, 0);

        this.mListAddressesRequest = new ListAddressesRequest(this);
    }

    private void loadService(Bundle args) {
        this.mServiceId = args.getInt(ARG_REQUESTED_SERVICE_ID);
        this.mService = HomelikeDBManager.getDBManager().getService(this.mServiceId);
        if (this.mService == null) {
            Toast.makeText(this, "Failed to recover address with id: " + this.mServiceId,
                    Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    private void setupActionBar() {
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
        this.setSupportProgressBarIndeterminateVisibility(true);
        this.mListAddressesRequest.executeAsync();
        this.mAddresses = HomelikeDBManager.getDBManager().loadAddresses();
        this.updateContent();
    }

    private void updateContent() {
        this.mLayoutAddresses.removeAllViews();
        int count = this.mAddresses.size();
        for (int i = 0 ; i < count ; i++) {
            final View rowView = LayoutInflater.from(this).inflate(
                    R.layout.list_item_address, this.mLayoutAddresses, false);
            ViewHolder holder = new ViewHolder(rowView);
            rowView.setTag(holder);

            Direccion a = this.mAddresses.get(i);
            holder.lblAlias.get().setText(a.getAlias());
            holder.lblAddress.get().setText(
                    a.getCalle() + " #" + a.getNexterior() + ", "
                            + a.getColonia() + ", " + a.getDelegacion());
            holder.imgBookmark.get().setVisibility(
                    a.getEsDefault() == 1 ? View.VISIBLE : View.GONE);

            final int position = i;
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(rowView, position);
                }
            });

            this.mLayoutAddresses.addView(rowView);
        }

        final View addAddressView = LayoutInflater.from(this).inflate(
                R.layout.list_item_new_address, this.mLayoutAddresses, false);
        addAddressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(addAddressView, -1);
            }
        });
        this.mLayoutAddresses.addView(addAddressView);
    }

    public void onItemClick(View view, int position) {
        Bundle args = new Bundle();
        if(position < 0) {
            Intent intent = new Intent(this, AddressActivity.class);
            args.putString(AddressActivity.ARG_ADDRESS_MODE, AddressActivity.MODE_REGISTER_ADDRESS);
            intent.putExtras(args);
            this.startActivity(intent);
        } else {
            Direccion address = this.mAddresses.get(position);
            Intent intent = new Intent(this, CheckOrderActivity.class);
            args.putInt(CheckOrderActivity.ARG_ADDRESS_ID, address.getIdDireccion());
            args.putInt(CheckOrderActivity.ARG_SERVICE_ID, this.mServiceId);
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

    @Override
    public void onListAddressesResponse(List<Direccion> addresses) {
        HomelikeDBManager dbManager = HomelikeDBManager.getDBManager();
        dbManager.saveAddresses(addresses);
        this.mAddresses = dbManager.loadAddresses();
        this.updateContent();
        this.setSupportProgressBarIndeterminateVisibility(false);
    }

    private class ViewHolder {

        private WeakReference<TextView> lblAlias;
        private WeakReference<TextView> lblAddress;
        private WeakReference<ImageView> imgBookmark;

        public ViewHolder(View view) {
            this.lblAlias = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_alias));
            this.lblAddress = new WeakReference<TextView>(
                    (TextView) view.findViewById(R.id.lbl_resumed_address));
            this.imgBookmark = new WeakReference<ImageView>(
                    (ImageView) view.findViewById(R.id.img_bookmark));
        }

    }

}