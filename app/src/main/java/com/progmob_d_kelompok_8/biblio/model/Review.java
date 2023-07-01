package com.progmob_d_kelompok_8.biblio.model;

public class Review {

    private int idPengguna;
    private String nama, tglReview, review;
    private float skorPengguna;
    private byte[] foto;

    public Review(int idPengguna
            , String nama
            , String tglReview
            , String review
            , float skorPengguna
            , byte[] foto) {
        this.idPengguna = idPengguna;
        this.nama = nama;
        this.tglReview = tglReview;
        this.review = review;
        this.skorPengguna = skorPengguna;
        this.foto = foto;
    }

    public int getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTglReview() {
        return tglReview;
    }

    public void setTglReview(String tglReview) {
        this.tglReview = tglReview;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
