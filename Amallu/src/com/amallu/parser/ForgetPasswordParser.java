package com.amallu.parser;

import android.util.Log;

import com.amallu.model.ForgetPassword;

public class ForgetPasswordParser{
	
	private static final String TAG="ForgetPasswordParser";
	
	//Method Parses the JSON Response of ForgetPassword Web service.
	public static ForgetPassword getForgetPasswordParsedResponse(String forgetPasswordStr){
		Log.i(TAG,"getSignUpParsedResponse() Entering.");
		
		ForgetPassword forgetPassword=null;
		/*try{
			forgetPassword=new ForgetPassword();
			JSONObject forgetPasswordJSONObj=new JSONObject(forgetPasswordStr);
			if(forgetPasswordJSONObj.get(ReqResNodes.ERRORDESCRIPTION)!=null)
				forgetPassword.setErrorDescription(forgetPasswordJSONObj.get(ReqResNodes.ERRORDESCRIPTION).toString());
			if(forgetPasswordJSONObj.get(ReqResNodes.ERRORCODE)!=null)
				forgetPassword.setErrorCode((forgetPasswordJSONObj.get(ReqResNodes.ERRORCODE).toString()));
		}catch(JSONException e){
			e.printStackTrace();
			forgetPassword=null;
		}*/
		
		Log.i(TAG,"getSignUpParsedResponse() Exiting.");
		return forgetPassword;
	}

}
