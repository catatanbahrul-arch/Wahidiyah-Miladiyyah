package com.wahidiyah.miladiyyah.data.source

object PhysicalCalendarSepDec2026 {

    /**
     * Scope aktif project saat ini:
     * September 2026 sampai Desember 2026.
     *
     * Hanya agenda HIJAU yang dipakai.
     * Agenda MERAH/general tidak dimasukkan.
     */
    val agendas: List<PhysicalAgenda> =
        PhysicalCalendar2026.agendas.filter {
            val start = it.startDate

            start in 20260901..20261231 ||
                it.endDate in 20260901..20261231 ||
                (
                    it.startDate < 20260901 &&
                        it.endDate > 20261231
                    )
        }
}