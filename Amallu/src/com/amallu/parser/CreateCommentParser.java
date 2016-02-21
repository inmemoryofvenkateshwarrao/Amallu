package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.CreateComment;

public class CreateCommentParser{
	
	private static final String TAG="CreateCommentParser";
	
	//Method Parses the JSON Response of CreateComment Web service.
	public static CreateComment getCreateCommentParsedResponse(String createCommentStr){
		Log.i(TAG,"getCreateCommentParsedResponse() Entering.");
		CreateComment createComment=null;
		try{
			createComment=new CreateComment();
			JSONObject nextChannelJSONObj=new JSONObject(createCommentStr);
		}catch(JSONException e){
			e.printStackTrace();
			createComment=null;
		}
		
		Log.i(TAG,"getCreateCommentParsedResponse() Exiting.");
		return createComment;
	}

}
