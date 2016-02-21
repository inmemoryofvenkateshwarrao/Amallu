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
			Log.v(TAG,"Network is available. Initiating REGISTRATION webservice call.");
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
		handlerType = CommonHandlerType.PROFILE;
		
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
		}catch(Exception e){
			Log.e("Exception", e.getMessage());
		}
		Log.i(TAG, "updateAsyncResult() Exiting.");
	}

}
