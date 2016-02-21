package com.amallu.parser;

import android.util.Log;

import com.amallu.model.Profile;

public class ProfileParser{
	
	private static final String TAG="ProfileParser";
	
	//Method Parses the JSON Response of ProfileParser Web service.
	public static Profile getProfileParsedResponse(String profileStr){
		Log.i(TAG,"getProfileParsedResponse() Entering.");
		
		Profile profile=null;
		/*try{
			profile=new Profile();
			JSONObject profileJSONObj=new JSONObject(forgetPasswordStr);
			if(profileJSONObj.get(ReqResNodes.ERRORDESCRIPTION)!=null)
				profile.setErrorDescription(profileJSONObj.get(ReqResNodes.ERRORDESCRIPTION).toString());
			if(profileJSONObj.get(ReqResNodes.ERRORCODE)!=null)
				profile.setErrorCode((profileJSONObj.get(ReqResNodes.ERRORCODE).toString()));
		}catch(JSONException e){
			e.printStackTrace();
			profile=null;
		}*/
		
		Log.i(TAG,"getProfileParsedResponse() Exiting.");
		return profile;
	}

}
