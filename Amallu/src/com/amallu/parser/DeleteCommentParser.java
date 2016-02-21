package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.DeleteComment;

public class DeleteCommentParser{
	
	private static final String TAG="DeleteCommentParser";
	
	//Method Parses the JSON Response of DeleteComment Web service.
	public static DeleteComment getDeleteCommentParsedResponse(String deleteCommentStr){
		Log.i(TAG,"getDeleteCommentParsedResponse() Entering.");
		DeleteComment deleteComment=null;
		try{
			deleteComment=new DeleteComment();
			JSONObject nextChannelJSONObj=new JSONObject(deleteCommentStr);
		}catch(JSONException e){
			e.printStackTrace();
			deleteComment=null;
		}
		
		Log.i(TAG,"getDeleteCommentParsedResponse() Exiting.");
		return deleteComment;
	}

}
