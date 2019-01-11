package com.jigar.android.retrofitcall;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG ="" ;
    int ADMIN_CHANNEL_ID = 101;

   @Override
   public void onMessageReceived(RemoteMessage remoteMessage) {

      NotificationManager notificationmanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


       String title = remoteMessage.getData().get("title");
       String body = remoteMessage.getData().get("body");
//       String icon = remoteMessage.getData().get(object_id);
//       String objectType = remoteMessage.getData().get(objectType”);

       NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
               .setContentTitle("ABC")
               .setContentText(body)
               .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);

        notificationmanager.notify(0, notification.build());

       Log.d(TAG, "From: " + remoteMessage.getFrom());
   }
}