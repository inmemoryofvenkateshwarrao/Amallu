package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.DisLikeChannel;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class DisLikeChannelParser{
	
	private static final String TAG="DisLikeChannelParser";
	
	//Method Parses the JSON Response of channel Web service.
	public static DisLikeChannel getDisLikeChannelParsedResponse(String likeChannelsStr){
		Log.i(TAG,"getDisLikeChannelParsedResponse() Entering.");
		DisLikeChannel disLikeChannel=new DisLikeChannel();
		try{
			JSONObject disLikeChannelJSONObj=new JSONObject(likeChannelsStr);
			if(!disLikeChannelJSONObj.isNull(ReqResNodes.ISSUCCESS))
				disLikeChannel.setIsSuccess(disLikeChannelJSONObj.get(ReqResNodes.ISSUCCESS).toString());
			if(!disLikeChannelJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					disLikeChannelJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISFAILURE))
				disLikeChannel.setMessage(disLikeChannelJSONObj.get(ReqResNodes.MESSAGE).toString());
			else{
				JSONObject messageJSONObj=(JSONObject)disLikeChannelJSONObj.get(ReqResNodes.MESSAGE);
				disLikeChannel.setAction(messageJSONObj.get(ReqResNodes.ACTION).toString());
				disLikeChannel.setLikecount(messageJSONObj.get(ReqResNodes.LIKECOUNT).toString());
				disLikeChannel.setDislikecount(messageJSONObj.get(ReqResNodes.DISLIKECOUNT).toString());
			}
		}catch(JSONException e){
			e.printStackTrace();
			disLikeChannel=null;
		}
		
		Log.i(TAG,"getDisLikeChannelParsedResponse() Exiting.");
		return disLikeChannel;
	}

}
