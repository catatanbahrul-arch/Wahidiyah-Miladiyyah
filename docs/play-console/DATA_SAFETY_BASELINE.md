# Google Play Data Safety Baseline

## Wahidiyah Miladiyyah

**STATUS: INTERNAL REVIEW**

Dokumen ini bukan pengganti formulir Data Safety Google Play.

Data Safety final harus dibuat berdasarkan APK/AAB release yang benar-benar dipublikasikan.

---

## Checklist Audit

### Personal Information

- [ ] Name
- [ ] Email address
- [ ] Phone number
- [ ] Address
- [ ] User IDs
- [ ] Other personal information

### Financial Information

- [ ] Payment information
- [ ] Purchase history
- [ ] Other financial information

### Authentication

- [ ] Password
- [ ] Authentication credentials

### Location

- [ ] Approximate location
- [ ] Precise location

### Messages

- [ ] Emails
- [ ] SMS
- [ ] Other messages

### Photos and Videos

- [ ] Photos
- [ ] Videos

### Audio

- [ ] Voice recordings
- [ ] Music/audio files

### Files and Documents

- [ ] Files
- [ ] Documents

### App Activity

- [ ] App interactions
- [ ] In-app search history
- [ ] Installed apps
- [ ] Other user-generated content

### Device or Other IDs

- [ ] Device ID
- [ ] Advertising ID
- [ ] Other identifiers

---

## Third-Party SDK Audit

Periksa dependency release final.

- [ ] Firebase
- [ ] Firebase Cloud Messaging
- [ ] Analytics
- [ ] Crash reporting
- [ ] YouTube
- [ ] Google APIs
- [ ] Other SDK

Setiap SDK yang benar-benar mengirim/mengumpulkan data harus diperiksa sebelum Data Safety diisi.

---

## Prinsip

Jangan mencentang:

"Collected"

atau

"Shared"

hanya karena sebuah library tersedia.

Yang harus menjadi dasar adalah perilaku release aktual.

---

## Final Approval

- [ ] Release AAB dianalisis
- [ ] Dependency release dianalisis
- [ ] Network/API dianalisis
- [ ] Firebase configuration dianalisis
- [ ] Privacy Policy sinkron
- [ ] Data Safety sinkron
- [ ] Reviewer menyetujui