package com.wahidiyah.miladiyyah.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

object PrayerNotificationHelper {

    const val CHANNEL_ID = "prayer_times"

    fun ensureChannel(
        context: Context
    ) {

        if (
            Build.VERSION.SDK_INT <
            Build.VERSION_CODES.O
        ) {
            return
        }

        val manager =
            context.getSystemService(
                NotificationManager::class.java
            )

        val channel =
            NotificationChannel(
                CHANNEL_ID,
                "Jadwal Shalat",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description =
                    "Pengingat Imsak dan waktu shalat dari kalender fisik Miladiyyah"
            }

        manager.createNotificationChannel(channel)
    }
}