package com.hansung.ac.kr.sfe.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.hansung.ac.kr.sfe.MainActivity;
import com.hansung.ac.kr.sfe.R;


import java.util.Map;

public class AppFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {

        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        Log.d("server send Token: ", token);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> pushDataMap = remoteMessage.getData();

        sendNotification(pushDataMap);
    }

    private void sendNotification(Map<String,String> data){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP ? R.drawable.alarm2 : R.mipmap.ic_launcher)
                .setContentTitle(data.get("title"))
                .setContentText(data.get("msg"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
