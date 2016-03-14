package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.Login;
import com.amallu.utility.ReqResNodes;

public class LoginParser{
	
	private static final String TAG="LoginParser";
	
	//Method Parses the JSON Response of Login Web service.
	public static Login getLoginParsedResponse(String loginStr){
		Log.i(TAG,"getLoginParsedResponse() Entering.");
		
		Login login=null;
		try{
			login=new Login();
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

}
