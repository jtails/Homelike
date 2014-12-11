package mx.jtails.homelike.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Dispositivo;
import mx.jtails.homelike.api.model.Dispositivop;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.api.model.Proveedor;

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

        String imei = getDeviceId(context);
        device.setImei(imei == null ? "000000000000000" : imei);

        return device;
    }

    public static Dispositivop newProviderApiDeviceInstance(Context context, Proveedor provider){
        Dispositivop device = new Dispositivop();

        device.setProveedor(provider);
        device.setEsDefault(1);
        device.setGcmid(getGCMId(context));
        device.setModelo(getModel());
        device.setPlataforma(PLATFORM);
        device.setTipoDispositivo(isTablet(context) ? "Tablet" : "Handset");

        String imei = getDeviceId(context);
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

    public static int getOrderStatusStringRes(int status){
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

    public static int getScheduleDaysStringRes(int scheduleId){
        int daysRes;
        switch (scheduleId) {
            case 1: daysRes = R.string.schedule_mon_fri; break;
            case 2: daysRes = R.string.schedule_sat; break;
            case 3: daysRes = R.string.schedule_sun; break;
            default: daysRes = -1; break;
        }
        return daysRes;
    }

    public static String getScheduleString(Context ctx, Date scheduleOpening, Date scheduleClosing){
        SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");

        String scheduleOpeningString = timeFormat.format(scheduleOpening);
        String scheduleClosingString = timeFormat.format(scheduleClosing);
        String scheduleConnector = ctx.getString(R.string.schedule_connector);

        return new StringBuilder().append(scheduleOpeningString)
            .append(" ").append(scheduleConnector).append(" ")
            .append(scheduleClosingString).toString();
    }

    public static Set<String> getSerializedSubtotal(Map<Integer, Integer> subtotal){
        Set<String> serializedSubtotal = new HashSet<String>();

        for(Integer position : subtotal.keySet()){
            serializedSubtotal.add(position + ";" + subtotal.get(position));
        }

        return serializedSubtotal;
    }

    public static Map<Integer, Integer> getDeserializedSubtotal(Set<String> serializedSubtotal){
        Map<Integer, Integer> deserializedSubtotal = new HashMap<Integer, Integer>();
        if(serializedSubtotal == null) { return deserializedSubtotal; }

        for(String subtotalItem : serializedSubtotal){
            String[] splittedSubtotal = subtotalItem.split(";");
            deserializedSubtotal.put(Integer.parseInt(splittedSubtotal[0]),
                    Integer.parseInt(splittedSubtotal[1]));
        }

        return deserializedSubtotal;
    }

    public static List<Pedido> getStatusFilteredOrders(List<Pedido> orders, int status){
        if(status < 0){ return orders; }
        List<Pedido> filteredOrders = new ArrayList<Pedido>();
        for(Pedido o : orders){
            if(o.getStatus() == status){
                filteredOrders.add(o);
            }
        }
        return filteredOrders;
    }

    public static String getDeviceId(Context ctx){
        return Settings.Secure.getString(ctx.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

}
