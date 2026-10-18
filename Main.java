import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Inisialisasi data menu ke dalam Array
        Menu[] daftarMenu = inisialisasiMenu();

        // 2. Menampilkan daftar menu restoran
        tampilkanDaftarMenu(daftarMenu);

        // Array untuk menampung pesanan pelanggan (maksimal 4 menu)
        Menu[] pesananMenu = new Menu[4];
        int[] pesananQty = new int[4];

        // 3. Menerima pesanan dari pelanggan
        System.out.println("--- PANDUAN PEMESANAN ---");
        System.out.println("Pelanggan dapat memesan maksimal 4 menu.");
        System.out.println("Contoh format input : Nasi Padang = 2");
        System.out.println("Ketik 'selesai' atau tekan Enter jika selesai memesan kurang dari 4 menu.\n");

        terimaPesanan(scanner, daftarMenu, pesananMenu, pesananQty);

        // Validasi: Cek apakah ada minimal 1 pesanan yang berhasil dicatat
        if (pesananMenu[0] == null && pesananMenu[1] == null && 
            pesananMenu[2] == null && pesananMenu[3] == null) {
            System.out.println("\nTidak ada menu yang dipesan. Program selesai.");
        } else {
            // 4. Hitung total dan cetak struk pesanan
            prosesTransaksi(scanner, pesananMenu, pesananQty);
        }

        scanner.close();
    }

    public static Menu[] inisialisasiMenu() {
        Menu[] menu = new Menu[8];

        // Kategori Makanan (Indeks 0 - 3)
        menu[0] = new Menu("Nasi Padang", 25000, "makanan");
        menu[1] = new Menu("Ayam Bakar", 22000, "makanan");
        menu[2] = new Menu("Mie Goreng Spesial", 18000, "makanan");
        menu[3] = new Menu("Sate Ayam", 20000, "makanan");

        // Kategori Minuman (Indeks 4 - 7)
        menu[4] = new Menu("Es Teh Manis", 5000, "minuman");
        menu[5] = new Menu("Es Jeruk", 7000, "minuman");
        menu[6] = new Menu("Jus Alpukat", 15000, "minuman");
        menu[7] = new Menu("Kopi Susu", 12000, "minuman");

        return menu;
    }

    public static void tampilkanDaftarMenu(Menu[] daftarMenu) {
        System.out.println("==========================================================");
        System.out.println("              DAFTAR MENU RESTORAN NUSANTARA              ");
        System.out.println("==========================================================");

        // Kelompok Makanan
        System.out.println(" [ KATEGORI MAKANAN ]");
        System.out.printf("  1. %-22s (Makanan) : Rp %,10.2f%n", daftarMenu[0].getNama(), daftarMenu[0].getHarga());
        System.out.printf("  2. %-22s (Makanan) : Rp %,10.2f%n", daftarMenu[1].getNama(), daftarMenu[1].getHarga());
        System.out.printf("  3. %-22s (Makanan) : Rp %,10.2f%n", daftarMenu[2].getNama(), daftarMenu[2].getHarga());
        System.out.printf("  4. %-22s (Makanan) : Rp %,10.2f%n", daftarMenu[3].getNama(), daftarMenu[3].getHarga());

        System.out.println("----------------------------------------------------------");

        // Kelompok Minuman
        System.out.println(" [ KATEGORI MINUMAN ]");
        System.out.printf("  5. %-22s (Minuman) : Rp %,10.2f%n", daftarMenu[4].getNama(), daftarMenu[4].getHarga());
        System.out.printf("  6. %-22s (Minuman) : Rp %,10.2f%n", daftarMenu[5].getNama(), daftarMenu[5].getHarga());
        System.out.printf("  7. %-22s (Minuman) : Rp %,10.2f%n", daftarMenu[6].getNama(), daftarMenu[6].getHarga());
        System.out.printf("  8. %-22s (Minuman) : Rp %,10.2f%n", daftarMenu[7].getNama(), daftarMenu[7].getHarga());
        System.out.println("==========================================================\n");
    }

    public static void terimaPesanan(Scanner scanner, Menu[] daftarMenu, Menu[] pesananMenu, int[] pesananQty) {
        // Slot Pesanan 1
        System.out.print("Pesanan 1: ");
        String input1 = scanner.nextLine().trim();
        if (!input1.equalsIgnoreCase("selesai") && !input1.isEmpty()) {
            olahInputPesanan(input1, daftarMenu, pesananMenu, pesananQty, 0);

            // Slot Pesanan 2
            System.out.print("Pesanan 2: ");
            String input2 = scanner.nextLine().trim();
            if (!input2.equalsIgnoreCase("selesai") && !input2.isEmpty()) {
                olahInputPesanan(input2, daftarMenu, pesananMenu, pesananQty, 1);

                // Slot Pesanan 3
                System.out.print("Pesanan 3: ");
                String input3 = scanner.nextLine().trim();
                if (!input3.equalsIgnoreCase("selesai") && !input3.isEmpty()) {
                    olahInputPesanan(input3, daftarMenu, pesananMenu, pesananQty, 2);

                    // Slot Pesanan 4 (Maksimal)
                    System.out.print("Pesanan 4: ");
                    String input4 = scanner.nextLine().trim();
                    if (!input4.equalsIgnoreCase("selesai") && !input4.isEmpty()) {
                        olahInputPesanan(input4, daftarMenu, pesananMenu, pesananQty, 3);
                    }
                }
            }
        }
    }

    public static void olahInputPesanan(String input, Menu[] daftarMenu, Menu[] pesananMenu, int[] pesananQty, int slotIndex) {
        String namaMenuDicari;
        int jumlahPesanan = 1;

        if (input.contains("=")) {
            String[] bagian = input.split("=");
            if (bagian.length == 2) {
                namaMenuDicari = bagian[0].trim();
                try {
                    jumlahPesanan = Integer.parseInt(bagian[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("  [!] Format jumlah tidak valid. Jumlah diatur ke 1.");
                    jumlahPesanan = 1;
                }
            } else {
                namaMenuDicari = input.trim();
            }
        } else {
            // Jika pengguna hanya mengetik nama menu tanpa simbol '='
            namaMenuDicari = input.trim();
        }

        if (jumlahPesanan <= 0) {
            System.out.println("  [!] Jumlah pesanan minimal 1.");
            return;
        }

        // Mencari menu pada daftar menu
        Menu menuDitemukan = cariMenu(namaMenuDicari, daftarMenu);
        if (menuDitemukan != null) {
            pesananMenu[slotIndex] = menuDitemukan;
            pesananQty[slotIndex] = jumlahPesanan;
            System.out.println("  [OK] Ditambahkan: " + menuDitemukan.getNama() + " (" + jumlahPesanan + " porsi)");
        } else {
            System.out.println("  [!] Menu '" + namaMenuDicari + "' tidak ditemukan!");
        }
    }


    public static Menu cariMenu(String query, Menu[] daftarMenu) {
        if (daftarMenu[0].getNama().equalsIgnoreCase(query) || query.equals("1")) {
            return daftarMenu[0];
        } else if (daftarMenu[1].getNama().equalsIgnoreCase(query) || query.equals("2")) {
            return daftarMenu[1];
        } else if (daftarMenu[2].getNama().equalsIgnoreCase(query) || query.equals("3")) {
            return daftarMenu[2];
        } else if (daftarMenu[3].getNama().equalsIgnoreCase(query) || query.equals("4")) {
            return daftarMenu[3];
        } else if (daftarMenu[4].getNama().equalsIgnoreCase(query) || query.equals("5")) {
            return daftarMenu[4];
        } else if (daftarMenu[5].getNama().equalsIgnoreCase(query) || query.equals("6")) {
            return daftarMenu[5];
        } else if (daftarMenu[6].getNama().equalsIgnoreCase(query) || query.equals("7")) {
            return daftarMenu[6];
        } else if (daftarMenu[7].getNama().equalsIgnoreCase(query) || query.equals("8")) {
            return daftarMenu[7];
        } else {
            return null; // Menu tidak ditemukan
        }
    }

    public static void prosesTransaksi(Scanner scanner, Menu[] pesananMenu, int[] pesananQty) {
        // --- 1. Menghitung Total Biaya Pemesanan (Subtotal) ---
        double subtotal = 0;
        if (pesananMenu[0] != null) subtotal += pesananMenu[0].getHarga() * pesananQty[0];
        if (pesananMenu[1] != null) subtotal += pesananMenu[1].getHarga() * pesananQty[1];
        if (pesananMenu[2] != null) subtotal += pesananMenu[2].getHarga() * pesananQty[2];
        if (pesananMenu[3] != null) subtotal += pesananMenu[3].getHarga() * pesananQty[3];

        // --- 2. Struktur Keputusan Diskon & Penawaran Khusus ---
        Menu minumanPromo = null;
        if (pesananMenu[0] != null && pesananMenu[0].getKategori().equalsIgnoreCase("minuman")) {
            minumanPromo = pesananMenu[0];
        } else if (pesananMenu[1] != null && pesananMenu[1].getKategori().equalsIgnoreCase("minuman")) {
            minumanPromo = pesananMenu[1];
        } else if (pesananMenu[2] != null && pesananMenu[2].getKategori().equalsIgnoreCase("minuman")) {
            minumanPromo = pesananMenu[2];
        } else if (pesananMenu[3] != null && pesananMenu[3].getKategori().equalsIgnoreCase("minuman")) {
            minumanPromo = pesananMenu[3];
        }

        double potonganBOGO = 0.0;
        boolean dapatPromoBOGO = false;
        if (subtotal > 50000 && minumanPromo != null) {
            dapatPromoBOGO = true;
            potonganBOGO = minumanPromo.getHarga(); // Potongan senilai 1 porsi minuman tersebut
        }

        // B. Diskon 10% jika subtotal > 100.000
        double diskon10Persen = 0.0;
        boolean dapatDiskon10 = false;
        if (subtotal > 100000) {
            dapatDiskon10 = true;
            diskon10Persen = subtotal * 0.10;
        }

        // Total potongan diskon dan penawaran
        double totalPotongan = diskon10Persen + potonganBOGO;
        double subtotalSetelahDiskon = subtotal - totalPotongan;
        if (subtotalSetelahDiskon < 0) {
            subtotalSetelahDiskon = 0;
        }

        // --- 3. Pajak dan Biaya Pelayanan ---
        // Pajak 10% dihitung dari total biaya pemesanan setelah diskon
        double pajak = subtotalSetelahDiskon * 0.10;
        double biayaPelayanan = 20000.0;

        // Total biaya keseluruhan akhir
        double totalAkhir = subtotalSetelahDiskon + pajak + biayaPelayanan;

        // --- 4. Mencetak Struk Pesanan ---
        cetakStruk(pesananMenu, pesananQty, subtotal, dapatDiskon10, diskon10Persen, 
                   dapatPromoBOGO, minumanPromo, potonganBOGO, subtotalSetelahDiskon, 
                   pajak, biayaPelayanan, totalAkhir);

        // --- 5. Pembayaran Tunai & Kembalian ---
        System.out.print("\nMasukkan Nominal Pembayaran (Uang Tunai): Rp ");
        if (scanner.hasNextDouble()) {
            double uangBayar = scanner.nextDouble();
            if (uangBayar >= totalAkhir) {
                double kembalian = uangBayar - totalAkhir;
                System.out.printf("Uang Diterima  : Rp %,12.2f%n", uangBayar);
                System.out.printf("Kembalian      : Rp %,12.2f%n", kembalian);
                System.out.println("Pembayaran BERHASIL. Terima kasih telah berkunjung!\n");
            } else {
                double kurang = totalAkhir - uangBayar;
                System.out.printf("Uang Diterima  : Rp %,12.2f%n", uangBayar);
                System.out.printf("Uang Kurang    : Rp %,12.2f%n", kurang);
                System.out.println("Pembayaran BELUM LUNAS. Silakan selesaikan pembayaran.\n");
            }
        }
    }

    public static void cetakStruk(Menu[] pesananMenu, int[] pesananQty, double subtotal,
                                  boolean dapatDiskon10, double diskon10Persen,
                                  boolean dapatPromoBOGO, Menu minumanPromo, double potonganBOGO,
                                  double subtotalSetelahDiskon, double pajak, 
                                  double biayaPelayanan, double totalAkhir) {
        
        System.out.println("\n");
        System.out.println("==========================================================");
        System.out.println("                  STRUK PEMBAYARAN RESTORAN               ");
        System.out.println("                     RESTORAN NUSANTARA                   ");
        System.out.println("==========================================================");
        System.out.printf("%-20s %-8s %-12s %-14s%n", "Item Menu", "Jumlah", "Harga/Item", "Total Harga");
        System.out.println("----------------------------------------------------------");

        // Menampilkan Item Pesanan ke-1
        if (pesananMenu[0] != null) {
            double totalItem0 = pesananMenu[0].getHarga() * pesananQty[0];
            System.out.printf("%-20s %-8d Rp %,9.2f  Rp %,11.2f%n", 
                pesananMenu[0].getNama(), pesananQty[0], pesananMenu[0].getHarga(), totalItem0);
        }

        // Menampilkan Item Pesanan ke-2
        if (pesananMenu[1] != null) {
            double totalItem1 = pesananMenu[1].getHarga() * pesananQty[1];
            System.out.printf("%-20s %-8d Rp %,9.2f  Rp %,11.2f%n", 
                pesananMenu[1].getNama(), pesananQty[1], pesananMenu[1].getHarga(), totalItem1);
        }

        // Menampilkan Item Pesanan ke-3
        if (pesananMenu[2] != null) {
            double totalItem2 = pesananMenu[2].getHarga() * pesananQty[2];
            System.out.printf("%-20s %-8d Rp %,9.2f  Rp %,11.2f%n", 
                pesananMenu[2].getNama(), pesananQty[2], pesananMenu[2].getHarga(), totalItem2);
        }

        // Menampilkan Item Pesanan ke-4
        if (pesananMenu[3] != null) {
            double totalItem3 = pesananMenu[3].getHarga() * pesananQty[3];
            System.out.printf("%-20s %-8d Rp %,9.2f  Rp %,11.2f%n", 
                pesananMenu[3].getNama(), pesananQty[3], pesananMenu[3].getHarga(), totalItem3);
        }

        System.out.println("----------------------------------------------------------");
        System.out.printf("Total Biaya Pemesanan (Subtotal)      : Rp %,12.2f%n", subtotal);

        // Menampilkan Penawaran Khusus jika ada (Struktur Keputusan)
        if (dapatPromoBOGO || dapatDiskon10) {
            System.out.println("\n--- DISKON & PENAWARAN KHUSUS ---");
            
            if (dapatDiskon10) {
                System.out.printf("  * Diskon 10%% (> Rp 100.000)          : -Rp %,11.2f%n", diskon10Persen);
            }

            if (dapatPromoBOGO) {
                System.out.printf("  * Promo Beli 1 Gratis 1 Minuman      : -Rp %,11.2f%n", potonganBOGO);
                System.out.printf("    (Gratis 1 pcs: %s)%n", minumanPromo.getNama());
            }

            System.out.printf("Total Biaya Setelah Diskon/Promo      : Rp %,12.2f%n", subtotalSetelahDiskon);
        } else {
            System.out.println("(Tidak ada diskon/penawaran yang memenuhi syarat)");
        }

        System.out.println("----------------------------------------------------------");
        System.out.printf("Pajak Restoran (10%%)                   : Rp %,12.2f%n", pajak);
        System.out.printf("Biaya Pelayanan                        : Rp %,12.2f%n", biayaPelayanan);
        System.out.println("==========================================================");
        System.out.printf("TOTAL PEMBAYARAN KESELURUHAN           : Rp %,12.2f%n", totalAkhir);
        System.out.println("==========================================================");
    }
}
