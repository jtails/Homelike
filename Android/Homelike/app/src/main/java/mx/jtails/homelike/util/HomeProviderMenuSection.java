package mx.jtails.homelike.util;

import android.support.v4.app.Fragment;

import mx.jtails.homelike.R;
import mx.jtails.homelike.ui.fragment.ConfirmedOrdersFragment;
import mx.jtails.homelike.ui.fragment.ContactFragment;
import mx.jtails.homelike.ui.fragment.NewOrdersFragment;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public enum HomeProviderMenuSection implements HomeLikeConfiguration.HomeMenuOption {

    NEW_ORDERS(NewOrdersFragment.class, R.string.home_new_orders, R.drawable.ic_menu_orders),
    CONFIRMED_ORDERS(ConfirmedOrdersFragment.class, R.string.home_confirmed_orders, R.drawable.ic_menu_orders),
    CONTACT(ContactFragment.class, R.string.home_contact, R.drawable.ic_menu_contact);

    public static final HomeProviderMenuSection defaultContent = HomeProviderMenuSection.NEW_ORDERS;

    private Class<? extends Fragment> fragmentClass;
    private int subtitleRes;
    private int iconRes;

    private HomeProviderMenuSection(Class<? extends Fragment> fragmentClass, int subtitleRes, int iconRes){
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
