package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.ForgetPassword;
import com.amallu.utility.ReqResNodes;

public class ForgetPasswordParser{
	
	private static final String TAG="ForgetPasswordParser";
	
	//Method Parses the JSON Response of ForgetPassword Web service.
	public static ForgetPassword getForgetPasswordParsedResponse(String forgetPasswordStr){
		Log.i(TAG,"getSignUpParsedResponse() Entering.");
		
		ForgetPassword forgetPassword=null;
		try{
			forgetPassword=new ForgetPassword();
			JSONObject forgetPasswordJSONObj=new JSONObject(forgetPasswordStr);
			Log.v(TAG,"forgetPasswordJSONObj : "+forgetPasswordJSONObj);
			if(!forgetPasswordJSONObj.isNull(ReqResNodes.ISSUCCESS))
				forgetPassword.setIsSuccess(forgetPasswordJSONObj.get(ReqResNodes.ISSUCCESS).toString());
			if(!forgetPasswordJSONObj.isNull(ReqResNodes.PASSWORD))
				forgetPassword.setPassword((forgetPasswordJSONObj.get(ReqResNodes.PASSWORD).toString()));
			if(!forgetPasswordJSONObj.isNull(ReqResNodes.MESSAGE))
				forgetPassword.setMessage((forgetPasswordJSONObj.get(ReqResNodes.MESSAGE).toString()));
		}catch(JSONException e){
			e.printStackTrace();
			forgetPassword=null;
		}
		
		Log.i(TAG,"getSignUpParsedResponse() Exiting.");
		return forgetPassword;
	}

}
