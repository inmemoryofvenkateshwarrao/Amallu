package com.amallu.backend;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.amallu.backend.Response.CommonHandlerType;
import com.amallu.exception.AmalluException;
import com.amallu.fragment.FriendsFragment;
import com.amallu.parser.LoginParser;
import com.amallu.utility.NetworkType;
import com.amallu.utility.ReqResNodes;
import com.amallu.utility.URLDetails;

public class ReqResHandler implements AsyncCallback{
	
	private AsyncServiceRequest asyncServiceRequest;
	private Context uiContext;
	private CommonHandlerType handlerType;
	private Response response;
	private static final String TAG="ReqResHandler";
	
	//Login API Call.
	//POST Request
	public void loginRequest(Context context, Response responseHandler,String email,String password){
		Log.i(TAG, "loginRequest() Entering.");
		handlerType = CommonHandlerType.LOGIN;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/user/login?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.EMAIL,email);
		paramsMap.put(ReqResNodes.PASSWORD,password);
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
		+"/"+URLDetails.COMMON_URL+"/"+URLDetails.LOGIN;
		Log.d(TAG,"LOGIN URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating LOGIN webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.POST);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "loginRequest() Exiting.");
	}

	//SignUp API Call.
	//POST Request
	public void signUpRequest(Context context, Response responseHandler,String emailid,String firstname,
			                String lastname,String password,String confPassword,String gender,String dob){
		Log.i(TAG, "signUpRequest() Entering.");
		handlerType = CommonHandlerType.SIGNUP;

		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/user/signup?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		//paramsMap.put("_format","json");
		paramsMap.put(ReqResNodes.USERNAME,emailid);
		paramsMap.put(ReqResNodes.FIRSTNAME,firstname);
		paramsMap.put(ReqResNodes.LASTNAME,lastname);
		paramsMap.put(ReqResNodes.PASSWORD,password);
		paramsMap.put(ReqResNodes.CONFPASSWORD,confPassword);
		paramsMap.put(ReqResNodes.GENDER,gender);
		paramsMap.put(ReqResNodes.DOB,dob);

		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
				+"/"+URLDetails.COMMON_URL+"/"+URLDetails.SIGNUP;
		Log.d(TAG,"SIGNUP URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating SIGNUP webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.POST);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "signUpRequest() Exiting.");
	}
	
	//ForgetPassword API Call.
	//POST Request
	public void forgetPasswordRequest(Context context, Response responseHandler,String emailid){
		Log.i(TAG, "forgetPasswordRequest() Entering.");
		handlerType = CommonHandlerType.FORGETPASSWORD;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/user/forgetpassword?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.EMAILID,emailid);
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.FORGETPASSWORD;
		Log.d(TAG,"FORGETPASSWORD URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating FORGETPASSWORD webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.POST);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "forgetPasswordRequest() Exiting.");
	}
	
	//Profile API Call.
	//POST Request
	public void profileRequest(Context context, Response responseHandler,String id){
		Log.i(TAG, "profileRequest() Entering.");
		handlerType = CommonHandlerType.PROFILE;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/user/profile?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.ID,id);
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.PROFILE;
		Log.d(TAG,"PROFILE URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating PROFILE webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.POST);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "profileRequest() Exiting.");
	}
	
	//List of Users API Call.
	//GET Request.
	public void usersRequest(Context context, Response responseHandler){
		Log.i(TAG, "usersRequest() Entering.");
		handlerType = CommonHandlerType.USERS;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/user?_format=json
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.USERS;
		Log.d(TAG,"USERS URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating USERS webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "usersRequest() Exiting.");
	}
	
	//NextChannel API Call.
	//GET Request.
	public void nextChannelRequest(Context context, Response responseHandler,String channelNo){
		Log.i(TAG, "nextChannelRequest() Entering.");
		handlerType = CommonHandlerType.NEXTCHANNEL;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/channel/channelinfo?_format=json&channelid=2&userid=32 
		
		String userID=LoginParser.getUserID();
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.NEXTCHANNEL+"&channelid="+channelNo
							+"&userid="+userID;
		Log.d(TAG,"NEXTCHANNEL URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating NEXTCHANNEL webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "nextChannelRequest() Exiting.");
	}
	
	//Comment API Call.
	//GET Request.
	public void commentRequest(Context context, Response responseHandler){
		Log.i(TAG, "commentRequest() Entering.");
		handlerType = CommonHandlerType.COMMENT;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/comment?_format=json
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.COMMENT;
		Log.d(TAG,"COMMENT URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating COMMENT webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "commentRequest() Exiting.");
	}
		
	//CreateComment API Call.
	//POST Request.
	public void createCommentRequest(Context context, Response responseHandler,String userid,
			String channel_id,String preference_id,String comment){
		Log.i(TAG, "createCommentRequest() Entering.");
		handlerType = CommonHandlerType.CREATECOMMENT;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/channel
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.USERID,userid);
		paramsMap.put(ReqResNodes.CHANNEL_ID,channel_id);
		paramsMap.put(ReqResNodes.PREFERENCE_ID,preference_id);
		paramsMap.put(ReqResNodes.COMMENT,comment);
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.COMMENT;
		Log.d(TAG,"CREATECOMMENT URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating CREATECOMMENT webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.POST);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "createCommentRequest() Exiting.");
	}
	
	//EditComment API Call.
	//POST Request.
	public void editCommentRequest(Context context, Response responseHandler,String userid,
			String channel_id,String preference_id,String comment){
		Log.i(TAG, "editCommentRequest() Entering.");
		handlerType = CommonHandlerType.EDITCOMMENT;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/comment/2?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.USERID,userid);
		paramsMap.put(ReqResNodes.CHANNEL_ID,channel_id);
		paramsMap.put(ReqResNodes.PREFERENCE_ID,preference_id);
		paramsMap.put(ReqResNodes.COMMENT,comment);
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.COMMENT;
		Log.d(TAG,"EDITCOMMENT URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating EDITCOMMENT webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.POST);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "editCommentRequest() Exiting.");
	}
		
	//DeleteComment API Call.
	//GET Request.With parameter
	public void deleteCommentRequest(Context context, Response responseHandler,String id){
		Log.i(TAG, "deleteCommentRequest() Entering.");
		handlerType = CommonHandlerType.DELETECOMMENT;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/comment/2?_format=json

		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.COMMENT;
		Log.d(TAG,"DELETECOMMENT URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating DELETECOMMENT webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "deleteCommentRequest() Exiting.");
	}
	
	//LikeChannel API Call.
	//POST Request
	public void likeChannelRequest(Context context, Response responseHandler,String userid,String channelid){
		Log.i(TAG, "likeChannelRequest() Entering.");
		handlerType = CommonHandlerType.LIKECHANNEL;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/channel/likechannel?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.USERID,userid);
		paramsMap.put(ReqResNodes.CHANNELID,channelid);

		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.LIKECHANNEL;
		Log.d(TAG,"likeChannelRequest URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating likeChannelRequest webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.POST);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "likeChannelRequest() Exiting.");
	}
	
	//DislikeChannel API Call.
	//POST Request
	public void dislikeChannelRequest(Context context, Response responseHandler,String channelid,String userid){
		Log.i(TAG, "dislikeChannelRequest() Entering.");
		handlerType = CommonHandlerType.DISLIKECHANNEL;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/channel/dislikechannel?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.USERID,userid);
		paramsMap.put(ReqResNodes.CHANNELID,channelid);

		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.DISLIKECHANNEL;
		Log.d(TAG,"DISLIKECHANNEL URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating DISLIKECHANNEL webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.POST);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "dislikeChannelRequest() Exiting.");
	}
		
	//CreateComment API Call.
	//GET Request.With parameter
	public void deleteCommentRequest(Context context, Response responseHandler,String userid,
			String channel_id,String preference_id,String comment){
		Log.i(TAG, "deleteCommentRequest() Entering.");
		handlerType = CommonHandlerType.DELETECOMMENT;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/comment/2?_format=json

		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.COMMENT;
		Log.d(TAG,"DELETECOMMENT URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating DELETECOMMENT webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "deleteCommentRequest() Exiting.");
	}
	
	//Categories List API Call.
	//GET Request
	public void categoriesListRequest(Context context, Response responseHandler){
		Log.i(TAG, "categoriesListRequest() Entering.");
		handlerType = CommonHandlerType.CATEGORY;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/category
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.CATEGORY;
		Log.d(TAG,"CATEGORY URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating CATEGORY webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "categoriesListRequest() Exiting.");
	}
	
	//Channels List API Call.
	//GET Request
	public void channelsListRequest(Context context, Response responseHandler){
		Log.i(TAG, "channelsListRequest() Entering.");
		handlerType = CommonHandlerType.CHANNEL;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/channel
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.CHANNEL;
		Log.d(TAG,"CHANNEL URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating CHANNEL webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "channelsListRequest() Exiting.");
	}
	
	//Channelinfo API Call.
	//GET Request
	public void defaultChannelInfoRequest(Context context, Response responseHandler,String userID){
		Log.i(TAG, "defaultChannelInfoRequest() Entering.");
		handlerType = CommonHandlerType.CHANNELINFO;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/channel/channelinfo?_format=json&userid=
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.CHANNELINFO+"&userid="+userID;
		Log.d(TAG,"CHANNELINFO URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating CHANNELINFO webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "defaultChannelInfoRequest() Exiting.");
	}
	
	//Languages List API Call.
	//GET Request
	public void languagesListRequest(Context context, Response responseHandler){
		Log.i(TAG, "languagesListRequest() Entering.");
		handlerType = CommonHandlerType.LANGUAGE;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/language
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.LANGUAGE;
		Log.d(TAG,"LANGUAGE URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating LANGUAGE webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "languagesListRequest() Exiting.");
	}
	
	//Change Password API Call.
	//POST Request
	public void changePasswordRequest(Context context, Response responseHandler,String emailid,String oldPwd,
									String newPwd,String confirmPwd){
		Log.i(TAG, "changePasswordRequest() Entering.");
		handlerType = CommonHandlerType.CHANGEPASSWORD;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/user/changepassword?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.EMAILID,emailid);
		paramsMap.put(ReqResNodes.OLDPASSWORD,oldPwd);
		paramsMap.put(ReqResNodes.NEWPASSWORD,newPwd);
		paramsMap.put(ReqResNodes.CONFIRMPASSWORD,confirmPwd);
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.CHANGEPASSWORD;
		Log.d(TAG,"CHANGEPASSWORD URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating CHANGEPASSWORD webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.POST);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "changePasswordRequest() Exiting.");
	}
	
	//ChannelsByCategory List API Call.
	//GET Request
	public void channelsByCategoryRequest(Context context, Response responseHandler,String categoryid){
		Log.i(TAG, "channelsByCategoryRequest() Entering.");
		handlerType = CommonHandlerType.CHANNELSBYCATEGORY;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/category/channelsbycategory?categoryid=1&_format=json
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.CHANNELSBYCATEGORY+"&"+"categoryid="+categoryid;
		Log.d(TAG,"CHANNELSBYCATEGORY URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating CHANNELSBYCATEGORY webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "channelsByCategoryRequest() Exiting.");
	}
	
	//ChannelsByLangugage List API Call.
	//GET Request
	public void channelsByLanguageRequest(Context context, Response responseHandler,String languageid){
		Log.i(TAG, "channelsByLanguageRequest() Entering.");
		handlerType = CommonHandlerType.CHANNELSBYLANGUAGE;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/language/channelsbylanguage?languageid=1&_format=json
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.CHANNELSBYLANGUAGE+"&"+"languageid="+languageid;
		Log.d(TAG,"CHANNELSBYLANGUAGE URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating CHANNELSBYLANGUAGE webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "channelsByLanguageRequest() Exiting.");
	}
	
	//TrendingChannels API Call.
	//GET Request
	public void trendingChannelsRequest(Context context, Response responseHandler){
		Log.i(TAG, "trendingChannelsRequest() Entering.");
		handlerType = CommonHandlerType.TRENDINGCHANNELS;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/channel/trendingchannels?_format=json
		
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.TRENDINGCHANNELS;
		Log.d(TAG,"TRENDINGCHANNELS URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating TRENDINGCHANNELS webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "trendingChannelsRequest() Exiting.");
	}
	
	//AddFriend API Call.
	//POST Request
	public void addFriendRequest(Context context, Response responseHandler,String userID,String friendID){
		Log.i(TAG, "addFriendRequest() Entering.");
		handlerType = CommonHandlerType.ADDFRIEND;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/friend/addfriend?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.USERID,userID);
		paramsMap.put(ReqResNodes.FRIENDID,friendID);
				
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.ADDFRIEND;
		Log.d(TAG,"ADDFRIEND URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating ADDFRIEND webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.POST);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "addFriendRequest() Exiting.");
	}
	
	//ActivityLog API Call.
	//GET Request
	public void activityLogRequest(Context context, Response responseHandler,String userID,String time){
		Log.i(TAG, "activityLogRequest() Entering.");
		handlerType = CommonHandlerType.ACTIVITYLOG;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/activity/friendsactivitylog?_format=json&userid=xx
		//http://www.app.amallu.com/api/activity/friendsactivitylog?_format=json&userid=xx&time=1458441600
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.USERID,userID);
		
		String url="";
		
		if(time==null){
			url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
					+"/"+URLDetails.COMMON_URL+"/"+URLDetails.ACTIVITYLOG+"&userid="+userID;
		}else{
			paramsMap.put(ReqResNodes.TIME,time);
			url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
					+"/"+URLDetails.COMMON_URL+"/"+URLDetails.ACTIVITYLOG+"&userid="+userID+"&time="+time;
		}
		
		Log.d(TAG,"ACTIVITYLOG URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating ACTIVITYLOG webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,paramsMap,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "activityLogRequest() Exiting.");
	}
	
	//FavoriteChannels API Call.
	//GET Request
	public void favoriteChannelsRequest(Context context, Response responseHandler,String userID){
		Log.i(TAG, "favoriteChannelsRequest() Entering.");
		handlerType = CommonHandlerType.FAVORITECHANNELS;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/channel/favoritechannels?_format=json&userid=xx
				
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.FAVORITECHANNELS+"&userid="+userID;
		Log.d(TAG,"FAVORITECHANNELS URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating FAVORITECHANNELS webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "favoriteChannelsRequest() Exiting.");
	}
	
	//FriendsList API Call.
	//GET Request
	public void friendsListRequest(Context context, Response responseHandler,String userID){
		Log.i(TAG, "friendsListRequest() Entering.");
		handlerType = CommonHandlerType.FRIENDSLIST;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/friend/friendslist?userid=40&_format=json
				
		String url = URLDetails.PROTOCOL+"://"+URLDetails.HOST
							+"/"+URLDetails.COMMON_URL+"/"+URLDetails.FRIENDSLIST+"&userid="+userID;
		Log.d(TAG,"FRIENDSLIST URL : "+url);
		if(checkNetworkAvailability(context)){
			Log.v(TAG,"Network is available. Initiating FRIENDSLIST webservice call.");
			asyncServiceRequest = new AsyncServiceRequest(this,null,uiContext,ReqResNodes.GET);
			asyncServiceRequest.execute(url);
		}else{
			Log.e(TAG,"Network connection not available.");
		}
		Log.i(TAG, "friendsListRequest() Exiting.");
	}
	
	private boolean checkNetworkAvailability(Context ctx){
		Log.i(TAG,"checkNetworkAvailability() Entering.");
		boolean netWorkCheck=true;
		switch(NetworkType.isConnected(ctx)){
			case 1:
				Log.v(TAG, "network check switch case 1");
			case 2:
				Log.v(TAG, "network check switch case 2");
			case 3:
				Log.v(TAG, "network check switch case 3");
				break;
			case 4:
				netWorkCheck=false;
				Log.v(TAG, "network check switch case 4");
				updateAsyncResult("Exception",new AmalluException("4100","No network available","Please check network connection."));
				break;
		}	
		Log.i(TAG,"checkNetworkAvailability() Exiting.");
		return netWorkCheck;
	}

	@Override
	public void updateAsyncResult(String result,AmalluException exception){
		Log.i(TAG, "updateAsyncResult() Entering.");
		try{
			if(handlerType.equals(CommonHandlerType.LOGIN)){
				Log.v(TAG, "Handler LOGIN");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "LOGIN Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.SIGNUP)){
				Log.v(TAG, "Handler SIGNUP");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "SIGNUP Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.FORGETPASSWORD)){
				Log.v(TAG, "Handler FORGETPASSWORD");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "FORGETPASSWORD Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.PROFILE)){
				Log.v(TAG, "Handler PROFILE");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "PROFILE Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.USERS)){
				Log.v(TAG, "Handler USERS");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "USERS Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.NEXTCHANNEL)){
				Log.v(TAG, "Handler NEXTCHANNEL");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "NEXTCHANNEL Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.LIKECHANNEL)){
				Log.v(TAG, "Handler LIKECHANNEL");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "LIKECHANNEL Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.DISLIKECHANNEL)){
				Log.v(TAG, "Handler DISLIKECHANNEL");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "DISLIKECHANNEL Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.COMMENT)){
				Log.v(TAG, "Handler COMMENT");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "COMMENT Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.CREATECOMMENT)){
				Log.v(TAG, "Handler CREATECOMMENT");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "CREATECOMMENT Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.EDITCOMMENT)){
				Log.v(TAG, "Handler EDITCOMMENT");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "EDITCOMMENT Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.DELETECOMMENT)){
				Log.v(TAG, "Handler DELETECOMMENT");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "DELETECOMMENT Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.CHANNELINFO)){
				Log.v(TAG, "Handler CHANNELINFO");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "CHANNELINFO Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.CATEGORY)){
				Log.v(TAG, "Handler CATEGORY");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "CATEGORY Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.CHANNEL)){
				Log.v(TAG, "Handler CHANNEL");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "CHANNEL Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.LANGUAGE)){
				Log.v(TAG, "Handler LANGUAGE");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "LANGUAGE Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.CHANGEPASSWORD)){
				Log.v(TAG, "Handler CHANGEPASSWORD");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "CHANGEPASSWORD Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.CHANNELSBYCATEGORY)){
				Log.v(TAG, "Handler CHANNELSBYCATEGORY");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "CHANNELSBYCATEGORY Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.CHANNELSBYLANGUAGE)){
				Log.v(TAG, "Handler CHANNELSBYLANGUAGE");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "CHANNELSBYLANGUAGE Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.TRENDINGCHANNELS)){
				Log.v(TAG, "Handler TRENDINGCHANNELS");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "TRENDINGCHANNELS Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.ADDFRIEND)){
				Log.v(TAG, "Handler ADDFRIEND");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "ADDFRIEND Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.ACTIVITYLOG)){
				Log.v(TAG, "Handler ACTIVITYLOG");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "ACTIVITYLOG Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.FAVORITECHANNELS)){
				Log.v(TAG, "Handler FAVORITECHANNELS");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "FAVORITECHANNELS Exception caught.");
				}else{
					//Do Nothing.
				}
				response.updateResponse(uiContext,result,handlerType,exception);
			}
			if(handlerType.equals(CommonHandlerType.FRIENDSLIST)){
				Log.v(TAG, "Handler FRIENDSLIST");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "FRIENDSLIST Exception caught.");
				}else{
					//Do Nothing.
				}
				//response.updateResponse(uiContext,result,handlerType,exception);
				
			}
		}catch(Exception e){
			Log.e("Exception", e.getMessage());
		}
		Log.i(TAG, "updateAsyncResult() Exiting.");
	}

}
