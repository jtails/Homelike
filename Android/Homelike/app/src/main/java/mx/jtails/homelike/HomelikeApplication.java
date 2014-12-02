package mx.jtails.homelike;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import mx.jtails.homelike.model.provider.HomelikeDBManager;
import mx.jtails.homelike.util.HomeLikeConfiguration;
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
        HomeLikeConfiguration.restoreConfiguration();
    }


}
