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

       Log.d(TAG, "From: " + remoteMessage.getFrom());
   }
}