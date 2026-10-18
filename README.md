# Dokumentasi Tugas Praktik 1: Aplikasi Kasir Restoran Sederhana (Java)

Aplikasi kasir restoran berbasis console (CLI) yang dibangun menggunakan bahasa pemrograman Java. Program ini dirancang khusus untuk memenuhi seluruh kriteria dan batasan teknis Tugas Praktik 1, termasuk pengelolaan data menggunakan **Array**, penerapan **Struktur Keputusan (Selection/Decision Structure)**, dan **TANPA menggunakan struktur perulangan (loop-free)**.

---

## 1. Struktur File Proyek

```text
basic_cashierPOS_java/
├── Menu.java       # Kelas model untuk merepresentasikan data item menu
├── Main.java       # Kelas utama dengan logika bisnis, alur pemesanan, dan cetak struk
└── README.md       # Dokumentasi teknis dan panduan presentasi video
```

---

## 2. Fitur & Pemenuhan Ketentuan Soal

| No | Kebutuhan Soal | Implementasi pada Kode |
|---|---|---|
| 1 | **Kelas `Menu`** | Memiliki atribut `nama` (String), `harga` (double), dan `kategori` (String) beserta getter-setter. |
| 2 | **Daftar Menu Minimal 4 per Kategori** | Terdapat 8 menu (4 makanan & 4 minuman) yang disimpan dalam array `Menu[8]`. |
| 3 | **Format Tampilan Menu Berdasarkan Kategori** | Ditampilkan terkelompok ke dalam kategori `[ KATEGORI MAKANAN ]` dan `[ KATEGORI MINUMAN ]`. |
| 4 | **Format Pemesanan (Maksimal 4 Menu)** | Mendukung format `Nasi Padang = 2` atau nomor menu `1 = 2`. Maksimal 4 slot pesanan. |
| 5 | **Pengelolaan Menggunakan Array** | Menu restoran disimpan dalam `Menu[] daftarMenu`, dan pesanan disimpan dalam `Menu[] pesananMenu` serta `int[] pesananQty`. |
| 6 | **Struktur Keputusan (Diskon & Penawaran)** | <ul><li>**Diskon 10%**: Jika subtotal > Rp 100.000,-</li><li>**Beli 1 Gratis 1 Minuman**: Jika subtotal > Rp 50.000,- dan memesan kategori minuman.</li></ul> |
| 7 | **Biaya Pajak & Pelayanan** | Pajak PB1 sebesar 10% dan Biaya Pelayanan tetap sebesar Rp 20.000,-. |
| 8 | **Mencetak Struk Pesanan** | Menampilkan rincian pesanan, harga/item, subtotal, detail diskon/promo, pajak, biaya pelayanan, dan total akhir. |
| 9 | **Tanpa Struktur Perulangan (Zero Loops)** | **100% tidak menggunakan** `for`, `while`, atau `do-while`. Seluruh alur dikontrol melalui percabangan `if / else if / else` dan indeks array. |

---

## 3. Cara Kompilasi dan Menjalankan Program

### Kompilasi & Menjalankan Program:

Karena di komputer Anda terpasang **JDK 25** (`javac`) dan **JRE 8** (`java`), gunakan opsi **`--release 8`** agar hasil kompilasi kompatibel dengan runtime Java Anda:

```powershell
# 1. Kompilasi (kompatibel Java 8)
javac --release 8 Menu.java Main.java

# 2. Jalankan Program
java Main
```

*Alternatif jika ingin menjalankan langsung menggunakan Java 25:*
```powershell
& "C:\Program Files\Java\jdk-25.0.1\bin\java.exe" Main
```

---

## 4. Skenario Pengujian Struktur Keputusan (Decision Structures)

Gunakan skenario-skenario berikut saat mendemokan aplikasi di video penjelasan:

### Skenario 1: Pesanan Normal (Subtotal ≤ Rp 50.000)
- **Pesanan**: 
  - `Nasi Padang = 1` (Rp 25.000)
  - `Es Teh Manis = 1` (Rp 5.000)
- **Subtotal**: Rp 30.000
- **Hasil Keputusan**: 
  - Tidak ada diskon 10% (karena ≤ Rp 100.000).
  - Tidak ada promo BOGO (karena ≤ Rp 50.000).
- **Pajak (10%)**: Rp 3.000
- **Biaya Pelayanan**: Rp 20.000
- **Total Pembayaran**: **Rp 53.000**

---

### Skenario 2: Subtotal > Rp 50.000 dan ≤ Rp 100.000 (Mendapat Promo Beli 1 Gratis 1 Minuman)
- **Pesanan**: 
  - `Nasi Padang = 2` (Rp 50.000)
  - `Es Jeruk = 2` (Rp 14.000)
- **Subtotal**: Rp 64.000
- **Hasil Keputusan**: 
  - Mendapat promo **Beli 1 Gratis 1 Minuman (Es Jeruk)**: Potongan Rp 7.000.
  - Subtotal setelah promo: Rp 57.000.
- **Pajak (10%)**: Rp 5.700
- **Biaya Pelayanan**: Rp 20.000
- **Total Pembayaran**: **Rp 82.700**

