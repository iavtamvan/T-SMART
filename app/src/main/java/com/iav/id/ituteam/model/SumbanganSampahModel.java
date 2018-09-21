package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class SumbanganSampahModel {

    @SerializedName("lng")
    private String lng;

    @SerializedName("input_sampah")
    private String inputSampah;

    @SerializedName("kode_vocer")
    private String kodeVocer;

    @SerializedName("harga_total")
    private String hargaTotal;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("token_reg_sampah")
    private String tokenRegSampah;

    @SerializedName("tempat")
    private String tempat;

    @SerializedName("tgl_waktu_sampah")
    private String tglWaktuSampah;

    @SerializedName("jenis_sampah")
    private String jenisSampah;

    @SerializedName("id_sumbangan_sampah")
    private String idSumbanganSampah;

    @SerializedName("lat")
    private String lat;

    @SerializedName("gambar_petugas")
    private String gambarPetugas;

    @SerializedName("status")
    private String status;

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }

    public void setInputSampah(String inputSampah) {
        this.inputSampah = inputSampah;
    }

    public String getInputSampah() {
        return inputSampah;
    }

    public void setKodeVocer(String kodeVocer) {
        this.kodeVocer = kodeVocer;
    }

    public String getKodeVocer() {
        return kodeVocer;
    }

    public void setHargaTotal(String hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    public String getHargaTotal() {
        return hargaTotal;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setTokenRegSampah(String tokenRegSampah) {
        this.tokenRegSampah = tokenRegSampah;
    }

    public String getTokenRegSampah() {
        return tokenRegSampah;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTglWaktuSampah(String tglWaktuSampah) {
        this.tglWaktuSampah = tglWaktuSampah;
    }

    public String getTglWaktuSampah() {
        return tglWaktuSampah;
    }

    public void setJenisSampah(String jenisSampah) {
        this.jenisSampah = jenisSampah;
    }

    public String getJenisSampah() {
        return jenisSampah;
    }

    public void setIdSumbanganSampah(String idSumbanganSampah) {
        this.idSumbanganSampah = idSumbanganSampah;
    }

    public String getIdSumbanganSampah() {
        return idSumbanganSampah;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setGambarPetugas(String gambarPetugas) {
        this.gambarPetugas = gambarPetugas;
    }

    public String getGambarPetugas() {
        return gambarPetugas;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}