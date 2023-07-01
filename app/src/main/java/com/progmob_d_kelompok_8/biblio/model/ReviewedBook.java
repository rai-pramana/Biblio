package com.progmob_d_kelompok_8.biblio.model;

public class ReviewedBook {
    private int idBuku;
    private String judulBuku, tglTerbit, tglMulai, tglSelesai, review;
    private float skorPengguna;
    private byte[] gambarSampul;

    public ReviewedBook(int idBuku
            , String judulBuku
            , String tglTerbit
            , String tglMulai
            , String tglSelesai
            , String review
            , float skorPengguna
            , byte[] gambarSampul) {
        this.idBuku = idBuku;
        this.judulBuku = judulBuku;
        this.tglTerbit = tglTerbit;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.review = review;
        this.skorPengguna = skorPengguna;
        this.gambarSampul = gambarSampul;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
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

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
    }

    public String getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(String tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getSkorPengguna() {
        return skorPengguna;
    }

    public void setSkorPengguna(float skorPengguna) {
        this.skorPengguna = skorPengguna;
    }

    public byte[] getGambarSampul() {
        return gambarSampul;
    }

    public void setGambarSampul(byte[] gambarSampul) {
        this.gambarSampul = gambarSampul;
    }
}
