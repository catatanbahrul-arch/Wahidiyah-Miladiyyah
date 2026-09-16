package com.wahidiyah.miladiyyah.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wahidiyah.miladiyyah.data.local.room.dao.AgendaDao
import com.wahidiyah.miladiyyah.data.local.room.dao.CalendarDayDao
import com.wahidiyah.miladiyyah.data.local.room.dao.NotificationPreferencesDao
import com.wahidiyah.miladiyyah.data.local.room.dao.PrayerTimeDao
import com.wahidiyah.miladiyyah.data.local.room.entity.AgendaEntity
import com.wahidiyah.miladiyyah.data.local.room.entity.CalendarDayEntity
import com.wahidiyah.miladiyyah.data.local.room.entity.NotificationPreferencesEntity
import com.wahidiyah.miladiyyah.data.local.room.entity.PrayerTimeEntity
import com.wahidiyah.miladiyyah.data.local.room.entity.RegionRuleEntity

@Database(
    entities = [
        CalendarDayEntity::class,
        AgendaEntity::class,
        PrayerTimeEntity::class,
        RegionRuleEntity::class,
        NotificationPreferencesEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun calendarDayDao(): CalendarDayDao
    abstract fun agendaDao(): AgendaDao
    abstract fun prayerTimeDao(): PrayerTimeDao
    abstract fun notificationPreferencesDao(): NotificationPreferencesDao
}