package com.mycompany.uts;

import java.util.Scanner;

/**
 * @author ACER
 */
public class Uts {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        char pilihan;
        String kode;
        int jumlah, uang, posisi, nomorTransaksi = 0;
        int subtotal;
        long total;
        boolean kosong, batal;

        // --- PENERAPAN ARRAY OBJEK ---
        // Membuat wadah array 'daftar' dengan tipe data kelas induk (Produk)
        Produk[] daftar = new Produk[5];
        daftar[0] = new Makanan("MKN01", "Mie Sedap Goreng", 15000, 20, "2026-12-31");
        daftar[1] = new Makanan("MKN02", "Indomie Soto", 3500, 100, "2027-03-15");
        daftar[2] = new NonCoffe("N-CFE01", "Es Jeruk", 85000, 10, "2026-12-1");
        daftar[3] = new Coffe("CFE01", "Kopi Hitam", 150000, 5, "2026-12-1");
        daftar[4] = new Game("GM01", "Mainan Bocil",20000, 2, 1);

        // ==========================================
        // PERULANGAN (LOOPING) UTAMA: DO-WHILE
        // ==========================================
        // Berfungsi agar aplikasi kasir terus berjalan menampilkan menu berulang-ulang
        // sampai pengguna memilih menu '0' (Keluar).
        do {
            System.out.println();
            System.out.println("===== APLIKASI KASIR =====");
            System.out.println("1. Lihat daftar produk");
            System.out.println("2. Tambah barang ke keranjang");
            System.out.println("3. Lihat keranjang");
            System.out.println("4. Hapus barang dari keranjang");
            System.out.println("5. Bayar");
            System.out.println("6. Ubah data produk");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.next().charAt(0);

            switch (pilihan) {

                // ==========================================
                // MENU 1: LIHAT DAFTAR PRODUK
                // ==========================================
                case '1':
                    System.out.println();
                    System.out.println("--- DAFTAR PRODUK ---");
                    
                    // --- PERULANGAN (LOOPING) FOR & POLYMORPHISM ---
                    // Perulangan 'for' untuk menyapu seluruh isi array 'daftar' dari awal sampai akhir.
                    for (int i = 0; i < daftar.length; i++) {
                        System.out.println(daftar[i].getKode() + " | "+ daftar[i].getNama()
                                + " | Rp " + daftar[i].getHarga()
                                + " | stok: " + daftar[i].getStok()
                                + " | " + daftar[i].getJenis()
                                // --- POLYMORPHISM ---
                                // Memanggil method hitungPajak() secara dinamis lewat wadah induk Produk.
                                // Hasilnya otomatis berubah sesuai jenis produk (Makanan/Kopi/Game).
                                + " | Pajak: " + daftar[i].hitungPajak() + "%"
                        );
                    }
                    System.out.println("Untuk membeli, pilih menu 2 lalu ketik kode produknya.");
                    break;

                // ==========================================
                // MENU 2: TAMBAH BARANG KE KERANJANG
                // ==========================================
                case '2':
                    System.out.println();
                    System.out.println("--- DAFTAR PRODUK ---");
                    
                    // --- PERULANGAN (LOOPING) FOR ---
                    for (int i = 0; i < daftar.length; i++) {
                        System.out.println(daftar[i].getKode() + " | " + daftar[i].getNama()
                                + " | Rp " + daftar[i].getHarga()
                                + " | stok: " + daftar[i].getStok());
                    }
                    System.out.print("Masukkan kode produk: ");
                    kode = input.next();

                    posisi = -1;
                    
                    // --- PERULANGAN (LOOPING) FOR UNTUK PENCARIAN ---
                    // Menyapu array untuk mencocokkan kode produk yang diketik user
                    for (int i = 0; i < daftar.length; i++) {
                        if (daftar[i].getKode().equalsIgnoreCase(kode)) {
                            posisi = i;
                        }
                    }

                    if (posisi == -1) {
                        System.out.println("Produk tidak ditemukan.");
                    } else {
                        System.out.print("Jumlah beli: ");
                        jumlah = input.nextInt();

                        if (jumlah <= 0) {
                            System.out.println("Jumlah harus lebih dari 0.");
                        } else if (daftar[posisi].getJumlahBeli() + jumlah > daftar[posisi].getStok()) {
                            System.out.println("Stok tidak cukup! Stok " + daftar[posisi].getNama()
                                    + " tinggal " + daftar[posisi].getStok());
                        } else {
                            // --- ENCAPSULATION (Setter) ---
                            // Mengubah data jumlah beli secara aman menggunakan method setter
                            daftar[posisi].setJumlahBeli(daftar[posisi].getJumlahBeli() + jumlah);
                            System.out.println("Berhasil ditambahkan ke keranjang.");
                        }
                    }
                    break;

                // ==========================================
                // MENU 3: LIHAT KERANJANG
                // ==========================================
                case '3':
                    kosong = true;
                    long subtotalKeranjang = 0;
                    long totalPajak = 0;
                    System.out.println("\n--- ISI KERANJANG ---");

                    // --- PERULANGAN (LOOPING) FOR & POLYMORPHISM ---
                    for (int i = 0; i < daftar.length; i++) {
                        if (daftar[i].getJumlahBeli() > 0) {
                            kosong = false;

                            long hargaBarang = (long) daftar[i].getHarga()* daftar[i].getJumlahBeli();
                            
                            // --- POLYMORPHISM ---
                            // Memanggil hitungPajak() untuk menghitung nominal pajak tiap barang di keranjang
                            long pajakBarang = Math.round(hargaBarang * daftar[i].hitungPajak() / 100.0);

                            subtotalKeranjang += hargaBarang;
                            totalPajak += pajakBarang;

                            System.out.println(
                                    daftar[i].getNama()
                                    + " | " + daftar[i].getJumlahBeli()
                                    + " x Rp " + daftar[i].getHarga()
                                    + " | Pajak: Rp " + pajakBarang
                                    + " | Total: Rp " + (hargaBarang + pajakBarang)
                            );
                        }
                    }

                    if (kosong) {
                        System.out.println("Keranjang masih kosong.");
                    } else {
                        System.out.println("------------------------------");
                        System.out.println("Subtotal   : Rp " + subtotalKeranjang);
                        System.out.println("Total pajak: Rp " + totalPajak);
                        System.out.println("TOTAL      : Rp " + (subtotalKeranjang + totalPajak));
                    }
                    break;

                // ==========================================
                // MENU 4: HAPUS BARANG DARI KERANJANG
                // ==========================================
                case '4':
                    System.out.print("Masukkan kode produk yang mau dihapus: ");
                    kode = input.next();

                    posisi = -1;
                    
                    // --- PERULANGAN (LOOPING) FOR ---
                    for (int i = 0; i < daftar.length; i++) {
                        if (daftar[i].getKode().equalsIgnoreCase(kode) && daftar[i].getJumlahBeli() > 0) {
                            posisi = i;
                        }
                    }

                    if (posisi == -1) {
                        System.out.println("Barang itu tidak ada di keranjang.");
                    } else {
                        // --- ENCAPSULATION (Setter) ---
                        daftar[posisi].setJumlahBeli(0);
                        System.out.println("Barang dihapus dari keranjang.");
                    }
                    break;

                // ==========================================
                // MENU 5: PEMBAYARAN & CETAK STRUK
                // ==========================================
                case '5':
                    kosong = true;
                    long subtotalBayar = 0;
                    long totalPajakBayar = 0;
                    
                    // --- PERULANGAN (LOOPING) FOR & POLYMORPHISM ---
                    for (int i = 0; i < daftar.length; i++) {
                        if (daftar[i].getJumlahBeli() > 0) {
                            kosong = false;
                            
                            long hargaBarang = (long) daftar[i].getHarga() * daftar[i].getJumlahBeli();
                            
                            // --- POLYMORPHISM ---
                            long pajakBarang = Math.round(hargaBarang * daftar[i].hitungPajak() / 100.0);
                            
                            subtotalBayar += hargaBarang;
                            totalPajakBayar += pajakBarang;
                        }
                    }

                    if (kosong) {
                        System.out.println("Keranjang masih kosong.");
                    } else {
                        total = subtotalBayar + totalPajakBayar;
                        
                        System.out.println("Subtotal : Rp. " + subtotalBayar);
                        System.out.println("Total Pajak : Rp. " + totalPajakBayar);
                        System.out.println("Total bayar : Rp. " + total);

                        uang = 0;
                        batal = false;
                        
                        // ==========================================
                        // PERULANGAN (LOOPING) WHILE UNTUK PEMBAYARAN
                        // ==========================================
                        // Memastikan uang yang dimasukkan pembeli cukup atau belum.
                        // Jika kurang, perulangan akan terus meminta input uang.
                        while (uang < total && batal == false) {
                            System.out.print("Uang dari pembeli (0 = batal): ");
                            uang = input.nextInt();
                            if (uang == 0) {
                                batal = true;
                            } else if (uang < total) {
                                System.out.println("Uang kurang Rp " + (total - uang));
                            }
                        }

                        if (batal) {
                            System.out.println("Pembayaran dibatalkan.");
                        } else {
                            nomorTransaksi++;
                            System.out.println();
                            System.out.println("========================================");
                            System.out.println("                POS KOPI                ");
                            System.out.println("         STRUK PEMBELIAN BELANJA        ");
                            System.out.println("========================================");
                            System.out.println(" No. Transaksi : " + nomorTransaksi);
                            System.out.println("--------------------------------------");
                            
                            // --- PERULANGAN (LOOPING) FOR UNTUK CETAK STRUK & KURANGI STOK ---
                            for (int i = 0; i < daftar.length; i++) {
                                if (daftar[i].getJumlahBeli() > 0) {
                                    int itemTotal = daftar[i].getHarga() * daftar[i].getJumlahBeli();
                                    System.out.println(" " + daftar[i].getNama());
                                    System.out.println("    " + daftar[i].getJumlahBeli() + " x Rp " + daftar[i].getHarga() + " \t= Rp " + itemTotal);
                                    
                                    // Menggunakan method biasa dan setter
                                    daftar[i].kurangiStok(daftar[i].getJumlahBeli());
                                    daftar[i].setJumlahBeli(0);
                                }
                            }
                            System.out.println("--------------------------------------");
                            System.out.println(" SUBTOTAL      : Rp " + subtotalBayar);
                            System.out.println(" TOTAL         : Rp " + total);
                            System.out.println(" BAYAR         : Rp " + uang);
                            System.out.println(" KEMBALIAN     : Rp " + (uang - total));
                            System.out.println("========================================");
                            System.out.println("    TERIMA KASIH ATAS KUNJUNGANNYA    ");
                            System.out.println("========================================");
                        }
                    }
                    break;
                    
                // ==========================================
                // MENU 6: UBAH DATA PRODUK
                // ==========================================
                case '6':
                    System.out.print("Masukkan kode produk: ");
                    kode = input.next();
                    posisi = -1;

                    // --- PERULANGAN (LOOPING) FOR ---
                    for (int i = 0; i < daftar.length; i++) {
                        if (daftar[i].getKode().equalsIgnoreCase(kode)) {
                            posisi = i;
                            break;
                        }
                    }

                    if (posisi == -1) {
                        System.out.println("Produk tidak ditemukan.");
                    } else {
                        input.nextLine();
                        System.out.print("Kode baru: ");
                        String kodeBaru = input.nextLine();

                        System.out.print("Nama baru: ");
                        String namaBaru = input.nextLine();

                        System.out.print("Harga baru: ");
                        int hargaBaru = input.nextInt();

                        System.out.print("Stok baru: ");
                        int stokBaru = input.nextInt();

                        if (hargaBaru < 0 || stokBaru < 0) {
                            System.out.println("Harga dan stok tidak boleh negatif.");
                        } else {
                            // --- ENCAPSULATION (Setter) ---
                            // Mengubah atribut privat secara aman lewat setter
                            daftar[posisi].setKode(kodeBaru);
                            daftar[posisi].setNama(namaBaru);
                            daftar[posisi].setHarga(hargaBaru);
                            daftar[posisi].setStok(stokBaru);

                            System.out.println("Data produk berhasil diubah.");
                        }
                    }
                    break;

                case '0':
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Menu tidak ada!");
            }

        } while (pilihan != '0');

        input.close();
    }
}