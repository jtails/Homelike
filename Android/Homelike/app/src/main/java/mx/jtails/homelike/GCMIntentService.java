package mx.jtails.homelike;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import mx.jtails.android.homelike.R;

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
        Log.d(TAG, "Starting GCMIntentService: " + intent.getExtras());
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        /*
        Toast.makeText(this.getApplicationContext(), "Push Received: " + intent.getExtras(),
                Toast.LENGTH_SHORT).show();
        Toast.makeText(this.getApplicationContext(), "MessageType: " + messageType,
                Toast.LENGTH_SHORT).show();
        */

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Notificación Recibida")
                .setContentText("Extras: " + intent.getExtras())
                .setAutoCancel(true)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(DEF_NOTIFICATION_ID, notification);

        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }
}
