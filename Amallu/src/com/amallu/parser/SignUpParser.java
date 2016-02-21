package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.SignUp;
import com.amallu.utility.ReqResNodes;

public class SignUpParser{
	
	private static final String TAG="SignUpParser";
	
	//Method Parses the JSON Response of SignUp Web service.
	public static SignUp getSignUpParsedResponse(String signUpStr){
		Log.i(TAG,"getSignUpParsedResponse() Entering.");
		
		SignUp signUp=null;
		/*try{
			signUp=new SignUp();
			JSONObject signUpJSONObj=new JSONObject(signUpStr);
			if(signUpJSONObj.get(ReqResNodes.ERRORDESCRIPTION)!=null)
				signUp.setErrorDescription(signUpJSONObj.get(ReqResNodes.ERRORDESCRIPTION).toString());
			if(signUpJSONObj.get(ReqResNodes.ERRORCODE)!=null)
				signUp.setErrorCode((signUpJSONObj.get(ReqResNodes.ERRORCODE).toString()));
		}catch(JSONException e){
			e.printStackTrace();
			signUp=null;
		}*/
		
		Log.i(TAG,"getSignUpParsedResponse() Exiting.");
		return signUp;
	}

}
