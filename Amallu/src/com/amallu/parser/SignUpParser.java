package com.amallu.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.SignUp;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class SignUpParser{
	
	private static final String TAG="SignUpParser";
	
	//Method Parses the JSON Response of SignUp Web service.
	public static SignUp getSignUpParsedResponse(String signUpStr){
		Log.i(TAG,"getSignUpParsedResponse() Entering.");
		
		SignUp signUp=null;
		try{
			signUp=new SignUp();
			JSONObject signUpJSONObj=new JSONObject(signUpStr);
			if(!signUpJSONObj.isNull(ReqResNodes.ISSUCCESS))
				signUp.setIsSuccess(signUpJSONObj.get(ReqResNodes.ISSUCCESS).toString());
			if(!signUpJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					signUpJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISSUCCESS)){
				JSONObject messageJSONObj=(JSONObject)signUpJSONObj.get(ReqResNodes.MESSAGE);
				for(int arr=0;arr<messageJSONObj.length();arr++){
					
					JSONArray firstNameArr=messageJSONObj.getJSONArray(ReqResNodes.FIRSTNAME);
					String firstname=firstNameArr.get(arr).toString();
					signUp.setFirstname(firstname);
					
					JSONArray lastNameArr=messageJSONObj.getJSONArray(ReqResNodes.LASTNAME);
					String lastname=lastNameArr.get(arr).toString();
					signUp.setLastname(lastname);
					
					JSONArray userNameArr=messageJSONObj.getJSONArray(ReqResNodes.USERNAME);
					String username=userNameArr.get(arr).toString();
					signUp.setUsername(username);
					
					JSONArray passwordArr=messageJSONObj.getJSONArray(ReqResNodes.PASSWORD);
					String password=passwordArr.get(arr).toString();
					signUp.setPassword(password);
					
					JSONArray confPasswordArr=messageJSONObj.getJSONArray(ReqResNodes.CONFPASSWORD);
					String confPassword=confPasswordArr.get(arr).toString();
					signUp.setConfPassword(confPassword);
					
					JSONArray genderArr=messageJSONObj.getJSONArray(ReqResNodes.GENDER);
					String gender=genderArr.get(arr).toString();
					signUp.setGender(gender);
					
					JSONArray dobArr=messageJSONObj.getJSONArray(ReqResNodes.DOB);
					String dob=dobArr.get(arr).toString();
					signUp.setDob(dob);
				}
			}else{
				if(!signUpJSONObj.isNull(ReqResNodes.MESSAGE))
					signUp.setIsSuccess(signUpJSONObj.get(ReqResNodes.ISSUCCESS).toString());
				if(!signUpJSONObj.isNull(ReqResNodes.USERID))
					signUp.setUserid(signUpJSONObj.get(ReqResNodes.USERID).toString());
				if(!signUpJSONObj.isNull(ReqResNodes.USERNAME))
					signUp.setUsername(signUpJSONObj.get(ReqResNodes.USERNAME).toString());
			}
			
		}catch(JSONException e){
			e.printStackTrace();
			signUp=null;
		}
		
		Log.i(TAG,"getSignUpParsedResponse() Exiting.");
		return signUp;
	}

}
