package com.amallu.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.ChannelDetail;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class ChannelParser{
	
	private static final String TAG="ChannelParser";
	
	//Method Parses the JSON Response of channel Web service.
	public static ChannelDetail getChannelParsedResponse(String channelsStr){
		Log.i(TAG,"getChannelParsedResponse() Entering.");
		ChannelDetail channelDetail=null;
		try{
			channelDetail=new ChannelDetail();
			JSONObject channelJSONObj=new JSONObject(channelsStr);
			if(!channelJSONObj.isNull(ReqResNodes.ISSUCCESS))
				channelDetail.setIsSuccess(channelJSONObj.get(ReqResNodes.ISSUCCESS).toString());
			if(!channelJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					channelJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISFAILURE)){
				String messageStr=(String)channelJSONObj.get(ReqResNodes.MESSAGE);
				channelDetail.setMessage(messageStr);
			}else{
				JSONObject channelDetailJSONObj=(JSONObject)channelJSONObj.get(ReqResNodes.MESSAGE);
				if(!channelDetailJSONObj.isNull(ReqResNodes.CHANNEL_ID))
					channelDetail.setChannel_id(channelDetailJSONObj.get(ReqResNodes.CHANNEL_ID).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.CHANNEL_CODE))
					channelDetail.setChannel_code(channelDetailJSONObj.get(ReqResNodes.CHANNEL_CODE).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.CATEGORY_ID))
					channelDetail.setCategory_id(channelDetailJSONObj.get(ReqResNodes.CATEGORY_ID).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.CHANNEL_NAME))
					channelDetail.setChannel_name(channelDetailJSONObj.get(ReqResNodes.CHANNEL_NAME).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.LANGUAGE_ID))
					channelDetail.setLanguage_id(channelDetailJSONObj.get(ReqResNodes.LANGUAGE_ID).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.DESCRIPTION))
					channelDetail.setDescription(channelDetailJSONObj.get(ReqResNodes.DESCRIPTION).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.RTMP_LINK))
					channelDetail.setRtmp_link(channelDetailJSONObj.get(ReqResNodes.RTMP_LINK).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.FOLLOWERS))
					channelDetail.setFollowers(channelDetailJSONObj.get(ReqResNodes.FOLLOWERS).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.VIEWS))
					channelDetail.setViews(channelDetailJSONObj.get(ReqResNodes.VIEWS).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.DISPLAY_CHANNEL))
					channelDetail.setDisplay_channel(channelDetailJSONObj.get(ReqResNodes.DISPLAY_CHANNEL).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.DEFAULT_CHANNEL))
					channelDetail.setDefault_channel(channelDetailJSONObj.get(ReqResNodes.DEFAULT_CHANNEL).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.TIME_WATCHED))
					channelDetail.setTime_watched(channelDetailJSONObj.get(ReqResNodes.TIME_WATCHED).toString());
				if(!channelDetailJSONObj.isNull(ReqResNodes.THUMBNAIL))
					channelDetail.setThumbnail(channelDetailJSONObj.get(ReqResNodes.THUMBNAIL).toString());
			}
		}catch(JSONException e){
			e.printStackTrace();
			channelDetail=null;
		}
		
		Log.i(TAG,"getChannelParsedResponse() Exiting.");
		return channelDetail;
	}

}
