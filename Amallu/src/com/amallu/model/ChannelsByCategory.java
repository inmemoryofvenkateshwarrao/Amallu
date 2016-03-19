package com.amallu.model;

import java.util.HashMap;
import java.util.List;

public class ChannelsByCategory {
	
	private String isSuccess;
	private String message;
	private String category_id;
	private String parent_id;
	private String category_name;
	private String is_active;
	private String display_order;
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
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public String getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(String display_order) {
		this.display_order = display_order;
	}

}
