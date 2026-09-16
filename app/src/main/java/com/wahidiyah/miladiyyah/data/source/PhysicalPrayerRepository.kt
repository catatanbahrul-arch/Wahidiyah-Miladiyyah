package com.wahidiyah.miladiyyah.data.source

import com.wahidiyah.miladiyyah.data.local.room.entity.PrayerTimeEntity

object PhysicalPrayerRepository {

    const val DEFAULT_REGION_CODE = "KEDIRI_WIB"

    fun forDate(
        year: Int,
        month: Int,
        day: Int,
        regionCode: String = DEFAULT_REGION_CODE
    ): PrayerTimeEntity? {

        if (year != 2026) {
            return null
        }

        val source =
            PhysicalCalendar2026.prayerTimeFor(
                month,
                day
            ) ?: return null

        return PrayerTimeEntity(
            date = "%04d-%02d-%02d".format(
                year,
                month,
                day
            ),
            regionCode = regionCode,
            imsak = source.imsak,
            subuh = source.subuh,
            dzuhur = source.dzuhur,
            ashar = source.ashar,
            maghrib = source.maghrib,
            isya = source.isya,
            sourceVersion =
                PhysicalCalendar2026.SOURCE_NAME
        )
    }
}