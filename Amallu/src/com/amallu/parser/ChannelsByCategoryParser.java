package com.amallu.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.ChannelsByCategory;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class ChannelsByCategoryParser{
	
	private static final String TAG="ChannelsByCategoryParser";
	
	//Method Parses the JSON Response of ChannelsByCategory Web service.
	public static ChannelsByCategory getChannelsByCategoryParsedResponse(String channelsByCategoryStr){
		Log.i(TAG,"getChannelsByCategoryParsedResponse() Entering.");
		
		ChannelsByCategory channelsByCategory=new ChannelsByCategory();
		List<HashMap<String,Object>> channelsRowsHMList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> channelsRowHM;
		
		try{
			JSONObject channelsByCategoryJSONObj=new JSONObject(channelsByCategoryStr);
			
			if(!channelsByCategoryJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					channelsByCategoryJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISFAILURE)){
				String isSuccessStr=(String)channelsByCategoryJSONObj.get(ReqResNodes.ISSUCCESS);
				channelsByCategory.setIsSuccess(isSuccessStr);
				String messageStr=(String)channelsByCategoryJSONObj.get(ReqResNodes.MESSAGE);
				channelsByCategory.setMessage(messageStr);
			}else{
				Log.v(TAG,"ChannelsByCategory success in parser");
				JSONArray messageJSONArray=(JSONArray)channelsByCategoryJSONObj.getJSONArray(ReqResNodes.MESSAGE);
				JSONObject catJSONObj=(JSONObject)messageJSONArray.get(0);
				
				channelsByCategory.setCategory_id(catJSONObj.get(ReqResNodes.CATEGORY_ID).toString());
				channelsByCategory.setParent_id(catJSONObj.get(ReqResNodes.PARENT_ID).toString());
				channelsByCategory.setCategory_name(catJSONObj.get(ReqResNodes.CATEGORY_NAME).toString());
				channelsByCategory.setIs_active(catJSONObj.get(ReqResNodes.IS_ACTIVE).toString());
				channelsByCategory.setDisplay_order(catJSONObj.get(ReqResNodes.DISPLAY_ORDER).toString());
				
				JSONArray channelsJSONArray=catJSONObj.getJSONArray(ReqResNodes.CHANNELS);
				
				for(int c=0;c<channelsJSONArray.length();c++){
					JSONObject channelJSONObj=(JSONObject)channelsJSONArray.get(c);
					channelsRowHM=new HashMap<String,Object>();
					if(!channelJSONObj.isNull(ReqResNodes.CHANNEL_ID))
						channelsRowHM.put(ReqResNodes.CHANNEL_ID,channelJSONObj.get(ReqResNodes.CHANNEL_ID).toString());
					if(!channelJSONObj.isNull(ReqResNodes.CHANNEL_CODE))
						channelsRowHM.put(ReqResNodes.CHANNEL_CODE,channelJSONObj.get(ReqResNodes.CHANNEL_CODE).toString());
					if(!channelJSONObj.isNull(ReqResNodes.CATEGORY_ID))
						channelsRowHM.put(ReqResNodes.CATEGORY_ID,channelJSONObj.get(ReqResNodes.CATEGORY_ID).toString());
					if(!channelJSONObj.isNull(ReqResNodes.CHANNEL_NAME))
						channelsRowHM.put(ReqResNodes.CHANNEL_NAME,channelJSONObj.get(ReqResNodes.CHANNEL_NAME).toString());
					if(!channelJSONObj.isNull(ReqResNodes.LANGUAGE_ID))
						channelsRowHM.put(ReqResNodes.LANGUAGE_ID,channelJSONObj.get(ReqResNodes.LANGUAGE_ID).toString());
					if(!channelJSONObj.isNull(ReqResNodes.DESCRIPTION))
						channelsRowHM.put(ReqResNodes.DESCRIPTION,channelJSONObj.get(ReqResNodes.DESCRIPTION).toString());
					if(!channelJSONObj.isNull(ReqResNodes.RTMP_LINK))
						channelsRowHM.put(ReqResNodes.RTMP_LINK,channelJSONObj.get(ReqResNodes.RTMP_LINK).toString());
					if(!channelJSONObj.isNull(ReqResNodes.FOLLOWERS))
						channelsRowHM.put(ReqResNodes.FOLLOWERS,channelJSONObj.get(ReqResNodes.FOLLOWERS).toString());
					if(!channelJSONObj.isNull(ReqResNodes.VIEWS))
						channelsRowHM.put(ReqResNodes.VIEWS,channelJSONObj.get(ReqResNodes.VIEWS).toString());
					if(!channelJSONObj.isNull(ReqResNodes.DISPLAY_CHANNEL))
						channelsRowHM.put(ReqResNodes.DISPLAY_CHANNEL,channelJSONObj.get(ReqResNodes.DISPLAY_CHANNEL).toString());
					if(!channelJSONObj.isNull(ReqResNodes.DEFAULT_CHANNEL))
						channelsRowHM.put(ReqResNodes.DEFAULT_CHANNEL,channelJSONObj.get(ReqResNodes.DEFAULT_CHANNEL).toString());
					if(!channelJSONObj.isNull(ReqResNodes.TIME_WATCHED))
						channelsRowHM.put(ReqResNodes.TIME_WATCHED,channelJSONObj.get(ReqResNodes.TIME_WATCHED).toString());
					if(!channelJSONObj.isNull(ReqResNodes.THUMBNAIL))
						channelsRowHM.put(ReqResNodes.THUMBNAIL,channelJSONObj.get(ReqResNodes.THUMBNAIL).toString());
					
					channelsRowsHMList.add(channelsRowHM);
				}
				channelsByCategory.setChannelsHMList(channelsRowsHMList);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
			channelsRowsHMList=null;
		}
		
		Log.i(TAG,"getChannelsByCategoryParsedResponse() Exiting.");
		return channelsByCategory;
	}

}
