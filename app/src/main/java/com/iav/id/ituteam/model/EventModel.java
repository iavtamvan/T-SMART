package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class EventModel {

    @SerializedName("judul_event")
    private String judulEvent;

    @SerializedName("tempat_alamat")
    private String tempatAlamat;

    @SerializedName("lng")
    private String lng;

    @SerializedName("no_hp")
    private Object noHp;

    @SerializedName("jenis_event")
    private String jenisEvent;

    @SerializedName("id_event")
    private String idEvent;

    @SerializedName("tgl_waktu_event")
    private String tglWaktuEvent;

    @SerializedName("tgl_event")
    private String tglEvent;

    @SerializedName("lat")
    private String lat;

    @SerializedName("gambar_event")
    private String gambarEvent;

    @SerializedName("likes")
    private String likes;

    public void setJudulEvent(String judulEvent) {
        this.judulEvent = judulEvent;
    }

    public String getJudulEvent() {
        return judulEvent;
    }

    public void setTempatAlamat(String tempatAlamat) {
        this.tempatAlamat = tempatAlamat;
    }

    public String getTempatAlamat() {
        return tempatAlamat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }

    public void setNoHp(Object noHp) {
        this.noHp = noHp;
    }

    public Object getNoHp() {
        return noHp;
    }

    public void setJenisEvent(String jenisEvent) {
        this.jenisEvent = jenisEvent;
    }

    public String getJenisEvent() {
        return jenisEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getIdEvent() {
        return idEvent;
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

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setGambarEvent(String gambarEvent) {
        this.gambarEvent = gambarEvent;
    }

    public String getGambarEvent() {
        return gambarEvent;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLikes() {
        return likes;
    }
}