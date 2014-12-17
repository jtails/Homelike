package mx.jtails.homelike;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

/**
 * Created by GrzegorzFeathers on 9/25/14.
 */
public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {

    private static final String TAG = GCMBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Intent Received: " + intent.getExtras());
        ComponentName componentName = new ComponentName(context.getPackageName(),
                GCMIntentService.class.getName());
        startWakefulService(context, intent.setComponent(componentName));
        setResultCode(ActionBarActivity.RESULT_OK);
    }
}
