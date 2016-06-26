package com.amallu.parser;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.FavoriteChannels;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class FavoriteChannelsParser{
	
	private static final String TAG="FavoriteChannelsParser";
	
	//Method Parses the JSON Response of FavoriteChannels Web service.
	public static FavoriteChannels getFavoriteChannelsParsedResponse(String favoriteChannelsStr){
		Log.i(TAG,"getFavoriteChannelsParsedResponse() Entering.");
		FavoriteChannels favoriteChannels=new FavoriteChannels();
		ArrayList<HashMap<String,Object>> favoriteChannelRowsHMList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> favoriteChannelRowHM;
		try{
			
			JSONObject favoriteChannelsJSONObj=new JSONObject(favoriteChannelsStr);
			if(!favoriteChannelsJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					favoriteChannelsJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISFAILURE)){
				favoriteChannels.setIsSuccess(favoriteChannelsJSONObj.get(ReqResNodes.ISSUCCESS).toString());
				favoriteChannels.setMessage(favoriteChannelsJSONObj.get(ReqResNodes.MESSAGE).toString());
			}else{
				favoriteChannels.setIsSuccess(favoriteChannelsJSONObj.get(ReqResNodes.ISSUCCESS).toString());
				JSONArray messageJSONArray=(JSONArray)favoriteChannelsJSONObj.getJSONArray(ReqResNodes.MESSAGE);
				for(int channelsArr=0;channelsArr<messageJSONArray.length();channelsArr++){
					favoriteChannelRowHM=new HashMap<String,Object>();
					JSONObject favoriteChannelItemJSONObj=(JSONObject)messageJSONArray.get(channelsArr);
					if(!favoriteChannelItemJSONObj.isNull(ReqResNodes.CHANNEL_CODE))
						favoriteChannelRowHM.put(ReqResNodes.CHANNEL_CODE,favoriteChannelItemJSONObj.get(ReqResNodes.CHANNEL_CODE));
					if(!favoriteChannelItemJSONObj.isNull(ReqResNodes.CHANNEL_NAME))
						favoriteChannelRowHM.put(ReqResNodes.CHANNEL_NAME,favoriteChannelItemJSONObj.get(ReqResNodes.CHANNEL_NAME));
					if(!favoriteChannelItemJSONObj.isNull(ReqResNodes.CATEGORY_ID))
						favoriteChannelRowHM.put(ReqResNodes.CATEGORY_ID,favoriteChannelItemJSONObj.get(ReqResNodes.CATEGORY_ID));
					if(!favoriteChannelItemJSONObj.isNull(ReqResNodes.LANGUAGE_ID))
						favoriteChannelRowHM.put(ReqResNodes.LANGUAGE_ID,favoriteChannelItemJSONObj.get(ReqResNodes.LANGUAGE_ID));
					if(!favoriteChannelItemJSONObj.isNull(ReqResNodes.VIEWS))
						favoriteChannelRowHM.put(ReqResNodes.VIEWS,favoriteChannelItemJSONObj.get(ReqResNodes.VIEWS));
					if(!favoriteChannelItemJSONObj.isNull(ReqResNodes.TIME_WATCHED))
						favoriteChannelRowHM.put(ReqResNodes.TIME_WATCHED,favoriteChannelItemJSONObj.get(ReqResNodes.TIME_WATCHED));
					
					favoriteChannelRowsHMList.add(favoriteChannelRowHM);
					favoriteChannels.setFavoriteChannelsList(favoriteChannelRowsHMList);
				}
			}
		}catch(JSONException e){
			e.printStackTrace();
			favoriteChannels=null;
		}
		
		Log.i(TAG,"getFavoriteChannelsParsedResponse() Exiting.");
		return favoriteChannels;
	}

}