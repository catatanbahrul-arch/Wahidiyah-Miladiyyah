# Wahidiyah Miladiyyah - Android Starter V1

Starter project Android Studio untuk fondasi aplikasi Kalender Jama'ah Wahidiyah.

## Sudah tersedia
- Kotlin + Jetpack Compose
- Identitas hijau pupus
- Beranda dasar
- Logo sementara terpusat
- Bottom navigation: Beranda | Kalender | Kegiatan | Media | Menu
- Quick menu 2 kolom
- Empty state, tanpa konten dummy
- Dependensi fondasi Room, DataStore, dan WorkManager

## Belum diaktifkan
- Data kalender fisik sumber
- Jam shalat/imsak
- Reminder Dana Box
- Reminder agenda H-7 s.d. H-1 + Hari H
- Google Apps Script API
- Firebase/FCM
- Deteksi YouTube Live
- Asset logo master final

## Cara membuka
1. Extract ZIP.
2. Android Studio -> Open -> pilih folder `WahidiyahMiladiyyah`.
3. Tunggu Gradle Sync selesai.
4. Jalankan pada emulator/perangkat Android.
5. Minimum Android: API 24.

Catatan:
Project ini sengaja dibuat sebagai skeleton build pertama. Data sumber kalender dan logo final jangan diisi dengan data buatan.


## V2 UI
V2 memperhalus Beranda tanpa mengubah arsitektur/data:
- kartu header lebih rapi
- hierarki Pengumuman/Hari Ini/Menu Cepat/Agenda/Konten
- warna hijau pupus lebih konsisten
- kartu dan spacing lebih siap untuk layar Android
- tetap memakai empty state, tanpa data palsu

Tahap berikutnya: UI Kalender dan Kegiatan, lalu memasukkan data sumber kalender.


## V3 UI Kalender & Kegiatan
V3 menambahkan fondasi tampilan:
- Kalender Miladiyyah
- Kegiatan
- Empty state yang aman
- Penanda bahwa data berasal dari kalender fisik
- Tidak ada tanggal/agenda buatan
- Struktur siap menerima data Room pada tahap berikutnya

Fungsi Beranda, navigasi, dan dependensi sebelumnya tidak diubah.


## V4 Data Foundation
V4 menambahkan fondasi penyimpanan lokal tanpa memasukkan data sumber:
- Room database
- CalendarDayEntity
- AgendaEntity
- PrayerTimeEntity
- RegionRuleEntity
- NotificationPreferencesEntity
- DAO dasar
- DataStore untuk preferensi perangkat
- KAPT + Room compiler

Tidak ada tanggal, jam shalat, atau agenda buatan yang dimasukkan.


## V5 Logo Locked
Logo aplikasi dikunci ke `logo_wahidiyah_full_master.svg`.
Logo selalu dipakai utuh, termasuk seluruh sinar/sunburst. Tidak memakai versi compact atau crop.
Untuk rendering Compose digunakan PNG transparan resolusi tinggi.
Master SVG disimpan di `app/src/main/res/raw/`.
Launcher menggunakan full logo asset.
Tidak ada perubahan pada fungsi Room, DataStore, navigation, atau fitur lain.
