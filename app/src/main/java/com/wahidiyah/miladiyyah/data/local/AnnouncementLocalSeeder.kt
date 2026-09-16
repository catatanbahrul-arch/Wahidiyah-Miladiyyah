package com.wahidiyah.miladiyyah.data.local

import android.content.Context
import com.wahidiyah.miladiyyah.data.local.room.db.DatabaseProvider
import com.wahidiyah.miladiyyah.data.local.room.entity.AnnouncementEntity
import com.wahidiyah.miladiyyah.data.repository.AnnouncementRepository

object AnnouncementLocalSeeder {

    suspend fun seedIfEmpty(context: Context) {
        val repository = AnnouncementRepository(
            DatabaseProvider.get(context).announcementDao()
        )

        if (repository.getActive().isNotEmpty()) return

        repository.save(
            listOf(
                AnnouncementEntity(
                    id = "local-demo-001",
                    title = "Selamat Datang di Wahidiyah Miladiyyah",
                    body = "Ini adalah data pengumuman lokal untuk pengujian tampilan aplikasi.",
                    publishedAt = "16 September 2026"
                ),
                AnnouncementEntity(
                    id = "local-demo-002",
                    title = "Informasi Kegiatan Jama'ah",
                    body = "Daftar kegiatan Wahidiyah ditampilkan berdasarkan data yang telah disiapkan di aplikasi.",
                    publishedAt = "16 September 2026"
                ),
                AnnouncementEntity(
                    id = "local-demo-003",
                    title = "Pengumuman Pusat",
                    body = "Tempat ini nantinya akan menerima pengumuman resmi melalui sinkronisasi data dari server.",
                    publishedAt = "16 September 2026"
                )
            )
        )
    }
}
