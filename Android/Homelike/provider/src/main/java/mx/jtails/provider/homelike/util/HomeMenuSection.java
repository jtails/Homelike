package mx.jtails.provider.homelike.util;

import android.support.v4.app.Fragment;

import mx.jtails.provider.homelike.R;
import mx.jtails.provider.homelike.ui.fragment.ConfirmedOrdersFragment;
import mx.jtails.provider.homelike.ui.fragment.ContactFragment;
import mx.jtails.provider.homelike.ui.fragment.NewOrdersFragment;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public enum HomeMenuSection {

    NEW_ORDERS(NewOrdersFragment.class, R.string.home_new_orders, R.drawable.ic_menu_orders),
    CONFIRMED_ORDERS(ConfirmedOrdersFragment.class, R.string.home_confirmed_orders, R.drawable.ic_menu_orders),
    CONTACT(ContactFragment.class, R.string.home_contact, R.drawable.ic_menu_contact);

    private Class<? extends Fragment> fragmentClass;
    private int subtitleRes;
    private int iconRes;

    private HomeMenuSection(Class<? extends Fragment> fragmentClass, int subtitleRes, int iconRes){
        this.fragmentClass = fragmentClass;
        this.subtitleRes = subtitleRes;
        this.iconRes = iconRes;
    }

    public Class<? extends Fragment> getFragmentClass(){
        return this.fragmentClass;
    }

    public Fragment getFragmentInstance(){
        try {
            return fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getIconRes(){
        return this.iconRes;
    }

    public int getSubtitleRes(){
        return this.subtitleRes;
    }

}
