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

        Produk[] daftar = new Produk[5];
        daftar[0] = new Makanan("MKN01", "Mie Sedap Goreng", 15000, 20, "2026-12-31");
        daftar[1] = new Makanan("MKN02", "Indomie Soto", 3500, 100, "2027-03-15");
        daftar[2] = new NonCoffe("N-CFE01", "Es Jeruk", 85000, 10, "2026-12-1");
        daftar[3] = new Coffe("CFE01", "Kopi Hitam", 150000, 5, "2026-12-1");
        daftar[4] = new Game("GM01", "Mainan Bocil",20000, 2, 1);

        // Perulangan Utama (DO-WHILE) untuk Menu Kasir
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

                case '1':
                    System.out.println();
                    System.out.println("--- DAFTAR PRODUK ---");
                    
                    // Perulangan FOR untuk menyapu array & Memanggil Polymorphism (hitungPajak)
                    for (int i = 0; i < daftar.length; i++) {
                        System.out.println(daftar[i].getKode() + " | "+ daftar[i].getNama()
                                + " | Rp " + daftar[i].getHarga()
                                + " | stok: " + daftar[i].getStok()
                                + " | " + daftar[i].getJenis()
                                + " | Pajak: " + daftar[i].hitungPajak() + "%"
                        );
                    }
                    System.out.println("Untuk membeli, pilih menu 2 lalu ketik kode produknya.");
                    break;

                case '2':
                    System.out.println();
                    System.out.println("--- DAFTAR PRODUK ---");
                    
                    for (int i = 0; i < daftar.length; i++) {
                        System.out.println(daftar[i].getKode() + " | " + daftar[i].getNama()
                                + " | Rp " + daftar[i].getHarga()
                                + " | stok: " + daftar[i].getStok());
                    }
                    System.out.print("Masukkan kode produk: ");
                    kode = input.next();

                    posisi = -1;
                    
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
                            // Mengubah data privat menggunakan method Setter (Encapsulation)
                            daftar[posisi].setJumlahBeli(daftar[posisi].getJumlahBeli() + jumlah);
                            System.out.println("Berhasil ditambahkan ke keranjang.");
                        }
                    }
                    break;

                case '3':
                    kosong = true;
                    long subtotalKeranjang = 0;
                    long totalPajak = 0;
                    System.out.println("\n--- ISI KERANJANG ---");

                    for (int i = 0; i < daftar.length; i++) {
                        if (daftar[i].getJumlahBeli() > 0) {
                            kosong = false;

                            long hargaBarang = (long) daftar[i].getHarga()* daftar[i].getJumlahBeli();
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

                case '4':
                    System.out.print("Masukkan kode produk yang mau dihapus: ");
                    kode = input.next();

                    posisi = -1;
                    
                    for (int i = 0; i < daftar.length; i++) {
                        if (daftar[i].getKode().equalsIgnoreCase(kode) && daftar[i].getJumlahBeli() > 0) {
                            posisi = i;
                        }
                    }

                    if (posisi == -1) {
                        System.out.println("Barang itu tidak ada di keranjang.");
                    } else {
                        daftar[posisi].setJumlahBeli(0);
                        System.out.println("Barang dihapus dari keranjang.");
                    }
                    break;

                case '5':
                    kosong = true;
                    long subtotalBayar = 0;
                    long totalPajakBayar = 0;
                    
                    for (int i = 0; i < daftar.length; i++) {
                        if (daftar[i].getJumlahBeli() > 0) {
                            kosong = false;
                            
                            long hargaBarang = (long) daftar[i].getHarga() * daftar[i].getJumlahBeli();
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
                            
                            for (int i = 0; i < daftar.length; i++) {
                                if (daftar[i].getJumlahBeli() > 0) {
                                    int itemTotal = daftar[i].getHarga() * daftar[i].getJumlahBeli();
                                    System.out.println(" " + daftar[i].getNama());
                                    System.out.println("    " + daftar[i].getJumlahBeli() + " x Rp " + daftar[i].getHarga() + " \t= Rp " + itemTotal);
                                    
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
                    
                case '6':
                    System.out.print("Masukkan kode produk: ");
                    kode = input.next();
                    posisi = -1;

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