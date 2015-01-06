package mx.jtails.homelike;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
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

    public GCMIntentService(){
        super(GCMIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        gcm.getMessageType(intent);

        Log.d(TAG, "Starting GCMIntentService: " + intent.getExtras());

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

        if(intent.getExtras().getString("Op").equals("1")
                && HomelikePreferences.loadBoolean(HomelikePreferences.IS_PROVIDER, false)){
            Notification notification = this.generateNotification(
                    intent.getExtras().getString("idPedido"),
                    R.string.new_order_received,
                    R.string.new_order_received_message, "1");
            NotificationManager notificationManager = (NotificationManager)
                    this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(Integer.parseInt(
                    intent.getExtras().getString("idPedido")), notification);
        } else if(intent.getExtras().getString("Op").equals("2")
                && !HomelikePreferences.loadBoolean(HomelikePreferences.IS_PROVIDER, false)){
            Notification notification = this.generateNotification(
                    intent.getExtras().getString("idPedido"),
                    R.string.confirmed_order_received,
                    R.string.confirmed_order_received_message, "2");
            NotificationManager notificationManager = (NotificationManager)
                    this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(Integer.parseInt(
                    intent.getExtras().getString("idPedido")), notification);
        }

        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }

    private Notification generateNotification(String orderId, int title, int message, String op){
        Notification notification = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification))
                .setContentTitle(this.getString(title))
                .setContentText(this.getString(message))
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(getNewOrderIntent(orderId, op))
                .setTicker(getString(title))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;


        return notification;
    }

    private PendingIntent getNewOrderIntent(String orderId, String op){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        if(orderId != null && !orderId.isEmpty()){
            intent.setData(Uri.parse("http://homelike.com.mx?orderId=" + orderId
                    + "&op=" + op));
        }
        return PendingIntent.getActivity(this, 1, intent, 0);
    }
}
