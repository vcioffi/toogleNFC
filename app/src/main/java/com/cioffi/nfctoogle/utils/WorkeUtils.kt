package com.cioffi.nfctoogle.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.cioffi.nfctoogle.R


fun makeStatusNotification(message: String, context: Context) {

    //intent for notification action
    val intentNfcAcxtion = Intent(Settings.ACTION_NFC_SETTINGS)
    intentNfcAcxtion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    var pintentNfcAcxtion = PendingIntent.getActivity(context, 0, intentNfcAcxtion,
        PendingIntent.FLAG_IMMUTABLE)

    // Make a channel if necessary
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val name =   "NFC"
        val description = "Shows notifications whenever work starts"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel("CHANNEL_ID", name, importance)
        channel.description = description

        // Add the channel
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }

    val action : NotificationCompat.Action = NotificationCompat.Action(R.drawable.nfc_icon,"Turn OFF driving mode", pintentNfcAcxtion)
    // Create the notification
    val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
        .setSmallIcon(R.drawable.nfc_icon)
        .setContentTitle("NFC")
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))
        .addAction(action)

    // Show the notification
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        Log.v("Test ", "No notification permission")
        return
    }
    NotificationManagerCompat.from(context).notify(1, builder.build())
}