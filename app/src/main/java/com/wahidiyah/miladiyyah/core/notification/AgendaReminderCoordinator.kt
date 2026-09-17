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

            /*
             * Reminder kegiatan khusus Jamaah dibuat H-7 sampai Hari H.
             *
             * Karena H-7 sebuah acara dapat berada jauh di masa depan,
             * coordinator harus mengambil seluruh kegiatan mendatang,
             * bukan hanya 7 hari ke depan.
             *
             * Rentang 366 hari digunakan untuk mencakup kalender kegiatan
             * satu tahun penuh termasuk kemungkinan tahun kabisat.
             */
            val fromDate = today.toString()
            val toDate = today.plusDays(366).toString()

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