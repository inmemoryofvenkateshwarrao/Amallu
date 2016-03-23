package com.amallu.parser;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.AddFriend;
import com.amallu.utility.ReqResNodes;

public class AddFriendParser{
	
	private static final String TAG="AddFriendParser";
	
	//Method Parses the JSON Response of AddFriend Web service.
	public static AddFriend getAddFriendParsedResponse(String addFriendStr){
		Log.i(TAG,"getAddFriendParsedResponse() Entering.");
		
		AddFriend addFriend;
		try{
			addFriend=new AddFriend();
			JSONObject addFriendJSONObj=new JSONObject(addFriendStr);
			addFriend.setUserid(addFriendJSONObj.get(ReqResNodes.ISSUCCESS).toString());
			addFriend.setFriendid(addFriendJSONObj.get(ReqResNodes.MESSAGE).toString());		
		}catch(JSONException e){
			e.printStackTrace();
			addFriend=null;
		}
		
		Log.i(TAG,"getAddFriendParsedResponse() Exiting.");
		return addFriend;
	}
	
}