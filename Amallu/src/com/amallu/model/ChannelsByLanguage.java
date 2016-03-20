package com.amallu.model;

import java.util.HashMap;
import java.util.List;

public class ChannelsByLanguage {
	
	private String isSuccess;
	private String message;
	private String language_id;
	private String language;
	private String is_active;
	private List<HashMap<String,Object>> channelsHMList;
	
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
	public List<HashMap<String, Object>> getChannelsHMList() {
		return channelsHMList;
	}
	public void setChannelsHMList(List<HashMap<String, Object>> channelsHMList) {
		this.channelsHMList = channelsHMList;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public String getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

}
