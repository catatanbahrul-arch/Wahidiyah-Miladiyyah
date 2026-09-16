package com.wahidiyah.miladiyyah.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agendas")
data class AgendaEntity(
    @PrimaryKey val id: String,
    val date: String,                        // YYYY-MM-DD
    val title: String,
    val detail: String? = null,
    val sourceColor: String = "GREEN",
    val status: String = "review",           // review | published
    val reminderEnabled: Boolean = true
)
