package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class UserPetugasModel {

    @SerializedName("provinsi")
    private String provinsi;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("lng")
    private Object lng;

    @SerializedName("token_firebase")
    private Object tokenFirebase;

    @SerializedName("nama_lengkap")
    private String namaLengkap;

    @SerializedName("agama")
    private String agama;

    @SerializedName("rule")
    private String rule;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("tempat_tgl_lahir")
    private String tempatTglLahir;

    @SerializedName("status_aplikasi")
    private Object statusAplikasi;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("status_verifikasi")
    private String statusVerifikasi;

    @SerializedName("token")
    private String token;

    @SerializedName("password")
    private String password;

    @SerializedName("foto_url")
    private String fotoUrl;

    @SerializedName("kota_kab")
    private String kotaKab;

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;

    @SerializedName("email")
    private String email;

    @SerializedName("lat")
    private Object lat;

    @SerializedName("username")
    private String username;

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public Object getLng() {
        return lng;
    }

    public void setTokenFirebase(Object tokenFirebase) {
        this.tokenFirebase = tokenFirebase;
    }

    public Object getTokenFirebase() {
        return tokenFirebase;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getAgama() {
        return agama;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setTempatTglLahir(String tempatTglLahir) {
        this.tempatTglLahir = tempatTglLahir;
    }

    public String getTempatTglLahir() {
        return tempatTglLahir;
    }

    public void setStatusAplikasi(Object statusAplikasi) {
        this.statusAplikasi = statusAplikasi;
    }

    public Object getStatusAplikasi() {
        return statusAplikasi;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setStatusVerifikasi(String statusVerifikasi) {
        this.statusVerifikasi = statusVerifikasi;
    }

    public String getStatusVerifikasi() {
        return statusVerifikasi;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setKotaKab(String kotaKab) {
        this.kotaKab = kotaKab;
    }

    public String getKotaKab() {
        return kotaKab;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLat() {
        return lat;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}