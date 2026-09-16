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

class AgendaReminderReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        AgendaNotificationHelper.ensureChannel(context)

        if (
            android.os.Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val title = intent.getStringExtra("agenda_title")
            ?: "Kegiatan Wahidiyah"

        val eventDate = intent.getStringExtra("event_date")
            ?: ""

        val offsetDays = intent.getIntExtra(
            "offset_days",
            0
        )

        val message = when {
            offsetDays == 0 ->
                "Hari ini: $title"

            offsetDays == 1 ->
                "Besok: $title"

            else ->
                "H-$offsetDays: $title"
        }

        val openIntent = Intent(
            context,
            MainActivity::class.java
        )

        val contentIntent = PendingIntent.getActivity(
            context,
            title.hashCode(),
            openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(
            context,
            AgendaNotificationHelper.CHANNEL_ID
        )
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Pengingat Kegiatan")
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("$message\nTanggal kegiatan: $eventDate")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(contentIntent)
            .build()

        NotificationManagerCompat
            .from(context)
            .notify(
                title.hashCode() + offsetDays,
                notification
            )
    }
}