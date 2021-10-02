package ru.abenda.marsexplorer.notifications

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import ru.abenda.marsexplorer.R
import ru.abenda.marsexplorer.ui.MainActivity

// Notification ID.
private const val NOTIFICATION_ID = 0

fun getNotificationManager(context: Context): NotificationManager =
    ContextCompat.getSystemService(
        context,
        NotificationManager::class.java
    ) as NotificationManager

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    val intent = Intent(applicationContext, MainActivity::class.java)

    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.new_photos_channel)
    )
        .setSmallIcon(R.drawable.ic_notification_new_photos)
        .setContentTitle(applicationContext.resources.getString(R.string.notification_title))
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_LOW)

    notify(NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications(applicationContext: Context) {
    cancelAll()
}

fun createChannel(applicationContext: Context, channelId: String, channelName: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
                .apply {
                    enableLights(true)
                    lightColor = Color.RED
                    enableVibration(true)
                    description = "There were added new mars photos"
                    setShowBadge(false)
                }

        val notificationManager = getNotificationManager(applicationContext)
        notificationManager.createNotificationChannel(notificationChannel)
    }
}