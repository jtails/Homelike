package mx.jtails.homelike;

import android.app.Application;

import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 9/3/14.
 */
public class HomelikeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HomelikeDBManager.init(this);
        HomelikePreferences.init(this);
    }

    public void logout(){
        HomelikePreferences.clearPreferences();
    }

}
