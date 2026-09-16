package com.wahidiyah.miladiyyah.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class AnnouncementApiClient(
    private val endpointUrl: String
) {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    suspend fun fetch(): AnnouncementApiResponse = withContext(Dispatchers.IO) {
        require(endpointUrl.startsWith("https://")) {
            "Endpoint Announcement API wajib menggunakan HTTPS."
        }

        val connection =
            (URL(endpointUrl).openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                connectTimeout = 15_000
                readTimeout = 15_000
                setRequestProperty("Accept", "application/json")
            }

        try {
            val status = connection.responseCode

            if (status !in 200..299) {
                throw IllegalStateException(
                    "Announcement API gagal. HTTP $status"
                )
            }

            val body = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    Charsets.UTF_8
                )
            ).use { it.readText() }

            json.decodeFromString<AnnouncementApiResponse>(body)
        } finally {
            connection.disconnect()
        }
    }
}