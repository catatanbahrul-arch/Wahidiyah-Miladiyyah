package com.wahidiyah.miladiyyah.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.wahidiyah.miladiyyah.feature.calendar.CalendarScreen
import com.wahidiyah.miladiyyah.feature.events.EventsScreen
import com.wahidiyah.miladiyyah.feature.home.HomeScreen
import com.wahidiyah.miladiyyah.feature.media.MediaScreen
import com.wahidiyah.miladiyyah.feature.menu.MenuScreen

private data class NavItem(
    val label: String,
    val icon: @Composable () -> Unit
)

@Composable
fun AppNavHost() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val items = listOf(
        NavItem("Beranda") { Icon(Icons.Default.Home, null) },
        NavItem("Kalender") { Icon(Icons.Default.CalendarMonth, null) },
        NavItem("Kegiatan") { Icon(Icons.Default.Event, null) },
        NavItem("Media") { Icon(Icons.Default.PlayCircle, null) },
        NavItem("Menu") { Icon(Icons.Default.Menu, null) }
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = item.icon,
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        when (selectedIndex) {
            0 -> HomeScreen(paddingValues)
            1 -> CalendarScreen(paddingValues)
            2 -> EventsScreen(paddingValues)
            3 -> MediaScreen(paddingValues)
            else -> MenuScreen(paddingValues)
        }
    }
}
