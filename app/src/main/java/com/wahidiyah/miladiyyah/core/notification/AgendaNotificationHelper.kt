package com.wahidiyah.miladiyyah.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

object AgendaNotificationHelper {

    const val CHANNEL_ID = "agenda_reminder"

    fun ensureChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val manager = context.getSystemService(NotificationManager::class.java)

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Pengingat Kegiatan Wahidiyah",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Pengingat kegiatan Wahidiyah H-7 sampai Hari H"
        }

        manager.createNotificationChannel(channel)
    }
}