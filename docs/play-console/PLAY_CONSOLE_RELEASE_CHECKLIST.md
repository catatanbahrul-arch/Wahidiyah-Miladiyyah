# Wahidiyah Miladiyyah
## Google Play Console Release Checklist

Dokumen ini adalah checklist internal untuk persiapan publikasi aplikasi.

---

## 1. Identitas Aplikasi

- [x] Nama aplikasi: Wahidiyah Miladiyyah
- [x] Application ID: `com.wahidiyah.miladiyyah`
- [x] Target SDK: API 36
- [x] Compile SDK: API 36
- [x] Version Code saat ini: 1
- [x] Version Name saat ini: 1.0.0
- [x] Release AAB berhasil dibuat oleh GitHub Actions
- [x] Release AAB berhasil diverifikasi tanda tangannya

---

## 2. Format Release

Google Play menggunakan Android App Bundle (AAB) sebagai format publikasi.

Release artifact yang dipakai:

`app/build/outputs/bundle/release/app-release.aab`

Jangan menggunakan APK debug sebagai file produksi.

---

## 3. Play Store Listing

### App name
Wahidiyah Miladiyyah

### Short description
Akan difinalkan sebelum listing dipublikasikan.

### Full description
Akan difinalkan setelah fitur inti aplikasi selesai dan diuji.

### App icon
- [ ] Final app icon
- [ ] Tidak memakai placeholder
- [ ] Identitas logo Wahidiyah tetap konsisten

### Feature graphic
- [ ] Final
- [ ] Mengikuti identitas visual Wahidiyah Miladiyyah
- [ ] Tidak memakai aset berhak cipta tanpa izin

### Screenshots
- [ ] Beranda
- [ ] Kalender
- [ ] Kegiatan
- [ ] Dana Box
- [ ] Jadwal Shalat
- [ ] Media
- [ ] Menu

Screenshot final dibuat setelah UI dan fungsi utama stabil.

---

## 4. Privacy Policy

Status saat ini:

- [ ] URL Privacy Policy publik
- [ ] Privacy Policy menjelaskan data yang benar-benar dikumpulkan
- [ ] Privacy Policy menjelaskan penggunaan notifikasi
- [ ] Privacy Policy menjelaskan layanan pihak ketiga jika digunakan
- [ ] Privacy Policy memiliki kontak pengembang/pengelola
- [ ] Privacy Policy dapat diakses tanpa login

Jangan menyatakan fitur/data yang belum benar-benar digunakan aplikasi.

---

## 5. Data Safety

Data Safety harus diisi berdasarkan implementasi aplikasi yang benar-benar masuk ke release.

Audit wajib dilakukan terhadap:

- akun/login
- nama
- nomor telepon
- email
- lokasi
- perangkat
- identifier
- analytics
- crash reporting
- Firebase
- FCM
- server/API
- konten yang dikirim pengguna

Jangan mencentang pengumpulan data hanya karena suatu library tersedia.

---

## 6. App Permissions

Permission harus ditambahkan hanya jika fitur membutuhkannya.

Checklist:

- [ ] POST_NOTIFICATIONS
- [ ] Permission lain diverifikasi berdasarkan fitur aktual
- [ ] Tidak ada permission sensitif yang tidak diperlukan
- [ ] Exact alarm permission ditinjau sebelum digunakan
- [ ] Tidak menggunakan full-screen intent untuk fitur yang bukan alarm/panggilan inti

---

## 7. Content Rating

- [ ] Questionnaire selesai
- [ ] Rating disimpan
- [ ] Jawaban sesuai konten aplikasi aktual

---

## 8. Target Audience

- [ ] Target audience ditentukan
- [ ] Tidak mengarahkan aplikasi sebagai aplikasi anak jika bukan targetnya
- [ ] Jawaban sesuai fungsi aktual aplikasi

---

## 9. App Access

Jika aplikasi tidak membutuhkan login:

