package com.wahidiyah.miladiyyah.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.wahidiyah.miladiyyah.data.local.room.entity.AgendaEntity

@Dao
interface AgendaDao {

    @Query("""
        SELECT * FROM agendas
        WHERE status = 'published'
        ORDER BY startDate ASC
    """)
    suspend fun getPublished(): List<AgendaEntity>

    @Query("""
        SELECT * FROM agendas
        WHERE sourceColor = 'GREEN'
        AND reminderEnabled = 1
        AND status = 'published'
        AND startDate >= :fromDate
        AND startDate <= :toDate
        ORDER BY startDate ASC
    """)
    suspend fun getReminderEligible(
        fromDate: String,
        toDate: String
    ): List<AgendaEntity>

    @Query("""
        DELETE FROM agendas
        WHERE startDate <= :scopeEnd
        AND endDate >= :scopeStart
    """)
    suspend fun deleteOverlappingScope(
        scopeStart: String,
        scopeEnd: String
    )

    @Upsert
    suspend fun upsertAll(items: List<AgendaEntity>)
}