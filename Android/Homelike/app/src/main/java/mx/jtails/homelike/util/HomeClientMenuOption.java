package mx.jtails.homelike.util;

import android.support.v4.app.Fragment;

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.ui.fragment.ContactFragment;
import mx.jtails.homelike.ui.fragment.MyAddressesFragment;
import mx.jtails.homelike.ui.fragment.OrdersFragment;
import mx.jtails.homelike.ui.fragment.ServicesFragment;
import mx.jtails.homelike.ui.fragment.SuggestionsFragment;

/**
 * Created by GrzegorzFeathers on 12/1/14.
 */
public enum HomeClientMenuOption implements HomeLikeConfiguration.HomeMenuOption {

    SERVICES(ServicesFragment.class, R.string.home_services, R.drawable.ic_menu_services),
    ADDRESSES(MyAddressesFragment.class, R.string.home_my_addresses, R.drawable.ic_menu_addresses),
    ORDERS(OrdersFragment.class, R.string.home_orders, R.drawable.ic_menu_orders),
    SUGGESTIONS(SuggestionsFragment.class, R.string.home_suggestions, R.drawable.ic_menu_suggestions),
    CONTACT(ContactFragment.class, R.string.home_contact, R.drawable.ic_menu_contact);

    public static final HomeClientMenuOption defaultContent = HomeClientMenuOption.SERVICES;

    protected Class<? extends Fragment> fragmentClass;
    protected int subtitleRes;
    protected int iconRes;

    private HomeClientMenuOption(Class<? extends Fragment> fragmentClass, int subtitleRes, int iconRes){
        this.fragmentClass = fragmentClass;
        this.subtitleRes = subtitleRes;
        this.iconRes = iconRes;
    }

    @Override
    public Class<? extends Fragment> getFragmentClass(){
        return this.fragmentClass;
    }

    @Override
    public int getIconRes(){
        return this.iconRes;
    }

    @Override
    public int getSubtitleRes(){
        return this.subtitleRes;
    }

}
