package com.wahidiyah.miladiyyah.data.source

import com.wahidiyah.miladiyyah.feature.events.reminder.AgendaReminderPlanner
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AgendaReminderPlanTest {

    @Test
    fun septemberDecemberAgendaCount_isTen() {

        assertEquals(
            10,
            PhysicalCalendarSepDec2026.agendas.size
        )
    }

    @Test
    fun everyAgenda_isGreen() {

        assertTrue(
            PhysicalCalendarSepDec2026.agendas.all {
                it.sourceColor == "GREEN"
            }
        )
    }

    @Test
    fun septemberAgenda_isCorrect() {

        val agenda = PhysicalCalendarSepDec2026.agendas
            .first { it.id == "agenda-27" }

        assertEquals(
            20260903,
            agenda.startDate
        )

        assertEquals(
            20260903,
            agenda.endDate
        )
    }

    @Test
    fun octoberAgenda_isCorrect() {

        val agenda = PhysicalCalendarSepDec2026.agendas
            .first { it.id == "agenda-28" }

        assertEquals(
            20261008,
            agenda.startDate
        )

        assertEquals(
            20261008,
            agenda.endDate
        )
    }

    @Test
    fun novemberDecemberLongAgenda_isPreservedAsRange() {

        val agenda = PhysicalCalendarSepDec2026.agendas
            .first { it.id == "agenda-29" }

        assertEquals(
            20261105,
            agenda.startDate
        )

        assertEquals(
            20261214,
            agenda.endDate
        )
    }

    @Test
    fun multiDayDecemberAgenda_isPreservedAsRange() {

        val agenda = PhysicalCalendarSepDec2026.agendas
            .first { it.id == "agenda-35" }

        assertEquals(
            20261224,
            agenda.startDate
        )

        assertEquals(
            20261228,
            agenda.endDate
        )
    }

    @Test
    fun eachGreenAgenda_generatesEightReminders() {

        PhysicalCalendarSepDec2026.agendas.forEach { agenda ->

            val reminders =
                AgendaReminderPlanner.planFor(agenda)

            assertEquals(
                8,
                reminders.size
            )

            val offsets =
                reminders.map { it.offsetDays }

            assertEquals(
                listOf(7, 6, 5, 4, 3, 2, 1, 0),
                offsets
            )
        }
    }

    @Test
    fun totalReminderPlans_isEighty() {

        val plans =
            AgendaReminderPlanner.planForAll(
                PhysicalCalendarSepDec2026.agendas
            )

        assertEquals(
            80,
            plans.size
        )
    }

    @Test
    fun december14StartReminder_isDecember7() {

        val agenda =
            PhysicalCalendarSepDec2026.agendas
                .first { it.id == "agenda-33" }

        val reminders =
            AgendaReminderPlanner.planFor(agenda)

        val h7 =
            reminders.first {
                it.offsetDays == 7
            }

        assertEquals(
            "2026-12-07",
            h7.reminderDate
        )

        assertEquals(
            "2026-12-14",
            h7.eventDate
        )
    }

    @Test
    fun november5StartReminder_isOctober29() {

        val agenda =
            PhysicalCalendarSepDec2026.agendas
                .first { it.id == "agenda-29" }

        val reminders =
            AgendaReminderPlanner.planFor(agenda)

        val h7 =
            reminders.first {
                it.offsetDays == 7
            }

        assertEquals(
            "2026-10-29",
            h7.reminderDate
        )

        assertEquals(
            "2026-11-05",
            h7.eventDate
        )
    }

    @Test
    fun redAgenda_isNeverScheduled() {

        val redAgenda =
            PhysicalAgenda(
                id = "test-red",
                startDate = 20261225,
                endDate = 20261225,
                title = "RED TEST",
                sourceColor = "RED"
            )

        val reminders =
            AgendaReminderPlanner.planFor(redAgenda)

        assertTrue(
            reminders.isEmpty()
        )
    }
}