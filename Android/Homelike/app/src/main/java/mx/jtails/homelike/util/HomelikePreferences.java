package mx.jtails.homelike.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by GrzegorzFeathers on 9/3/14.
 */
public class HomelikePreferences {

    private static final String PREFS_NAME = "homelike_prefs";

    public static final String REGISTRATION_ID = "registration_id";
    public static final String APP_VERSION = "app_version";

    public static final String APP_CONFIG = "app_configuration";

    public static final String IS_PROVIDER = "is_provider";

    //User data
    public static final String USER_NAME = "user_name";
    public static final String USER_IMG = "user_img";
    public static final String ACCOUNT_ID = "account_id";
    public static final String DEVICE_ID = "device_id";

    //Temporal Order
    public static final String HAS_TEMP_ORDER = "has_temp_order";
    public static final String TEMP_ORDER_PROVIDER_ID = "temp_order_provider_id";
    public static final String TEMP_ORDER_ADDRESS_ID = "temp_order_address_id";
    public static final String TEMP_ORDER_SERVICE_ID = "temp_order_service_id";
    public static final String TEMP_ORDER_SUBTOTAL_SET = "temp_order_subtotal_set";

    private static SharedPreferences preferences = null;
    private static SharedPreferences.Editor editor = null;

    public static void init(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static String loadString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public static int loadInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public static long loadLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    public static boolean loadBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public static Set<String> loadStringSet(String key, Set<String> defValue){
        return preferences.getStringSet(key, defValue);
    }

    public static void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static void saveLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public static void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void saveStringSet(String key, Set<String> value){
        editor.putStringSet(key, value);
        editor.commit();
    }

    public static boolean containsPreference(String key) {
        return preferences.contains(key);
    }

    public static void clearPreferences(){
        editor.clear();
        editor.commit();
    }

}
