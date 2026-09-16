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

class DanaBoxNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        DanaBoxNotificationHelper.ensureChannel(context)

        if (
            android.os.Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val period =
            intent.getStringExtra("period") ?: return

        val title =
            if (period == "morning") {
                "Dana Box Pagi"
            } else {
                "Dana Box Sore"
            }

        val message =
            "Jangan lupa berDana Box hari ini."

        val openIntent =
            Intent(
                context,
                MainActivity::class.java
            )

        val contentIntent =
            PendingIntent.getActivity(
                context,
                if (period == "morning") 73001 else 73002,
                openIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
            )

        val notification =
            NotificationCompat.Builder(
                context,
                DanaBoxNotificationHelper.CHANNEL_ID
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
                if (period == "morning") 73001 else 73002,
                notification
            )
    }
}