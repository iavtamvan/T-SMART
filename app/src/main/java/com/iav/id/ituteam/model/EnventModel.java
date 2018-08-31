package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class EnventModel {

    @SerializedName("judul_event")
    private String judulEvent;

    @SerializedName("deskripsi_event")
    private Object deskripsiEvent;

    @SerializedName("tempat_alamat")
    private String tempatAlamat;

    @SerializedName("kota")
    private String kota;

    @SerializedName("lng")
    private String lng;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("jenis_event")
    private String jenisEvent;

    @SerializedName("nama_petugas")
    private String namaPetugas;

    @SerializedName("tgl_waktu_event")
    private String tglWaktuEvent;

    @SerializedName("tgl_event")
    private String tglEvent;

    @SerializedName("gambar_event")
    private String gambarEvent;

    @SerializedName("jenis_kategori")
    private String jenisKategori;

    @SerializedName("status_event")
    private String statusEvent;

    @SerializedName("id_event")
    private String idEvent;

    @SerializedName("lat")
    private String lat;

    @SerializedName("likes")
    private String likes;

    public void setJudulEvent(String judulEvent) {
        this.judulEvent = judulEvent;
    }

    public String getJudulEvent() {
        return judulEvent;
    }

    public void setDeskripsiEvent(Object deskripsiEvent) {
        this.deskripsiEvent = deskripsiEvent;
    }

    public Object getDeskripsiEvent() {
        return deskripsiEvent;
    }

    public void setTempatAlamat(String tempatAlamat) {
        this.tempatAlamat = tempatAlamat;
    }

    public String getTempatAlamat() {
        return tempatAlamat;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKota() {
        return kota;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setJenisEvent(String jenisEvent) {
        this.jenisEvent = jenisEvent;
    }

    public String getJenisEvent() {
        return jenisEvent;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setTglWaktuEvent(String tglWaktuEvent) {
        this.tglWaktuEvent = tglWaktuEvent;
    }

    public String getTglWaktuEvent() {
        return tglWaktuEvent;
    }

    public void setTglEvent(String tglEvent) {
        this.tglEvent = tglEvent;
    }

    public String getTglEvent() {
        return tglEvent;
    }

    public void setGambarEvent(String gambarEvent) {
        this.gambarEvent = gambarEvent;
    }

    public String getGambarEvent() {
        return gambarEvent;
    }

    public void setJenisKategori(String jenisKategori) {
        this.jenisKategori = jenisKategori;
    }

    public String getJenisKategori() {
        return jenisKategori;
    }

    public void setStatusEvent(String statusEvent) {
        this.statusEvent = statusEvent;
    }

    public String getStatusEvent() {
        return statusEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLikes() {
        return likes;
    }
}