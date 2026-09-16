package com.wahidiyah.miladiyyah.core.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.wahidiyah.miladiyyah.data.source.PhysicalPrayerRepository
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

object PrayerNotificationScheduler {

    private const val REQUEST_BASE = 52000

    /*
     * Source kalender fisik menggunakan Waktu Indonesia Barat (WIB).
     *
     * Asia/Jakarta digunakan sebagai timezone IANA untuk WIB.
     * Scheduler tidak menggunakan timezone perangkat sebagai
     * sumber kebenaran jadwal.
     */
    private val SOURCE_ZONE_ID: ZoneId =
        ZoneId.of("Asia/Jakarta")

    private val SOURCE_CLOCK: Clock =
        Clock.system(SOURCE_ZONE_ID)

    private data class PrayerItem(
        val label: String,
        val time: String,
        val isImsak: Boolean
    )

    fun scheduleForDate(
        context: Context,
        date: LocalDate
    ) {

        if (date.year != 2026) {
            return
        }

        val prayer =
            PhysicalPrayerRepository.forDate(
                year = date.year,
                month = date.monthValue,
                day = date.dayOfMonth
            ) ?: return

        val items =
            listOfNotNull(

                prayer.imsak?.let {
                    PrayerItem(
                        label = "Imsak",
                        time = it,
                        isImsak = true
                    )
                },

                prayer.subuh?.let {
                    PrayerItem(
                        label = "Subuh",
                        time = it,
                        isImsak = false
                    )
                },

                prayer.dzuhur?.let {
                    PrayerItem(
                        label = "Dzuhur",
                        time = it,
                        isImsak = false
                    )
                },

                prayer.ashar?.let {
                    PrayerItem(
                        label = "Ashar",
                        time = it,
                        isImsak = false
                    )
                },

                prayer.maghrib?.let {
                    PrayerItem(
                        label = "Maghrib",
                        time = it,
                        isImsak = false
                    )
                },

                prayer.isya?.let {
                    PrayerItem(
                        label = "Isya",
                        time = it,
                        isImsak = false
                    )
                }
            )

        val alarmManager =
            context.getSystemService(
                AlarmManager::class.java
            )

        items.forEachIndexed { index, item ->

            val time =
                LocalTime.parse(item.time)

            val dateTime =
                LocalDateTime.of(
                    date,
                    time
                )

            val triggerAt =
                dateTime
                    .atZone(SOURCE_ZONE_ID)
                    .toInstant()
                    .toEpochMilli()

            /*
             * Jangan menjadwalkan alarm yang sudah lewat.
             *
             * Perbandingan juga menggunakan clock WIB agar
             * konsisten dengan source kalender fisik.
             */
            if (
                triggerAt <=
                System.currentTimeMillis()
            ) {
                return@forEachIndexed
            }

            val requestCode =
                REQUEST_BASE +
                    date.dayOfYear * 10 +
                    index

            val intent =
                Intent(
                    context,
                    PrayerNotificationReceiver::class.java
                ).apply {

                    putExtra(
                        "prayer_name",
                        item.label
                    )

                    putExtra(
                        "prayer_time",
                        item.time
                    )

                    putExtra(
                        "is_imsak",
                        item.isImsak
                    )
                }

            val pendingIntent =
                PendingIntent.getBroadcast(
                    context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or
                        PendingIntent.FLAG_IMMUTABLE
                )

            /*
             * Inexact alarm sengaja digunakan.
             * Tidak membutuhkan exact-alarm permission.
             */
            alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerAt,
                pendingIntent
            )
        }
    }

    fun scheduleUpcoming(
        context: Context,
        days: Int = 7
    ) {

        val safeDays =
            days.coerceIn(1, 30)

        val today =
            LocalDate.now(SOURCE_CLOCK)

        for (
            offset in 0 until safeDays
        ) {

            scheduleForDate(
                context,
                today.plusDays(
                    offset.toLong()
                )
            )
        }
    }
}