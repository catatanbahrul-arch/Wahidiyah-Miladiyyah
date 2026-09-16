package com.wahidiyah.miladiyyah

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.wahidiyah.miladiyyah.core.navigation.AppNavHost
import com.wahidiyah.miladiyyah.data.sync.AnnouncementSyncScheduler
import com.wahidiyah.miladiyyah.core.notification.AgendaNotificationHelper
import com.wahidiyah.miladiyyah.core.notification.PrayerNotificationHelper
import com.wahidiyah.miladiyyah.core.notification.PrayerNotificationScheduler
import com.wahidiyah.miladiyyah.core.notification.DanaBoxNotificationHelper
import com.wahidiyah.miladiyyah.core.notification.DanaBoxNotificationScheduler
import com.wahidiyah.miladiyyah.core.notification.TasyafuanNotificationHelper
import com.wahidiyah.miladiyyah.core.notification.TasyafuanNotificationScheduler
import com.wahidiyah.miladiyyah.core.notification.AgendaReminderCoordinator
import com.wahidiyah.miladiyyah.core.theme.WahidiyahTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    companion object {
        private const val ANNOUNCEMENT_ENDPOINT =
            "https://script.google.com/macros/s/AKfycbz58q9tgjq97JlFopa7xJdVNrdtaroPvtOcYabZGNCxVBHdFOHRFLS9j3i2CsOSyMS2/exec"
    }

    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            /*
             * Scheduler tetap berjalan setelah hasil permission.
             *
             * Jika user menolak permission, receiver notification
             * tidak akan menampilkan notifikasi.
             */
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AgendaNotificationHelper.ensureChannel(this)
        PrayerNotificationHelper.ensureChannel(this)

        requestNotificationPermissionIfNeeded()

        lifecycleScope.launch {

            AnnouncementSyncScheduler.schedule(
                this@MainActivity,
                ANNOUNCEMENT_ENDPOINT
            )

            AnnouncementSyncScheduler.syncNow(
                this@MainActivity,
                ANNOUNCEMENT_ENDPOINT
            )

            AgendaReminderCoordinator.reschedule(this@MainActivity)
            PrayerNotificationScheduler.scheduleUpcoming(this@MainActivity)
            DanaBoxNotificationHelper.ensureChannel(this@MainActivity)
            TasyafuanNotificationHelper.ensureChannel(this@MainActivity)
            DanaBoxNotificationScheduler.scheduleUpcoming(this@MainActivity)
            TasyafuanNotificationScheduler.scheduleUpcoming(this@MainActivity)
        }

        setContent {
            WahidiyahTheme {
                AppNavHost()
            }
        }
    }

    private fun requestNotificationPermissionIfNeeded() {

        if (
            android.os.Build.VERSION.SDK_INT >=
            android.os.Build.VERSION_CODES.TIRAMISU
        ) {

            if (
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                notificationPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }
    }
}
