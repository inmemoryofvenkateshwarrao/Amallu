package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.EditComment;

public class EditCommentParser{
	
	private static final String TAG="EditCommentParser";
	
	//Method Parses the JSON Response of EditComment Web service.
	public static EditComment getEditCommentParsedResponse(String editCommentStr){
		Log.i(TAG,"getEditCommentParsedResponse() Entering.");
		EditComment editComment=null;
		try{
			editComment=new EditComment();
			JSONObject editCommentJSONObj=new JSONObject(editCommentStr);
		}catch(JSONException e){
			e.printStackTrace();
			editComment=null;
		}
		
		Log.i(TAG,"getEditCommentParsedResponse() Exiting.");
		return editComment;
	}

}
