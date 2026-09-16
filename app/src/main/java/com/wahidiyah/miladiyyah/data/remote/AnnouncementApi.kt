package com.wahidiyah.miladiyyah.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementApiResponse(
    val announcements: List<AnnouncementApiItem> = emptyList()
)

@Serializable
data class AnnouncementApiItem(
    val id: String,
    val title: String,
    val body: String = "",
    val publishedAt: String = "",
    val actionLabel: String? = null,
    val actionUrl: String? = null
)