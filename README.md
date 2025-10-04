# Biblio - Aplikasi Review Buku Online

![Biblio Logo](screenshot/Logo%20Frame%201.png)

**Biblio** adalah aplikasi mobile Android untuk review dan manajemen buku online yang dikembangkan sebagai tugas Pemrograman Mobile D oleh Kelompok 8. Aplikasi ini memungkinkan pengguna untuk mengeksplorasi, meninjau, dan mengelola koleksi buku pribadi mereka dengan antarmuka yang user-friendly.

## 📱 Fitur Utama

### 👤 Fitur Pengguna

-   **Registrasi dan Login** - Sistem autentikasi pengguna yang aman
-   **Beranda** - Tampilan utama dengan rekomendasi buku
-   **Pencarian Buku** - Fitur pencarian untuk menemukan buku favorit
-   **Detail Buku** - Informasi lengkap tentang buku termasuk sinopsis dan rating
-   **Review dan Rating** - Memberikan ulasan dan penilaian pada buku
-   **My List** - Manajemen koleksi pribadi dengan kategori:
    -   📖 **Sedang Dibaca** - Buku yang sedang dibaca
    -   ⭐ **Favorit** - Buku favorit pengguna
    -   ✅ **Selesai Dibaca** - Buku yang telah selesai dibaca
-   **Profil Pengguna** - Manajemen informasi akun pengguna
-   **Dark Theme** - Mode gelap untuk kenyamanan mata

### 👨‍💼 Fitur Administrator

-   **Manajemen Buku** - CRUD (Create, Read, Update, Delete) data buku
-   **Manajemen Pengguna** - Kelola data pengguna aplikasi
-   **Dashboard Admin** - Panel kontrol untuk administrator
-   **Laporan Review** - Melihat semua review yang diberikan pengguna

## 🛠️ Teknologi & Library

### Platform

-   **Android SDK** (API Level 29-33)
-   **Java** - Bahasa pemrograman utama
-   **Gradle** - Build system

### Core Libraries

-   **AndroidX AppCompat** (1.6.1) - Kompatibilitas backward
-   **Material Design Components** (1.8.0) - UI components modern
-   **ConstraintLayout** (2.1.4) - Layout fleksibel
-   **RecyclerView** - Untuk tampilan list yang efisien
-   **CardView** - UI components untuk card layout

### Architecture Components

-   **Lifecycle** (2.6.1) - LiveData dan ViewModel
-   **Navigation Component** (2.5.3) - Navigasi antar fragment
-   **ViewBinding** - Binding view yang type-safe

### Database

-   **SQLite** - Database lokal untuk penyimpanan data
-   **DatabaseHelper** - Custom database management

### Additional Features

-   **SplashScreen** (1.0.0) - Splash screen native Android 12+
-   **Image Handling** - Upload dan preview gambar buku
-   **Session Management** - Manajemen sesi pengguna
-   **Search Functionality** - Pencarian real-time

## 📸 Screenshot Aplikasi

### 🔐 Login

| Login                                                     |
| --------------------------------------------------------- |
| ![Login](screenshot/Login/Screenshot_20230704-231329.png) |

### 📋 Daftar

| Registrasi                                                         |
| ------------------------------------------------------------------ |
| ![Daftar](screenshot/Daftar/Screenshot_20230704-231334_Biblio.png) |

### 👤 Pengguna

#### Beranda

| Beranda Home                                                                  | Beranda Most Popular                                                               |
| ----------------------------------------------------------------------------- | ---------------------------------------------------------------------------------- |
| ![Beranda](screenshot/Pengguna/Beranda/Screenshot_20230704-233947_Biblio.png) | ![Most Popular](screenshot/Pengguna/Beranda/Screenshot_20230704-233950_Biblio.png) |

#### Pencarian

| Pencarian 1                                                                       | Pencarian 2                                                                         |
| --------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| ![Pencarian](screenshot/Pengguna/Pencarian/Screenshot_20230704-234019_Biblio.png) | ![Pencarian 2](screenshot/Pengguna/Pencarian/Screenshot_20230704-234042_Biblio.png) |

#### Detail Buku

| Detail Buku 1                                                                    | Detail Buku 2                                                                      | Detail Buku 3                                                                             |
| -------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| ![Detail Buku](screenshot/Pengguna/Detail%20Buku/Screenshot_20230704-234338.png) | ![Detail Buku 2](screenshot/Pengguna/Detail%20Buku/Screenshot_20230704-234423.png) | ![Detail Buku 3](screenshot/Pengguna/Detail%20Buku/Screenshot_20230704-234546_Biblio.png) |

#### Detail Pengguna

| Detail Pengguna                                                                                 |
| ----------------------------------------------------------------------------------------------- |
| ![Detail Pengguna](screenshot/Pengguna/Detail%20Pengguna/Screenshot_20230704-234658_Biblio.png) |

