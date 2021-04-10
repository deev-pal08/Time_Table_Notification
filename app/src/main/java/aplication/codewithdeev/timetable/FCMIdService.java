package aplication.codewithdeev.timetable;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMIdService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getNotification()!=null){
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    public void sendNotification(String body){
        showNotification(body);
    }

    public void showNotification(String notificationsBody) {
        String title = "Time Table Notifications";
        String desc = "Notify Change";


        NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("SendNotification", title, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(desc);
            nManager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(getApplicationContext(),SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"SendNotification")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(notificationsBody)
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText(notificationsBody))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setGroup("Time_Table_Group")
                .setSound(soundUri);
        nManager.notify(1000, builder.build());
    }
}
