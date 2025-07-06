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


# **To-Do List Application: Penjelasan Lengkap dengan Proses, Tujuan, dan Istilah Teknis**

Berikut penjelasan **sangat detail** tentang proyek To-Do List, mencakup **tujuan, alur kerja, istilah teknis, dan implementasi setiap komponen**.

---

## **1. Tujuan Proyek**  
**Apa yang ingin dicapai?**  
Membangun aplikasi manajemen tugas yang:  
✔ **Menyimpan data secara permanen** (menggunakan database MySQL).  
✔ **Memiliki antarmuka pengguna (UI)** yang interaktif dan mudah digunakan.  
✔ **Mendukung operasi dasar (CRUD)** untuk mengelola tugas.  

**Target Pengguna:**  
- Individu yang ingin mengorganisir tugas harian.  
- Developer yang mempelajari Spring Boot + Thymeleaf + MySQL.  

---

## **2. Arsitektur Sistem**  
### **Diagram Alur**  
```
[Browser] 
  → [Spring Boot Controller] 
  → [Service Layer] 
  → [Repository/JPA] 
  → [MySQL Database]
```

### **Penjelasan Komponen:**  
1. **Browser**: Tampilan UI (HTML/CSS/JS) diakses pengguna.  
2. **Spring Boot Controller**: Menangani request HTTP (GET/POST).  
3. **Service Layer**: Logika bisnis (misal: filter tugas "hari ini").  
4. **Repository/JPA**: Berinteraksi dengan database.  
5. **MySQL**: Penyimpanan data persisten.  

---

## **3. Penjelasan Detail Setiap Poin**  

### **A. Penggunaan Database (MySQL)**  
**Mengapa MySQL?**  
- **Open-source**, populer, dan kompatibel dengan XAMPP.  
- Mendukung transaksi ACID (*Atomicity, Consistency, Isolation, Durability*).  

**Proses Setup:**  
1. **Instal XAMPP** (termasuk MySQL).  
2. **Buat Database**:  
   ```sql
   CREATE DATABASE todolist_db;
   ```  
