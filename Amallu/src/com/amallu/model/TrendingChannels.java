package com.amallu.model;

import java.util.ArrayList;
import java.util.HashMap;

public class TrendingChannels {
	
	private String isSuccess;
	private String message;
	private ArrayList<HashMap<String,Object>> trendingChannelsHMList;
	
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
	
	public ArrayList<HashMap<String, Object>> getTrendingChannelsHMList(){
		return trendingChannelsHMList;
	}
	
	public void setTrendingChannelsHMList(ArrayList<HashMap<String, Object>> trendingChannelsHMList){
		this.trendingChannelsHMList = trendingChannelsHMList;
	}
	
}
