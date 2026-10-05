# Praktikum 3

Aplikasi yang menampilkan kartu profil berisi foto, nama, bio, tombol *Follow*, serta informasi kontak (email, telepon, lokasi). Tampilan bersifat responsif dan menyesuaikan lebar jendela.

## Teknologi

| Komponen | Keterangan |
|----------|------------|
| Bahasa | Kotlin |
| UI Framework | Jetpack Compose Multiplatform (Desktop) |
| Desain | Material 3 (`androidx.compose.material3`) |
| Ikon | `androidx.compose.material.icons` (Email, Phone, LocationOn, Person) |

## Struktur Kode

| Composable | Fungsi |
|------------|--------|
| `ProfileScreen()` | Layar utama. Mendeteksi lebar jendela dengan `BoxWithConstraints` untuk menentukan mode compact, serta menyediakan scroll |
| `ProfileCard()` | Kartu utama yang berisi header profil dan daftar info kontak |
| `ProfileHeader()` | Baris berisi foto profil, nama, bio, dan tombol Follow |
| `InfoItem()` | Satu baris informasi (ikon, label, dan nilai) dalam sebuah kartu kecil |

## Cara Menjalankan

1. masuk ke folder `praktikum3/src/main/kotlin/com/example/p3/main.kt`.
2. Jalankan aplikasi:

   ```bash
   ./gradlew run
   ```

   Atau klik tombol **Run** di IDE pada fungsi `main()`.


## Kustomisasi

Data profil diatur di dalam `ProfileScreen()`:

```kotlin
ProfileCard(
    name = "Nama Anda",
    bio = "Bio singkat Anda",
    email = "email@contoh.com",
    phone = "08xxxxxxxxxx",
    location = "Kota, Negara",
    isCompact = isCompact
)
```

## Bukti Screenshot Hasil Akhir
![Profile App Screenshot](D:\Kuliah\Semester5\PRAKTIKUMPAM\praktikum3/hasil.png)
