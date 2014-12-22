package mx.jtails.homelike;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.ui.HomeActivity;
import mx.jtails.homelike.util.HomelikePreferences;

/**
 * Created by GrzegorzFeathers on 9/24/14.
 */
public class GCMIntentService extends IntentService {

    private static final String TAG = GCMIntentService.class.getSimpleName();
    private static final int DEF_NOTIFICATION_ID = 666;

    public GCMIntentService(){
        super(GCMIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        gcm.getMessageType(intent);

        Log.d(TAG, "Starting GCMIntentService: " + intent.getExtras());

        if(HomelikePreferences.loadBoolean(HomelikePreferences.IS_PROVIDER, false)){
            final HomelikeApplication.OnNewOrderReceivedListener listener
                    = ((HomelikeApplication) this.getApplication()).getNewOrderListener();
            if(listener != null){
                new Handler(this.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onNewOrderReceived();
                    }
                });
            }

            Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle(this.getString(R.string.new_order_received))
                    .setContentText(this.getString(R.string.new_order_received_message))
                    .setAutoCancel(true)
                    .setContentIntent(getNewOrderIntent(intent.getExtras().getString("idPedido")))
                    .build();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            NotificationManager notificationManager = (NotificationManager)
                    this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(Integer.parseInt(
                    intent.getExtras().getString("idPedido")), notification);
        }

        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }

    private PendingIntent getNewOrderIntent(String orderId){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        if(orderId != null && !orderId.isEmpty()){
            intent.setData(Uri.parse("http://homelike.com.mx?orderId=" + orderId));
        }
        return PendingIntent.getActivity(this, 1, intent, 0);
    }
}