#### Fragment List

| List 1                                                                               | List 2                                                                                        | List 3                                                                                        |
| ------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| ![Fragment List](screenshot/Pengguna/Fragment%20List/Screenshot_20230704-234223.png) | ![Fragment List 2](screenshot/Pengguna/Fragment%20List/Screenshot_20230704-234225_Biblio.png) | ![Fragment List 3](screenshot/Pengguna/Fragment%20List/Screenshot_20230704-234227_Biblio.png) |

| List 4                                                                                        | List 5                                                                                        |
| --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| ![Fragment List 4](screenshot/Pengguna/Fragment%20List/Screenshot_20230704-234229_Biblio.png) | ![Fragment List 5](screenshot/Pengguna/Fragment%20List/Screenshot_20230704-234231_Biblio.png) |

#### Review

| Review                                                                      |
| --------------------------------------------------------------------------- |
| ![Review](screenshot/Pengguna/Review/Screenshot_20230704-234648_Biblio.png) |

#### Tambah List

| Tambah List 1                                                                           | Tambah List 2                                                                             |
| --------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| ![Tambah List](screenshot/Pengguna/Tambah%20List/Screenshot_20230704-234603_Biblio.png) | ![Tambah List 2](screenshot/Pengguna/Tambah%20List/Screenshot_20230704-234606_Biblio.png) |

#### Update List

| Update List                                                                             |
| --------------------------------------------------------------------------------------- |
| ![Update List](screenshot/Pengguna/Update%20List/Screenshot_20230704-234635_Biblio.png) |

#### Profil Pengguna

| Profil 1                                                                               | Profil 2                                                                                 | Profil 3                                                                                 |
| -------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------- |
| ![Profil](screenshot/Pengguna/Profil%20Pengguna/Screenshot_20230704-234807_Biblio.png) | ![Profil 2](screenshot/Pengguna/Profil%20Pengguna/Screenshot_20230704-234809_Biblio.png) | ![Profil 3](screenshot/Pengguna/Profil%20Pengguna/Screenshot_20230704-234819_Biblio.png) |

| Profil 4                                                                                 |
| ---------------------------------------------------------------------------------------- |
| ![Profil 4](screenshot/Pengguna/Profil%20Pengguna/Screenshot_20230704-234823_Biblio.png) |

### 👨‍💼 Admin

#### Buku

| Admin Buku 1                                                        | Admin Buku 2                                                                 |
| ------------------------------------------------------------------- | ---------------------------------------------------------------------------- |
| ![Admin Buku](screenshot/Admin/Buku/Screenshot_20230704-233244.png) | ![Admin Buku 2](screenshot/Admin/Buku/Screenshot_20230704-233325_Biblio.png) |

#### Detail Buku

| Detail Buku Admin 1                                                                        | Detail Buku Admin 2                                                                          | Detail Buku Admin 3                                                                          |
| ------------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
| ![Detail Buku Admin](screenshot/Admin/Detail%20Buku/Screenshot_20230704-233427_Biblio.png) | ![Detail Buku Admin 2](screenshot/Admin/Detail%20Buku/Screenshot_20230704-233430_Biblio.png) | ![Detail Buku Admin 3](screenshot/Admin/Detail%20Buku/Screenshot_20230704-233433_Biblio.png) |

| Detail Buku Admin 4                                                                          |
| -------------------------------------------------------------------------------------------- |
| ![Detail Buku Admin 4](screenshot/Admin/Detail%20Buku/Screenshot_20230704-233741_Biblio.png) |

#### Detail Pengguna

| Detail Pengguna Admin 1                                                                            | Detail Pengguna Admin 2                                                                              | Detail Pengguna Admin 3                                                                              |
| -------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| ![Detail Pengguna Admin](screenshot/Admin/Detail%20Pengguna/Screenshot_20230704-233655_Biblio.png) | ![Detail Pengguna Admin 2](screenshot/Admin/Detail%20Pengguna/Screenshot_20230704-233658_Biblio.png) | ![Detail Pengguna Admin 3](screenshot/Admin/Detail%20Pengguna/Screenshot_20230704-233701_Biblio.png) |

| Detail Pengguna Admin 4                                                                              |
| ---------------------------------------------------------------------------------------------------- |
| ![Detail Pengguna Admin 4](screenshot/Admin/Detail%20Pengguna/Screenshot_20230704-233732_Biblio.png) |

#### Pengguna

| List Pengguna                                                                | Pencarian Pengguna                                                                     |
| ---------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- |
| ![Pengguna](screenshot/Admin/Pengguna/Screenshot_20230704-233614_Biblio.png) | ![Pencarian Pengguna](screenshot/Admin/Pengguna/Screenshot_20230704-233619_Biblio.png) |

#### Profil Admin

