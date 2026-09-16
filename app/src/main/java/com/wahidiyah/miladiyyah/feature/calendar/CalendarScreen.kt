package com.wahidiyah.miladiyyah.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wahidiyah.miladiyyah.core.theme.GreenDark
import com.wahidiyah.miladiyyah.core.theme.GreenSoft
import com.wahidiyah.miladiyyah.core.theme.TextMuted
import com.wahidiyah.miladiyyah.data.source.PhysicalAgenda
import com.wahidiyah.miladiyyah.data.source.PhysicalCalendar2026
import com.wahidiyah.miladiyyah.data.source.PhysicalPrayerTime
import java.util.Calendar

private val MonthNames = listOf(
    "Januari", "Februari", "Maret", "April", "Mei", "Juni",
    "Juli", "Agustus", "September", "Oktober", "November", "Desember"
)

private val DayNames = listOf(
    "Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min"
)

@Composable
fun CalendarScreen(
    paddingValues: PaddingValues
) {
    val now = remember { Calendar.getInstance() }

    var month by remember { mutableIntStateOf(now.get(Calendar.MONTH) + 1) }
    var year by remember { mutableIntStateOf(2026) }
    var selectedDay by remember {
        mutableIntStateOf(
            if (now.get(Calendar.YEAR) == 2026) {
                now.get(Calendar.DAY_OF_MONTH)
            } else {
                1
            }
        )
    }

    val daysInMonth = daysInMonth(year, month)
    val firstColumn = firstColumnMondayBased(year, month)

    val selectedDateKey = year * 10000 + month * 100 + selectedDay
    val selectedAgendas = PhysicalCalendar2026.agendaFor(selectedDateKey)
    val selectedPrayer = PhysicalCalendar2026.prayerTimeFor(month, selectedDay)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8F4))
            .padding(paddingValues)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Kalender Miladiyyah",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = GreenDark
                )
                Text(
                    "Kalender $year",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted
                )
            }

            IconButton(
                onClick = {
                    year = 2026
                    month = 1
                    selectedDay = 1
                }
            ) {
                Icon(
                    Icons.Default.Today,
                    contentDescription = "Ke Januari 2026"
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        enabled = month > 1,
                        onClick = {
                            if (month > 1) {
                                month--
                                selectedDay = 1
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.ChevronLeft,
                            contentDescription = "Bulan sebelumnya"
                        )
                    }

                    Text(
                        text = "${MonthNames[month - 1]} $year",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold,
                        color = GreenDark
                    )

                    IconButton(
                        enabled = month < 12,
                        onClick = {
                            if (month < 12) {
                                month++
                                selectedDay = 1
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = "Bulan berikutnya"
                        )
                    }
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    DayNames.forEach { dayName ->
                        Text(
                            dayName,
                            modifier = Modifier.weight(1f),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = TextMuted
                        )
                    }
                }

                for (week in 0 until 6) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        for (column in 0 until 7) {

                            val cellIndex = week * 7 + column
                            val day = cellIndex - firstColumn + 1

                            if (day in 1..daysInMonth) {
                                val dateKey = year * 10000 + month * 100 + day
                                val agendas = PhysicalCalendar2026.agendaFor(dateKey)
                                val isSelected = day == selectedDay
                                val isToday =
                                    now.get(Calendar.YEAR) == year &&
                                        now.get(Calendar.MONTH) + 1 == month &&
                                        now.get(Calendar.DAY_OF_MONTH) == day

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(2.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .clickable {
                                            selectedDay = day
                                        }
                                        .background(
                                            when {
                                                isSelected -> GreenSoft
                                                else -> Color.Transparent
                                            }
                                        )
                                        .padding(vertical = 7.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            day.toString(),
                                            fontWeight = when {
                                                isToday || agendas.isNotEmpty() -> FontWeight.Bold
                                                else -> FontWeight.Normal
                                            },
                                            color = when {
                                                isSelected -> GreenDark
                                                isToday -> GreenDark
                                                else -> MaterialTheme.colorScheme.onSurface
                                            }
                                        )

                                        if (agendas.isNotEmpty()) {
                                            Spacer(Modifier.height(3.dp))
                                            Box(
                                                modifier = Modifier
                                                    .size(5.dp)
                                                    .clip(CircleShape)
                                                    .background(GreenDark)
                                            )
                                        }
                                    }
                                }
                            } else {
                                Spacer(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        Text(
            "Tanggal $selectedDay ${MonthNames[month - 1]} $year",
            fontWeight = FontWeight.Bold,
            color = GreenDark
        )

        PrayerCard(selectedPrayer)

        AgendaCard(selectedAgendas)

        Text(
            "Sumber: ${PhysicalCalendar2026.SOURCE_NAME} • ${PhysicalCalendar2026.SOURCE_AREA} • ${PhysicalCalendar2026.SOURCE_TIMEZONE}",
            style = MaterialTheme.typography.labelSmall,
            color = TextMuted
        )
    }
}

@Composable
private fun PrayerCard(
    prayer: PhysicalPrayerTime?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                "Jadwal Sholat",
                fontWeight = FontWeight.Bold,
                color = GreenDark
            )

            if (prayer == null) {
                Text(
                    "Data jadwal sholat tidak tersedia untuk tanggal ini.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted
                )
            } else {
                PrayerLine("Imsak", prayer.imsak)
                PrayerLine("Shubuh", prayer.subuh)
                PrayerLine("Terbit", prayer.terbit)
                PrayerLine("Dhuha", prayer.dhuha)
                PrayerLine("Dhuhur", prayer.dzuhur)
                PrayerLine("Ashar", prayer.ashar)
                PrayerLine("Maghrib", prayer.maghrib)
                PrayerLine("Isya'", prayer.isya)
            }
        }
    }
}

@Composable
private fun PrayerLine(
    label: String,
    time: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodySmall,
            color = TextMuted
        )
        Text(
            time,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun AgendaCard(
    agendas: List<PhysicalAgenda>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (agendas.isEmpty()) {
                Color.White
            } else {
                GreenSoft
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "Kegiatan Wahidiyah",
                fontWeight = FontWeight.Bold,
                color = GreenDark
            )

            if (agendas.isEmpty()) {
                Text(
                    "Tidak ada agenda Wahidiyah pada tanggal ini.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted
                )
            } else {
                agendas.forEach { agenda ->
                    Text(
                        "• ${agenda.title}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

private fun daysInMonth(
    year: Int,
    month: Int
): Int {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)
    calendar.add(Calendar.DAY_OF_MONTH, -1)
    return calendar.get(Calendar.DAY_OF_MONTH)
}

private fun firstColumnMondayBased(
    year: Int,
    month: Int
): Int {
    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, 1)

    return when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> 0
        Calendar.TUESDAY -> 1
        Calendar.WEDNESDAY -> 2
        Calendar.THURSDAY -> 3
        Calendar.FRIDAY -> 4
        Calendar.SATURDAY -> 5
        Calendar.SUNDAY -> 6
        else -> 0
    }
}