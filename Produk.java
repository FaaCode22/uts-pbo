package com.mycompany.uts;

// --- ABSTRACTION (Abstract Class) ---
// Kelas 'Produk' bertindak sebagai kerangka/standar dasar umum yang tidak bisa di-new langsung.
public abstract class Produk {

    // --- ENCAPSULATION (Private Data) ---
    // Atribut disembunyikan menggunakan 'private' agar aman dari akses luar secara langsung.
    private String kode, nama;
    private int harga, stok, jumlahBeli;

    public Produk(String kode, String nama, int harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.jumlahBeli = 0;
    }

    // --- ENCAPSULATION (Setters & Getters) ---
    // Jalan resmi untuk mengambil (get) dan mengubah (set) data yang berstatus private.
    public void setKode(String kode) { this.kode = kode; }
    public void setNama(String nama) { this.nama = nama; }
    public void setHarga(int harga) { this.harga = harga; }
    public void setStok(int stok) { this.stok = stok; }
    public void setJumlahBeli(int jumlahBeli) { this.jumlahBeli = jumlahBeli; }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public int getStok() { return stok; }
    public int getJumlahBeli() { return jumlahBeli; }

    public void kurangiStok(int jumlah) {
        this.stok -= jumlah;
    }

    // --- ABSTRACTION (Abstract Methods) ---
    // Kontrak wajib yang harus diisi ulang oleh semua kelas anak.
    public abstract double hitungPajak();
    public abstract String getJenis();
}


// --- INHERITANCE (Pewarisan) & POLYMORPHISM ---
// 'Makanan' mewarisi sifat dari 'Produk' menggunakan kata kunci 'extends'.
class Makanan extends Produk {
    private String tanggalKadaluarsa;

    public Makanan(String kode, String nama, int harga, int stok, String tanggalKadaluarsa) {
        super(kode, nama, harga, stok); // Memanggil constructor induk (fondasi)
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    // --- POLYMORPHISM (Method Overriding) ---
    // Menulis ulang method abstrak milik induk dengan versi khusus untuk Makanan.
    @Override
    public double hitungPajak() {
        return 10; // Pajak makanan 10%
    }

    @Override
    public String getJenis() {
        return "Makanan (exp " + tanggalKadaluarsa + ")";
    }
}

class NonCoffe extends Produk {
    private String tanggalKadaluarsa;

    public NonCoffe(String kode, String nama, int harga, int stok, String tanggalKadaluarsa) {
        super(kode, nama, harga, stok);
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    @Override
    public double hitungPajak() {
        return 5; // Pajak non-kopi 5%
    }

    @Override
    public String getJenis() {
        return "NonCoffe (exp " + tanggalKadaluarsa + ")";
    }
}

class Coffe extends Produk {
    private String tanggalKadaluarsa;

    public Coffe(String kode, String nama, int harga, int stok, String tanggalKadaluarsa) {
        super(kode, nama, harga, stok);
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    @Override
    public double hitungPajak() {
        return 4; // Pajak kopi 4%
    }

    @Override
    public String getJenis() {
        return "Coffe (exp " + tanggalKadaluarsa + ")";
    }
}

class Game extends Produk {
    private int garansiBulan;

    public Game(String kode, String nama, int harga, int stok, int garansi) {
        super(kode, nama, harga, stok);
        this.garansiBulan = garansi;
    }

    @Override
    public double hitungPajak() {
        return 3; // Pajak game 3%
    }

    @Override
    public String getJenis() {
        return "Game (garansi " + garansiBulan + " bulan)";
    }
}