package com.wahidiyah.miladiyyah.data.sync

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

object AnnouncementSyncScheduler {

    private const val UNIQUE_WORK_NAME =
        "wahidiyah_announcement_periodic_sync"

    private const val REPEAT_INTERVAL_HOURS = 6L

    /**
     * Menjadwalkan sinkronisasi pengumuman berkala.
     *
     * Scheduler ini hanya dipanggil ketika endpoint resmi
     * Google Apps Script sudah tersedia.
     */
    fun schedule(
        context: Context,
        endpointUrl: String
    ) {
        val endpoint = endpointUrl.trim()

        require(endpoint.startsWith("https://")) {
            "Announcement endpoint wajib menggunakan HTTPS."
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val inputData = workDataOf(
            AnnouncementSyncWorker.KEY_ENDPOINT_URL to endpoint
        )

        val request = PeriodicWorkRequestBuilder<
            AnnouncementSyncWorker
        >(
            REPEAT_INTERVAL_HOURS,
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        WorkManager.getInstance(context.applicationContext)
            .enqueueUniquePeriodicWork(
                UNIQUE_WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }

    /**
     * Membatalkan sinkronisasi pengumuman berkala.
     */
    fun cancel(context: Context) {
        WorkManager.getInstance(context.applicationContext)
            .cancelUniqueWork(UNIQUE_WORK_NAME)
    }
}