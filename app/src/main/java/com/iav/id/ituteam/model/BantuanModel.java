package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class BantuanModel{

	@SerializedName("pertanyaan_bantuan")
	private String pertanyaanBantuan;

	@SerializedName("gambar_bantuan")
	private String gambarBantuan;

	@SerializedName("jawaban_bantuan")
	private String jawabanBantuan;

	@SerializedName("jenis_bantuan")
	private String jenisBantuan;

	@SerializedName("id_bantuan")
	private String idBantuan;

	public void setPertanyaanBantuan(String pertanyaanBantuan){
		this.pertanyaanBantuan = pertanyaanBantuan;
	}

	public String getPertanyaanBantuan(){
		return pertanyaanBantuan;
	}

	public void setGambarBantuan(String gambarBantuan){
		this.gambarBantuan = gambarBantuan;
	}

	public String getGambarBantuan(){
		return gambarBantuan;
	}

	public void setJawabanBantuan(String jawabanBantuan){
		this.jawabanBantuan = jawabanBantuan;
	}

	public String getJawabanBantuan(){
		return jawabanBantuan;
	}

	public void setJenisBantuan(String jenisBantuan){
		this.jenisBantuan = jenisBantuan;
	}

	public String getJenisBantuan(){
		return jenisBantuan;
	}

	public void setIdBantuan(String idBantuan){
		this.idBantuan = idBantuan;
	}

	public String getIdBantuan(){
		return idBantuan;
	}
}