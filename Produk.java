package com.mycompany.uts;

// ==========================================
// 1. ABSTRACTION (Abstract Class)
// ==========================================
public abstract class Produk {

    // Atribut privat (Encapsulation data)
    private String kode, nama;
    private int harga, stok, jumlahBeli;

    // --- CONSTRUCTOR KELAS INDUK ---
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
    
    // Method Setter untuk mengubah kode produk secara aman
    public void setKode(String kode) { 
        this.kode = kode; 
    }

    // Method Setter untuk mengubah nama produk secara aman
    public void setNama(String nama) { 
        this.nama = nama; 
    }

    // Method Setter untuk mengubah harga produk secara aman
    public void setHarga(int harga) { 
        this.harga = harga; 
    }

    // Method Setter untuk mengubah stok produk secara aman
    public void setStok(int stok) { 
        this.stok = stok; 
    }

    // Method Setter untuk mengubah jumlah beli secara aman
    public void setJumlahBeli(int jumlahBeli) { 
        this.jumlahBeli = jumlahBeli; 
    }

    // ==========================================
    // 2. ENCAPSULATION (Getter Methods)
    // ==========================================

    // Method Getter untuk mengambil nilai kode
    public String getKode() { 
        return kode; 
    }

    // Method Getter untuk mengambil nilai nama
    public String getNama() { 
        return nama; 
    }

    // Method Getter untuk mengambil nilai harga
    public int getHarga() { 
        return harga; 
    }

    // Method Getter untuk mengambil nilai stok
    public int getStok() { 
        return stok; 
    }

    // Method Getter untuk mengambil nilai jumlah beli
    public int getJumlahBeli() { 
        return jumlahBeli; 
    }

    // Method helper biasa untuk mengurangi stok
    public void kurangiStok(int jumlah) {
        this.stok -= jumlah;
    }

    // ==========================================
    // 1. ABSTRACTION (Abstract Methods)
    // ==========================================
    
    // Method abstrak 1: Kontrak kosong untuk perhitungan pajak
    public abstract double hitungPajak();

    // Method abstrak 2: Kontrak kosong untuk jenis produk
    public abstract String getJenis();
}


// ==========================================
// 3. INHERITANCE & 4. POLYMORPHISM (Makanan)
// ==========================================
class Makanan extends Produk {
    private String tanggalKadaluarsa;

    // --- 3. INHERITANCE (Constructor & Super Method) ---
    public Makanan(String kode, String nama, int harga, int stok, String tanggalKadaluarsa) {
        super(kode, nama, harga, stok); // Mewarisi atribut dari constructor induk Produk
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    // --- 4. POLYMORPHISM (Method Overriding) ---
    // Mengubah bentuk method hitungPajak() khusus untuk Makanan (10%)
    @Override
    public double hitungPajak() {
        return 10; 
    }

    // --- 4. POLYMORPHISM (Method Overriding) ---
    // Mengubah bentuk method getJenis() khusus untuk Makanan
    @Override
    public String getJenis() {
        return "Makanan (exp " + tanggalKadaluarsa + ")";
    }
}


// ==========================================
// 3. INHERITANCE & 4. POLYMORPHISM (NonCoffe)
// ==========================================
class NonCoffe extends Produk {
    private String tanggalKadaluarsa;

    // --- 3. INHERITANCE ---
    public NonCoffe(String kode, String nama, int harga, int stok, String tanggalKadaluarsa) {
        super(kode, nama, harga, stok);
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    // --- 4. POLYMORPHISM ---
    @Override
    public double hitungPajak() {
        return 5; // Pajak khusus NonCoffe: 5%
    }

    // --- 4. POLYMORPHISM ---
    @Override
    public String getJenis() {
        return "NonCoffe (exp " + tanggalKadaluarsa + ")";
    }
}


// ==========================================
// 3. INHERITANCE & 4. POLYMORPHISM (Coffe)
// ==========================================
class Coffe extends Produk {
    private String tanggalKadaluarsa;

    // --- 3. INHERITANCE ---
    public Coffe(String kode, String nama, int harga, int stok, String tanggalKadaluarsa) {
        super(kode, nama, harga, stok);
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    // --- 4. POLYMORPHISM ---
    @Override
    public double hitungPajak() {
        return 4; // Pajak khusus Coffe: 4%
    }

    // --- 4. POLYMORPHISM ---
    @Override
    public String getJenis() {
        return "Coffe (exp " + tanggalKadaluarsa + ")";
    }
}


// ==========================================
// 3. INHERITANCE & 4. POLYMORPHISM (Game)
// ==========================================
class Game extends Produk {
    private int garansiBulan;

    // --- 3. INHERITANCE ---
    public Game(String kode, String nama, int harga, int stok, int garansi) {
        super(kode, nama, harga, stok);
        this.garansiBulan = garansi;
    }

    // --- 4. POLYMORPHISM ---
    @Override
    public double hitungPajak() {
        return 3; // Pajak khusus Game: 3%
    }

    // --- 4. POLYMORPHISM ---
    @Override
    public String getJenis() {
        return "Game (garansi " + garansiBulan + " bulan)";
    }
}