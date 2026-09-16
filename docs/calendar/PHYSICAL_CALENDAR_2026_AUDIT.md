# AUDIT SUMBER KALENDER FISIK 2026

## Source of Truth

Nama sumber:
`foto fisik kalender Miladiyyah.zip`

SHA-256:
`b0c5be294baae7d5d6f13d9b67178e0a6f6ea712a8188fc939f62fb1c29c41e3`

Jumlah foto sumber:
`16`

## Aturan Data

Semua data kalender aplikasi wajib mengacu kepada kalender fisik yang dikirim pada project.

### Jadwal ibadah

Yang diambil dari tabel fisik:

- Imsak
- Shubuh
- Terbit
- Dhuha
- Dhuhur
- Ashar
- Maghrib
- Isya'

Wilayah dasar:
`Kediri & sekitarnya`

Zona waktu sumber:
`Waktu Indonesia Barat`

Nilai waktu tidak dihitung ulang.

## Aturan Agenda

Agenda reminder hanya diambil dari:

**keterangan tulisan warna HIJAU pada bagian agenda bawah kanan setiap halaman bulan.**

Keterangan warna MERAH tidak dimasukkan sebagai reminder.

## Hasil Audit

Agenda hijau terverifikasi:
`36 record`

Rentang jadwal sholat:
`121+ record` dalam representasi source range.

Catatan:
Jumlah record source range dapat berubah jika format data dinormalisasi pada tahap Room, tetapi nilai per tanggal harus tetap identik dengan tabel fisik.

## Agenda Lintas Bulan

Dua rentang lintas bulan wajib dipertahankan:

- 30 April 2026 s/d 15 Juni 2026
- 5 November 2026 s/d 14 Desember 2026

Jangan dipotong menjadi agenda baru per bulan.

## Warna Merah

Agenda/penanda umum berwarna merah tidak menjadi data reminder.

Tidak boleh ada proses otomatis yang memasukkan seluruh tulisan bawah kalender tanpa pemeriksaan warna.

## Konversi Daerah

Kalender fisik juga menyediakan bagian:

`KONVERSI WAKTU UNTUK DAERAH DILUAR KEDIRI`

Sebagian daftar pengurangan terlihat jelas pada sumber, tetapi daftar lengkap `DAERAH YANG DITAMBAH` belum dimasukkan ke engine pada tahap ini.

Keputusan audit:
**JANGAN MENGISI DATA YANG BELUM TERVERIFIKASI.**

Konversi wilayah akan menjadi tahap terpisah setelah seluruh tabel dapat diekstraksi dan diaudit 100%.

## Larangan

Source berikut tidak boleh menggantikan kalender fisik:

- kalkulator waktu sholat online
- API waktu sholat umum
- kalender internet
- data pihak ketiga
- perhitungan astronomi otomatis

Mereka hanya boleh dipakai sebagai pembanding internal bila diperlukan, bukan sebagai source aplikasi.

## Audit UI

CalendarScreen harus:

- menampilkan kalender Miladiyyah 2026
- menampilkan indikator agenda hijau
- menampilkan detail agenda saat tanggal dipilih
- menampilkan waktu ibadah tanggal terpilih
- menyebut source kalender fisik
- tidak membuat agenda dummy

## Status

Source data:
IMPLEMENTED

Calendar UI:
IMPLEMENTED

Unit test:
IMPLEMENTED

Room integration:
NEXT

Notification scheduling:
NEXT

Regional conversion:
PENDING FULL TRANSCRIPTION