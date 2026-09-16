package com.wahidiyah.miladiyyah.data.local.room.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseMigrations {

    val MIGRATION_1_2 = object : Migration(1, 2) {

        override fun migrate(
            database: SupportSQLiteDatabase
        ) {
            database.execSQL(
                "ALTER TABLE agendas ADD COLUMN startDate TEXT NOT NULL DEFAULT ''"
            )

            database.execSQL(
                "ALTER TABLE agendas ADD COLUMN endDate TEXT NOT NULL DEFAULT ''"
            )

            database.execSQL(
                """
                UPDATE agendas
                SET startDate = date,
                    endDate = date
                WHERE startDate = ''
                   OR endDate = ''
                """.trimIndent()
            )

            database.execSQL(
                "ALTER TABLE notification_preferences ADD COLUMN agendaReminderTime TEXT NOT NULL DEFAULT '08:00'"
            )
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {

        override fun migrate(
            database: SupportSQLiteDatabase
        ) {
            database.execSQL(
                """
                CREATE TABLE IF NOT EXISTS announcements (
                    id TEXT NOT NULL,
                    title TEXT NOT NULL,
                    body TEXT,
                    publishedAt TEXT,
                    actionLabel TEXT,
                    actionUrl TEXT,
                    isPublished INTEGER NOT NULL DEFAULT 1,
                    PRIMARY KEY(id)
                )
                """.trimIndent()
            )
        }
    }
}