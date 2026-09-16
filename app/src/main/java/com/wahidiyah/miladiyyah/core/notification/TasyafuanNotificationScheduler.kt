package com.wahidiyah.miladiyyah.core.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

object TasyafuanNotificationScheduler {

    private const val REQUEST_BASE = 74000
    private val SOURCE_ZONE_ID = ZoneId.of("Asia/Jakarta")

    fun scheduleUpcoming(
        context: Context,
        days: Int = 60
    ) {
        val safeDays = days.coerceIn(1, 90)
        val today = LocalDate.now(SOURCE_ZONE_ID)

        for (offset in 0 until safeDays) {
            scheduleForDate(
                context = context,
                date = today.plusDays(offset.toLong())
            )
        }
    }

    fun scheduleForDate(
        context: Context,
        date: LocalDate
    ) {
        val time = LocalTime.of(3, 0)

        val triggerAt =
            LocalDateTime.of(date, time)
                .atZone(SOURCE_ZONE_ID)
                .toInstant()
                .toEpochMilli()

        if (triggerAt <= System.currentTimeMillis()) {
            return
        }

        val intent =
            Intent(
                context,
                TasyafuanNotificationReceiver::class.java
            )

        val requestCode =
            REQUEST_BASE +
                (date.toEpochDay() % 100000).toInt()

        val pendingIntent =
            PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
            )

        val alarmManager =
            context.getSystemService(
                AlarmManager::class.java
            )

        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerAt,
            pendingIntent
        )
    }
}