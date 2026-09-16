package com.wahidiyah.miladiyyah.data.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wahidiyah.miladiyyah.data.remote.AnnouncementApiClient
import com.wahidiyah.miladiyyah.data.repository.AnnouncementRepository
import com.wahidiyah.miladiyyah.data.repository.AnnouncementSyncRepository
import com.wahidiyah.miladiyyah.data.local.room.DatabaseProvider

class AnnouncementSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val endpointUrl = inputData.getString(KEY_ENDPOINT_URL)
            ?.trim()
            .orEmpty()

        /*
         * Endpoint belum dikonfigurasi.
         * Jangan melakukan request kosong dan jangan mengganggu
         * cache lokal yang sudah ada.
         */
        if (endpointUrl.isBlank()) {
            return Result.success()
        }

        return try {
            val database = DatabaseProvider.get(applicationContext)

            val localRepository = AnnouncementRepository(
                database.announcementDao()
            )

            val apiClient = AnnouncementApiClient(
                endpointUrl = endpointUrl
            )

            val syncRepository = AnnouncementSyncRepository(
                apiClient = apiClient,
                localRepository = localRepository
            )

            syncRepository.sync()

            Result.success()
        } catch (exception: Exception) {
            /*
             * Error jaringan/API bersifat sementara.
             * WorkManager akan mencoba kembali sesuai backoff.
             */
            Result.retry()
        }
    }

    companion object {
        const val KEY_ENDPOINT_URL = "announcement_endpoint_url"
    }
}