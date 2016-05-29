package com.amallu.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityLog {
	
	private String isSuccess;
	private String message;
	private ArrayList<HashMap<String,Object>> activityLogHMList;
	
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
	
	public ArrayList<HashMap<String, Object>> getActivityLogHMList(){
		return activityLogHMList;
	}
	
	public void setActivityLogHMList(ArrayList<HashMap<String, Object>> activityLogHMList){
		this.activityLogHMList = activityLogHMList;
	}
	
}
