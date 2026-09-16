package com.wahidiyah.miladiyyah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wahidiyah.miladiyyah.core.navigation.AppNavHost
import com.wahidiyah.miladiyyah.core.theme.WahidiyahTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WahidiyahTheme {
                AppNavHost()
            }
        }
    }
}
