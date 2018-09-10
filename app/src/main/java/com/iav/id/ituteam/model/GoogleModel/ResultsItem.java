package com.iav.id.ituteam.model.GoogleModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultsItem{

	@SerializedName("reference")
	private String reference;

	@SerializedName("types")
	private ArrayList<String> types;

	@SerializedName("scope")
	private String scope;

	@SerializedName("icon")
	private String icon;

	@SerializedName("name")
	private String name;

	@SerializedName("rating")
	private int rating;

	@SerializedName("geometry")
	private Geometry geometry;

	@SerializedName("vicinity")
	private String vicinity;

	@SerializedName("id")
	private String id;

	@SerializedName("photos")
	private ArrayList<PhotosItem> photos;

	@SerializedName("plus_code")
	private PlusCode plusCode;

	@SerializedName("place_id")
	private String placeId;

	@SerializedName("opening_hours")
	private OpeningHours openingHours;

	public void setReference(String reference){
		this.reference = reference;
	}

	public String getReference(){
		return reference;
	}

	public void setTypes(ArrayList<String> types){
		this.types = types;
	}

	public ArrayList<String> getTypes(){
		return types;
	}

	public void setScope(String scope){
		this.scope = scope;
	}

	public String getScope(){
		return scope;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setRating(int rating){
		this.rating = rating;
	}

	public int getRating(){
		return rating;
	}

	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public void setVicinity(String vicinity){
		this.vicinity = vicinity;
	}

	public String getVicinity(){
		return vicinity;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPhotos(ArrayList<PhotosItem> photos){
		this.photos = photos;
	}

	public ArrayList<PhotosItem> getPhotos(){
		return photos;
	}

	public void setPlusCode(PlusCode plusCode){
		this.plusCode = plusCode;
	}

	public PlusCode getPlusCode(){
		return plusCode;
	}

	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}

	public String getPlaceId(){
		return placeId;
	}

	public void setOpeningHours(OpeningHours openingHours){
		this.openingHours = openingHours;
	}

	public OpeningHours getOpeningHours(){
		return openingHours;
	}
}