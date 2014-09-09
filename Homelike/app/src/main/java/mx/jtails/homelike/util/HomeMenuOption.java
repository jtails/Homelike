package mx.jtails.homelike.util;

import android.support.v4.app.Fragment;

import mx.jtails.homelike.ui.fragment.ServicesFragment;

/**
 * Created by GrzegorzFeathers on 9/1/14.
 */
public enum HomeMenuOption {

    SERVICES(ServicesFragment.class, 0, 0);

    private Class<?> fragmentClass;
    private int subtitleRes;
    private int iconRes;

    private HomeMenuOption(Class<?> fragmentClass, int subtitleRes, int iconRes){
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

}
