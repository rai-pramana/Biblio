package com.progmob_d_kelompok_8.biblio.model;

public class User {
    private int id_pengguna, selesai, rencana_dibaca, sedang_dibaca, dijatuhkan;
    private String role, nama, email, password_pengguna, no_hp, jenis_kelamin, alamat, tgl_gabung;
    private float skor_mean;
    private byte[] foto;

    public User(int id_pengguna, int selesai, int rencana_dibaca, int sedang_dibaca, int dijatuhkan, String role, String nama, String email, String password_pengguna, String no_hp, String jenis_kelamin, String alamat, byte[] foto, String tgl_gabung, float skor_mean) {
        this.id_pengguna = id_pengguna;
        this.selesai = selesai;
        this.rencana_dibaca = rencana_dibaca;
        this.sedang_dibaca = sedang_dibaca;
        this.dijatuhkan = dijatuhkan;
        this.role = role;
        this.nama = nama;
        this.email = email;
        this.password_pengguna = password_pengguna;
        this.no_hp = no_hp;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.foto = foto;
        this.tgl_gabung = tgl_gabung;
        this.skor_mean = skor_mean;
    }

    public int getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(int id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public int getSelesai() {
        return selesai;
    }

    public void setSelesai(int selesai) {
        this.selesai = selesai;
    }

    public int getRencana_dibaca() {
        return rencana_dibaca;
    }

    public void setRencana_dibaca(int rencana_dibaca) {
        this.rencana_dibaca = rencana_dibaca;
    }

    public int getSedang_dibaca() {
        return sedang_dibaca;
    }

    public void setSedang_dibaca(int sedang_dibaca) {
        this.sedang_dibaca = sedang_dibaca;
    }

    public int getDijatuhkan() {
        return dijatuhkan;
    }

    public void setDijatuhkan(int dijatuhkan) {
        this.dijatuhkan = dijatuhkan;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_pengguna() {
        return password_pengguna;
    }

    public void setPassword_pengguna(String password_pengguna) {
        this.password_pengguna = password_pengguna;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getTgl_gabung() {
        return tgl_gabung;
    }

    public void setTgl_gabung(String tgl_gabung) {
        this.tgl_gabung = tgl_gabung;
    }

    public float getSkor_mean() {
        return skor_mean;
    }

    public void setSkor_mean(float skor_mean) {
        this.skor_mean = skor_mean;
    }

}
