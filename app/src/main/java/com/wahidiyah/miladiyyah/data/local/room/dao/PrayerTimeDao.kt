package com.wahidiyah.miladiyyah.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wahidiyah.miladiyyah.data.local.room.entity.PrayerTimeEntity

@Dao
interface PrayerTimeDao {
    @Query("SELECT * FROM prayer_times WHERE date = :date AND regionCode = :regionCode LIMIT 1")
    suspend fun get(date: String, regionCode: String): PrayerTimeEntity?
}
