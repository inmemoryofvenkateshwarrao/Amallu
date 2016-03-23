package com.amallu.model;

import java.util.HashMap;
import java.util.List;

public class FavoriteChannels {
	
	private String isSuccess;
	private String message;
	private List<HashMap<String,Object>> favoriteChannelsList;
	
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<HashMap<String, Object>> getFavoriteChannelsList() {
		return favoriteChannelsList;
	}
	public void setFavoriteChannelsList(
			List<HashMap<String, Object>> favoriteChannelsList) {
		this.favoriteChannelsList = favoriteChannelsList;
	}

}
