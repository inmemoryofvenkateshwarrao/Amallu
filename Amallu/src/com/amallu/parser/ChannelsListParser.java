package com.amallu.parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.utility.ReqResNodes;

public class ChannelsListParser{
	
	private static final String TAG="ChannelsListParser";
	
	//Method Parses the JSON Response of channels list Web service.
	public static List<HashMap<String,Object>> getChannelsListParsedResponse(String channelsStr){
		Log.i(TAG,"getChannelsListParsedResponse() Entering.");
		List<HashMap<String,Object>> channelRowsHMList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> channelRowHM;
		try{
			JSONArray channelsJSONArray=new JSONArray(channelsStr);
			for(int channelArr=0;channelArr<channelsJSONArray.length();channelArr++){
				channelRowHM=new HashMap<String,Object>();
				JSONObject channelDetailJSONObj=(JSONObject)channelsJSONArray.get(channelArr);
				if(!channelDetailJSONObj.isNull(ReqResNodes.CHANNEL_ID))
					channelRowHM.put(ReqResNodes.CHANNEL_ID,channelDetailJSONObj.get(ReqResNodes.CHANNEL_ID));
				if(!channelDetailJSONObj.isNull(ReqResNodes.CHANNEL_CODE))
					channelRowHM.put(ReqResNodes.CHANNEL_CODE,channelDetailJSONObj.get(ReqResNodes.CHANNEL_CODE));
				if(!channelDetailJSONObj.isNull(ReqResNodes.CATEGORY_ID))
					channelRowHM.put(ReqResNodes.CATEGORY_ID,channelDetailJSONObj.get(ReqResNodes.CATEGORY_ID));
				if(!channelDetailJSONObj.isNull(ReqResNodes.CHANNEL_NAME))
					channelRowHM.put(ReqResNodes.CHANNEL_NAME,channelDetailJSONObj.get(ReqResNodes.CHANNEL_NAME));
				if(!channelDetailJSONObj.isNull(ReqResNodes.LANGUAGE_ID))
					channelRowHM.put(ReqResNodes.LANGUAGE_ID,channelDetailJSONObj.get(ReqResNodes.LANGUAGE_ID));
				if(!channelDetailJSONObj.isNull(ReqResNodes.DESCRIPTION))
					channelRowHM.put(ReqResNodes.DESCRIPTION,channelDetailJSONObj.get(ReqResNodes.DESCRIPTION));
				if(!channelDetailJSONObj.isNull(ReqResNodes.RTMP_LINK))
					channelRowHM.put(ReqResNodes.RTMP_LINK,channelDetailJSONObj.get(ReqResNodes.RTMP_LINK));
				if(!channelDetailJSONObj.isNull(ReqResNodes.FOLLOWERS))
					channelRowHM.put(ReqResNodes.FOLLOWERS,channelDetailJSONObj.get(ReqResNodes.FOLLOWERS));
				if(!channelDetailJSONObj.isNull(ReqResNodes.VIEWS))
					channelRowHM.put(ReqResNodes.VIEWS,channelDetailJSONObj.get(ReqResNodes.VIEWS));
				if(!channelDetailJSONObj.isNull(ReqResNodes.DISPLAY_CHANNEL))
					channelRowHM.put(ReqResNodes.DISPLAY_CHANNEL,channelDetailJSONObj.get(ReqResNodes.DISPLAY_CHANNEL));
				if(!channelDetailJSONObj.isNull(ReqResNodes.DEFAULT_CHANNEL))
					channelRowHM.put(ReqResNodes.DEFAULT_CHANNEL,channelDetailJSONObj.get(ReqResNodes.DEFAULT_CHANNEL));
				if(!channelDetailJSONObj.isNull(ReqResNodes.TIME_WATCHED))
					channelRowHM.put(ReqResNodes.TIME_WATCHED,channelDetailJSONObj.get(ReqResNodes.TIME_WATCHED));
				if(!channelDetailJSONObj.isNull(ReqResNodes.THUMBNAIL))
					channelRowHM.put(ReqResNodes.THUMBNAIL,channelDetailJSONObj.get(ReqResNodes.THUMBNAIL));
				channelRowsHMList.add(channelRowHM);
			}
		}catch(JSONException e){
			e.printStackTrace();
			channelRowsHMList=null;
		}
		
		Log.i(TAG,"getChannelsListParsedResponse() Exiting.");
		return channelRowsHMList;
	}
	
}	
	
	/*public static List<ChannelDetail> getChannelsListParsedResponse(String channelsStr){
		Log.i(TAG,"getChannelsListParsedResponse() Entering.");
		List<ChannelDetail> channelsDetailList=new ArrayList<ChannelDetail>();
		ChannelDetail channelDetail=null;
		try{
			JSONArray channelsJSONArray=new JSONArray(channelsStr);
			for(int channelArr=0;channelArr<channelsJSONArray.length();channelArr++){
				channelDetail=new ChannelDetail();
				JSONObject channelDetailJSONObj=(JSONObject)channelsJSONArray.get(channelArr);
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
			channelsDetailList=null;
		}
		
		Log.i(TAG,"getChannelsListParsedResponse() Exiting.");
		return channelsDetailList;
	}*/

