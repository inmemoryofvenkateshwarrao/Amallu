package com.amallu.parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.TrendingChannels;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class TrendingChannelsParser{
	
	private static final String TAG="TrendingChannelsParser";
	
	//Method Parses the JSON Response of TrendingChannels Web service.
	public static TrendingChannels getTrendingChannelsListParsedResponse(String trendChannelStr){
		Log.i(TAG,"getTrendingChannelsListParsedResponse() Entering.");
		TrendingChannels trendingChannels=new TrendingChannels();
		List<HashMap<String,Object>> trendChannlesRowsHMList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> trendChannelRowHM;
		try{
			
			JSONObject trendChannelsJSONObj=new JSONObject(trendChannelStr);
			if(!trendChannelsJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					trendChannelsJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISFAILURE)){
				trendingChannels.setIsSuccess(trendChannelsJSONObj.get(ReqResNodes.ISSUCCESS).toString());
				trendingChannels.setMessage(trendChannelsJSONObj.get(ReqResNodes.MESSAGE).toString());
			}else{
				trendingChannels.setIsSuccess(trendChannelsJSONObj.get(ReqResNodes.ISSUCCESS).toString());
				JSONArray messageJSONArray=(JSONArray)trendChannelsJSONObj.getJSONArray(ReqResNodes.MESSAGE);
				for(int channelsArr=0;channelsArr<messageJSONArray.length();channelsArr++){
					trendChannelRowHM=new HashMap<String,Object>();
					JSONObject trendChannelItemJSONObj=(JSONObject)messageJSONArray.get(channelsArr);
					if(!trendChannelItemJSONObj.isNull(ReqResNodes.CHANNEL_CODE))
						trendChannelRowHM.put(ReqResNodes.CHANNEL_CODE,trendChannelItemJSONObj.get(ReqResNodes.CHANNEL_CODE));
					if(!trendChannelItemJSONObj.isNull(ReqResNodes.CHANNEL_NAME))
						trendChannelRowHM.put(ReqResNodes.CHANNEL_NAME,trendChannelItemJSONObj.get(ReqResNodes.CHANNEL_NAME));
					if(!trendChannelItemJSONObj.isNull(ReqResNodes.CATEGORY_ID))
						trendChannelRowHM.put(ReqResNodes.CATEGORY_ID,trendChannelItemJSONObj.get(ReqResNodes.CATEGORY_ID));
					if(!trendChannelItemJSONObj.isNull(ReqResNodes.LANGUAGE_ID))
						trendChannelRowHM.put(ReqResNodes.LANGUAGE_ID,trendChannelItemJSONObj.get(ReqResNodes.LANGUAGE_ID));
					if(!trendChannelItemJSONObj.isNull(ReqResNodes.VIEWS))
						trendChannelRowHM.put(ReqResNodes.VIEWS,trendChannelItemJSONObj.get(ReqResNodes.VIEWS));
					if(!trendChannelItemJSONObj.isNull(ReqResNodes.TIME_WATCHED))
						trendChannelRowHM.put(ReqResNodes.TIME_WATCHED,trendChannelItemJSONObj.get(ReqResNodes.TIME_WATCHED));
					
					trendChannlesRowsHMList.add(trendChannelRowHM);
					trendingChannels.setTrendingChannelsHMList(trendChannlesRowsHMList);
				}
			}
		}catch(JSONException e){
			e.printStackTrace();
			trendingChannels=null;
		}
		
		Log.i(TAG,"getTrendingChannelsListParsedResponse() Exiting.");
		return trendingChannels;
	}

}