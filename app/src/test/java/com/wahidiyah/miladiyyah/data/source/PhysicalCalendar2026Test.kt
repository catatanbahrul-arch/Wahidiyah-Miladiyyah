package com.wahidiyah.miladiyyah.data.source

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicalCalendar2026Test {

    @Test
    fun agendaCount_isExactly36() {
        assertEquals(
            PhysicalCalendar2026.EXPECTED_GREEN_AGENDA_COUNT,
            PhysicalCalendar2026.agendas.size
        )
    }

    @Test
    fun agendaIds_areUnique() {
        val ids = PhysicalCalendar2026.agendas.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun everyAgenda_isGreenSource() {
        assertTrue(
            PhysicalCalendar2026.agendas.all {
                it.sourceColor == "GREEN"
            }
        )
    }

    @Test
    fun januaryFirst_containsThreeGreenAgendaRecords() {
        val result = PhysicalCalendar2026.agendaFor(20260101)
        assertEquals(3, result.size)
    }

    @Test
    fun crossMonthAgenda_isAvailableOnBothEnds() {
        assertTrue(
            PhysicalCalendar2026.agendaFor(20260430)
                .any { it.id == "agenda-15" }
        )

        assertTrue(
            PhysicalCalendar2026.agendaFor(20260615)
                .any { it.id == "agenda-15" }
        )
    }

    @Test
    fun decemberCrossMonthAgenda_isAvailableOnBothEnds() {
        assertTrue(
            PhysicalCalendar2026.agendaFor(20261105)
                .any { it.id == "agenda-29" }
        )

        assertTrue(
            PhysicalCalendar2026.agendaFor(20261214)
                .any { it.id == "agenda-29" }
        )
    }

    @Test
    fun redCalendarEntries_areNotStoredAsAgenda() {
        val forbiddenDates = listOf(
            20260217,
            20260319,
            20260321,
            20260322,
            20260501,
            20260514,
            20260527,
            20260531,
            20260601,
            20260617,
            20260817,
            20260825,
            20261225
        )

        forbiddenDates.forEach { date ->
            val titles = PhysicalCalendar2026.agendaFor(date)
                .map { it.title }

            assertTrue(
                "Unexpected red/general calendar data on $date",
                titles.none {
                    it.contains("Tahun Baru Imlek", ignoreCase = true) ||
                        it.contains("Hari Buruh", ignoreCase = true) ||
                        it.contains("Natal", ignoreCase = true)
                }
            )
        }
    }

    @Test
    fun prayerTime_existsForEveryDayOf2026() {
        val daysPerMonth = mapOf(
            1 to 31,
            2 to 28,
            3 to 31,
            4 to 30,
            5 to 31,
            6 to 30,
            7 to 31,
            8 to 31,
            9 to 30,
            10 to 31,
            11 to 30,
            12 to 31
        )

        daysPerMonth.forEach { (month, maxDay) ->
            for (day in 1..maxDay) {
                assertNotNull(
                    "Prayer time missing for $month-$day",
                    PhysicalCalendar2026.prayerTimeFor(month, day)
                )
            }
        }
    }

    @Test
    fun prayerRecords_useWibSource() {
        assertEquals(
            "Waktu Indonesia Barat",
            PhysicalCalendar2026.SOURCE_TIMEZONE
        )
    }
}