---

### Skenario 3: Subtotal > Rp 50.000 dan ≤ Rp 100.000 Tanpa Minuman
- **Pesanan**: 
  - `Nasi Padang = 2` (Rp 50.000)
  - `Mie Goreng Spesial = 1` (Rp 18.000)
- **Subtotal**: Rp 68.000
- **Hasil Keputusan**: 
  - Meskipun subtotal > Rp 50.000, pelanggan **TIDAK** mendapat promo BOGO karena tidak memesan kategori minuman.
- **Pajak (10%)**: Rp 6.800
- **Biaya Pelayanan**: Rp 20.000
- **Total Pembayaran**: **Rp 94.800**

---

### Skenario 4: Subtotal > Rp 100.000 Dengan Minuman (Mendapat Diskon 10% DAN Promo Beli 1 Gratis 1)
- **Pesanan**: 
  - `Nasi Padang = 4` (Rp 100.000)
  - `Jus Alpukat = 2` (Rp 30.000)
- **Subtotal**: Rp 130.000
- **Hasil Keputusan**: 
  - Diskon 10%: Rp 13.000
  - Promo BOGO Minuman (Jus Alpukat): Rp 15.000
  - Subtotal setelah potongan: Rp 102.000
- **Pajak (10%)**: Rp 10.200
- **Biaya Pelayanan**: Rp 20.000
- **Total Pembayaran**: **Rp 132.200**

---

### Skenario 5: Subtotal > Rp 100.000 Tanpa Minuman
- **Pesanan**: 
  - `Ayam Bakar = 5` (Rp 110.000)
- **Subtotal**: Rp 110.000
- **Hasil Keputusan**: 
  - Diskon 10%: Rp 11.000
  - Promo BOGO Minuman: Tidak ada (karena tidak ada minuman).
  - Subtotal setelah potongan: Rp 99.000
- **Pajak (10%)**: Rp 9.900
- **Biaya Pelayanan**: Rp 20.000
- **Total Pembayaran**: **Rp 128.900**

---

## 5. Panduan & Naskah Presentasi Video (Maksimal 15 Menit)

Rencana pembagian waktu presentasi video:

1. **Menit 00:00 - 01:30 (Pendahuluan & Perkenalan)**:
   - Salam pembuka, sebutkan nama, NIM, mata kuliah, dan judul tugas: "Aplikasi Kasir Restoran Sederhana Berbasis Java".
   - Jelaskan garis besar tugas dan batasan utama: penggunaan kelas `Menu` dan `Main`, array, struktur keputusan, serta larangan penggunaan struktur pengulangan (loops).

2. **Menit 01:30 - 04:30 (Penjelasan Kode Sumber / Code Walkthrough)**:
   - **Buka `Menu.java`**: Jelaskan atribut `nama`, `harga`, `kategori`, constructor, dan method getter-setter.
   - **Buka `Main.java`**:
     - Tunjukkan method `inisialisasiMenu()`: Inisialisasi array 8 menu (4 makanan, 4 minuman).
     - Tunjukkan method `tampilkanDaftarMenu()`: Menampilkan menu per kategori tanpa loop menggunakan `System.out.printf`.
     - Tunjukkan method `terimaPesanan()` & `olahInputPesanan()`: Menerima input hingga 4 menu (format `Menu = Qty`) menggunakan percabangan `if`.
     - Tunjukkan method `cariMenu()`: Pencarian case-insensitive dan nomor menu menggunakan `if - else if`.
     - Tunjukkan method `prosesTransaksi()`: Perhitungan subtotal, logika kondisi diskon 10% (> 100rb) dan promo beli 1 gratis 1 minuman (> 50rb), perhitungan pajak 10%, biaya pelayanan Rp 20.000, serta cetak struk.

3. **Menit 04:30 - 13:00 (Live Demo Menjalankan Program)**:
   - Buka terminal / command prompt.
   - Jalankan `javac Menu.java Main.java` lalu `java Main`.
   - **Demo Skenario 1**: Pesanan <= Rp 50.000 (tunjukkan tidak ada diskon di struk).
   - **Demo Skenario 2**: Pesanan > Rp 50.000 dengan minuman (tunjukkan promo Beli 1 Gratis 1 minuman muncul dan memotong harga).
   - **Demo Skenario 3**: Pesanan > Rp 50.000 tanpa minuman (tunjukkan promo minuman tidak aktif).
   - **Demo Skenario 4**: Pesanan > Rp 100.000 dengan minuman (tunjukkan kedua promo aktif sekaligus: Diskon 10% + Promo Beli 1 Gratis 1).
   - Masukkan nominal pembayaran tunai dan perlihatkan perhitungan uang kembalian.

4. **Menit 13:00 - 15:00 (Kesimpulan & Penutup)**:
   - Rangkum bahwa seluruh kriteria tugas telah terpenuhi dengan baik: penggunaan OOP, array, struktur keputusan, dan kepatuhan penuh tanpa perulangan.
   - Ucapkan terima kasih dan salam penutup.
