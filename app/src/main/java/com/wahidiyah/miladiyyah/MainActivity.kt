package com.wahidiyah.miladiyyah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.wahidiyah.miladiyyah.core.navigation.AppNavHost
import com.wahidiyah.miladiyyah.core.notification.AgendaNotificationHelper
import com.wahidiyah.miladiyyah.core.notification.AgendaReminderCoordinator
import com.wahidiyah.miladiyyah.core.theme.WahidiyahTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AgendaNotificationHelper.ensureChannel(this)

        lifecycleScope.launch {
            AgendaReminderCoordinator.reschedule(this@MainActivity)
        }

        setContent {
            WahidiyahTheme {
                AppNavHost()
            }
        }
    }
}