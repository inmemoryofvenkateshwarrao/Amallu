package com.amallu.parser;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import com.amallu.model.LikeChannel;

public class LikeChannelParser{
	
	private static final String TAG="ChannelParser";
	
	//Method Parses the JSON Response of channel Web service.
	public static LikeChannel getLikeChannelParsedResponse(String likeChannelsStr){
		Log.i(TAG,"getLikeChannelParsedResponse() Entering.");
		LikeChannel likeChannel=null;
		try{
			likeChannel=new LikeChannel();
			JSONArray channelsJSONArr=new JSONArray(likeChannelsStr);
			
		}catch(JSONException e){
			e.printStackTrace();
			likeChannel=null;
		}
		
		Log.i(TAG,"getChannelParsedResponse() Exiting.");
		return likeChannel;
	}

}
