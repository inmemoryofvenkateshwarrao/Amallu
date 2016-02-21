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
import com.amallu.model.Login;
import com.amallu.parser.LoginParser;
import com.amallu.utility.ErrorCodes;

public class LoginScreen extends Activity implements OnClickListener{

	private static final String TAG="LoginScreen";
	
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
			String emailid="";
		    String password="";
			boolean isValidated=validate(email,password);
			if(isValidated){
				Log.v(TAG,"Login details validated successfully.");
				sendLoginReq(email,password);
			}else{
				Log.v(TAG,"Login validation failure.");
			}
			break;*/
		default:
			Log.e(TAG,"In Default option");
			break;
		}
		Log.i(TAG,"onClick() Exiting");
	}

	
	//Method to check for Android native validations.
	private boolean validate(String email,String password){
		Log.i(TAG,"validate() Entering.");
		
		boolean isValidated=true;
		if(email==null||email.trim().equals("")){
			Log.e(TAG,"Please enter email");
			//Attach Error text to View.
			isValidated=false;
		}
		if(password==null||password.trim().equals("")){
			Log.e(TAG,"Please enter password");
			//Attach Error text to View.
			isValidated=false;
		}
	    Log.i(TAG,"validate() Exiting.");
	    
		return isValidated;
	}
	
	//Method to send Login request.
	private void sendLoginReq(String email,String password){
		Log.i(TAG,"sendLoginReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(LoginScreen.this);

		req.loginRequest(LoginScreen.this,new ResponseHandler(),email,password);
		
		Log.i(TAG,"sendLoginReq() Exiting.");
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
			if(amalluEx.getErrorCode().equals(ErrorCodes.FAILED_RESPONSE)){
				//errorText.setText(R.string.failedResponse);
				//errorText.setVisibility(View.VISIBLE);
			}else if(amalluEx.getErrorCode().equals(ErrorCodes.OUT_OF_MEMORY_EXCEPTION)){
				//errorText.setText(R.string.outOfMemoryException);
				//errorText.setVisibility(View.VISIBLE);
			}else if(amalluEx.getErrorCode().equals(ErrorCodes.IO_EXCEPTION)){
				//errorText.setText(R.string.ioException);
				//errorText.setVisibility(View.VISIBLE);
			}else if(amalluEx.getErrorCode().equals(ErrorCodes.CONNECTION_EXCEPTION)){
				//errorText.setText(R.string.connectionException);
				//errorText.setVisibility(View.VISIBLE);
			}else if(amalluEx.getErrorCode().equals(ErrorCodes.SECURITY_EXCEPTION)){
				//errorText.setText(R.string.securityException);
				//errorText.setVisibility(View.VISIBLE);
			}else{
				//errorText.setText(R.string.exception);
				//errorText.setVisibility(View.VISIBLE);
			}
		}else{
			Login login=LoginParser.getLoginParsedResponse(result);
			/*if(login!=null){
				Log.v(TAG,"login response parsing success.");
				if(login.getErrorCode().equals(ErrorCodes.SQL_EXCEPTION_ERR_CODE)){
					   Log.e(TAG,"Error Code : "+login.getErrorCode());
					   Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.INVALID_SESSION_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.SESSION_EXPIRED_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.COMMUNICATION_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}/*else if(login.getErrorCode().equals(ErrorCodes.login_DETS_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_EMAIL_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_MOBNO_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_UNAME_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_UTYPE_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_EMAIL_NOT_PROP_FMT_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_MOBNO_LENGTH_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_ALREADY_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_UTYPE_INVALID_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_UNAME_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_EMAIL_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_MOBNO_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else if(login.getErrorCode().equals(ErrorCodes.login_MAX_LENGTH_EXCEEDED_ERR_CODE)){
						Log.e(TAG,"Error Code : "+login.getErrorCode());
						Log.e(TAG,"Error Message : "+login.getErrorDescription());
					}else{
						Log.v(TAG,"User login Successfullly. Please find the below details.");
						Log.d(TAG,"Text : "+login.getErrorDescription());
						Log.d(TAG,"ErrCode : "+login.getErrorCode());
						Log.d(TAG,"ID : "+login.getUserId());
						Log.d(TAG,"Balance : "+login.getBalance());
					}
			}else{
				Log.e(TAG,"login response parsing failed.");
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
