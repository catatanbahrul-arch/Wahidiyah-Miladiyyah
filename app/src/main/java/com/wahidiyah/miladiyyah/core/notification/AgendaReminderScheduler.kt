package com.wahidiyah.miladiyyah.core.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.wahidiyah.miladiyyah.data.local.room.entity.AgendaEntity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

object AgendaReminderScheduler {

    private const val REQUEST_BASE = 42000

    fun schedule(
        context: Context,
        agenda: AgendaEntity,
        offsetDays: Int,
        reminderTime: String
    ) {
        if (!agenda.reminderEnabled) return
        if (agenda.sourceColor != "GREEN") return
        if (agenda.status != "published") return

        val eventDate = LocalDate.parse(agenda.startDate)
        val reminderDate = eventDate.minusDays(offsetDays.toLong())

        val time = LocalTime.parse(reminderTime)
        val dateTime = LocalDateTime.of(reminderDate, time)

        val triggerAt = dateTime
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        if (triggerAt <= System.currentTimeMillis()) return

        val requestCode = REQUEST_BASE +
            (agenda.id.hashCode() and 0x7FFF) +
            offsetDays

        val intent = Intent(
            context,
            AgendaReminderReceiver::class.java
        ).apply {
            putExtra("agenda_id", agenda.id)
            putExtra("agenda_title", agenda.title)
            putExtra("event_date", agenda.startDate)
            putExtra("offset_days", offsetDays)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(AlarmManager::class.java)

        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerAt,
            pendingIntent
        )
    }

    fun scheduleAll(
        context: Context,
        agendas: List<AgendaEntity>,
        reminderTime: String
    ) {
        agendas.forEach { agenda ->
            listOf(7, 6, 5, 4, 3, 2, 1, 0).forEach { offset ->
                schedule(
                    context = context,
                    agenda = agenda,
                    offsetDays = offset,
                    reminderTime = reminderTime
                )
            }
        }
    }

    fun cancelAll(
        context: Context,
        agendas: List<AgendaEntity>
    ) {
        val alarmManager = context.getSystemService(AlarmManager::class.java)

        agendas.forEach { agenda ->
            listOf(7, 6, 5, 4, 3, 2, 1, 0).forEach { offset ->
                val requestCode = REQUEST_BASE +
                    (agenda.id.hashCode() and 0x7FFF) +
                    offset

                val intent = Intent(
                    context,
                    AgendaReminderReceiver::class.java
                )

                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
                )

                if (pendingIntent != null) {
                    alarmManager.cancel(pendingIntent)
                    pendingIntent.cancel()
                }
            }
        }
    }
}