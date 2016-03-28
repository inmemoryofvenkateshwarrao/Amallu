package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.Login;
import com.amallu.utility.ReqResNodes;

public class LoginParser{
	
	private static final String TAG="LoginParser";
	public static Login login=new Login();
	
	//Method Parses the JSON Response of Login Web service.
	public static Login getLoginParsedResponse(String loginStr){
		Log.i(TAG,"getLoginParsedResponse() Entering.");		
		
		try{
			JSONObject loginJSONObj=new JSONObject(loginStr);
			if(!loginJSONObj.isNull(ReqResNodes.ISSUCCESS))
				login.setIsSuccess(loginJSONObj.get(ReqResNodes.ISSUCCESS).toString());
			if(!loginJSONObj.isNull(ReqResNodes.USERID))
				login.setUserid((loginJSONObj.get(ReqResNodes.USERID).toString()));
			if(!loginJSONObj.isNull(ReqResNodes.USERNAME))
				login.setUsername((loginJSONObj.get(ReqResNodes.USERNAME).toString()));
			if(!loginJSONObj.isNull(ReqResNodes.MESSAGE))
				login.setMessage((loginJSONObj.get(ReqResNodes.MESSAGE).toString()));
		}catch(JSONException e){
			e.printStackTrace();
			login=null;
		}
		
		Log.i(TAG,"getLoginParsedResponse() Exiting.");
		return login;
	}
	
	//Returns Logged in user id
	public static String getUserID(){
	   Log.i(TAG,"getUserID() Entering.");
	   String userID="";
	   try{
		   userID=login.getUserid();
	   }catch(Exception e){
		   userID="";
	   }
	   Log.i(TAG,"getUserID() Exiting.");
	   return userID;
	}
	
	//Returns Logged in user name
	public static String getUserName(){
	   Log.i(TAG,"getUserName() Entering.");
	   String userName="";
	   try{
		   userName=login.getUsername();
	   }catch(Exception e){
		   userName="";
	   }
	   Log.i(TAG,"getUserName() Exiting.");
	   return userName;
	}

}