| Profil Admin 1                                                                         | Profil Admin 2                                                                           | Profil Admin 3                                                                           |
| -------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------- |
| ![Profil Admin](screenshot/Admin/Profil%20Admin/Screenshot_20230704-233815_Biblio.png) | ![Profil Admin 2](screenshot/Admin/Profil%20Admin/Screenshot_20230704-233824_Biblio.png) | ![Profil Admin 3](screenshot/Admin/Profil%20Admin/Screenshot_20230704-233827_Biblio.png) |

| Profil Admin 4                                                                           |
| ---------------------------------------------------------------------------------------- |
| ![Profil Admin 4](screenshot/Admin/Profil%20Admin/Screenshot_20230704-233847_Biblio.png) |

#### Tambah Buku

| Tambah Buku 1                                                                        | Tambah Buku 2                                                                          | Tambah Buku 3                                                                         |
| ------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| ![Tambah Buku](screenshot/Admin/Tambah%20Buku/Screenshot_20230704-233335_Biblio.png) | ![Tambah Buku 2](screenshot/Admin/Tambah%20Buku/Screenshot_20230704-233339_Biblio.png) | ![Tambah Buku 3](screenshot/Admin/Tambah%20Buku/Screenshot_20230704-233401_Files.png) |

| Tambah Buku 4                                                                          |
| -------------------------------------------------------------------------------------- |
| ![Tambah Buku 4](screenshot/Admin/Tambah%20Buku/Screenshot_20230704-233408_Biblio.png) |

#### Tambah Pengguna

| Tambah Pengguna 1                                                                            | Tambah Pengguna 2                                                                              |
| -------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| ![Tambah Pengguna](screenshot/Admin/Tambah%20Pengguna/Screenshot_20230704-233626_Biblio.png) | ![Tambah Pengguna 2](screenshot/Admin/Tambah%20Pengguna/Screenshot_20230704-233629_Biblio.png) |

#### Update Buku

| Update Buku 1                                                                        | Update Buku 2                                                                          |
| ------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------- |
| ![Update Buku](screenshot/Admin/Update%20Buku/Screenshot_20230704-233521_Biblio.png) | ![Update Buku 2](screenshot/Admin/Update%20Buku/Screenshot_20230704-233524_Biblio.png) |

#### Update Pengguna

| Update Pengguna 1                                                                            | Update Pengguna 2                                                                              |
| -------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| ![Update Pengguna](screenshot/Admin/Update%20Pengguna/Screenshot_20230704-233710_Biblio.png) | ![Update Pengguna 2](screenshot/Admin/Update%20Pengguna/Screenshot_20230704-233712_Biblio.png) |

### 🌙 Dark Theme

| Dark Theme 1                                                          | Dark Theme 2                                                            | Dark Theme 3                                                                   |
| --------------------------------------------------------------------- | ----------------------------------------------------------------------- | ------------------------------------------------------------------------------ |
| ![Dark Theme](screenshot/Dark%20Theme/Screenshot_20230705-000416.png) | ![Dark Theme 2](screenshot/Dark%20Theme/Screenshot_20230705-000441.png) | ![Dark Theme 3](screenshot/Dark%20Theme/Screenshot_20230705-000535_Biblio.png) |

| Dark Theme 4                                                                   | Dark Theme 5                                                                   | Dark Theme 6                                                                   |
| ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ |
| ![Dark Theme 4](screenshot/Dark%20Theme/Screenshot_20230705-000548_Biblio.png) | ![Dark Theme 5](screenshot/Dark%20Theme/Screenshot_20230705-000557_Biblio.png) | ![Dark Theme 6](screenshot/Dark%20Theme/Screenshot_20230705-000600_Biblio.png) |

| Dark Theme 7                                                                   | Dark Theme 8                                                                   | Dark Theme 9                                                                   |
| ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ |
| ![Dark Theme 7](screenshot/Dark%20Theme/Screenshot_20230705-000606_Biblio.png) | ![Dark Theme 8](screenshot/Dark%20Theme/Screenshot_20230705-000623_Biblio.png) | ![Dark Theme 9](screenshot/Dark%20Theme/Screenshot_20230705-000648_Biblio.png) |

| Dark Theme 10                                                            | Dark Theme 11                                                                   | Dark Theme 12                                                                   |
| ------------------------------------------------------------------------ | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| ![Dark Theme 10](screenshot/Dark%20Theme/Screenshot_20230705-000741.png) | ![Dark Theme 11](screenshot/Dark%20Theme/Screenshot_20230705-000823_Biblio.png) | ![Dark Theme 12](screenshot/Dark%20Theme/Screenshot_20230705-000848_Biblio.png) |

### 🧪 Pengujian

#### Daftar

