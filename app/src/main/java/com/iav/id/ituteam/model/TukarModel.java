package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class TukarModel{

	@SerializedName("alamat_penjual")
	private String alamatPenjual;

	@SerializedName("ongkir")
	private String ongkir;

	@SerializedName("jenis_tukar")
	private String jenisTukar;

	@SerializedName("tgl_barang")
	private String tglBarang;

	@SerializedName("harga_barang")
	private String hargaBarang;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("id_tukar_barang")
	private String idTukarBarang;

	@SerializedName("alamat_user")
	private String alamatUser;

	@SerializedName("foto_url")
	private String fotoUrl;

	@SerializedName("nama_penjual_barang")
	private String namaPenjualBarang;

	@SerializedName("tukarkan")
	private String tukarkan;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("deskripsi_barang")
	private String deskripsiBarang;

	public void setAlamatPenjual(String alamatPenjual){
		this.alamatPenjual = alamatPenjual;
	}

	public String getAlamatPenjual(){
		return alamatPenjual;
	}

	public void setOngkir(String ongkir){
		this.ongkir = ongkir;
	}

	public String getOngkir(){
		return ongkir;
	}

	public void setJenisTukar(String jenisTukar){
		this.jenisTukar = jenisTukar;
	}

	public String getJenisTukar(){
		return jenisTukar;
	}

	public void setTglBarang(String tglBarang){
		this.tglBarang = tglBarang;
	}

	public String getTglBarang(){
		return tglBarang;
	}

	public void setHargaBarang(String hargaBarang){
		this.hargaBarang = hargaBarang;
	}

	public String getHargaBarang(){
		return hargaBarang;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setIdTukarBarang(String idTukarBarang){
		this.idTukarBarang = idTukarBarang;
	}

	public String getIdTukarBarang(){
		return idTukarBarang;
	}

	public void setAlamatUser(String alamatUser){
		this.alamatUser = alamatUser;
	}

	public String getAlamatUser(){
		return alamatUser;
	}

	public void setFotoUrl(String fotoUrl){
		this.fotoUrl = fotoUrl;
	}

	public String getFotoUrl(){
		return fotoUrl;
	}

	public void setNamaPenjualBarang(String namaPenjualBarang){
		this.namaPenjualBarang = namaPenjualBarang;
	}

	public String getNamaPenjualBarang(){
		return namaPenjualBarang;
	}

	public void setTukarkan(String tukarkan){
		this.tukarkan = tukarkan;
	}

	public String getTukarkan(){
		return tukarkan;
	}

	public void setNamaBarang(String namaBarang){
		this.namaBarang = namaBarang;
	}

	public String getNamaBarang(){
		return namaBarang;
	}

	public void setDeskripsiBarang(String deskripsiBarang){
		this.deskripsiBarang = deskripsiBarang;
	}

	public String getDeskripsiBarang(){
		return deskripsiBarang;
	}
}