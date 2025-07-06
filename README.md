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

| Stack          | Teknologi                                           |
| -------------- | --------------------------------------------------- |
| **Backend**    | Spring Boot 3.x, Spring Data JPA, Hibernate         |
| **Frontend**   | Thymeleaf, HTML5, CSS3, JavaScript, Bootstrap Icons |
| **Database**   | MySQL                                     |
| **Build Tool** | Maven / Gradle                                      |
| **Framework**  | Spring MVC                                          |



## 💻 Persiapan & Instalasi

### 🛠 Persyaratan Sistem

* Java JDK 17+
* Maven 3.6+ / Gradle 7.x
* XAMPP dengan MySQL
* Browser modern (Chrome, Firefox, Edge, dsb.)

### 🧾 Setup Database

1. Jalankan XAMPP, aktifkan **Apache** dan **MySQL**
2. Akses [phpMyAdmin](http://localhost/phpmyadmin)
3. Buat database baru bernama `todolist_db`

### ⚙️ Konfigurasi Aplikasi

1. Clone repository ini
2. Buka `src/main/resources/application.properties`
3. Sesuaikan pengaturan koneksi database jika perlu:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/todolist_db
   spring.datasource.username=root
   spring.datasource.password=
   ```

### ▶️ Menjalankan Aplikasi

```bash
# Menggunakan Maven
mvn clean install
mvn spring-boot:run

# atau menggunakan Gradle
gradle build
gradle bootRun
```

Buka browser dan akses:

```
http://localhost:8080/tasks
```

---

## 🗃 Struktur Database Otomatis

| Kolom         | Tipe Data    | Keterangan                 |
| ------------- | ------------ | -------------------------- |
| `id`          | BIGINT (PK)  | ID unik untuk setiap tugas |
| `title`       | VARCHAR(255) | Judul singkat tugas        |
| `description` | TEXT         | Penjelasan lebih detail    |
| `completed`   | BOOLEAN      | Status selesai/belum       |
| `created_at`  | DATETIME     | Tanggal tugas dibuat       |
| `due_date`    | DATETIME     | Tenggat waktu tugas        |
| `priority`    | ENUM         | LOW, MEDIUM, atau HIGH     |



🖼️ Screenshot
![image](https://github.com/user-attachments/assets/0e5d5c0a-d85d-45bd-9def-407c0817277e)



