package com.wahidiyah.miladiyyah.feature.events

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wahidiyah.miladiyyah.core.theme.GreenDark
import com.wahidiyah.miladiyyah.core.theme.GreenSoft
import com.wahidiyah.miladiyyah.core.theme.TextMuted
import com.wahidiyah.miladiyyah.data.source.PhysicalAgenda
import com.wahidiyah.miladiyyah.data.source.PhysicalCalendar2026

private data class AgendaDisplay(
    val agenda: PhysicalAgenda,
    val dateLabel: String
)

private fun formatDateKey(value: Int): String {
    val year = value / 10000
    val month = (value / 100) % 100
    val day = value % 100

    val monthName =
        when (month) {
            1 -> "Januari"
            2 -> "Februari"
            3 -> "Maret"
            4 -> "April"
            5 -> "Mei"
            6 -> "Juni"
            7 -> "Juli"
            8 -> "Agustus"
            9 -> "September"
            10 -> "Oktober"
            11 -> "November"
            12 -> "Desember"
            else -> ""
        }

    return "$day $monthName $year"
}

private fun toDisplayAgenda(
    agenda: PhysicalAgenda
): AgendaDisplay {
    val start = formatDateKey(agenda.startDate)
    val end = formatDateKey(agenda.endDate)

    val label =
        if (agenda.startDate == agenda.endDate) {
            start
        } else {
            "$start – $end"
        }

    return AgendaDisplay(
        agenda = agenda,
        dateLabel = label
    )
}

@Composable
fun EventsScreen(
    paddingValues: PaddingValues
) {
    val agendas =
        remember {
            PhysicalCalendar2026.agendas
                .filter {
                    it.sourceColor == "GREEN"
                }
                .sortedBy {
                    it.startDate
                }
                .map(::toDisplayAgenda)
        }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8F4))
            .padding(paddingValues),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 14.dp,
            bottom = 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Kegiatan",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = GreenDark
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Agenda Wahidiyah 2026",
                style = MaterialTheme.typography.bodyMedium,
                color = TextMuted
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 14.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = GreenSoft
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            modifier = Modifier.padding(12.dp),
                            tint = GreenDark
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp)
                    ) {
                        Text(
                            text = "${agendas.size} kegiatan",
                            fontWeight = FontWeight.SemiBold,
                            color = GreenDark
                        )

                        Text(
                            text = "Bersumber dari agenda HIJAU kalender fisik Miladiyyah 2026.",
                            modifier = Modifier.padding(top = 3.dp),
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMuted
                        )
                    }
                }
            }
        }

        items(
            items = agendas,
            key = { it.agenda.id }
        ) { item ->
            AgendaCard(
                item = item
            )
        }

        item {
            Text(
                text = "Sumber: ${PhysicalCalendar2026.SOURCE_NAME} • ${PhysicalCalendar2026.SOURCE_AREA} • ${PhysicalCalendar2026.SOURCE_TIMEZONE}",
                modifier = Modifier.padding(
                    top = 4.dp,
                    start = 2.dp,
                    end = 2.dp
                ),
                style = MaterialTheme.typography.labelSmall,
                color = TextMuted
            )
        }
    }
}

@Composable
private fun AgendaCard(
    item: AgendaDisplay
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 15.dp,
                    end = 12.dp,
                    bottom = 15.dp
                ),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = GreenSoft
            ) {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = null,
                    modifier = Modifier.padding(11.dp),
                    tint = GreenDark
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = item.dateLabel,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = GreenDark
                )

                Text(
                    text = item.agenda.title,
                    modifier = Modifier.padding(top = 5.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF202820)
                )

                if (expanded) {
                    Text(
                        text = "Agenda Wahidiyah",
                        modifier = Modifier.padding(top = 9.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = TextMuted
                    )

                    Text(
                        text = "Pengingat mengikuti agenda ini, termasuk H-7 sampai H-1 dan Hari H.",
                        modifier = Modifier.padding(top = 3.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = if (expanded) {
                    "Tutup detail"
                } else {
                    "Lihat detail"
                },
                tint = TextMuted,
                modifier = Modifier.padding(
                    start = 6.dp,
                    top = 2.dp
                )
            )
        }
    }
}