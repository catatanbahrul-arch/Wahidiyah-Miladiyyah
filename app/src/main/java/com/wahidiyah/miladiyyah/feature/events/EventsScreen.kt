package com.wahidiyah.miladiyyah.feature.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wahidiyah.miladiyyah.core.theme.GreenDark
import com.wahidiyah.miladiyyah.core.theme.GreenSoft
import com.wahidiyah.miladiyyah.core.theme.TextMuted

@Composable
fun EventsScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8F4))
            .padding(paddingValues)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            "Kegiatan",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = GreenDark
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 36.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = GreenSoft
                ) {
                    Icon(
                        Icons.Default.Event,
                        contentDescription = null,
                        modifier = Modifier.padding(18.dp),
                        tint = GreenDark
                    )
                }
                Text(
                    "Belum ada kegiatan",
                    modifier = Modifier.padding(top = 14.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Agenda dari kalender sumber akan muncul di sini setelah data diverifikasi.",
                    modifier = Modifier.padding(top = 6.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted
                )
            }
        }

        Text(
            "Pengingat kegiatan nantinya mengikuti agenda yang telah disahkan, termasuk H-7 sampai H-1 dan Hari H.",
            style = MaterialTheme.typography.bodySmall,
            color = TextMuted
        )
    }
}
