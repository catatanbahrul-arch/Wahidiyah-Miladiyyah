package com.wahidiyah.miladiyyah.feature.home

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wahidiyah.miladiyyah.R
import com.wahidiyah.miladiyyah.data.local.room.db.DatabaseProvider
import com.wahidiyah.miladiyyah.data.local.room.entity.AnnouncementEntity
import com.wahidiyah.miladiyyah.data.repository.AnnouncementRepository
import com.wahidiyah.miladiyyah.core.theme.GreenDark
import com.wahidiyah.miladiyyah.core.theme.GreenPale
import com.wahidiyah.miladiyyah.core.theme.GreenSoft
import com.wahidiyah.miladiyyah.core.theme.TextMuted

private data class QuickMenu(val title: String, val icon: ImageVector)

@Composable
fun HomeScreen(paddingValues: PaddingValues) {
    val context = LocalContext.current
    var announcements by remember { mutableStateOf<List<AnnouncementEntity>>(emptyList()) }

    LaunchedEffect(Unit) {
        announcements = AnnouncementRepository(
            DatabaseProvider.get(context).announcementDao()
        ).getActive()
    }

    val quick = listOf(
        QuickMenu("Kalender", Icons.Default.CalendarMonth),
        QuickMenu("Kegiatan", Icons.Default.Event),
        QuickMenu("Dana Box", Icons.Default.Favorite),
        QuickMenu("Shalat", Icons.Default.Schedule),
        QuickMenu("Live", Icons.Default.LiveTv),
        QuickMenu("Media", Icons.Default.PlayArrow)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenPale)
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            color = GreenSoft
        ) {
            Column(
                modifier = Modifier.padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_wahidiyah_full),
                    contentDescription = "Logo Wahidiyah Miladiyyah",
                    modifier = Modifier.size(118.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Wahidiyah Miladiyyah",
                    style = MaterialTheme.typography.titleMedium,
                    color = GreenDark,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Kalender & informasi Jama'ah Wahidiyah",
                    style = MaterialTheme.typography.bodySmall,
                    color = GreenDark
                )
            }
        }

        SectionTitle("Pengumuman")
        if (announcements.isEmpty()) {
            EmptyCard("Belum ada pengumuman.")
        } else {
            announcements.forEach { announcement ->
                AnnouncementCard(announcement)
            }
        }

        SectionTitle("Hari Ini")
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    color = GreenSoft
                ) {
                    Icon(
                        Icons.Default.CalendarMonth,
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp),
                        tint = GreenDark
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text("Kalender Miladiyyah", fontWeight = FontWeight.SemiBold)
                    Text(
                        "Data sumber kalender akan dihubungkan.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }
        }

        SectionTitle("Menu Cepat")
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth().height(246.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            userScrollEnabled = false
        ) {
            items(quick) { QuickMenuCard(it) }
        }

        SectionTitle("Agenda Terdekat")
        EmptyCard("Belum ada kegiatan terjadwal.")

        SectionTitle("Konten Terbaru")
        EmptyCard("Belum ada konten tersedia.")
        Spacer(Modifier.height(6.dp))
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = GreenDark
    )
}

@Composable
private fun QuickMenuCard(item: QuickMenu) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 13.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(38.dp).clip(CircleShape),
                color = GreenSoft
            ) {
                Icon(
                    item.icon,
                    contentDescription = item.title,
                    modifier = Modifier.padding(9.dp),
                    tint = GreenDark
                )
            }
            Text(
                item.title,
                modifier = Modifier.padding(start = 9.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun AnnouncementCard(item: AnnouncementEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                item.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = GreenDark
            )

            if (item.body.isNotBlank()) {
                Text(
                    item.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextMuted
                )
            }

            if (item.publishedAt.isNotBlank()) {
                Text(
                    item.publishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextMuted
                )
            }
        }
    }
}

@Composable
private fun EmptyCard(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White)
    ) {
        Text(
            text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = TextMuted
        )
    }
}
