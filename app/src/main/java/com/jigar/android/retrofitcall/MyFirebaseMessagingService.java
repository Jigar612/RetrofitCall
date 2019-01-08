package com.jigar.android.retrofitcall;

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

//      String CHANNEL_ID = "my_channel_01";
//
//      NotificationCompat.Builder builder = new NotificationCompat.Builder(MyFirebaseMessagingService.this);
//      builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);
//      builder.setChannelId(CHANNEL_ID);
//      builder.setContentTitle("Notification");
//      builder.setContentText(messageBody);
//      builder.setAutoCancel(true);
//      builder.setVibrate(new long[]{200, 200, 200, 200});//,300,300
//      builder.setSound(defaultSoundUri);
//      //builder.setBadgeIconType(new_notification_counter);
////                builder.setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);
//      new_notification_counter++;
       Log.d(TAG, "From: " + remoteMessage.getFrom());
   }
}