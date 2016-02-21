package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.NextChannel;
import com.amallu.utility.ReqResNodes;

public class NextChannelParser{
	
	private static final String TAG="ChannNextChannelParserelParser";
	
	//Method Parses the JSON Response of NextChannel Web service.
	public static NextChannel getNextChannelParsedResponse(String nextChannelsStr){
		Log.i(TAG,"getNextChannelParsedResponse() Entering.");
		NextChannel nextChannel=null;
		try{
			nextChannel=new NextChannel();
			JSONObject nextChannelJSONObj=new JSONObject(nextChannelsStr);
			String status=nextChannelJSONObj.get(ReqResNodes.ISSUCCESS).toString();
			if(status.equalsIgnoreCase(ReqResNodes.SUCCESS)){
			  nextChannel.setIsSuccess(status);
			  nextChannel.setMessage(nextChannelJSONObj.get(ReqResNodes.MESSAGE).toString());
			}else{
			  nextChannel.setIsSuccess(status);
			  JSONObject messageJSONObject=(JSONObject)nextChannelJSONObj.get(ReqResNodes.MESSAGE);
			    if(messageJSONObject.get(ReqResNodes.CHANNEL_ID)!=null)
			    	nextChannel.setChannel_id(messageJSONObject.get(ReqResNodes.CHANNEL_ID).toString());
			    if(messageJSONObject.get(ReqResNodes.CHANNEL_CODE)!=null)
			    	nextChannel.setChannel_code(messageJSONObject.get(ReqResNodes.CHANNEL_CODE).toString());
			    if(messageJSONObject.get(ReqResNodes.CHANNEL_NAME)!=null)
			    	nextChannel.setChannel_name(messageJSONObject.get(ReqResNodes.CHANNEL_NAME).toString());
			    if(messageJSONObject.get(ReqResNodes.LANGUAGE_ID)!=null)
			    	nextChannel.setLanguage_id(messageJSONObject.get(ReqResNodes.LANGUAGE_ID).toString());
			    if(messageJSONObject.get(ReqResNodes.CATEGORY_ID)!=null)
			    	nextChannel.setCategory_id(messageJSONObject.get(ReqResNodes.CATEGORY_ID).toString());
			    if(messageJSONObject.get(ReqResNodes.VIEWS)!=null)
			    	nextChannel.setViews(messageJSONObject.get(ReqResNodes.VIEWS).toString());
			    if(messageJSONObject.get(ReqResNodes.TIME_WATCHED)!=null)
			    	nextChannel.setTime_watched(messageJSONObject.get(ReqResNodes.TIME_WATCHED).toString());
			}
		}catch(JSONException e){
			e.printStackTrace();
			nextChannel=null;
		}
		
		Log.i(TAG,"getNextChannelParsedResponse() Exiting.");
		return nextChannel;
	}

}
