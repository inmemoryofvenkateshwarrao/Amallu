package com.amallu.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.Users;
import com.amallu.utility.ReqResNodes;

public class UsersParser{
	
	private static final String TAG="UsersParser";
	
	//Method Parses the JSON Response of Users Web service.
	public static List<Users> getUsersParsedResponse(String usersStr){
		Log.i(TAG,"getUsersParsedResponse() Entering.");
		List<Users> usersList=new ArrayList<Users>();
		Users users=null;
		try{
			users=new Users();
			JSONArray usersJSONArr=new JSONArray(usersStr);
			for(int userIndex=0;userIndex<usersJSONArr.length();userIndex++){
				JSONObject usersJSONObj=usersJSONArr.getJSONObject(userIndex);
				if(usersJSONObj.get(ReqResNodes.USERID)!=null)
					users.setUserid(usersJSONObj.get(ReqResNodes.USERID).toString());
				if(usersJSONObj.get(ReqResNodes.USERNAME)!=null)
					users.setUsername((usersJSONObj.get(ReqResNodes.USERNAME).toString()));
				if(usersJSONObj.get(ReqResNodes.PASSWORD)!=null)
					users.setPassword((usersJSONObj.get(ReqResNodes.PASSWORD).toString()));
				if(usersJSONObj.get(ReqResNodes.TWITTER_NAME)!=null)
					users.setTwitter_username((usersJSONObj.get(ReqResNodes.TWITTER_NAME).toString()));
				if(usersJSONObj.get(ReqResNodes.CHAT_NAME)!=null)
					users.setChat_name((usersJSONObj.get(ReqResNodes.CHAT_NAME).toString()));
				if(usersJSONObj.get(ReqResNodes.FIRSTNAME)!=null)
					users.setFirstname((usersJSONObj.get(ReqResNodes.FIRSTNAME).toString()));
				if(usersJSONObj.get(ReqResNodes.LASTNAME)!=null)
					users.setLastname((usersJSONObj.get(ReqResNodes.LASTNAME).toString()));
				if(usersJSONObj.get(ReqResNodes.COUNTRY_CODE)!=null)
					users.setCountry_code((usersJSONObj.get(ReqResNodes.COUNTRY_CODE).toString()));
				if(usersJSONObj.get(ReqResNodes.MOBILE_NUMBER)!=null)
					users.setMobile_number((usersJSONObj.get(ReqResNodes.MOBILE_NUMBER).toString()));
				if(usersJSONObj.get(ReqResNodes.DOB)!=null)
					users.setDob((usersJSONObj.get(ReqResNodes.DOB).toString()));
				if(usersJSONObj.get(ReqResNodes.GENDER)!=null)
					users.setGender((usersJSONObj.get(ReqResNodes.GENDER).toString()));
				if(usersJSONObj.get(ReqResNodes.LOCATION)!=null)
					users.setLocation((usersJSONObj.get(ReqResNodes.LOCATION).toString()));
				if(usersJSONObj.get(ReqResNodes.IS_ADMIN)!=null)
					users.setIs_admin((usersJSONObj.get(ReqResNodes.IS_ADMIN).toString()));
				if(usersJSONObj.get(ReqResNodes.DT_CREATED)!=null)
					users.setDt_created((usersJSONObj.get(ReqResNodes.DT_CREATED).toString()));
				if(usersJSONObj.get(ReqResNodes.DT_MODIFIED)!=null)
					users.setDt_modified((usersJSONObj.get(ReqResNodes.DT_MODIFIED).toString()));
				if(usersJSONObj.get(ReqResNodes.SIGNUP_SOURCE)!=null)
					users.setSignup_source((usersJSONObj.get(ReqResNodes.SIGNUP_SOURCE).toString()));
				if(usersJSONObj.get(ReqResNodes.USER_UNIQUE_ID)!=null)
					users.setUser_unique_id((usersJSONObj.get(ReqResNodes.USER_UNIQUE_ID).toString()));
				if(usersJSONObj.get(ReqResNodes.PROFILE_URL)!=null)
					users.setProfile_url((usersJSONObj.get(ReqResNodes.PROFILE_URL).toString()));
				if(usersJSONObj.get(ReqResNodes.MY_FRIENDS)!=null)
					users.setMy_friends((usersJSONObj.get(ReqResNodes.MY_FRIENDS).toString()));
				usersList.add(users);
			}
		}catch(JSONException e){
			e.printStackTrace();
			users=null;
		}
		
		Log.i(TAG,"getUsersParsedResponse() Exiting.");
		return usersList;
	}

}
