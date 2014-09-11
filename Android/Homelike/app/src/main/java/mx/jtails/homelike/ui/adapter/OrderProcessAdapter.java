package mx.jtails.homelike.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.ui.fragment.CreateOrderFragment;
import mx.jtails.homelike.ui.fragment.ProvidersFragment;

/**
 * Created by GrzegorzFeathers on 9/10/14.
 */
public class OrderProcessAdapter extends FragmentPagerAdapter {

    private List<Fragment> mOrderProcessFragments;

    public OrderProcessAdapter(FragmentManager fm, int addressId, int serviceId) {
        super(fm);
        this.mOrderProcessFragments = new ArrayList<Fragment>();

        this.mOrderProcessFragments.add(ProvidersFragment.getInstance(addressId, serviceId));
        this.mOrderProcessFragments.add(new CreateOrderFragment());
    }

    @Override
    public Fragment getItem(int i) {
        return this.mOrderProcessFragments.get(i);
    }

    @Override
    public int getCount() {
        return this.mOrderProcessFragments.size();
    }
}
