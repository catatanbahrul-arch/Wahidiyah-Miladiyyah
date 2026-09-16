package com.wahidiyah.miladiyyah.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wahidiyah.miladiyyah.data.local.room.entity.AgendaEntity

@Dao
interface AgendaDao {
    @Query("SELECT * FROM agendas WHERE status = 'published' ORDER BY date ASC")
    suspend fun getPublished(): List<AgendaEntity>

    @Query("SELECT * FROM agendas WHERE date >= :fromDate ORDER BY date ASC")
    suspend fun getFromDate(fromDate: String): List<AgendaEntity>
}
