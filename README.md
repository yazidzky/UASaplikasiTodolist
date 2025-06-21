# ✅ To-Do List Web Application

Aplikasi **To-Do List** berbasis web untuk membantu kamu mengelola tugas harian secara efisien dengan fitur lengkap seperti *prioritas tugas*, *deadline*, *pencarian pintar*, dan *notifikasi cerdas*. Cocok untuk pelajar, pekerja, maupun siapa pun yang ingin tetap produktif!

---

## ✨ Fitur Unggulan

### 📝 Manajemen Tugas
- ➕ Tambah, ✏️ edit, 🗑️ hapus tugas
- ✅ Tandai tugas sebagai selesai / belum selesai
- 🔺 Atur prioritas: **Tinggi**, **Sedang**, **Rendah**
- ⏰ Tambahkan deadline untuk pengingat waktu

### 🔍 Filter & Pencarian
- 🎯 Filter berdasarkan status: *semua*, *selesai*, *belum selesai*
- 📊 Filter berdasarkan prioritas
- 🔎 Cari tugas berdasarkan kata kunci tertentu

### 🔔 Notifikasi Pintar
- 🚨 Peringatan visual untuk tugas mendekati deadline
- 🌐 Notifikasi browser untuk tugas dengan prioritas tinggi

### 💡 Tampilan & UI
- ⏱️ Jam real-time
- 📅 Daftar tugas hari ini
- 📈 Statistik jumlah tugas (total, selesai, belum selesai)
- 📱 Responsif dan user-friendly untuk desktop & mobile

---

## 🛠️ Teknologi yang Digunakan

### Backend
- ☕ **Java 17**
- 🌱 **Spring Boot 3.x**
- 📦 **Spring Data JPA**
- 🗃️ **H2 Database** (embedded)

### Frontend
- 🧩 **Thymeleaf**
- 🎨 HTML5 + CSS3 + JavaScript
- ⭐ Font Awesome (ikon tugas, status, dll.)

### Tools
- 🧰 **Maven** (build & dependensi)
- ♻️ **Spring Boot DevTools** (live reload)

---

## 🚀 Cara Menjalankan

### Persiapan
Pastikan kamu sudah install:
- Java JDK 17+
- Maven 3.8+

### Jalankan
```bash
git clone https://github.com/username/todolist-app.git
cd todolist-app
mvn spring-boot:run

📡 API Endpoint
Method	Path	Deskripsi
GET	/tasks	Tampilkan semua tugas
POST	/tasks	Tambah tugas baru
POST	/tasks/{id}/toggle	Tandai tugas selesai/belum selesai
POST	/tasks/{id}/delete	Hapus tugas
GET	/tasks/completed	Tugas yang sudah selesai
GET	/tasks/pending	Tugas yang belum selesai
GET	/tasks/priority/{p}	Filter berdasarkan prioritas
GET	/tasks/search?q=...	Cari tugas berdasarkan keyword
GET	/tasks/urgent	Tugas yang mendekati deadline

🖼️ Screenshot
![image](https://github.com/user-attachments/assets/0e5d5c0a-d85d-45bd-9def-407c0817277e)
