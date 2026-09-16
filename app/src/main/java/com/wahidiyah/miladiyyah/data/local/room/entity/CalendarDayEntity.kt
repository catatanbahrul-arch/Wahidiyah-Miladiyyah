package com.wahidiyah.miladiyyah.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calendar_days")
data class CalendarDayEntity(
    @PrimaryKey val date: String,            // YYYY-MM-DD
    val hijriDate: String? = null,
    val dayName: String? = null,
    val monthName: String? = null,
    val note: String? = null,
    val sourceVersion: String = "physical-calendar"
)