3. **Konfigurasi Spring Boot** (`application.properties`):  
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/todolist_db?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root  # Default XAMPP
   spring.datasource.password=      # Kosong di XAMPP default
   spring.jpa.hibernate.ddl-auto=update  # Auto-create tabel
   ```  

**Istilah Teknis:**  
- **JDBC**: Java Database Connectivity (API untuk koneksi database).  
- **JPA/Hibernate**: Framework ORM (Object-Relational Mapping) untuk mengubah objek Java ke tabel database.  
- **DDL-Auto**:  
  - `update`: Update schema database tanpa menghapus data lama.  
  - `create-drop`: Hapus dan buat ulang tabel setiap restart.  

---

### **B. User Interface (Tampilan)**  
**Teknologi UI:**  
- **Thymeleaf**: Template engine untuk render HTML di Spring Boot.  
- **CSS/JS**: Styling dan interaktivitas (tanpa framework eksternal).  

**Struktur File UI:**  
- `tasks.html`: Halaman utama (daftar tugas + form input).  
- `error.html`: Tampilan pesan error.  

**Fitur UI:**  
1. **Form Input Tugas**:  
   ```html
   <form th:action="@{/tasks}" method="post">
     <input type="text" th:field="*{title}" placeholder="Judul tugas">
     <select th:field="*{priority}">
       <option value="HIGH">High</option>
       <!-- Opsi lainnya -->
     </select>
     <button type="submit">Tambah</button>
   </form>
   ```  
   - **`th:field`**: Binding data ke objek `Task` di backend.  

2. **Daftar Tugas dengan Prioritas**:  
   ```html
   <div th:each="task : ${tasks}" class="task-card" th:classappend="${task.priority}">
     <span th:text="${task.title}"></span>
   </div>
   ```  
   - **`th:each`**: Loop untuk menampilkan semua tugas.  
   - **`th:classappend`**: Tambah class CSS berdasarkan prioritas.  

**Istilah Teknis:**  
- **Binding**: Proses menghubungkan data UI (form) dengan objek Java.  
- **Thymeleaf Syntax**:  
  - `th:text`: Render teks dinamis.  
  - `th:if`: Conditional rendering.  

---

### **C. Fitur CRUD (Create, Retrieve, Update, Delete)**  
**Operasi dan Implementasinya:**  

#### **1. Create (Tambah Tugas Baru)**  
**Alur:**  
1. Pengguna isi form → Submit (`POST /tasks`).  
2. Controller terima data → Simpan ke database.  

**Code:**  
```java
@PostMapping("/tasks")
public String createTask(@ModelAttribute Task task) {
    taskService.save(task);  // Simpan ke DB via JPA
    return "redirect:/tasks";  // Refresh halaman
}
```  

**Istilah:**  
- **`@ModelAttribute`**: Auto-bind form input ke objek `Task`.  
- **`redirect:`**: Hindari resubmit form saat refresh.  

#### **2. Retrieve (Ambil Daftar Tugas)**  
**Endpoint:**  
- `GET /tasks`: Semua tugas.  
- `GET /tasks/completed`: Tugas selesai.  

**Code:**  
```java
@GetMapping("/tasks")
public String getTasks(Model model) {
    model.addAttribute("tasks", taskRepository.findAll());
    return "tasks";
}
```  

**Istilah:**  
- **`Model`**: Objek untuk passing data ke Thymeleaf.  

#### **3. Update (Toggle Status Selesai)**  
**Alur:**  
1. Klik checkbox → `POST /tasks/{id}/toggle`.  
2. Service toggle nilai `completed`.  

**Code:**  
```java
@PostMapping("/tasks/{id}/toggle")
public String toggleTask(@PathVariable Long id) {
    Task task = taskRepository.findById(id).orElseThrow();
    task.setCompleted(!task.isCompleted());
    taskRepository.save(task);
    return "redirect:/tasks";
}
```  

#### **4. Delete (Hapus Tugas)**  
**Code:**  
```java
@PostMapping("/tasks/{id}/delete")
public String deleteTask(@PathVariable Long id) {
    taskRepository.deleteById(id);
    return "redirect:/tasks";
}
```  

**Istilah:**  
- **`@PathVariable`**: Ambil nilai `id` dari URL.  

---

## **4. Error Handling & Validasi**  
**Contoh Exception Handling:**  
```java
@ExceptionHandler(TaskNotFoundException.class)
public String handleError(TaskNotFoundException e, Model model) {
    model.addAttribute("error", "Task tidak ditemukan!");
    return "error";
}
```  

**Validasi Form:**  
```java
public class Task {
    @NotBlank(message = "Judul wajib diisi!")
    private String title;
}
```  

---

## **5. Cara Menjalankan Proyek**  
**Langkah-Langkah:**  
1. **Clone Repositori**:  
   ```bash
   git clone https://github.com/username/todolist.git
   ```  
2. **Jalankan XAMPP**: Start Apache dan MySQL.  
3. **Buat Database**:  
   ```sql
   CREATE DATABASE todolist_db;
   ```  
4. **Run Aplikasi**:  
   ```bash
   mvn spring-boot:run
   ```  
5. **Akses di Browser**:  
   ```
   http://localhost:8080/tasks
   ```  

---

## **6. Kesimpulan**  
Proyek ini:  
✅ **Menggunakan MySQL** untuk penyimpanan data persisten.  
✅ **Memiliki UI interaktif** dengan Thymeleaf + CSS.  
✅ **Mendukung CRUD lengkap** melalui Spring Boot.  

**Pengembangan Selanjutnya:**  
- Tambah fitur user authentication (login/logout).  
- Gunakan React/Vue untuk frontend yang lebih dinamis.  
- Deploy ke cloud (AWS/Heroku).  

Dengan penjelasan ini, Anda seharusnya sudah memahami **seluruh alur kerja, istilah teknis, dan implementasi proyek** secara mendetail. 🚀
