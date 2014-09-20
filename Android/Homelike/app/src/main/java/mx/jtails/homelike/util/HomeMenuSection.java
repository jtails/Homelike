package mx.jtails.homelike.util;

import android.support.v4.app.Fragment;

import mx.jtails.homelike.R;
import mx.jtails.homelike.ui.fragment.OrdersFragment;
import mx.jtails.homelike.ui.fragment.ServicesFragment;

/**
 * Created by GrzegorzFeathers on 9/19/14.
 */
public enum HomeMenuSection {

    SERVICES(ServicesFragment.class, R.string.home_services, R.drawable.ic_menu_services),
    ORDERS(OrdersFragment.class, R.string.home_orders, R.drawable.ic_menu_orders);

    private Class<? extends Fragment> fragmentClass;
    private int subtitleRes;
    private int iconRes;

    private HomeMenuSection(Class<? extends Fragment> fragmentClass, int subtitleRes, int iconRes){
        this.fragmentClass = fragmentClass;
        this.subtitleRes = subtitleRes;
        this.iconRes = iconRes;
    }

    public Fragment getFragmentInstance(){
        try {
            return (Fragment) fragmentClass.newInstance();
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