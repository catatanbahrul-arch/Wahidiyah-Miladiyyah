package com.wahidiyah.miladiyyah.data.repository

import com.wahidiyah.miladiyyah.data.local.room.dao.AnnouncementDao
import com.wahidiyah.miladiyyah.data.local.room.entity.AnnouncementEntity

class AnnouncementRepository(
    private val dao: AnnouncementDao
) {
    suspend fun getActive(): List<AnnouncementEntity> =
        dao.getActive()

    suspend fun getById(id: String): AnnouncementEntity? =
        dao.getById(id)

    suspend fun save(items: List<AnnouncementEntity>) {
        dao.upsertAll(items)
    }

    suspend fun clear() {
        dao.deleteAll()
    }
}