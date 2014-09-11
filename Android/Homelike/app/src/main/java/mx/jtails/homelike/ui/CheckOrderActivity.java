package mx.jtails.homelike.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.ui.adapter.OrderProcessAdapter;
import mx.jtails.homelike.ui.fragment.CreateOrderFragment;
import mx.jtails.homelike.ui.fragment.ProvidersFragment;
import mx.jtails.homelike.util.HomelikeUtils;

/**
 * Created by GrzegorzFeathers on 9/8/14.
 */
public class CheckOrderActivity extends ActionBarActivity
    implements ProvidersFragment.OnProviderSelectedListener,
        ViewPager.OnPageChangeListener {

    public static final String ARG_ADDRESS_ID = "arg_address_id";
    public static final String ARG_SERVICE_ID = "arg_service_id";

    private int originPage;
    private int mAddressId;
    private int mServiceId;

    private ViewPager mOrderProcessPager;
    private OrderProcessAdapter mOrderProcessAdapter;

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

        this.mOrderProcessPager = (ViewPager) this.findViewById(R.id.pager_order_process);
        this.mOrderProcessAdapter = new OrderProcessAdapter(
                this.getSupportFragmentManager(), this.mAddressId, this.mServiceId);
        this.mOrderProcessPager.setAdapter(this.mOrderProcessAdapter);
        this.mOrderProcessPager.setOnPageChangeListener(this);
    }

    private void setupActionBar(){
        ActionBar ab = this.getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setTitle("Homelike");
        ab.setSubtitle("Providers");
        this.updateActionBarAlpha(1.0f);
    }

    private void loadAddress(Bundle args){
        this.mAddressId = args.getInt(ARG_ADDRESS_ID);
        this.mServiceId = args.getInt(ARG_SERVICE_ID);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_ADDRESS_ID, this.mAddressId);
        outState.putInt(ARG_SERVICE_ID, this.mServiceId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onProviderSelected(Proveedor provider) {
        CreateOrderFragment fragment = (CreateOrderFragment) this.mOrderProcessAdapter.getItem(1);
        fragment.refreshProviderView(provider);
        this.originPage = 0;
        this.mOrderProcessPager.setCurrentItem(1);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
        switch (originPage) {
            case 0:
                if( i == 1 ) { this.updateActionBarAlpha(0.0f); }
                else { this.updateActionBarAlpha(1.0f - v); }
                break;
            case 1:
                if( i == 0 ) { this.updateActionBarAlpha(1.0f); }
                else { this.updateActionBarAlpha(v); }
                break;
        }
    }

    @Override
    public void onPageSelected(int i) {}
    @Override
    public void onPageScrollStateChanged(int i) {}

    public void updateActionBarAlpha(float factor){
        this.getActionBar().setBackgroundDrawable(
                new ColorDrawable(HomelikeUtils.colorWithAlpha(
                        this.getResources().getColor(R.color.primary), factor))
        );
    }
}
