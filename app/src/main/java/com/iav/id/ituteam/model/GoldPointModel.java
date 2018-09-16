package com.iav.id.ituteam.model;

import com.google.gson.annotations.SerializedName;

public class GoldPointModel{

	@SerializedName("total_gold")
	private String totalGold;

	@SerializedName("id_point")
	private String idPoint;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("total_point")
	private String totalPoint;

	public void setTotalGold(String totalGold){
		this.totalGold = totalGold;
	}

	public String getTotalGold(){
		return totalGold;
	}

	public void setIdPoint(String idPoint){
		this.idPoint = idPoint;
	}

	public String getIdPoint(){
		return idPoint;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setTotalPoint(String totalPoint){
		this.totalPoint = totalPoint;
	}

	public String getTotalPoint(){
		return totalPoint;
	}
}