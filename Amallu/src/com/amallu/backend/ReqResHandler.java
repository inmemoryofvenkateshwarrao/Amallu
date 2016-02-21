package com.amallu.backend;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.amallu.backend.Response.CommonHandlerType;
import com.amallu.exception.AmalluException;
import com.amallu.utility.NetworkType;
import com.amallu.utility.ReqResNodes;
import com.amallu.utility.URLDetails;

public class ReqResHandler implements AsyncCallback{
	
	private AsyncServiceRequest asyncServiceRequest;
	private Context uiContext;
	private CommonHandlerType handlerType;
	private Response response;
	private static final String TAG="ReqResHandler";
	
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
			                String lastname,String password,String confPassword,String gender,String month,
			                String year,String day){
		Log.i(TAG, "signUpRequest() Entering.");
		handlerType = CommonHandlerType.SIGNUP;

		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/user/signup?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.EMAILID,emailid);
		paramsMap.put(ReqResNodes.FIRSTNAME,firstname);
		paramsMap.put(ReqResNodes.LASTNAME,lastname);
		paramsMap.put(ReqResNodes.PASSWORD,password);
		paramsMap.put(ReqResNodes.CONFPASSWORD,confPassword);
		paramsMap.put(ReqResNodes.GENDER,gender);
		paramsMap.put(ReqResNodes.MONTH,month);
		paramsMap.put(ReqResNodes.YEAR,year);
		paramsMap.put(ReqResNodes.DAY,day);

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
	
	//Channel API Call.
	//GET Request.
	public void channelRequest(Context context, Response responseHandler){
		Log.i(TAG, "channelRequest() Entering.");
		handlerType = CommonHandlerType.PROFILE;
		
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
		Log.i(TAG, "channelRequest() Exiting.");
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
	//POST Request.With parameter
	public void likeChannelRequest(Context context, Response responseHandler,String userid,
			String channel_id,String preference_id,String comment){
		Log.i(TAG, "likeChannelRequest() Entering.");
		handlerType = CommonHandlerType.LIKECHANNEL;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/channel/likechannel?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.USERID,userid);
		paramsMap.put(ReqResNodes.CHANNEL_ID,channel_id);

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
	//POST Request.With parameter
	public void dislikeChannelRequest(Context context, Response responseHandler,String channel_id,String userid){
		Log.i(TAG, "dislikeChannelRequest() Entering.");
		handlerType = CommonHandlerType.DISLIKECHANNEL;
		
		response = responseHandler;
		uiContext = context;
		
		//http://www.app.amallu.com/api/channel/dislikechannel?_format=json
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(ReqResNodes.USERID,userid);
		paramsMap.put(ReqResNodes.CHANNEL_ID,channel_id);

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
			if(handlerType.equals(CommonHandlerType.CHANNEL)){
				Log.v(TAG, "Handler CHANNEL");
				if (result.equalsIgnoreCase("Exception")){
					Log.e(TAG, "CHANNEL Exception caught.");
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
		}catch(Exception e){
			Log.e("Exception", e.getMessage());
		}
		Log.i(TAG, "updateAsyncResult() Exiting.");
	}

}
