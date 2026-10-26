package com.mycompany.uts;

// ==========================================
// 1. ABSTRACTION (Abstract Class)
// ==========================================
// Kelas Induk 'Produk' dideklarasikan abstract (kerangka umum, tidak bisa di-new langsung)
public abstract class Produk {

    // ==========================================
// 2. ENCAPSULATION (Private Data Fields)
// ==========================================
// Atribut disembunyikan menggunakan 'private' agar aman dari akses luar
    private String kode, nama;
    private int harga, stok, jumlahBeli;

    // Constructor Induk
    public Produk(String kode, String nama, int harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.jumlahBeli = 0;
    }

    // ==========================================
// 2. ENCAPSULATION (Setter Methods)
// ==========================================
// Method untuk MENGUBAH nilai variabel privat secara aman dari luar kelas
    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    // ==========================================
// 2. ENCAPSULATION (Getter Methods)
// ==========================================
// Method untuk MEMBACA nilai variabel privat dari luar kelas
    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    // Method biasa untuk mengurangi stok saat transaksi
    public void kurangiStok(int jumlah) {
        this.stok -= jumlah;
    }

    // ==========================================
// 1. ABSTRACTION (Abstract Methods)
// ==========================================
// Kontrak wajib berupa kerangka kosong tanpa isi, wajib di-override oleh kelas anak
    public abstract double hitungPajak();

    public abstract String getJenis();
}


// ==========================================
// 3. INHERITANCE (Pewarisan)
// ==========================================
// Kelas 'Makanan' mewarisi sifat dari kelas 'Produk' menggunakan 'extends'
class Makanan extends Produk {
    private String tanggalKadaluarsa;

    // Constructor Anak
    public Makanan(String kode, String nama, int harga, int stok, String tanggalKadaluarsa) {
        // ==========================================
        // 3. INHERITANCE (Super Method)
        // ==========================================
        // Keyword 'super' memanggil constructor dari kelas induk (Produk) untuk mewariskan atribut dasar
        super(kode, nama, harga, stok);
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    // ==========================================
    // 4. POLYMORPHISM (Method Overriding)
    // ==========================================
    // Menulis ulang (override) method abstrak hitungPajak() milik induk dengan rumus spesifik Makanan
    @Override
    public double hitungPajak() {
        return 10; // Pajak khusus makanan: 10%
    }

    // Method overriding untuk menampilkan jenis produk yang spesifik
    @Override
    public String getJenis() {
        return "Makanan (exp " + tanggalKadaluarsa + ")";
    }
}


// Kelas 'NonCoffe' mewarisi 'Produk' (Inheritance)
class NonCoffe extends Produk {
    private String tanggalKadaluarsa;

    public NonCoffe(String kode, String nama, int harga, int stok, String tanggalKadaluarsa) {
        // Memanggil constructor induk lewat 'super' (Inheritance)
        super(kode, nama, harga, stok);
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    // Polymorphism (Method Overriding) untuk NonCoffe
    @Override
    public double hitungPajak() {
        return 5; // Pajak khusus NonCoffe: 5%
    }

    @Override
    public String getJenis() {
        return "NonCoffe (exp " + tanggalKadaluarsa + ")";
    }
}


// Kelas 'Coffe' mewarisi 'Produk' (Inheritance)
class Coffe extends Produk {
    private String tanggalKadaluarsa;

    public Coffe(String kode, String nama, int harga, int stok, String tanggalKadaluarsa) {
        // Memanggil constructor induk lewat 'super' (Inheritance)
        super(kode, nama, harga, stok);
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    // Polymorphism (Method Overriding) untuk Coffe
    @Override
    public double hitungPajak() {
        return 4; // Pajak khusus Coffe: 4%
    }

    @Override
    public String getJenis() {
        return "Coffe (exp " + tanggalKadaluarsa + ")";
    }
}


// Kelas 'Game' mewarisi 'Produk' (Inheritance)
class Game extends Produk {
    private int garansiBulan;

    public Game(String kode, String nama, int harga, int stok, int garansi) {
        // Memanggil constructor induk lewat 'super' (Inheritance)
        super(kode, nama, harga, stok);
        this.garansiBulan = garansi;
    }

    // Polymorphism (Method Overriding) untuk Game
    @Override
    public double hitungPajak() {
        return 3; // Pajak khusus Game: 3%
    }

    @Override
    public String getJenis() {
        return "Game (garansi " + garansiBulan + " bulan)";
    }
}