package com.amallu.parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.ActivityLog;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class ActivityLogParser{
	
	private static final String TAG="ActivityLogParser";
	
	//Method Parses the JSON Response of ActivityLog Web service.
	public static ActivityLog getActivityLogParsedResponse(String activityLogStr){
		Log.i(TAG,"getActivityLogParsedResponse() Entering.");
		ActivityLog activityLog=new ActivityLog();
		List<HashMap<String,Object>> activityLogRowsHMList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> activityLogRowHM;
		try{
			
			JSONObject activityLogJSONObj=new JSONObject(activityLogStr);
			if(!activityLogJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					activityLogJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISFAILURE)){
				activityLog.setIsSuccess(activityLogJSONObj.get(ReqResNodes.ISSUCCESS).toString());
				activityLog.setMessage(activityLogJSONObj.get(ReqResNodes.MESSAGE).toString());
			}else{
				activityLog.setIsSuccess(activityLogJSONObj.get(ReqResNodes.ISSUCCESS).toString());
				JSONArray messageJSONArray=(JSONArray)activityLogJSONObj.getJSONArray(ReqResNodes.MESSAGE);
				for(int channelsArr=0;channelsArr<messageJSONArray.length();channelsArr++){
					activityLogRowHM=new HashMap<String,Object>();
					JSONObject activityLogItemJSONObj=(JSONObject)messageJSONArray.get(channelsArr);
					if(!activityLogItemJSONObj.isNull(ReqResNodes.LOG_ID))
						activityLogRowHM.put(ReqResNodes.LOG_ID,activityLogItemJSONObj.get(ReqResNodes.LOG_ID));
					if(!activityLogItemJSONObj.isNull(ReqResNodes.ACTIVITY_ID))
						activityLogRowHM.put(ReqResNodes.ACTIVITY_ID,activityLogItemJSONObj.get(ReqResNodes.ACTIVITY_ID));
					if(!activityLogItemJSONObj.isNull(ReqResNodes.FIRSTNAME))
						activityLogRowHM.put(ReqResNodes.FIRSTNAME,activityLogItemJSONObj.get(ReqResNodes.FIRSTNAME));
					if(!activityLogItemJSONObj.isNull(ReqResNodes.LASTNAME))
						activityLogRowHM.put(ReqResNodes.LASTNAME,activityLogItemJSONObj.get(ReqResNodes.LASTNAME));
					if(!activityLogItemJSONObj.isNull(ReqResNodes.LOG_MSG))
						activityLogRowHM.put(ReqResNodes.LOG_MSG,activityLogItemJSONObj.get(ReqResNodes.LOG_MSG));
					if(!activityLogItemJSONObj.isNull(ReqResNodes.DT_CREATED))
						activityLogRowHM.put(ReqResNodes.DT_CREATED,activityLogItemJSONObj.get(ReqResNodes.DT_CREATED));
					if(!activityLogItemJSONObj.isNull(ReqResNodes.COMMENT_ID))
						activityLogRowHM.put(ReqResNodes.COMMENT_ID,activityLogItemJSONObj.get(ReqResNodes.COMMENT_ID));
					if(!activityLogItemJSONObj.isNull(ReqResNodes.COMMENT))
						activityLogRowHM.put(ReqResNodes.COMMENT,activityLogItemJSONObj.get(ReqResNodes.COMMENT));
					if(!activityLogItemJSONObj.isNull(ReqResNodes.USERID))
						activityLogRowHM.put(ReqResNodes.USERID,activityLogItemJSONObj.get(ReqResNodes.USERID));
					if(!activityLogItemJSONObj.isNull(ReqResNodes.TIME_DIFF))
						activityLogRowHM.put(ReqResNodes.TIME_DIFF,activityLogItemJSONObj.get(ReqResNodes.TIME_DIFF));
					if(!activityLogItemJSONObj.isNull(ReqResNodes.PROFILE_URL))
						activityLogRowHM.put(ReqResNodes.PROFILE_URL,activityLogItemJSONObj.get(ReqResNodes.PROFILE_URL));
					
					activityLogRowsHMList.add(activityLogRowHM);
					activityLog.setActivityLogHMList(activityLogRowsHMList);
				}
			}
		}catch(JSONException e){
			e.printStackTrace();
			activityLog=null;
		}
		
		Log.i(TAG,"getActivityLogParsedResponse() Exiting.");
		return activityLog;
	}

}