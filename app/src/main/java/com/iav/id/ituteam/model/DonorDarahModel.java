package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class DonorDarahModel {

    @SerializedName("jatuh_tempo_donor")
    private String jatuhTempoDonor;

    @SerializedName("no_reg_pmi")
    private String noRegPmi;

    @SerializedName("kota")
    private String kota;

    @SerializedName("status_reg_pasien")
    private String statusRegPasien;

    @SerializedName("rhesus_gol")
    private String rhesusGol;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("token_reg_donor")
    private String tokenRegDonor;

    @SerializedName("tgl_donor")
    private String tglDonor;

    @SerializedName("status_donor")
    private String statusDonor;

    @SerializedName("gol_darah")
    private String golDarah;

    @SerializedName("bukti_foto")
    private String buktiFoto;

    @SerializedName("tgl_donor_time")
    private String tglDonorTime;

    @SerializedName("id_donor")
    private String idDonor;

    public void setJatuhTempoDonor(String jatuhTempoDonor) {
        this.jatuhTempoDonor = jatuhTempoDonor;
    }

    public String getJatuhTempoDonor() {
        return jatuhTempoDonor;
    }

    public void setNoRegPmi(String noRegPmi) {
        this.noRegPmi = noRegPmi;
    }

    public String getNoRegPmi() {
        return noRegPmi;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKota() {
        return kota;
    }

    public void setStatusRegPasien(String statusRegPasien) {
        this.statusRegPasien = statusRegPasien;
    }

    public String getStatusRegPasien() {
        return statusRegPasien;
    }

    public void setRhesusGol(String rhesusGol) {
        this.rhesusGol = rhesusGol;
    }

    public String getRhesusGol() {
        return rhesusGol;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setTokenRegDonor(String tokenRegDonor) {
        this.tokenRegDonor = tokenRegDonor;
    }

    public String getTokenRegDonor() {
        return tokenRegDonor;
    }

    public void setTglDonor(String tglDonor) {
        this.tglDonor = tglDonor;
    }

    public String getTglDonor() {
        return tglDonor;
    }

    public void setStatusDonor(String statusDonor) {
        this.statusDonor = statusDonor;
    }

    public String getStatusDonor() {
        return statusDonor;
    }

    public void setGolDarah(String golDarah) {
        this.golDarah = golDarah;
    }

    public String getGolDarah() {
        return golDarah;
    }

    public void setBuktiFoto(String buktiFoto) {
        this.buktiFoto = buktiFoto;
    }

    public String getBuktiFoto() {
        return buktiFoto;
    }

    public void setTglDonorTime(String tglDonorTime) {
        this.tglDonorTime = tglDonorTime;
    }

    public String getTglDonorTime() {
        return tglDonorTime;
    }

    public void setIdDonor(String idDonor) {
        this.idDonor = idDonor;
    }

    public String getIdDonor() {
        return idDonor;
    }
}