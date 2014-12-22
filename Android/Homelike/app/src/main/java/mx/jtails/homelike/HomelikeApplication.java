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

    private OnNewOrderReceivedListener mNewOrderListener = null;

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
    }

    public void logout(){
        HomelikePreferences.clearPreferences();
        HomelikeDBManager.getDBManager().clearDatabase();
        HomeLikeConfiguration.restoreConfiguration();
    }

    public void setNewOrderListener(OnNewOrderReceivedListener listener){
        this.mNewOrderListener = listener;
    }

    public void removeNewOrderListener(){
        this.mNewOrderListener = null;
    }

    public OnNewOrderReceivedListener getNewOrderListener(){
        return this.mNewOrderListener;
    }

    public static interface OnNewOrderReceivedListener {
        public void onNewOrderReceived();
    }

}
