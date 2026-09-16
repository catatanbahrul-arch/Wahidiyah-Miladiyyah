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
import com.wahidiyah.miladiyyah.data.local.AnnouncementLocalSeeder
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
            AnnouncementLocalSeeder.seedIfEmpty(this@MainActivity)
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
