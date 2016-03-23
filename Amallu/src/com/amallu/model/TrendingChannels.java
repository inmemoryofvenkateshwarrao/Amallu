package com.amallu.model;

import java.util.HashMap;
import java.util.List;

public class TrendingChannels {
	
	private String isSuccess;
	private String message;
	private List<HashMap<String,Object>> trendingChannelsHMList;
	
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
	public List<HashMap<String, Object>> getTrendingChannelsHMList() {
		return trendingChannelsHMList;
	}
	public void setTrendingChannelsHMList(
			List<HashMap<String, Object>> trendingChannelsHMList) {
		this.trendingChannelsHMList = trendingChannelsHMList;
	}
	
}
