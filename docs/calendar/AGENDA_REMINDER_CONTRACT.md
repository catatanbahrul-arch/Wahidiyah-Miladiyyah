# Agenda Reminder Contract

## Source

Kalender fisik Miladiyyah 2026.

## Current Scope

September 2026 - Desember 2026.

## Eligibility

Hanya agenda:

`sourceColor = GREEN`

yang boleh dibuatkan reminder.

`sourceColor = RED`

tidak boleh dibuatkan reminder.

## Reminder Offsets

Setiap agenda menghasilkan:

- H-7
- H-6
- H-5
- H-4
- H-3
- H-2
- H-1
- Hari H

## Multi-Day Event

Untuk event dengan:

`startDate != endDate`

reminder dihitung berdasarkan `startDate`.

Contoh:

5 November 2026 - 14 Desember 2026

Reminder:

29 Oktober 2026
30 Oktober 2026
31 Oktober 2026
1 November 2026
2 November 2026
3 November 2026
4 November 2026
5 November 2026

Tidak dibuat reminder harian sampai 14 Desember.

## Reminder Time

Belum ditentukan oleh kalender fisik.

Karena sumber tidak memberikan jam reminder, engine berikutnya wajib membuat jam reminder sebagai setting aplikasi yang dapat dikonfigurasi pengguna.

Jangan menanam jam reminder sebagai data kalender.

## Notification Engine Next Step

AgendaReminderPlanner hanya menghasilkan rencana tanggal.

Tahap berikutnya:

AgendaReminder
→ Android scheduling
→ NotificationChannel
→ POST_NOTIFICATIONS
→ reboot reschedule
→ cancel/update
→ deduplication