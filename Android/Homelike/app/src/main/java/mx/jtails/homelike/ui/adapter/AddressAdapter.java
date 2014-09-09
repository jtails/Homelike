package mx.jtails.homelike.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.ui.fragment.AddressDetailsFragment;
import mx.jtails.homelike.ui.fragment.AddressMapFragment;

/**
 * Created by GrzegorzFeathers on 9/3/14.
 */
public class AddressAdapter extends FragmentPagerAdapter {

    private Context mContext;
    List<Fragment> mAddressFragments;

    public AddressAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
        this.mAddressFragments = new ArrayList<Fragment>();

        this.mAddressFragments.add(new AddressMapFragment());
        this.mAddressFragments.add(new AddressDetailsFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return this.mAddressFragments.get(position);
    }

    @Override
    public int getCount() {
        return this.mAddressFragments.size();
    }

}

