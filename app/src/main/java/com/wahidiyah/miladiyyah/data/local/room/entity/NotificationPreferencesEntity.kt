package com.wahidiyah.miladiyyah.data.local.room.entity

import androidx.room.Entity

@Entity(tableName = "notification_preferences")
data class NotificationPreferencesEntity(
    @androidx.room.PrimaryKey val id: Int = 1,
    val danaBoxMorningEnabled: Boolean = true,
    val danaBoxMorningTime: String = "07:00",
    val danaBoxEveningEnabled: Boolean = true,
    val danaBoxEveningTime: String = "17:00",
    val imsakEnabled: Boolean = true,
    val adzanEnabled: Boolean = true,
    val agendaReminderEnabled: Boolean = true,
    val agendaReminderTime: String = "08:00"
)