- [ ] Nyatakan bahwa aplikasi dapat digunakan tanpa login

Jika nanti ada fitur login:

- [ ] Sediakan instruksi akses reviewer
- [ ] Sediakan akun demo jika diwajibkan

Jangan mengklaim login diperlukan jika aplikasi sebenarnya tidak membutuhkannya.

---

## 10. Testing

### Internal Testing

- [ ] Upload AAB
- [ ] Internal test
- [ ] Install pada beberapa perangkat
- [ ] Test startup
- [ ] Test navigasi
- [ ] Test kalender
- [ ] Test notifikasi
- [ ] Test perubahan tanggal
- [ ] Test reboot
- [ ] Test permission

### Closed Testing

Jika akun developer personal termasuk akun yang terkena persyaratan Google Play:

- [ ] Minimal 12 tester
- [ ] Tester tetap opted-in
- [ ] Durasi minimal 14 hari
- [ ] Kumpulkan feedback
- [ ] Perbaiki bug
- [ ] Upload build final jika diperlukan
- [ ] Ajukan production access setelah persyaratan terpenuhi

---

## 11. Release Quality

Sebelum production:

- [ ] GitHub Actions GREEN
- [ ] Unit tests GREEN
- [ ] Android Lint GREEN
- [ ] Release AAB GREEN
- [ ] AAB signature GREEN
- [ ] Tidak ada secret di repository
- [ ] Tidak ada keystore di repository
- [ ] Version Code benar
- [ ] Version Name benar
- [ ] Privacy Policy siap
- [ ] Data Safety siap
- [ ] Content Rating siap
- [ ] Store Listing siap

---

## 12. Fitur Wajib Sebelum Production

### Kalender
- [ ] Kalender Miladiyyah
- [ ] Sumber tanggal sesuai kalender fisik yang ditetapkan project
- [ ] Agenda Wahidiyah sesuai data resmi

### Kegiatan
- [ ] Daftar kegiatan
- [ ] Detail kegiatan
- [ ] Reminder H-7 sampai H-1
- [ ] Reminder Hari H

### Dana Box
- [ ] Pengingat pagi
- [ ] Pengingat sore
- [ ] Pengaturan notifikasi

### Shalat
- [ ] Subuh
- [ ] Dzuhur
- [ ] Ashar
- [ ] Maghrib
- [ ] Isya
- [ ] Imsak

### Live
- [ ] Deteksi live YouTube pusat
- [ ] Push notification
- [ ] Link menuju live
- [ ] Deduplikasi broadcast

### Pengumuman
- [ ] API
- [ ] Tampilan dashboard
- [ ] Action/link jika tersedia

### Media
- [ ] Slot media
- [ ] Link valid
- [ ] Empty state jika belum ada konten

---

## 13. Release Policy

Jangan menaikkan `versionCode` hanya untuk testing lokal.

Setiap release Play harus mempunyai versionCode yang lebih tinggi dari release sebelumnya.

VersionName digunakan untuk identitas versi yang terlihat pengguna.

---

## 14. Final Production Gate

Production belum dianggap siap hanya karena AAB berhasil dibuat.

Production-ready apabila:

1. AAB berhasil dibuat.
2. AAB berhasil diverifikasi.
3. Fungsi utama sudah berjalan.
4. Permission sudah benar.
5. Notification engine sudah diuji.
6. Data kalender sudah tervalidasi.
7. Privacy Policy tersedia.
8. Data Safety sesuai implementasi.
9. Store listing selesai.
10. Closed testing selesai jika diwajibkan.
11. Tidak ada blocker kritis.

---

## Current Baseline

Project:
Wahidiyah Miladiyyah

Application ID:
com.wahidiyah.miladiyyah

Version:
1.0.0

Version Code:
1

Compile SDK:
36

Target SDK:
36

Release signing:
Configured in GitHub Actions

Release build:
GREEN

Quality gate:
GREEN