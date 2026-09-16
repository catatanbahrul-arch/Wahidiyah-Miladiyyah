package com.wahidiyah.miladiyyah.data.local.room.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun get(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "wahidiyah_miladiyyah.db"
            )
                .addMigrations(DatabaseMigrations.MIGRATION_1_2)
                .build()
                .also { INSTANCE = it }
        }
    }
}