package com.wahidiyah.miladiyyah.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.wahidiyah.miladiyyah.data.local.room.entity.AnnouncementEntity

@Dao
interface AnnouncementDao {

    @Query("""
        SELECT * FROM announcements
        WHERE isActive = 1
        ORDER BY publishedAt DESC
    """)
    suspend fun getActive(): List<AnnouncementEntity>

    @Query("""
        SELECT * FROM announcements
        WHERE id = :id
        LIMIT 1
    """)
    suspend fun getById(id: String): AnnouncementEntity?

    @Upsert
    suspend fun upsertAll(items: List<AnnouncementEntity>)

    @Query("DELETE FROM announcements")
    suspend fun deleteAll()
}