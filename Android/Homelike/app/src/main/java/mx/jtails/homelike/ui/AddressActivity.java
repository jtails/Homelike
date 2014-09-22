package mx.jtails.homelike.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLng;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.ui.adapter.AddressAdapter;
import mx.jtails.homelike.ui.fragment.AddressDetailsFragment;
import mx.jtails.homelike.ui.fragment.AddressMapFragment;
import mx.jtails.homelike.util.HomelikeUtils;

public class AddressActivity extends ActionBarActivity
    implements AddressMapFragment.OnAddressLocationSelectedListener,
        ViewPager.OnPageChangeListener {

    public static final String ARG_ADDRESS_MODE = "address_mode";
    public static final String ARG_ADDRESS_ID = "address_id";

    public static final String MODE_REGISTER_ADDRESS = "register_address";
    public static final String MODE_OPEN_ADDRESS = "open_address";

    private AddressAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private Direccion mSelectedAddress;
    private String mAddressMode;
    private int originPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_address);

        this.setupActionBar();

        mSectionsPagerAdapter = new AddressAdapter(this, getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(this);

        if(savedInstanceState != null) {
            this.setAddressMode(savedInstanceState);
        } else {
            this.setAddressMode(this.getIntent().getExtras());
        }
    }

    private void setAddressMode(Bundle args) {
        if(!args.containsKey(ARG_ADDRESS_MODE)) {
            this.setAddressModeRegister();
            return;
        }

        String addressMode = args.getString(ARG_ADDRESS_MODE);
        if(addressMode.equals(MODE_REGISTER_ADDRESS)) {
            this.setAddressModeRegister();
        } else if(addressMode.equals(MODE_OPEN_ADDRESS)) {
            this.setAddressModeOpen(args.getInt(ARG_ADDRESS_ID, -1));
        } else {
            this.setAddressModeRegister();
        }
    }

    private void setAddressModeRegister() {
        this.mAddressMode = MODE_REGISTER_ADDRESS;
        this.originPage = 0;
        this.mViewPager.setCurrentItem(0);
    }

    private void setAddressModeOpen(int addressId) {
        if(addressId < 0) {
            this.setAddressModeRegister();
        } else {
            this.mAddressMode = MODE_OPEN_ADDRESS;
            this.mSelectedAddress = HomelikeDBManager.getDBManager().getAddress(addressId);

            ((AddressDetailsFragment) this.mSectionsPagerAdapter.getItem(1))
                    .updateAddress(this.mSelectedAddress);
            ((AddressDetailsFragment) this.mSectionsPagerAdapter.getItem(1))
                    .setEditMode();

            this.originPage = 0;
            this.mViewPager.setCurrentItem(1);
        }
    }

    private void setupActionBar(){
        ActionBar ab = this.getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onAddressLocationSelectedListener(Direccion address) {
        if(address == null) {
            this.mSelectedAddress = new Direccion();
        } else {
            this.mSelectedAddress = address;
        }

        AddressDetailsFragment detailsFragment = (AddressDetailsFragment)
                this.mSectionsPagerAdapter.getItem(1);
        detailsFragment.reloadFormContent(this.mSelectedAddress);
        this.originPage = 0;
        this.mViewPager.setCurrentItem(1);
    }

    public void backToMap(LatLng currentLatLng) {
        this.originPage = 1;
        ((AddressMapFragment) this.mSectionsPagerAdapter.getItem(0)).moveMapCameraTo(currentLatLng);
        this.mViewPager.setCurrentItem(0);
    }

    public void updateActionBarAlpha(float factor){
        this.getActionBar().setBackgroundDrawable(
                new ColorDrawable(HomelikeUtils.colorWithAlpha(
                        this.getResources().getColor(R.color.primary), factor))
        );
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
        switch (originPage) {
            case 0:
                if( i == 1 ) { this.updateActionBarAlpha(1.0f); }
                else { this.updateActionBarAlpha(v); }
                break;
            case 1:
                if( i == 0 ) { this.updateActionBarAlpha(0.0f); }
                else { this.updateActionBarAlpha(1.0f - v); }
                break;
        }
    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(this.mAddressMode == MODE_REGISTER_ADDRESS) {
                    this.confirmDiscard();
                } else {
                    this.finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if(this.mAddressMode == MODE_REGISTER_ADDRESS) {
            this.confirmDiscard();
        } else {
            super.onBackPressed();
        }
    }

    private void confirmDiscard(){
        new AlertDialog.Builder(this)
                .setTitle("Discard")
                .setMessage("Are your sure you want to discard your selection?")
                .setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Stay", null)
                .show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_ADDRESS_MODE, this.mAddressMode);
        super.onSaveInstanceState(outState);
    }
}
