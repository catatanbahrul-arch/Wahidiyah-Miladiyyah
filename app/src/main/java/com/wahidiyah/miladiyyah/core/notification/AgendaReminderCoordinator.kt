package com.wahidiyah.miladiyyah.core.notification

import android.content.Context
import com.wahidiyah.miladiyyah.data.local.room.db.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

object AgendaReminderCoordinator {

    suspend fun reschedule(context: Context) {
        withContext(Dispatchers.IO) {

            val database = DatabaseProvider.get(context)

            val preferences =
                database.notificationPreferencesDao().get()
                    ?: return@withContext

            if (!preferences.agendaReminderEnabled) {
                return@withContext
            }

            val today = LocalDate.now()

            val fromDate = today.toString()
            val toDate = today.plusDays(7).toString()

            val agendas =
                database.agendaDao()
                    .getReminderEligible(
                        fromDate = fromDate,
                        toDate = toDate
                    )

            AgendaReminderScheduler.scheduleAll(
                context = context,
                agendas = agendas,
                reminderTime = preferences.agendaReminderTime
            )
        }
    }
}