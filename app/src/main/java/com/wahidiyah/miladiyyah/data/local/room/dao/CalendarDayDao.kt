package com.wahidiyah.miladiyyah.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wahidiyah.miladiyyah.data.local.room.entity.CalendarDayEntity

@Dao
interface CalendarDayDao {
    @Query("SELECT * FROM calendar_days WHERE date = :date LIMIT 1")
    suspend fun getByDate(date: String): CalendarDayEntity?

    @Query("SELECT * FROM calendar_days ORDER BY date ASC")
    suspend fun getAll(): List<CalendarDayEntity>
}
