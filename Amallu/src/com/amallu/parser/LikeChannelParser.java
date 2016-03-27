package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.LikeChannel;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class LikeChannelParser{
	
	private static final String TAG="LikeChannelParser";
	
	//Method Parses the JSON Response of channel Web service.
	public static LikeChannel getLikeChannelParsedResponse(String likeChannelsStr){
		Log.i(TAG,"getLikeChannelParsedResponse() Entering.");
		LikeChannel likeChannel=new LikeChannel();
		try{
			JSONObject likeChannelJSONObj=new JSONObject(likeChannelsStr);
			if(!likeChannelJSONObj.isNull(ReqResNodes.ISSUCCESS))
				likeChannel.setIsSuccess(likeChannelJSONObj.get(ReqResNodes.ISSUCCESS).toString());
			if(!likeChannelJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					likeChannelJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISFAILURE))
				likeChannel.setMessage(likeChannelJSONObj.get(ReqResNodes.MESSAGE).toString());
			else{
				JSONObject messageJSONObj=(JSONObject)likeChannelJSONObj.get(ReqResNodes.MESSAGE);
				likeChannel.setAction(messageJSONObj.get(ReqResNodes.ACTION).toString());
				likeChannel.setLikecount(messageJSONObj.get(ReqResNodes.LIKECOUNT).toString());
				likeChannel.setDislikecount(messageJSONObj.get(ReqResNodes.DISLIKECOUNT).toString());
			}
		}catch(JSONException e){
			e.printStackTrace();
			likeChannel=null;
		}
		
		Log.i(TAG,"getLikeChannelParsedResponse() Exiting.");
		return likeChannel;
	}

}
