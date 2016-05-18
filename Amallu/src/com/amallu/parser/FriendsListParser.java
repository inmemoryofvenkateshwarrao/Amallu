package com.amallu.parser;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.Friend;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class FriendsListParser{
	
	private static final String TAG="FriendsListParser";
	
	//Method Parses the JSON Response of FriendsList Web service.
	public static Friend getFriendsListParsedResponse(String friendsListStr){
		Log.i(TAG,"getFriendsListParsedResponse() Entering.");
		Friend friend=new Friend();
		ArrayList<HashMap<String,Object>> friendsRowsHMList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> friendRowHM;
		try{
			JSONObject friendsJSONObj=new JSONObject(friendsListStr);
			if(!friendsJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					friendsJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISFAILURE)){
				friend.setIsSuccess(friendsJSONObj.get(ReqResNodes.ISSUCCESS).toString());
				friend.setMessage(friendsJSONObj.get(ReqResNodes.MESSAGE).toString());
			}else{
				friend.setIsSuccess(friendsJSONObj.get(ReqResNodes.ISSUCCESS).toString());
				JSONArray messageJSONArray=(JSONArray)friendsJSONObj.getJSONArray(ReqResNodes.MESSAGE);
				for(int friendsArr=0;friendsArr<messageJSONArray.length();friendsArr++){
					friendRowHM=new HashMap<String,Object>();
					JSONObject friendItemJSONObj=(JSONObject)messageJSONArray.get(friendsArr);
					if(!friendItemJSONObj.isNull(ReqResNodes.USERID))
						friendRowHM.put(ReqResNodes.USERID,friendItemJSONObj.get(ReqResNodes.USERID));
					if(!friendItemJSONObj.isNull(ReqResNodes.USERNAME))
						friendRowHM.put(ReqResNodes.USERNAME,friendItemJSONObj.get(ReqResNodes.USERNAME));
					if(!friendItemJSONObj.isNull(ReqResNodes.PASSWORD))
						friendRowHM.put(ReqResNodes.PASSWORD,friendItemJSONObj.get(ReqResNodes.PASSWORD));
					if(!friendItemJSONObj.isNull(ReqResNodes.TWITTER_NAME))
						friendRowHM.put(ReqResNodes.TWITTER_NAME,friendItemJSONObj.get(ReqResNodes.TWITTER_NAME));
					if(!friendItemJSONObj.isNull(ReqResNodes.CHAT_NAME))
						friendRowHM.put(ReqResNodes.CHAT_NAME,friendItemJSONObj.get(ReqResNodes.CHAT_NAME));
					if(!friendItemJSONObj.isNull(ReqResNodes.FIRSTNAME))
						friendRowHM.put(ReqResNodes.FIRSTNAME,friendItemJSONObj.get(ReqResNodes.FIRSTNAME));
					if(!friendItemJSONObj.isNull(ReqResNodes.LASTNAME))
						friendRowHM.put(ReqResNodes.LASTNAME,friendItemJSONObj.get(ReqResNodes.LASTNAME));
					if(!friendItemJSONObj.isNull(ReqResNodes.COUNTRY_CODE))
						friendRowHM.put(ReqResNodes.COUNTRY_CODE,friendItemJSONObj.get(ReqResNodes.COUNTRY_CODE));
					if(!friendItemJSONObj.isNull(ReqResNodes.MOBILE_NUMBER))
						friendRowHM.put(ReqResNodes.MOBILE_NUMBER,friendItemJSONObj.get(ReqResNodes.MOBILE_NUMBER));
					if(!friendItemJSONObj.isNull(ReqResNodes.DOB))
						friendRowHM.put(ReqResNodes.DOB,friendItemJSONObj.get(ReqResNodes.DOB));
					if(!friendItemJSONObj.isNull(ReqResNodes.GENDER))
						friendRowHM.put(ReqResNodes.GENDER,friendItemJSONObj.get(ReqResNodes.GENDER));
					if(!friendItemJSONObj.isNull(ReqResNodes.LOCATION))
						friendRowHM.put(ReqResNodes.LOCATION,friendItemJSONObj.get(ReqResNodes.LOCATION));
					if(!friendItemJSONObj.isNull(ReqResNodes.IS_ADMIN))
						friendRowHM.put(ReqResNodes.IS_ADMIN,friendItemJSONObj.get(ReqResNodes.IS_ADMIN));
					if(!friendItemJSONObj.isNull(ReqResNodes.DT_CREATED))
						friendRowHM.put(ReqResNodes.DT_CREATED,friendItemJSONObj.get(ReqResNodes.DT_CREATED));
					if(!friendItemJSONObj.isNull(ReqResNodes.DT_MODIFIED))
						friendRowHM.put(ReqResNodes.DT_MODIFIED,friendItemJSONObj.get(ReqResNodes.DT_MODIFIED));
					if(!friendItemJSONObj.isNull(ReqResNodes.SIGNUP_SOURCE))
						friendRowHM.put(ReqResNodes.SIGNUP_SOURCE,friendItemJSONObj.get(ReqResNodes.SIGNUP_SOURCE));
					if(!friendItemJSONObj.isNull(ReqResNodes.USER_UNIQUE_ID))
						friendRowHM.put(ReqResNodes.USER_UNIQUE_ID,friendItemJSONObj.get(ReqResNodes.USER_UNIQUE_ID));
					if(!friendItemJSONObj.isNull(ReqResNodes.DEFAULT_CHANNEL))
						friendRowHM.put(ReqResNodes.DEFAULT_CHANNEL,friendItemJSONObj.get(ReqResNodes.DEFAULT_CHANNEL));
					if(!friendItemJSONObj.isNull(ReqResNodes.PROFILE_URL))
						friendRowHM.put(ReqResNodes.PROFILE_URL,friendItemJSONObj.get(ReqResNodes.PROFILE_URL));
					if(!friendItemJSONObj.isNull(ReqResNodes.MY_FRIENDS))
						friendRowHM.put(ReqResNodes.MY_FRIENDS,friendItemJSONObj.get(ReqResNodes.MY_FRIENDS));
					
					friendsRowsHMList.add(friendRowHM);
					friend.setFriendsHMList(friendsRowsHMList);
				}
			}
		}catch(JSONException e){
			e.printStackTrace();
			friend=null;
		}
		
		Log.i(TAG,"getFriendsListParsedResponse() Exiting.");
		return friend;
	}

}