package com.wahidiyah.miladiyyah.feature.events.reminder

import com.wahidiyah.miladiyyah.data.source.PhysicalAgenda
import java.util.Calendar

data class AgendaReminder(
    val id: String,
    val agendaId: String,
    val eventDate: String,
    val reminderDate: String,
    val offsetDays: Int
)

object AgendaReminderPlanner {

    private val supportedOffsets = listOf(
        7, 6, 5, 4, 3, 2, 1, 0
    )

    fun planFor(
        agenda: PhysicalAgenda
    ): List<AgendaReminder> {

        if (agenda.sourceColor != "GREEN") {
            return emptyList()
        }

        return supportedOffsets.map { offset ->

            val reminderDate = shiftDate(
                agenda.startDate,
                -offset
            )

            AgendaReminder(
                id = "${agenda.id}-h$offset",
                agendaId = agenda.id,
                eventDate = formatDate(agenda.startDate),
                reminderDate = formatDate(reminderDate),
                offsetDays = offset
            )
        }
    }

    fun planForAll(
        agendas: List<PhysicalAgenda>
    ): List<AgendaReminder> =
        agendas
            .filter { it.sourceColor == "GREEN" }
            .flatMap(::planFor)

    private fun parseDate(value: Int): Calendar {

        val year = value / 10000
        val month = (value / 100) % 100
        val day = value % 100

        return Calendar.getInstance().apply {
            clear()
            set(
                Calendar.YEAR,
                year
            )
            set(
                Calendar.MONTH,
                month - 1
            )
            set(
                Calendar.DAY_OF_MONTH,
                day
            )
        }
    }

    private fun shiftDate(
        value: Int,
        amount: Int
    ): Int {

        val calendar = parseDate(value)

        calendar.add(
            Calendar.DAY_OF_MONTH,
            amount
        )

        return (
            calendar.get(Calendar.YEAR) * 10000
                + (calendar.get(Calendar.MONTH) + 1) * 100
                + calendar.get(Calendar.DAY_OF_MONTH)
            )
    }

    private fun formatDate(
        value: Int
    ): String =
        "%04d-%02d-%02d".format(
            value / 10000,
            (value / 100) % 100,
            value % 100
        )
}