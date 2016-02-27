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
import com.amallu.model.SignUp;
import com.amallu.parser.SignUpParser;
import com.amallu.utility.ErrorCodes;

public class SignUpScreen extends Activity implements OnClickListener{

	private static final String TAG="SignUpScreen";
	
	//Method executes whenever object is created.
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate Entering.");
		setContentView(R.layout.signup);
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
			String firstname="";
			String lastname="";
			String password="";
			String confPassword="";
			String gender="";
			String month="";
			String year="";
			String day="";
			boolean isValidated=validate(emailid,firstname,lastname,password,confPassword,gender,month,year,day);
			if(isValidated){
				Log.v(TAG,"SignUp details validated successfully.");
				sendSignUpReq(emailid,firstname,lastname,password,confPassword,gender,month,year,day);
			}else{
				Log.v(TAG,"SignUp validation failure.");
			}
			break;*/
		default:
			Log.e(TAG,"In Default option");
			break;
		}
		Log.i(TAG,"onClick() Exiting");
	}
	
	//Method to check for Android native validations.
	private boolean validate(String emailid,String firstname,String lastname,String password,String confPassword,
			         String gender,String month,String year,String day){
		Log.i(TAG,"validate() Entering.");
		
		boolean isValidated=true;
		if(emailid==null||emailid.trim().equals("")){
			Log.e(TAG,"Please enter emailid");
			//Attach Error text to View.
			isValidated=false;
		}
		if(firstname==null||firstname.trim().equals("")){
			Log.e(TAG,"Please enter firstname");
			//Attach Error text to View.
			isValidated=false;
		}
		if(lastname==null||lastname.trim().equals("")){
			Log.e(TAG,"Please enter lastname");
			//Attach Error text to View.
			isValidated=false;
		}
		if(password==null||password.trim().equals("")){
			Log.e(TAG,"Please send valid password");
			//Attach Error text to View.
			isValidated=false;
		}
		if(confPassword==null||confPassword.trim().equals("")){
			Log.e(TAG,"Please send valid confPassword");
			//Attach Error text to View.
			isValidated=false;
		}
		if(gender==null||gender.trim().equals("")){
			Log.e(TAG,"Please send valid gender");
			//Attach Error text to View.
			isValidated=false;
		}
		if(month==null||month.trim().equals("")){
			Log.e(TAG,"Please send valid month");
			//Attach Error text to View.
			isValidated=false;
		}
		if(year==null||year.trim().equals("")){
			Log.e(TAG,"Please send valid year");
			//Attach Error text to View.
			isValidated=false;
		}
		if(day==null||day.trim().equals("")){
			Log.e(TAG,"Please send valid day");
			//Attach Error text to View.
			isValidated=false;
		}
		Log.i(TAG,"validate() Exiting.");
		return isValidated;
	}
	
	//Method to send SignUp API request.
	private void sendSignUpReq(String emailid,String firstname,String lastname,String password,String confPassword,
	         String gender,String month,String year,String day){
		Log.i(TAG,"sendSignUpReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(SignUpScreen.this);

		req.signUpRequest(SignUpScreen.this,new ResponseHandler(),emailid,firstname,lastname,password,confPassword,
		         gender,month,year,day);
		
		Log.i(TAG,"sendSignUpReq() Exiting.");
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
			SignUp signUp=SignUpParser.getSignUpParsedResponse(result);
			/*if(signUp!=null){
				Log.v(TAG,"SignUp response parsing success.");
				if(signUp.getErrorCode().equals(ErrorCodes.SQL_EXCEPTION_ERR_CODE)){
					   Log.e(TAG,"Error Code : "+signUp.getErrorCode());
					   Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.INVALID_SESSION_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.SESSION_EXPIRED_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.COMMUNICATION_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}/*else if(signUp.getErrorCode().equals(ErrorCodes.signUp_DETS_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_EMAIL_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_MOBNO_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_UNAME_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_UTYPE_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_EMAIL_NOT_PROP_FMT_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_MOBNO_LENGTH_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_ALREADY_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_UTYPE_INVALID_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_UNAME_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_EMAIL_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_MOBNO_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else if(signUp.getErrorCode().equals(ErrorCodes.signUp_MAX_LENGTH_EXCEEDED_ERR_CODE)){
						Log.e(TAG,"Error Code : "+signUp.getErrorCode());
						Log.e(TAG,"Error Message : "+signUp.getErrorDescription());
					}else{
						Log.v(TAG,"User SignedUP Successfullly. Please find the below details.");
						Log.d(TAG,"Text : "+signUp.getErrorDescription());
						Log.d(TAG,"ErrCode : "+signUp.getErrorCode());
						Log.d(TAG,"ID : "+signUp.getUserId());
						Log.d(TAG,"Balance : "+signUp.getBalance());
					}
			}else{
				Log.e(TAG,"SignUp response parsing failed.");
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
