package mx.jtails.homelike.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Dispositivo;

/**
 * Created by GrzegorzFeathers on 9/5/14.
 */
public class HomelikeUtils {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 300;

    private static final String SENDER_ID = "429890560769";
    private static final String PLATFORM = "Android";

    public static int colorWithAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public static Dispositivo newApiDeviceInstance(Context context){
        Dispositivo device = new Dispositivo();

        device.setEsDefault(1);
        device.setGcmid(getGCMId(context));
        device.setModelo(getModel());
        device.setPlataforma(PLATFORM);
        device.setStatus(1);
        device.setTipoDispositivo(isTablet(context) ? "Tablet" : "Handset");

        String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                .getDeviceId();
        device.setImei(imei == null ? "000000000000000" : imei);

        return device;
    }

    public static boolean isTablet(Context context){
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String getModel(){
        String manufacturer = Build.MANUFACTURER;
        String brand = Build.BRAND;
        String product = Build.PRODUCT;
        String model = Build.MODEL;

        return manufacturer+" "+brand+" "+product+" "+model;
    }

    public static String getGCMId(Context context){
        if (checkPlayServices(context)) {
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
            String regid = getRegistrationId(context);

            if (regid.isEmpty()) {
                try {
                    regid = gcm.register(SENDER_ID);
                    HomelikePreferences.saveString(
                            HomelikePreferences.REGISTRATION_ID, regid);
                    HomelikePreferences.saveInt(
                            HomelikePreferences.APP_VERSION, getAppVersion(context));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return  regid;
        }
        return "";
    }

    public static boolean checkPlayServices(Context context) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, (Activity) context,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }

    public static String getRegistrationId(Context context) {
        String registrationId = HomelikePreferences.loadString(
                HomelikePreferences.REGISTRATION_ID, "");
        if (registrationId.isEmpty()) {
            return "";
        }

        int registeredVersion = HomelikePreferences.loadInt(
                HomelikePreferences.APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            return "";
        }

        return registrationId;
    }

    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public static String getImageUrlInSize(int dim, String imageUrl){
        String newSizeQuery = "?sz=" + dim;
        String newImageUrl;

        if(imageUrl.contains("?sz=")){
            newImageUrl = imageUrl.replaceAll("\\?sz=[0-9]+", newSizeQuery);
        } else {
            newImageUrl = imageUrl + newSizeQuery;
        }

        return newImageUrl;
    }

    public static int getOrderStatusString(int status){
        int statusRes;
        switch (status){
            case 0:
                statusRes = R.string.status_new; break;
            case 1:
                statusRes = R.string.status_confirmed; break;
            case 2:
                statusRes = R.string.status_delivered; break;
            default:
                statusRes = R.string.status_unknown; break;
        }
        return statusRes;
    }

}
