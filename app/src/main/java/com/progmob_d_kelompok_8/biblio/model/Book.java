package com.progmob_d_kelompok_8.biblio.model;

public class Book {
    private int idBuku, idPengguna, jumlahPembaca, peringkat;
    private String namaJenisBuku, namaGenre, namaPenulis, namaPenerbit, judulBuku, tglTerbit, sinopsis;
    private float skor;
    private byte[] gambarSampul;

    public Book(int idBuku
            , int idPengguna
            , String namaJenisBuku
            , String namaGenre
            , String namaPenulis
            , String namaPenerbit
            , String judulBuku
            , String tglTerbit
            , String sinopsis
            , float skor
            , byte[] gambarSampul
            , int jumlahPembaca
            , int peringkat) {
        this.idBuku = idBuku;
        this.idPengguna = idPengguna;
        this.namaJenisBuku = namaJenisBuku;
        this.namaGenre = namaGenre;
        this.namaPenulis = namaPenulis;
        this.namaPenerbit = namaPenerbit;
        this.judulBuku = judulBuku;
        this.tglTerbit = tglTerbit;
        this.sinopsis = sinopsis;
        this.skor = skor;
        this.gambarSampul = gambarSampul;
        this.jumlahPembaca = jumlahPembaca;
        this. peringkat = peringkat;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public int getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNamaJenisBuku() {
        return namaJenisBuku;
    }

    public void setNamaJenisBuku(String namaJenisBuku) {
        this.namaJenisBuku = namaJenisBuku;
    }

    public String getNamaGenre() {
        return namaGenre;
    }

    public void setNamaGenre(String namaGenre) {
        this.namaGenre = namaGenre;
    }

    public String getNamaPenulis() {
        return namaPenulis;
    }

    public void setNamaPenulis(String namaPenulis) {
        this.namaPenulis = namaPenulis;
    }

    public String getNamaPenerbit() {
        return namaPenerbit;
    }

    public void setNamaPenerbit(String namaPenerbit) {
        this.namaPenerbit = namaPenerbit;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public String getTglTerbit() {
        return tglTerbit;
    }

    public void setTglTerbit(String tglTerbit) {
        this.tglTerbit = tglTerbit;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public float getSkor() {
        return skor;
    }

    public void setSkor(float skor) {
        this.skor = skor;
    }

    public byte[] getGambarSampul() {
        return gambarSampul;
    }

    public void setGambarSampul(byte[] gambarSampul) {
        this.gambarSampul = gambarSampul;
    }

    public int getJumlahPembaca() {
        return jumlahPembaca;
    }

    public void setJumlahPembaca(int jumlahPembaca) {
        this.jumlahPembaca = jumlahPembaca;
    }

    public void setPeringkat(int peringkat) {
        this.peringkat = peringkat;
    }

    public int getPeringkat() {
        return peringkat;
    }
}
