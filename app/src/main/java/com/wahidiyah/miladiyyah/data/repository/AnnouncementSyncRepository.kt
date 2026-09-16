package com.wahidiyah.miladiyyah.data.repository

import com.wahidiyah.miladiyyah.data.local.room.entity.AnnouncementEntity
import com.wahidiyah.miladiyyah.data.remote.AnnouncementApiClient

class AnnouncementSyncRepository(
    private val apiClient: AnnouncementApiClient,
    private val localRepository: AnnouncementRepository
) {
    suspend fun sync(): Int {
        val response = apiClient.fetch()

        val mapped = response.announcements.mapNotNull { item ->
            val id = item.id.trim()
            val title = item.title.trim()

            if (id.isBlank() || title.isBlank()) {
                return@mapNotNull null
            }

            val actionUrl = item.actionUrl
                ?.trim()
                ?.takeIf {
                    it.startsWith("https://") ||
                    it.startsWith("http://")
                }

            AnnouncementEntity(
                id = id,
                title = title,
                body = item.body,
                publishedAt = item.publishedAt,
                actionLabel = item.actionLabel,
                actionUrl = actionUrl,
                isActive = true
            )
        }

        /*
         * Jangan menghapus cache lokal jika server mengembalikan
         * daftar kosong. Respons kosong belum tentu berarti
         * seluruh pengumuman memang harus dihapus.
         */
        if (mapped.isEmpty()) {
            return 0
        }

        localRepository.clear()
        localRepository.save(mapped)

        return mapped.size
    }
}