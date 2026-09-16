package com.wahidiyah.miladiyyah.data.repository

import com.wahidiyah.miladiyyah.data.local.AnnouncementEntity
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

        localRepository.clear()

        if (mapped.isNotEmpty()) {
            localRepository.save(mapped)
        }

        return mapped.size
    }
}