| Daftar Berhasil                                              | Daftar Gagal - Data Kosong                                                 |
| ------------------------------------------------------------ | -------------------------------------------------------------------------- |
| ![Daftar Berhasil](screenshot/Pengujian/Daftar/berhasil.png) | ![Daftar Gagal](<screenshot/Pengujian/Daftar/gagal%20(data%20kosong).png>) |

| Daftar Gagal - Email Sudah Dipakai                                                          | Daftar Gagal - Password Tidak Cocok                                                                       |
| ------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| ![Email Sudah Dipakai](<screenshot/Pengujian/Daftar/gagal%20(email%20sudah%20dipakai).png>) | ![Password Tidak Cocok](<screenshot/Pengujian/Daftar/gagal%20(passord%20konfirmasi%20tidak%20cocok).png>) |

#### Login

| Login Berhasil                                             | Login Gagal                                          |
| ---------------------------------------------------------- | ---------------------------------------------------- |
| ![Login Berhasil](screenshot/Pengujian/Login/berhasil.png) | ![Login Gagal](screenshot/Pengujian/Login/gagal.png) |

#### Tambah Buku

| Tambah Buku Berhasil                                                     | Tambah Buku Gagal                                                  |
| ------------------------------------------------------------------------ | ------------------------------------------------------------------ |
| ![Tambah Buku Berhasil](screenshot/Pengujian/Tambah%20Buku/berhasil.png) | ![Tambah Buku Gagal](screenshot/Pengujian/Tambah%20Buku/gagal.png) |

#### Tambah Pengguna

| Tambah Pengguna Berhasil                                                         | Tambah Pengguna Gagal                                                      |
| -------------------------------------------------------------------------------- | -------------------------------------------------------------------------- |
| ![Tambah Pengguna Berhasil](screenshot/Pengujian/Tambah%20Pengguna/berhasil.png) | ![Tambah Pengguna Gagal](screenshot/Pengujian/Tambah%20Pengguna/gagal.png) |

## 🚀 Cara Menjalankan Aplikasi

### Prasyarat

-   Android Studio (versi terbaru)
-   Java Development Kit (JDK) 8 atau lebih tinggi
-   Android SDK dengan API Level 29-33
-   Device atau emulator Android

### Langkah Instalasi

1. **Clone Repository**

    ```bash
    git clone https://github.com/rai-pramana/Biblio.git
    cd Biblio
    ```

2. **Buka di Android Studio**

    - Pilih "Open an existing Android Studio project"
    - Navigate ke folder project yang sudah di-clone

3. **Sync Project**

    - Android Studio akan otomatis melakukan sync Gradle
    - Tunggu hingga proses selesai

4. **Build & Run**
    - Pilih device/emulator target
    - Klik tombol "Run" atau tekan `Shift + F10`

### Build APK

```bash
./gradlew assembleDebug
```

APK akan tersedia di `app/build/outputs/apk/debug/`

## 📁 Struktur Project

```
app/
├── src/main/
│   ├── java/com/progmob_d_kelompok_8/biblio/
│   │   ├── admin/              # Fitur administrator
│   │   ├── adminuser/          # Fitur bersama admin-user
│   │   ├── user/               # Fitur pengguna
│   │   ├── database/           # Database helper
│   │   ├── model/              # Data models
│   │   └── tool/               # Utilities & adapters
│   ├── res/                    # Resources (layout, drawable, values)
│   └── AndroidManifest.xml
├── build.gradle                # Dependencies & build config
└── proguard-rules.pro         # ProGuard configuration
```

## 🏗️ Arsitektur Aplikasi

Aplikasi menggunakan arsitektur **MVP (Model-View-Presenter)** dengan komponen:

-   **Model**: Database entities dan data management
-   **View**: Activities dan Fragments untuk UI
-   **Presenter**: Business logic dan data binding
-   **Database**: SQLite dengan DatabaseHelper untuk data persistence
-   **Session**: Manajemen state dan user session

## 📱 Minimum Requirements

-   **Android Version**: Android 10 (API Level 29)
-   **Target SDK**: API Level 33
-   **RAM**: Minimum 2GB
-   **Storage**: 50MB space available
-   **Permissions**:
    -   Read/Write External Storage (untuk gambar)
    -   Internet (untuk future features)

## 👥 Tim Pengembang

**Kelompok 8 - Pemrograman Mobile D**

-   Kontributor utama dan maintainer project
-   Pengembangan fitur core dan UI/UX
-   Testing dan quality assurance

## 📄 Lisensi

Project ini dikembangkan untuk keperluan akademik dalam mata kuliah Pemrograman Mobile D.

## 🔮 Future Development

-   [ ] Implementasi backend API
-   [ ] Sinkronisasi cloud
-   [ ] Social features (follow users, share reviews)
-   [ ] Push notifications
-   [ ] Barcode scanner untuk menambah buku
-   [ ] Export data ke PDF

---

**Developed with ❤️ by Kelompok 8**
