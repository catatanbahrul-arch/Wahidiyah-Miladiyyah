package com.wahidiyah.miladiyyah.core.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

object DanaBoxNotificationScheduler {

    private const val MORNING_REQUEST_CODE = 73001
    private const val EVENING_REQUEST_CODE = 73002
    private val SOURCE_ZONE_ID = ZoneId.of("Asia/Jakarta")

    fun scheduleUpcoming(
        context: Context,
        days: Int = 30
    ) {
        val safeDays = days.coerceIn(1, 60)
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
        scheduleOne(
            context = context,
            date = date,
            time = LocalTime.of(7, 0),
            period = "morning",
            requestCode = MORNING_REQUEST_CODE + date.toEpochDay().toInt()
        )

        scheduleOne(
            context = context,
            date = date,
            time = LocalTime.of(17, 0),
            period = "evening",
            requestCode = EVENING_REQUEST_CODE + date.toEpochDay().toInt()
        )
    }

    private fun scheduleOne(
        context: Context,
        date: LocalDate,
        time: LocalTime,
        period: String,
        requestCode: Int
    ) {
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
                DanaBoxNotificationReceiver::class.java
            ).apply {
                putExtra("period", period)
            }

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