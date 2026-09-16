package com.wahidiyah.miladiyyah.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.wahidiyah.miladiyyah.data.local.room.entity.PrayerTimeEntity

@Dao
interface PrayerTimeDao {

    @Query("""
        SELECT * FROM prayer_times
        WHERE date = :date
        AND regionCode = :regionCode
        LIMIT 1
    """)
    suspend fun get(
        date: String,
        regionCode: String
    ): PrayerTimeEntity?

    @Query("""
        SELECT * FROM prayer_times
        WHERE date >= :fromDate
        AND date <= :toDate
        AND regionCode = :regionCode
        ORDER BY date ASC
    """)
    suspend fun getRange(
        fromDate: String,
        toDate: String,
        regionCode: String
    ): List<PrayerTimeEntity>

    @Upsert
    suspend fun upsertAll(
        items: List<PrayerTimeEntity>
    )
}