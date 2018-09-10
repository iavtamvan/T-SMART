package com.iav.id.ituteam.model.GoogleModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PhotosItem{

	@SerializedName("photo_reference")
	private String photoReference;

	@SerializedName("width")
	private int width;

	@SerializedName("html_attributions")
	private ArrayList<String> htmlAttributions;

	@SerializedName("height")
	private int height;

	public void setPhotoReference(String photoReference){
		this.photoReference = photoReference;
	}

	public String getPhotoReference(){
		return photoReference;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setHtmlAttributions(ArrayList<String> htmlAttributions){
		this.htmlAttributions = htmlAttributions;
	}

	public ArrayList<String> getHtmlAttributions(){
		return htmlAttributions;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}
}