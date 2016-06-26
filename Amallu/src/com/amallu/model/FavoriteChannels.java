package com.amallu.model;

import java.util.ArrayList;
import java.util.HashMap;

public class FavoriteChannels{
	
	private String isSuccess;
	private String message;
	private ArrayList<HashMap<String,Object>> favoriteChannelsList;
	
	public String getIsSuccess(){
		return isSuccess;
	}
	
	public void setIsSuccess(String isSuccess){
		this.isSuccess = isSuccess;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public ArrayList<HashMap<String, Object>> getFavoriteChannelsList(){
		return favoriteChannelsList;
	}
	
	public void setFavoriteChannelsList(ArrayList<HashMap<String, Object>> favoriteChannelsList){
		this.favoriteChannelsList = favoriteChannelsList;
	}

}
