package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class DonorASIModel {

    @SerializedName("id_donor_asi")
    private String idDonorAsi;

    @SerializedName("token_asi")
    private String tokenAsi;

    @SerializedName("status_publikasi")
    private String statusPublikasi;

    @SerializedName("lng")
    private String lng;

    @SerializedName("penerima_asi_username")
    private String penerimaAsiUsername;

    @SerializedName("tgl_input_apps")
    private String tglInputApps;

    @SerializedName("bukti_foto")
    private String buktiFoto;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("tgl_perah")
    private String tglPerah;

    @SerializedName("no_hp_wa")
    private String noHpWa;

    @SerializedName("lat")
    private String lat;

    public void setIdDonorAsi(String idDonorAsi) {
        this.idDonorAsi = idDonorAsi;
    }

    public String getIdDonorAsi() {
        return idDonorAsi;
    }

    public void setTokenAsi(String tokenAsi) {
        this.tokenAsi = tokenAsi;
    }

    public String getTokenAsi() {
        return tokenAsi;
    }

    public void setStatusPublikasi(String statusPublikasi) {
        this.statusPublikasi = statusPublikasi;
    }

    public String getStatusPublikasi() {
        return statusPublikasi;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }

    public void setPenerimaAsiUsername(String penerimaAsiUsername) {
        this.penerimaAsiUsername = penerimaAsiUsername;
    }

    public String getPenerimaAsiUsername() {
        return penerimaAsiUsername;
    }

    public void setTglInputApps(String tglInputApps) {
        this.tglInputApps = tglInputApps;
    }

    public String getTglInputApps() {
        return tglInputApps;
    }

    public void setBuktiFoto(String buktiFoto) {
        this.buktiFoto = buktiFoto;
    }

    public String getBuktiFoto() {
        return buktiFoto;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setTglPerah(String tglPerah) {
        this.tglPerah = tglPerah;
    }

    public String getTglPerah() {
        return tglPerah;
    }

    public void setNoHpWa(String noHpWa) {
        this.noHpWa = noHpWa;
    }

    public String getNoHpWa() {
        return noHpWa;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }
}