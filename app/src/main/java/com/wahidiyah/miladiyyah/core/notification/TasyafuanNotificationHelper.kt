package com.wahidiyah.miladiyyah.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build

object TasyafuanNotificationHelper {

    const val CHANNEL_ID = "tasyafuan"
    private const val CHANNEL_NAME = "Tasyafuan"

    fun ensureChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val manager =
            context.getSystemService(
                NotificationManager::class.java
            )

        val soundUri =
            Uri.parse(
                "android.resource://${context.packageName}/raw/tasyafuan"
            )

        val audioAttributes =
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

        val channel =
            NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description =
                    "Pengingat Tasyafuan setiap pukul 03.00 WIB"

                setSound(
                    soundUri,
                    audioAttributes
                )
            }

        manager.createNotificationChannel(channel)
    }
}