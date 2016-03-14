package com.amallu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.amallu.backend.CustomProgressDialog;
import com.amallu.backend.ReqResHandler;
import com.amallu.backend.ResponseHandler;
import com.amallu.exception.AmalluException;
import com.amallu.model.Profile;
import com.amallu.parser.ProfileParser;
import com.amallu.utility.ErrorCodes;

public class ProfileScreen extends Activity implements OnClickListener{

	private static final String TAG="ProfileScreen";
	
	//Method executes whenever object is created.
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate Entering.");
		setContentView(R.layout.splash);
		intializeViews();
		setListeners();
		Log.i(TAG,"onCreate() Exiting.");
	}
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
		Log.i(TAG,"intializeViews() Entering.");
		//changempin_btn = (Button)findViewById(R.id.changempin_btn);
		//failureTxt=(TextView)findViewById(R.id.failure_txt);
		Log.i(TAG,"intializeViews() Exiting.");
	}

	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		//changempin_btn.setOnClickListener(this);
		Log.i(TAG,"setListeners() Exiting");
	}

	@Override
	public void onClick(View view){
		Log.i(TAG,"onClick() Entering.");
		switch(view.getId()){
		/*case R.id.changempin_btn:
			Log.i(TAG,"changempin_btn button clicked");
			String id="";
		
			boolean isValidated=validate(id);
			if(isValidated){
				Log.v(TAG,"Profile details validated successfully.");
				sendProfileReq(id);
			}else{
				Log.v(TAG,"profile validation failure.");
			}
			break;*/
		default:
			Log.e(TAG,"In Default option");
			break;
		}
		Log.i(TAG,"onClick() Exiting");
	}

	
	//Method to check for Android native validations.
	private boolean validate(String id){
		Log.i(TAG,"validate() Entering.");
		
		boolean isValidated=true;
		if(id==null||id.trim().equals("")){
			Log.e(TAG,"Please enter id");
			//Attach Error text to View.
			isValidated=false;
		}
	    Log.i(TAG,"validate() Exiting.");
	    
		return isValidated;
	}
	
	//Method to send Profile API request.
	private void sendProfileReq(String id){
		Log.i(TAG,"sendProfileReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(ProfileScreen.this);

		req.profileRequest(ProfileScreen.this,new ResponseHandler(),id);
		
		Log.i(TAG,"sendProfileReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void proceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"proceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "proceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.v("proceedUI", "Exception Case");
			
		}else{
			Profile profile=ProfileParser.getProfileParsedResponse(result);
			/*if(profile!=null){
				Log.v(TAG,"profile response parsing success.");
				if(profile.getErrorCode().equals(ErrorCodes.SQL_EXCEPTION_ERR_CODE)){
					   Log.e(TAG,"Error Code : "+profile.getErrorCode());
					   Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.INVALID_SESSION_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.SESSION_EXPIRED_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.COMMUNICATION_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}/*else if(profile.getErrorCode().equals(ErrorCodes.profile_DETS_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_EMAIL_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_MOBNO_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_UNAME_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_UTYPE_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_EMAIL_NOT_PROP_FMT_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_MOBNO_LENGTH_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_ALREADY_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_UTYPE_INVALID_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_UNAME_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_EMAIL_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_MOBNO_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else if(profile.getErrorCode().equals(ErrorCodes.profile_MAX_LENGTH_EXCEEDED_ERR_CODE)){
						Log.e(TAG,"Error Code : "+profile.getErrorCode());
						Log.e(TAG,"Error Message : "+profile.getErrorDescription());
					}else{
						Log.v(TAG,"User profile Successfullly. Please find the below details.");
						Log.d(TAG,"Text : "+profile.getErrorDescription());
						Log.d(TAG,"ErrCode : "+profile.getErrorCode());
						Log.d(TAG,"ID : "+profile.getUserId());
						Log.d(TAG,"Balance : "+profile.getBalance());
					}
			}else{
				Log.e(TAG,"profile response parsing failed.");
			}*/
		}
		
		Log.i(TAG,"proceedUI() Exiting.");
	}

	//Method to handle Device back button.
	@Override
	public void onBackPressed(){
	   Log.i(TAG,"onBackPressed Entering.");
	   super.onBackPressed();
	   Log.i(TAG,"onBackPressed Exiting.");
	}

}
