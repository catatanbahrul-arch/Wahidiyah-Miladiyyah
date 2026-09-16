package com.wahidiyah.miladiyyah.core.notification

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.wahidiyah.miladiyyah.MainActivity

class PrayerNotificationReceiver :
    BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        PrayerNotificationHelper.ensureChannel(
            context
        )

        if (
            android.os.Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val prayerName =
            intent.getStringExtra(
                "prayer_name"
            ) ?: return

        val prayerTime =
            intent.getStringExtra(
                "prayer_time"
            ) ?: return

        val isImsak =
            intent.getBooleanExtra(
                "is_imsak",
                false
            )

        val title =
            if (isImsak) {
                "Waktu Imsak"
            } else {
                "Waktu $prayerName"
            }

        val message =
            if (isImsak) {
                "Imsak pukul $prayerTime"
            } else {
                "$prayerName pukul $prayerTime"
            }

        val openIntent =
            Intent(
                context,
                MainActivity::class.java
            )

        val contentIntent =
            PendingIntent.getActivity(
                context,
                prayerName.hashCode(),
                openIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
            )

        val notification =
            NotificationCompat.Builder(
                context,
                PrayerNotificationHelper.CHANNEL_ID
            )
                .setSmallIcon(
                    android.R.drawable.ic_dialog_info
                )
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(message)
                )
                .setPriority(
                    NotificationCompat.PRIORITY_DEFAULT
                )
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .build()

        NotificationManagerCompat
            .from(context)
            .notify(
                prayerName.hashCode(),
                notification
            )
    }
}