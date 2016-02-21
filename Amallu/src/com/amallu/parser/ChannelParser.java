package com.amallu.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.Channel;
import com.amallu.utility.ReqResNodes;

public class ChannelParser{
	
	private static final String TAG="ChannelParser";
	
	//Method Parses the JSON Response of channel Web service.
	public static List<Channel> getChannelParsedResponse(String channelsStr){
		Log.i(TAG,"getChannelParsedResponse() Entering.");
		List<Channel> channelsList=new ArrayList<Channel>();
		Channel channel=null;
		try{
			channel=new Channel();
			JSONArray channelsJSONArr=new JSONArray(channelsStr);
			for(int userIndex=0;userIndex<channelsJSONArr.length();userIndex++){
				JSONObject channelJSONObj=channelsJSONArr.getJSONObject(userIndex);
				if(channelJSONObj.get(ReqResNodes.CHANNEL_ID)!=null)
					channel.setChannel_id(channelJSONObj.get(ReqResNodes.CHANNEL_ID).toString());
				if(channelJSONObj.get(ReqResNodes.CHANNEL_CODE)!=null)
					channel.setChannel_code((channelJSONObj.get(ReqResNodes.CHANNEL_CODE).toString()));
				if(channelJSONObj.get(ReqResNodes.CATEGORY_ID)!=null)
					channel.setCategory_id((channelJSONObj.get(ReqResNodes.CATEGORY_ID).toString()));
				if(channelJSONObj.get(ReqResNodes.CHANNEL_NAME)!=null)
					channel.setChannel_name((channelJSONObj.get(ReqResNodes.CHANNEL_NAME).toString()));
				if(channelJSONObj.get(ReqResNodes.LANGUAGE_ID)!=null)
					channel.setLanguage_id((channelJSONObj.get(ReqResNodes.LANGUAGE_ID).toString()));
				if(channelJSONObj.get(ReqResNodes.DESCRIPTION)!=null)
					channel.setDescription((channelJSONObj.get(ReqResNodes.DESCRIPTION).toString()));
				if(channelJSONObj.get(ReqResNodes.RTMP_LINK)!=null)
					channel.setRtmp_link((channelJSONObj.get(ReqResNodes.RTMP_LINK).toString()));
				if(channelJSONObj.get(ReqResNodes.FOLLOWERS)!=null)
					channel.setFollowers((channelJSONObj.get(ReqResNodes.FOLLOWERS).toString()));
				if(channelJSONObj.get(ReqResNodes.VIEWS)!=null)
					channel.setViews((channelJSONObj.get(ReqResNodes.VIEWS).toString()));
				if(channelJSONObj.get(ReqResNodes.DISPLAY_CHANNEL)!=null)
					channel.setDisplay_channel((channelJSONObj.get(ReqResNodes.DISPLAY_CHANNEL).toString()));
				if(channelJSONObj.get(ReqResNodes.DEFAULT_CHANNEL)!=null)
					channel.setDefault_channel((channelJSONObj.get(ReqResNodes.DEFAULT_CHANNEL).toString()));
				if(channelJSONObj.get(ReqResNodes.TIME_WATCHED)!=null)
					channel.setTime_watched((channelJSONObj.get(ReqResNodes.TIME_WATCHED).toString()));
				if(channelJSONObj.get(ReqResNodes.THUMBNAIL)!=null)
					channel.setThumbnail((channelJSONObj.get(ReqResNodes.THUMBNAIL).toString()));
				channelsList.add(channel);
			}
		}catch(JSONException e){
			e.printStackTrace();
			channel=null;
		}
		
		Log.i(TAG,"getChannelParsedResponse() Exiting.");
		return channelsList;
	}

}
