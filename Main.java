import java.util.Scanner;

public class Main {

    // Array dinamis untuk mengelola daftar menu restoran
    private static Menu[] daftarMenu;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Inisialisasi data awal menu restoran (minimal 4 makanan & 4 minuman)
        daftarMenu = inisialisasiMenu();

        // 2. Navigasi Menu Utama Aplikasi (Menggunakan struktur pengulangan)
        manajemenMenuAplikasi(scanner);

        scanner.close();
    }

    /**
     * Inisialisasi menu awal restoran ke dalam Array
     */
    public static Menu[] inisialisasiMenu() {
        Menu[] menu = new Menu[8];

        // Kategori Makanan (Minimal 4 menu)
        menu[0] = new Menu("Nasi Padang", 25000, "makanan");
        menu[1] = new Menu("Ayam Bakar", 22000, "makanan");
        menu[2] = new Menu("Mie Goreng Spesial", 18000, "makanan");
        menu[3] = new Menu("Sate Ayam", 20000, "makanan");

        // Kategori Minuman (Minimal 4 menu)
        menu[4] = new Menu("Es Teh Manis", 5000, "minuman");
        menu[5] = new Menu("Es Jeruk", 7000, "minuman");
        menu[6] = new Menu("Jus Alpukat", 15000, "minuman");
        menu[7] = new Menu("Kopi Susu", 12000, "minuman");

        return menu;
    }

    /**
     * Manajemen Menu Utama Aplikasi (Pelanggan & Pemilik Restoran)
     */
    public static void manajemenMenuAplikasi(Scanner scanner) {
        boolean aplikasiBerjalan = true;

        while (aplikasiBerjalan) {
            System.out.println("\n==========================================================");
            System.out.println("          APLIKASI KASIR & MANAJEMEN RESTORAN             ");
            System.out.println("                    RESTORAN NUSANTARA                    ");
            System.out.println("==========================================================");
            System.out.println("  1. Menu Pelanggan (Pemesanan Makanan & Minuman)");
            System.out.println("  2. Menu Pengelolaan Restoran (Khusus Pemilik)");
            System.out.println("  3. Keluar Aplikasi");
            System.out.println("==========================================================");
            System.out.print("Pilih menu utama (1-3): ");

            String pilihan = scanner.nextLine().trim();

            switch (pilihan) {
                case "1":
                    menuPelanggan(scanner);
                    break;
                case "2":
                    menuPengelolaan(scanner);
                    break;
                case "3":
                    System.out.println("\nTerima kasih telah menggunakan Aplikasi Restoran Nusantara!");
                    aplikasiBerjalan = false;
                    break;
                default:
                    System.out.println("  [!] Pilihan tidak valid! Silakan masukkan angka 1, 2, atau 3.");
                    break;
            }
        }
    }

    /**
     * Menampilkan daftar menu restoran yang dikelompokkan berdasarkan kategori
     * menggunakan struktur pengulangan
     */
    public static void tampilkanDaftarMenu(Menu[] menuArray) {
        System.out.println("\n==========================================================");
        System.out.println("              DAFTAR MENU RESTORAN NUSANTARA              ");
        System.out.println("==========================================================");

        int nomorUrut = 1;

        // Kelompok Makanan
        System.out.println(" [ KATEGORI MAKANAN ]");
        boolean adaMakanan = false;
        for (int i = 0; i < menuArray.length; i++) {
            if (menuArray[i] != null && menuArray[i].getKategori().equalsIgnoreCase("makanan")) {
                System.out.printf("  %d. %-24s : Rp %,10.2f%n",
                        nomorUrut, menuArray[i].getNama(), menuArray[i].getHarga());
                nomorUrut++;
                adaMakanan = true;
            }
        }
        if (!adaMakanan) {
            System.out.println("  (Belum ada menu makanan)");
        }

        System.out.println("----------------------------------------------------------");

        // Kelompok Minuman
        System.out.println(" [ KATEGORI MINUMAN ]");
        boolean adaMinuman = false;
        for (int i = 0; i < menuArray.length; i++) {
            if (menuArray[i] != null && menuArray[i].getKategori().equalsIgnoreCase("minuman")) {
                System.out.printf("  %d. %-24s : Rp %,10.2f%n",
                        nomorUrut, menuArray[i].getNama(), menuArray[i].getHarga());
                nomorUrut++;
                adaMinuman = true;
            }
        }
        if (!adaMinuman) {
            System.out.println("  (Belum ada menu minuman)");
        }

        System.out.println("==========================================================");
    }

    /**
     * Mengambil objek Menu berdasarkan nomor urut tampilan (1-based) atau nama menu
     */
    public static Menu cariMenu(String query, Menu[] menuArray) {
        // Cek apakah input berupa nomor urut tampilan
        try {
            int nomorPilihan = Integer.parseInt(query);
            int nomorUrut = 1;

            // Urutan 1: Kategori Makanan
            for (int i = 0; i < menuArray.length; i++) {
                if (menuArray[i] != null && menuArray[i].getKategori().equalsIgnoreCase("makanan")) {
                    if (nomorUrut == nomorPilihan) {
                        return menuArray[i];
                    }
                    nomorUrut++;
                }
            }
            // Urutan 2: Kategori Minuman
            for (int i = 0; i < menuArray.length; i++) {
                if (menuArray[i] != null && menuArray[i].getKategori().equalsIgnoreCase("minuman")) {
                    if (nomorUrut == nomorPilihan) {
                        return menuArray[i];
                    }
                    nomorUrut++;
                }
            }
        } catch (NumberFormatException e) {
            // Bukan angka, lanjutkan pencarian berdasarkan nama menu
        }

        // Pencarian berdasarkan nama menu (case-insensitive)
        for (int i = 0; i < menuArray.length; i++) {
            if (menuArray[i] != null && menuArray[i].getNama().equalsIgnoreCase(query)) {
                return menuArray[i];
            }
        }

        return null; // Menu tidak ditemukan
    }

    /**
     * Mengambil indeks asli pada array daftarMenu berdasarkan nomor urut tampilan
     */
    public static int cariIndeksMenuDariNomorTampilan(int nomorPilihan, Menu[] menuArray) {
        int nomorUrut = 1;

        // Urutan 1: Kategori Makanan
        for (int i = 0; i < menuArray.length; i++) {
            if (menuArray[i] != null && menuArray[i].getKategori().equalsIgnoreCase("makanan")) {
                if (nomorUrut == nomorPilihan) {
                    return i;
                }
                nomorUrut++;
            }
        }
        // Urutan 2: Kategori Minuman
        for (int i = 0; i < menuArray.length; i++) {
            if (menuArray[i] != null && menuArray[i].getKategori().equalsIgnoreCase("minuman")) {
                if (nomorUrut == nomorPilihan) {
                    return i;
                }
                nomorUrut++;
            }
        }

        return -1; // Tidak ditemukan
    }

    // =========================================================================
    // BAGIAN 1: PEMESANAN PELANGGAN, PERHITUNGAN BIAYA, & CETAK STRUK
    // =========================================================================

    /**
     * Menu Pelanggan: Menampilkan menu, menerima pesanan tanpa batas hingga 'selesai',
     * menghitung total biaya, dan mencetak struk pesanan.
     */
    public static void menuPelanggan(Scanner scanner) {
        if (daftarMenu.length == 0) {
            System.out.println("\n  [!] Maaf, daftar menu restoran saat ini sedang kosong.");
            return;
        }

        tampilkanDaftarMenu(daftarMenu);

        System.out.println("\n--- PANDUAN PEMESANAN PELANGGAN ---");
        System.out.println("Masukkan nama menu atau nomor menu yang ingin dipesan (tidak terbatas).");
        System.out.println("Anda dapat menginput format 'Nama Menu = Jumlah' (contoh: Nasi Padang = 2)");
        System.out.println("atau ketik Nama/Nomor Menu terlebih dahulu lalu masukkan jumlahnya.");
        System.out.println("Ketik 'selesai' untuk menyelesaikan pemesanan, atau 'batal' untuk kembali.\n");

        // Array untuk menyimpan pesanan pelanggan (kapasitas awal 10, otomatis bertambah jika penuh)
        Menu[] pesananMenu = new Menu[10];
        int[] pesananQty = new int[10];
        int jumlahItemPesanan = 0;

        while (true) {
            System.out.print("Masukkan Pesanan ke-" + (jumlahItemPesanan + 1) + " (atau ketik 'selesai'): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("selesai")) {
                break;
            }
            if (input.equalsIgnoreCase("batal") || input.equalsIgnoreCase("kembali")) {
                System.out.println("  [i] Pemesanan dibatalkan. Kembali ke Menu Utama.");
                return;
            }
            if (input.isEmpty()) {
                System.out.println("  [!] Input tidak boleh kosong. Silakan masukkan menu atau ketik 'selesai'.");
                continue;
            }

            String namaAtauNomorMenu;
            int qty = -1;

            // Cek apakah pelanggan menginput dengan format "Menu = Jumlah"
            if (input.contains("=")) {
                String[] bagian = input.split("=", 2);
                namaAtauNomorMenu = bagian[0].trim();
                try {
                    qty = Integer.parseInt(bagian[1].trim());
                    if (qty <= 0) {
                        System.out.println("  [!] Jumlah pesanan harus lebih dari 0! Silakan input kembali.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  [!] Format jumlah tidak valid! Gunakan angka bulat positif.");
                    continue;
                }
            } else {
                namaAtauNomorMenu = input;
            }

            // Validasi apakah menu ada di dalam daftar menu restoran
            Menu menuDipilih = cariMenu(namaAtauNomorMenu, daftarMenu);
            if (menuDipilih == null) {
                System.out.println("  [!] Menu '" + namaAtauNomorMenu + "' tidak ada dalam daftar menu restoran! Silakan input kembali.");
                continue;
            }

            // Jika jumlah belum diinput melalui format '=', minta input jumlah dengan validasi berulang
            while (qty <= 0) {
                System.out.print("  Masukkan jumlah porsi untuk '" + menuDipilih.getNama() + "': ");
                String inputQty = scanner.nextLine().trim();
                try {
                    qty = Integer.parseInt(inputQty);
                    if (qty <= 0) {
                        System.out.println("  [!] Jumlah porsi minimal 1. Silakan masukkan kembali.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  [!] Input jumlah harus berupa angka! Silakan masukkan kembali.");
                }
            }

            // Cek apakah menu sudah pernah dipesan sebelumnya (jika ya, tambahkan jumlahnya)
            boolean sudahAdaDiKeranjang = false;
            for (int i = 0; i < jumlahItemPesanan; i++) {
                if (pesananMenu[i].getNama().equalsIgnoreCase(menuDipilih.getNama())) {
                    pesananQty[i] += qty;
                    sudahAdaDiKeranjang = true;
                    System.out.println("  [OK] Jumlah '" + menuDipilih.getNama() + "' ditambahkan menjadi " + pesananQty[i] + " porsi.");
                    break;
                }
            }

            // Jika belum ada di keranjang pesanan, tambahkan ke array pesanan
            if (!sudahAdaDiKeranjang) {
                // Perbesar ukuran array pesanan jika sudah penuh (mendukung pesanan tidak terbatas)
                if (jumlahItemPesanan >= pesananMenu.length) {
                    pesananMenu = perbesarArrayMenu(pesananMenu, pesananMenu.length * 2);
                    pesananQty = perbesarArrayInt(pesananQty, pesananQty.length * 2);
                }

                pesananMenu[jumlahItemPesanan] = menuDipilih;
                pesananQty[jumlahItemPesanan] = qty;
                jumlahItemPesanan++;
                System.out.println("  [OK] Berhasil memesan: " + menuDipilih.getNama() + " (" + qty + " porsi).");
            }
        }

        // Cek apakah ada pesanan yang dimasukkan sebelum ketik 'selesai'
        if (jumlahItemPesanan == 0) {
            System.out.println("\n  [i] Anda belum memesan menu apa pun. Kembali ke Menu Utama.");
            return;
        }

        // Proses perhitungan biaya dan cetak struk pesanan
        hitungDanCetakStruk(scanner, pesananMenu, pesananQty, jumlahItemPesanan);
    }

    /**
     * Menghitung total biaya pesanan menggunakan struktur pengulangan dan keputusan,
     * lalu mencetak struk pesanan ke layar.
     */
    public static void hitungDanCetakStruk(Scanner scanner, Menu[] pesananMenu, int[] pesananQty, int jumlahItemPesanan) {
        // 1. Menghitung total biaya pemesanan (Subtotal) dengan struktur pengulangan
        double totalBiayaPemesanan = 0;
        for (int i = 0; i < jumlahItemPesanan; i++) {
            totalBiayaPemesanan += pesananMenu[i].getHarga() * pesananQty[i];
        }

        // 2. Struktur Keputusan untuk Diskon & Penawaran Khusus
        // A. Penawaran Beli 1 Gratis 1 untuk salah satu kategori minuman jika total biaya > Rp 50.000
        Menu minumanPromo = null;
        for (int i = 0; i < jumlahItemPesanan; i++) {
            if (pesananMenu[i].getKategori().equalsIgnoreCase("minuman")) {
                minumanPromo = pesananMenu[i];
                break; // Mengambil salah satu minuman yang dipesan
            }
        }

        boolean dapatPromoBOGO = false;
        double potonganBOGO = 0;
        if (totalBiayaPemesanan > 50000 && minumanPromo != null) {
            dapatPromoBOGO = true;
            potonganBOGO = minumanPromo.getHarga(); // Gratis 1 item untuk salah satu minuman
        }

        // B. Diskon 10% jika total biaya keseluruhan pesanan melebihi Rp 100.000
        boolean dapatDiskon10 = false;
        double nominalDiskon10 = 0;
        if (totalBiayaPemesanan > 100000) {
            dapatDiskon10 = true;
            nominalDiskon10 = totalBiayaPemesanan * 0.10;
        }

        double totalPotongan = nominalDiskon10 + potonganBOGO;
        double hargaSetelahDiskon = totalBiayaPemesanan - totalPotongan;
        if (hargaSetelahDiskon < 0) {
            hargaSetelahDiskon = 0;
        }

        // 3. Menghitung Pajak (10% dari total biaya) dan Biaya Pelayanan (Rp 20.000)
        double biayaPajak = totalBiayaPemesanan * 0.10;
        double biayaPelayanan = 20000;

        // Total Tagihan Akhir yang harus dibayar
        double totalTagihanAkhir = hargaSetelahDiskon + biayaPajak + biayaPelayanan;

        // 4. Mencetak Struk Pesanan
        cetakStrukPesanan(pesananMenu, pesananQty, jumlahItemPesanan, totalBiayaPemesanan,
                dapatDiskon10, nominalDiskon10, dapatPromoBOGO, minumanPromo, potonganBOGO,
                hargaSetelahDiskon, biayaPajak, biayaPelayanan, totalTagihanAkhir);

        // 5. Simulasi Pembayaran Tunai
        while (true) {
            System.out.print("\nMasukkan Nominal Uang Pembayaran (atau tekan Enter untuk selesai): Rp ");
            String inputBayar = scanner.nextLine().trim();
            if (inputBayar.isEmpty()) {
                break;
            }
            try {
                double uangBayar = Double.parseDouble(inputBayar);
                if (uangBayar >= totalTagihanAkhir) {
                    double kembalian = uangBayar - totalTagihanAkhir;
                    System.out.printf("Uang Diterima  : Rp %,12.2f%n", uangBayar);
                    System.out.printf("Kembalian      : Rp %,12.2f%n", kembalian);
                    System.out.println("Pembayaran LUNAS. Terima kasih atas kunjungan Anda!");
                    break;
                } else {
                    double kurang = totalTagihanAkhir - uangBayar;
                    System.out.printf("  [!] Uang pembayaran kurang sebesar Rp %,12.2f. Silakan masukkan nominal yang cukup.%n", kurang);
                }
            } catch (NumberFormatException e) {
                System.out.println("  [!] Masukkan nominal angka yang valid!");
            }
        }
    }

    /**
     * Mencetak struk pesanan lengkap ke layar monitor
     */
    public static void cetakStrukPesanan(Menu[] pesananMenu, int[] pesananQty, int jumlahItemPesanan,
                                         double totalBiayaPemesanan, boolean dapatDiskon10, double nominalDiskon10,
                                         boolean dapatPromoBOGO, Menu minumanPromo, double potonganBOGO,
                                         double hargaSetelahDiskon, double biayaPajak, double biayaPelayanan,
                                         double totalTagihanAkhir) {
        System.out.println("\n==================================================================");
        System.out.println("                     STRUK PESANAN PELANGGAN                      ");
        System.out.println("                        RESTORAN NUSANTARA                        ");
        System.out.println("==================================================================");
        System.out.printf("%-22s %-8s %-15s %-15s%n", "Item Menu", "Jumlah", "Harga/Item", "Total Harga");
        System.out.println("------------------------------------------------------------------");

        // Mencetak daftar item yang dipesan dengan struktur pengulangan
        for (int i = 0; i < jumlahItemPesanan; i++) {
            double totalPerItem = pesananMenu[i].getHarga() * pesananQty[i];
            System.out.printf("%-22s %-8d Rp %,11.2f  Rp %,12.2f%n",
                    pesananMenu[i].getNama(),
                    pesananQty[i],
                    pesananMenu[i].getHarga(),
                    totalPerItem);
        }

        System.out.println("------------------------------------------------------------------");
        System.out.printf("Total Biaya Pemesanan                 : Rp %,14.2f%n", totalBiayaPemesanan);

        // Struktur Keputusan untuk menampilkan informasi Diskon / Penawaran Khusus
        if (dapatDiskon10 || dapatPromoBOGO) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("Informasi Diskon & Penawaran Khusus:");
            if (dapatDiskon10) {
                System.out.printf("  - Diskon 10%% (Pesanan > Rp 100.000) : -Rp %,13.2f%n", nominalDiskon10);
            }
            if (dapatPromoBOGO) {
                System.out.printf("  - Promo Beli 1 Gratis 1 Minuman     : -Rp %,13.2f%n", potonganBOGO);
                System.out.printf("    (Bonus 1x %s)%n", minumanPromo.getNama());
            }
            System.out.printf("Harga Setelah Diskon / Penawaran      : Rp %,14.2f%n", hargaSetelahDiskon);
        } else {
            System.out.println("Info Promo: Tidak ada diskon/penawaran (Min. > Rp 50.000)");
        }

        System.out.println("------------------------------------------------------------------");
        System.out.printf("Pajak Restoran (10%%)                  : Rp %,14.2f%n", biayaPajak);
        System.out.printf("Biaya Pelayanan                       : Rp %,14.2f%n", biayaPelayanan);
        System.out.println("==================================================================");
        System.out.printf("TOTAL BIAYA KESELURUHAN               : Rp %,14.2f%n", totalTagihanAkhir);
        System.out.println("==================================================================");
    }

    // =========================================================================
    // BAGIAN 2: MANAJEMEN / PENGELOLAAN MENU RESTORAN (PEMILIK RESTORAN)
    // =========================================================================

    /**
     * Menu Pengelolaan Restoran: Menambah menu baru, mengubah harga, menghapus menu,
     * dan kembali ke menu utama (parent).
     */
    public static void menuPengelolaan(Scanner scanner) {
        boolean diMenuPengelolaan = true;

        while (diMenuPengelolaan) {
            System.out.println("\n==========================================================");
            System.out.println("          MENU PENGELOLAAN RESTORAN (PEMILIK)             ");
            System.out.println("==========================================================");
            System.out.println("  1. Lihat Daftar Menu Restoran");
            System.out.println("  2. Tambah Menu Restoran Baru (Bisa Beberapa Sekaligus)");
            System.out.println("  3. Ubah Harga Menu Restoran");
            System.out.println("  4. Hapus Menu Restoran");
            System.out.println("  5. Kembali ke Menu Utama");
            System.out.println("==========================================================");
            System.out.print("Pilih menu pengelolaan (1-5): ");

            String pilihan = scanner.nextLine().trim();

            switch (pilihan) {
                case "1":
                    tampilkanDaftarMenu(daftarMenu);
                    break;
                case "2":
                    tambahMenuBaru(scanner);
                    break;
                case "3":
                    ubahHargaMenu(scanner);
                    break;
                case "4":
                    hapusMenuRestoran(scanner);
                    break;
                case "5":
                    System.out.println("  [i] Kembali ke Menu Utama...");
                    diMenuPengelolaan = false;
                    break;
                default:
                    System.out.println("  [!] Pilihan tidak valid! Silakan masukkan angka 1 sampai 5.");
                    break;
            }
        }
    }

    /**
     * Menambahkan beberapa menu restoran baru sekaligus menggunakan struktur pengulangan
     */
    public static void tambahMenuBaru(Scanner scanner) {
        System.out.println("\n--- TAMBAH MENU RESTORAN BARU ---");

        boolean tambahLagi = true;
        while (tambahLagi) {
            // 1. Input Nama Menu Baru
            String namaBaru = "";
            while (namaBaru.isEmpty()) {
                System.out.print("Masukkan Nama Menu Baru (atau ketik 'batal' untuk kembali): ");
                namaBaru = scanner.nextLine().trim();
                if (namaBaru.equalsIgnoreCase("batal")) {
                    System.out.println("  [i] Penambahan menu dibatalkan. Kembali ke Menu Pengelolaan.");
                    return;
                }
                if (namaBaru.isEmpty()) {
                    System.out.println("  [!] Nama menu tidak boleh kosong!");
                } else if (cariMenu(namaBaru, daftarMenu) != null) {
                    System.out.println("  [!] Menu '" + namaBaru + "' sudah ada di daftar menu! Gunakan nama lain.");
                    namaBaru = "";
                }
            }

            // 2. Input Kategori Menu (makanan / minuman) dengan validasi berulang
            String kategoriBaru = "";
            while (true) {
                System.out.print("Pilih Kategori Menu (1 = Makanan, 2 = Minuman, atau ketik makanan/minuman): ");
                String inputKat = scanner.nextLine().trim();
                if (inputKat.equals("1") || inputKat.equalsIgnoreCase("makanan")) {
                    kategoriBaru = "makanan";
                    break;
                } else if (inputKat.equals("2") || inputKat.equalsIgnoreCase("minuman")) {
                    kategoriBaru = "minuman";
                    break;
                } else {
                    System.out.println("  [!] Kategori tidak valid! Masukkan 'makanan' atau 'minuman'.");
                }
            }

            // 3. Input Harga Menu Baru dengan validasi berulang
            double hargaBaru = -1;
            while (hargaBaru <= 0) {
                System.out.print("Masukkan Harga Menu (Rp): ");
                String inputHarga = scanner.nextLine().trim();
                try {
                    hargaBaru = Double.parseDouble(inputHarga);
                    if (hargaBaru <= 0) {
                        System.out.println("  [!] Harga menu harus lebih dari Rp 0!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  [!] Input harga tidak valid! Masukkan angka yang benar.");
                }
            }

            // Tambahkan ke array daftarMenu
            Menu itemBaru = new Menu(namaBaru, hargaBaru, kategoriBaru);
            daftarMenu = tambahKeArrayMenu(daftarMenu, itemBaru);
            System.out.printf("  [OK] Menu '%s' (%s - Rp %,.2f) berhasil ditambahkan!%n",
                    namaBaru, kategoriBaru, hargaBaru);

            // Tanya apakah ingin menambah menu baru lagi (mendukung beberapa menu sekaligus)
            while (true) {
                System.out.print("\nApakah Anda ingin menambahkan menu baru lagi? (Ya/Tidak): ");
                String lanjut = scanner.nextLine().trim();
                if (lanjut.equalsIgnoreCase("Ya") || lanjut.equalsIgnoreCase("Y")) {
                    System.out.println();
                    break;
                } else if (lanjut.equalsIgnoreCase("Tidak") || lanjut.equalsIgnoreCase("T")) {
                    tambahLagi = false;
                    break;
                } else {
                    System.out.println("  [!] Silakan masukkan 'Ya' atau 'Tidak'.");
                }
            }
        }

        System.out.println("  [i] Selesai menambahkan menu. Kembali ke Menu Pengelolaan.");
    }

    /**
     * Mengubah harga menu restoran berdasarkan nomor menu dengan konfirmasi 'Ya'
     */
    public static void ubahHargaMenu(Scanner scanner) {
        if (daftarMenu.length == 0) {
            System.out.println("\n  [!] Daftar menu kosong, tidak ada menu yang dapat diubah.");
            return;
        }

        // Munculkan daftar menu terlebih dahulu
        tampilkanDaftarMenu(daftarMenu);
        System.out.println("--- UBAH HARGA MENU RESTORAN ---");

        int indeksTarget = -1;
        int nomorPilihan = -1;

        // Meminta input nomor menu dengan validasi berulang jika di luar pilihan
        while (indeksTarget == -1) {
            System.out.print("Masukkan nomor menu yang ingin diubah harganya (1-" + daftarMenu.length + ", atau 0 untuk batal): ");
            String inputNomor = scanner.nextLine().trim();

            if (inputNomor.equals("0") || inputNomor.equalsIgnoreCase("batal")) {
                System.out.println("  [i] Pengubahan harga dibatalkan. Kembali ke Menu Pengelolaan.");
                return;
            }

            try {
                nomorPilihan = Integer.parseInt(inputNomor);
                indeksTarget = cariIndeksMenuDariNomorTampilan(nomorPilihan, daftarMenu);
                if (indeksTarget == -1) {
                    System.out.println("  [!] Nomor menu tidak ditemukan dalam daftar! Silakan masukkan kembali.");
                }
            } catch (NumberFormatException e) {
                System.out.println("  [!] Input harus berupa nomor menu (angka)! Silakan masukkan kembali.");
            }
        }

        Menu menuTarget = daftarMenu[indeksTarget];
        System.out.printf("Menu terpilih: %s (Harga saat ini: Rp %,.2f)%n",
                menuTarget.getNama(), menuTarget.getHarga());

        // Input harga baru
        double hargaBaru = -1;
        while (hargaBaru <= 0) {
            System.out.print("Masukkan Harga Baru untuk '" + menuTarget.getNama() + "' (Rp): ");
            String inputHarga = scanner.nextLine().trim();
            try {
                hargaBaru = Double.parseDouble(inputHarga);
                if (hargaBaru <= 0) {
                    System.out.println("  [!] Harga harus lebih dari Rp 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("  [!] Input harga harus berupa angka! Silakan coba lagi.");
            }
        }

        // Konfirmasi sebelum dieksekusi
        System.out.printf("%n[KONFIRMASI] Apakah Anda yakin ingin mengubah harga '%s' dari Rp %,.2f menjadi Rp %,.2f?%n",
                menuTarget.getNama(), menuTarget.getHarga(), hargaBaru);
        System.out.print("Ketik 'Ya' untuk konfirmasi mengubah (atau lainnya untuk batal): ");
        String konfirmasi = scanner.nextLine().trim();

        if (konfirmasi.equalsIgnoreCase("Ya")) {
            menuTarget.setHarga(hargaBaru);
            System.out.println("  [OK] Harga menu '" + menuTarget.getNama() + "' berhasil diubah!");
            tampilkanDaftarMenu(daftarMenu);
        } else {
            System.out.println("  [i] Perubahan dibatalkan. Tidak ada perubahan pada daftar menu.");
        }

        System.out.println("  [i] Kembali ke Menu Pengelolaan.");
    }

    /**
     * Menghapus menu restoran berdasarkan nomor menu dengan konfirmasi 'Ya'
     */
    public static void hapusMenuRestoran(Scanner scanner) {
        if (daftarMenu.length == 0) {
            System.out.println("\n  [!] Daftar menu kosong, tidak ada menu yang dapat dihapus.");
            return;
        }

        // Munculkan daftar menu terlebih dahulu
        tampilkanDaftarMenu(daftarMenu);
        System.out.println("--- HAPUS MENU RESTORAN ---");

        int indeksTarget = -1;
        int nomorPilihan = -1;

        // Meminta input nomor menu dengan validasi berulang jika di luar pilihan
        while (indeksTarget == -1) {
            System.out.print("Masukkan nomor menu yang ingin dihapus (1-" + daftarMenu.length + ", atau 0 untuk batal): ");
            String inputNomor = scanner.nextLine().trim();

            if (inputNomor.equals("0") || inputNomor.equalsIgnoreCase("batal")) {
                System.out.println("  [i] Penghapusan menu dibatalkan. Kembali ke Menu Pengelolaan.");
                return;
            }

            try {
                nomorPilihan = Integer.parseInt(inputNomor);
                indeksTarget = cariIndeksMenuDariNomorTampilan(nomorPilihan, daftarMenu);
                if (indeksTarget == -1) {
                    System.out.println("  [!] Nomor menu tidak ditemukan dalam daftar! Silakan masukkan kembali.");
                }
            } catch (NumberFormatException e) {
                System.out.println("  [!] Input harus berupa nomor menu (angka)! Silakan masukkan kembali.");
            }
        }

        Menu menuTarget = daftarMenu[indeksTarget];

        // Konfirmasi sebelum dieksekusi
        System.out.printf("%n[KONFIRMASI] Apakah Anda yakin ingin menghapus menu '%s' (%s - Rp %,.2f)?%n",
                menuTarget.getNama(), menuTarget.getKategori(), menuTarget.getHarga());
        System.out.print("Ketik 'Ya' untuk konfirmasi menghapus (atau lainnya untuk batal): ");
        String konfirmasi = scanner.nextLine().trim();

        if (konfirmasi.equalsIgnoreCase("Ya")) {
            String namaDihapus = menuTarget.getNama();
            daftarMenu = hapusDariArrayMenu(daftarMenu, indeksTarget);
            System.out.println("  [OK] Menu '" + namaDihapus + "' berhasil dihapus dari daftar menu!");
            tampilkanDaftarMenu(daftarMenu);
        } else {
            System.out.println("  [i] Penghapusan dibatalkan. Tidak ada perubahan pada daftar menu.");
        }

        System.out.println("  [i] Kembali ke Menu Pengelolaan.");
    }

    // =========================================================================
    // METHOD BANTUAN MANAJEMEN ARRAY DINAMIS
    // =========================================================================

    private static Menu[] tambahKeArrayMenu(Menu[] arrayLama, Menu itemBaru) {
        Menu[] arrayBaru = new Menu[arrayLama.length + 1];
        for (int i = 0; i < arrayLama.length; i++) {
            arrayBaru[i] = arrayLama[i];
        }
        arrayBaru[arrayLama.length] = itemBaru;
        return arrayBaru;
    }

    private static Menu[] hapusDariArrayMenu(Menu[] arrayLama, int indeksHapus) {
        Menu[] arrayBaru = new Menu[arrayLama.length - 1];
        int idxBaru = 0;
        for (int i = 0; i < arrayLama.length; i++) {
            if (i != indeksHapus) {
                arrayBaru[idxBaru] = arrayLama[i];
                idxBaru++;
            }
        }
        return arrayBaru;
    }

    private static Menu[] perbesarArrayMenu(Menu[] arrayLama, int kapasitasBaru) {
        Menu[] arrayBaru = new Menu[kapasitasBaru];
        for (int i = 0; i < arrayLama.length; i++) {
            arrayBaru[i] = arrayLama[i];
        }
        return arrayBaru;
    }

    private static int[] perbesarArrayInt(int[] arrayLama, int kapasitasBaru) {
        int[] arrayBaru = new int[kapasitasBaru];
        for (int i = 0; i < arrayLama.length; i++) {
            arrayBaru[i] = arrayLama[i];
        }
        return arrayBaru;
    }
}
