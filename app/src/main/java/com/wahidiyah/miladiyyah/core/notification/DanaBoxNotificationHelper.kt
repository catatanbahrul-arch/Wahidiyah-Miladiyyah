package com.wahidiyah.miladiyyah.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build

object DanaBoxNotificationHelper {

    const val CHANNEL_ID = "dana_box"
    private const val CHANNEL_NAME = "Dana Box"

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
                "android.resource://${context.packageName}/raw/dana_box"
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
                    "Pengingat Dana Box pagi dan sore"

                setSound(
                    soundUri,
                    audioAttributes
                )
            }

        manager.createNotificationChannel(channel)
    }
}