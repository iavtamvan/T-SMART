package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class BantuanListModel{

	@SerializedName("gambar_bantuan")
	private String gambarBantuan;

	@SerializedName("id_bantuan_list")
	private String idBantuanList;

	@SerializedName("jenis_bantuan")
	private String jenisBantuan;

	public void setGambarBantuan(String gambarBantuan){
		this.gambarBantuan = gambarBantuan;
	}

	public String getGambarBantuan(){
		return gambarBantuan;
	}

	public void setIdBantuanList(String idBantuanList){
		this.idBantuanList = idBantuanList;
	}

	public String getIdBantuanList(){
		return idBantuanList;
	}

	public void setJenisBantuan(String jenisBantuan){
		this.jenisBantuan = jenisBantuan;
	}

	public String getJenisBantuan(){
		return jenisBantuan;
	}
}