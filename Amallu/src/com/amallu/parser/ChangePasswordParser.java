package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.ChangePassword;
import com.amallu.utility.ReqResNodes;

public class ChangePasswordParser{
	
	private static final String TAG="ChangePasswordParser";
	public static ChangePassword changePassword=new ChangePassword();
	
	//Method Parses the JSON Response of ChangePassword Web service.
	public static ChangePassword getChangePWDParsedResponse(String changePWDStr){
		Log.i(TAG,"getChangePWDParsedResponse() Entering.");		
		
		try{
			JSONObject changePWDJSONObj=new JSONObject(changePWDStr);
			if(!changePWDJSONObj.isNull(ReqResNodes.ISSUCCESS))
				changePassword.setIsSuccess(changePWDJSONObj.get(ReqResNodes.ISSUCCESS).toString());
			if(!changePWDJSONObj.isNull(ReqResNodes.MESSAGE))
				changePassword.setMessage((changePWDJSONObj.get(ReqResNodes.MESSAGE).toString()));
		}catch(JSONException e){
			e.printStackTrace();
			changePassword=null;
		}
		
		Log.i(TAG,"getChangePWDParsedResponse() Exiting.");
		return changePassword;
	}

}
