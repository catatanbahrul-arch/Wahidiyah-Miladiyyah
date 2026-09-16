package com.wahidiyah.miladiyyah.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.wahidiyah.miladiyyah.data.local.room.entity.NotificationPreferencesEntity

@Dao
interface NotificationPreferencesDao {
    @Query("SELECT * FROM notification_preferences WHERE id = 1 LIMIT 1")
    suspend fun get(): NotificationPreferencesEntity?

    @Upsert
    suspend fun upsert(value: NotificationPreferencesEntity)
}
