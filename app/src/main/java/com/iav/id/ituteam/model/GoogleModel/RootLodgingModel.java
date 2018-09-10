package com.iav.id.ituteam.model.GoogleModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootLodgingModel{

	@SerializedName("next_page_token")
	private String nextPageToken;

	@SerializedName("html_attributions")
	private ArrayList<Object> htmlAttributions;

	@SerializedName("results")
	private ArrayList<ResultsItem> results;

	@SerializedName("status")
	private String status;

	public void setNextPageToken(String nextPageToken){
		this.nextPageToken = nextPageToken;
	}

	public String getNextPageToken(){
		return nextPageToken;
	}

	public void setHtmlAttributions(ArrayList<Object> htmlAttributions){
		this.htmlAttributions = htmlAttributions;
	}

	public ArrayList<Object> getHtmlAttributions(){
		return htmlAttributions;
	}

	public void setResults(ArrayList<ResultsItem> results){
		this.results = results;
	}

	public ArrayList<ResultsItem> getResults(){
		return results;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}