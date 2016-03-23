package com.amallu.model;

import java.util.HashMap;
import java.util.List;

public class ActivityLog {
	
	private String isSuccess;
	private String message;
	private List<HashMap<String,Object>> activityLogHMList;
	
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
	public List<HashMap<String, Object>> getActivityLogHMList() {
		return activityLogHMList;
	}
	public void setActivityLogHMList(List<HashMap<String, Object>> activityLogHMList) {
		this.activityLogHMList = activityLogHMList;
	}
	
}
