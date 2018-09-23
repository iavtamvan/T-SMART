package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class HelpListModel {

    @SerializedName("gamabr_help_list")
    private String gamabrHelpList;

    @SerializedName("nama_help_list")
    private String namaHelpList;

    @SerializedName("id_help_list")
    private String idHelpList;

    public void setGamabrHelpList(String gamabrHelpList) {
        this.gamabrHelpList = gamabrHelpList;
    }

    public String getGamabrHelpList() {
        return gamabrHelpList;
    }

    public void setNamaHelpList(String namaHelpList) {
        this.namaHelpList = namaHelpList;
    }

    public String getNamaHelpList() {
        return namaHelpList;
    }

    public void setIdHelpList(String idHelpList) {
        this.idHelpList = idHelpList;
    }

    public String getIdHelpList() {
        return idHelpList;
    }
}