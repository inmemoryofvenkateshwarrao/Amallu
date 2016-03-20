package com.amallu.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.ChannelsByLanguage;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class ChannelsByLanguageParser{
	
	private static final String TAG="ChannelsByLanguageParser";
	
	//Method Parses the JSON Response of ChannelsByLanguage Web service.
	public static List<HashMap<String,Object>> getChannelsByLanguageParsedResponse(String channelsByLanguageStr){
		Log.i(TAG,"getChannelsByLanguageParsedResponse() Entering.");
		
		ChannelsByLanguage channelsByLanguage=new ChannelsByLanguage();
		List<HashMap<String,Object>> channelsRowsHMList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> channelsRowHM;
		
		try{
			JSONObject channelsByLanguageJSONObj=new JSONObject(channelsByLanguageStr);
			
			if(!channelsByLanguageJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					channelsByLanguageJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISFAILURE)){
				String isSuccessStr=(String)channelsByLanguageJSONObj.get(ReqResNodes.ISSUCCESS);
				channelsByLanguage.setIsSuccess(isSuccessStr);
				String messageStr=(String)channelsByLanguageJSONObj.get(ReqResNodes.MESSAGE);
				channelsByLanguage.setMessage(messageStr);
			}else{
				Log.v(TAG,"ChannelsByLanguge success in parser");
				JSONArray messageJSONArray=(JSONArray)channelsByLanguageJSONObj.getJSONArray(ReqResNodes.MESSAGE);
				JSONObject langJSONObj=(JSONObject)messageJSONArray.get(0);
				
				//Not using below setters any where.
				channelsByLanguage.setLanguage_id(langJSONObj.get(ReqResNodes.LANGUAGE_ID).toString());
				channelsByLanguage.setLanguage(langJSONObj.get(ReqResNodes.LANGUAGE).toString());
				channelsByLanguage.setIs_active(langJSONObj.get(ReqResNodes.IS_ACTIVE).toString());
				
				JSONArray channelsJSONArray=langJSONObj.getJSONArray(ReqResNodes.CHANNELS);
				
				for(int c=0;c<channelsJSONArray.length();c++){
					JSONObject channelJSONObj=(JSONObject)channelsJSONArray.get(c);
					channelsRowHM=new HashMap<String,Object>();
					
					//Adding below common category info in every HashMap.
					if(!langJSONObj.isNull(ReqResNodes.LANGUAGE_ID))
						channelsRowHM.put(ReqResNodes.LANGUAGE_ID,langJSONObj.get(ReqResNodes.LANGUAGE_ID).toString());
					if(!langJSONObj.isNull(ReqResNodes.LANGUAGE))
						channelsRowHM.put(ReqResNodes.LANGUAGE,langJSONObj.get(ReqResNodes.LANGUAGE).toString());
					if(!langJSONObj.isNull(ReqResNodes.IS_ACTIVE))
						channelsRowHM.put(ReqResNodes.IS_ACTIVE,langJSONObj.get(ReqResNodes.IS_ACTIVE).toString());
					
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
				channelsByLanguage.setChannelsHMList(channelsRowsHMList);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
			channelsRowsHMList=null;
		}
		
		Log.i(TAG,"getChannelsByLanguageParsedResponse() Exiting.");
		return channelsRowsHMList;
	}

}
