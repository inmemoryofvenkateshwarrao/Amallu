package com.amallu.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.model.ChannelDetail;
import com.amallu.model.ChannelInfo;
import com.amallu.model.Comment;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class ChannelInfoParser{
	
	private static final String TAG="ChannelInfoParser";
	
	//Method Parses the JSON Response of ChannelInfo Web service.
	public static ChannelInfo getChannelInfoParsedResponse(String channelInfoStr){
		Log.i(TAG,"getChannelInfoParsedResponse() Entering.");
		
		ChannelInfo channelInfo=null;
		ChannelDetail channelDetail=null;
		Comment comment=null; 
		List<Comment> commentsList=null;
		try{
			channelInfo=new ChannelInfo();
			channelDetail=new ChannelDetail();
			commentsList=new ArrayList<Comment>();
			JSONObject channelInfoJSONObj=new JSONObject(channelInfoStr);
			if(!channelInfoJSONObj.isNull(ReqResNodes.ISSUCCESS))
				channelInfo.setIsSuccess(channelInfoJSONObj.get(ReqResNodes.ISSUCCESS).toString());
			if(!channelInfoJSONObj.isNull(ReqResNodes.ISSUCCESS)&&
					channelInfoJSONObj.get(ReqResNodes.ISSUCCESS).toString().equals(ErrorCodes.ISFAILURE)){
				String isSuccessStr=(String)channelInfoJSONObj.get(ReqResNodes.ISSUCCESS);
				channelInfo.setIsSuccess(isSuccessStr);
				String messageStr=(String)channelInfoJSONObj.get(ReqResNodes.MESSAGE);
				channelInfo.setMessage(messageStr);
			}else{
				Log.v(TAG,"ChannelInfo success in parser");
				JSONObject messageJSONObj=(JSONObject)channelInfoJSONObj.get(ReqResNodes.MESSAGE);
				JSONObject channelDetailJSONObj=(JSONObject)messageJSONObj.get(ReqResNodes.CHANNELDETAIL);
				
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
				
				channelInfo.setChannelDetail(channelDetail);
				
				JSONArray commentsJSONArr=(JSONArray)messageJSONObj.getJSONArray(ReqResNodes.COMMENTS);
				for(int c=0;c<commentsJSONArr.length();c++){
					JSONObject commentJSONObj=(JSONObject)commentsJSONArr.get(c);
					comment=new Comment();
					if(!commentJSONObj.isNull(ReqResNodes.COMMENT_ID))
						comment.setComment_id(commentJSONObj.get(ReqResNodes.COMMENT_ID).toString());
					if(!commentJSONObj.isNull(ReqResNodes.USERID))
						comment.setUserid(commentJSONObj.get(ReqResNodes.USERID).toString());
					if(!commentJSONObj.isNull(ReqResNodes.CHANNEL_ID))
						comment.setChannel_id(commentJSONObj.get(ReqResNodes.CHANNEL_ID).toString());
					if(!commentJSONObj.isNull(ReqResNodes.COMMENT))
						comment.setComment(commentJSONObj.get(ReqResNodes.COMMENT).toString());
					if(!commentJSONObj.isNull(ReqResNodes.PREFERENCE_TYPE))
						comment.setPreference_type(commentJSONObj.get(ReqResNodes.PREFERENCE_TYPE).toString());
					if(!commentJSONObj.isNull(ReqResNodes.HIDE_COMMENT))
						comment.setHide_comment(commentJSONObj.get(ReqResNodes.HIDE_COMMENT).toString());
					if(!commentJSONObj.isNull(ReqResNodes.DT_CREATED))
						comment.setDt_created(commentJSONObj.get(ReqResNodes.DT_CREATED).toString());
					
					commentsList.add(comment);
				}
				
				channelInfo.setCommentsList(commentsList);
				
				String noOfWatMems=(String)messageJSONObj.get(ReqResNodes.NOOFWATCHINGMEMBERS);
				String likeCount=(String)messageJSONObj.get(ReqResNodes.LIKECOUNT);
				String disLikeCount=(String)messageJSONObj.get(ReqResNodes.DISLIKECOUNT);
				
				channelInfo.setNoofwatchingmembers(noOfWatMems);
				channelInfo.setLikecount(likeCount);
				channelInfo.setDislikecount(disLikeCount);
				//Log.v(TAG,"commentsJSONArr : "+commentsJSONArr);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
			channelInfo=null;
		}
		
		Log.i(TAG,"getChannelInfoParsedResponse() Exiting.");
		return channelInfo;
	}

}
