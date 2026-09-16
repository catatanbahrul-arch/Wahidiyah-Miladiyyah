package com.wahidiyah.miladiyyah.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "announcements")
data class AnnouncementEntity(
    @PrimaryKey val id: String,
    val title: String,
    val body: String,
    val publishedAt: String,
    val actionLabel: String? = null,
    val actionUrl: String? = null,
    val isActive: Boolean = true
)