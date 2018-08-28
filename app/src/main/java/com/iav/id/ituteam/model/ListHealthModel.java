package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class ListHealthModel {

    @SerializedName("jenis_kategori")
    private String jenisKategori;

    @SerializedName("id_jenis_event")
    private String idJenisEvent;

    @SerializedName("jenis_event")
    private String jenisEvent;

    @SerializedName("gambar")
    private String gambar;

    public void setJenisKategori(String jenisKategori) {
        this.jenisKategori = jenisKategori;
    }

    public String getJenisKategori() {
        return jenisKategori;
    }

    public void setIdJenisEvent(String idJenisEvent) {
        this.idJenisEvent = idJenisEvent;
    }

    public String getIdJenisEvent() {
        return idJenisEvent;
    }

    public void setJenisEvent(String jenisEvent) {
        this.jenisEvent = jenisEvent;
    }

    public String getJenisEvent() {
        return jenisEvent;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }
}