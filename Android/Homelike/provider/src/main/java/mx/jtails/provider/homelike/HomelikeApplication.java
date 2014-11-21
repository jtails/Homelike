package mx.jtails.provider.homelike;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import mx.jtails.provider.homelike.model.HomelikeDBManager;
import mx.jtails.provider.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 11/18/14.
 */
public class HomelikeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HomelikeDBManager.init(this);
        HomelikePreferences.init(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .diskCacheSize(20 * 1024 * 1024)
                .diskCacheFileCount(20)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);

        this.startOrderUpdateService();
    }

    private void startOrderUpdateService(){

    }

    public void logout(){
        HomelikePreferences.clearPreferences();
        HomelikeDBManager.getDBManager().clearDatabase();
    }

}
