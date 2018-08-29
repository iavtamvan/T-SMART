package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("provinsi")
    private String provinsi;

    @SerializedName("error_msg")
    private String errorMsg;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("lng")
    private String lng;

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
    private String statusAplikasi;

    @SerializedName("error")
    private boolean error;

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

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;

    @SerializedName("email")
    private String email;

    @SerializedName("lat")
    private String lat;

    @SerializedName("username")
    private String username;
    @SerializedName("kota_kab")
    private String kota_kab;

    public String getKota_kab() {
        return kota_kab;
    }

    public void setKota_kab(String kota_kab) {
        this.kota_kab = kota_kab;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return lng;
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

    public void setStatusAplikasi(String statusAplikasi) {
        this.statusAplikasi = statusAplikasi;
    }

    public String getStatusAplikasi() {
        return statusAplikasi;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
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

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}