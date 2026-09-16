package com.wahidiyah.miladiyyah.data.local.room.entity

import androidx.room.Entity

@Entity(
    tableName = "prayer_times",
    primaryKeys = ["date", "regionCode"]
)
data class PrayerTimeEntity(
    val date: String,                        // YYYY-MM-DD
    val regionCode: String,
    val imsak: String? = null,
    val subuh: String? = null,
    val dzuhur: String? = null,
    val ashar: String? = null,
    val maghrib: String? = null,
    val isya: String? = null,
    val sourceVersion: String = "physical-calendar"
)
