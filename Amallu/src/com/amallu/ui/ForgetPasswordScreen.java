package com.amallu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amallu.backend.CustomProgressDialog;
import com.amallu.backend.ReqResHandler;
import com.amallu.backend.ResponseHandler;
import com.amallu.exception.AmalluException;
import com.amallu.model.ForgetPassword;
import com.amallu.parser.ForgetPasswordParser;
import com.amallu.utility.ErrorCodes;

public class ForgetPasswordScreen extends Activity implements OnClickListener{

	private static final String TAG="ForgetPasswordScreen";
	private EditText emailid_edit_txt_view;
	private TextView emailid_error_txt_view,page_level_error_txt_view;
	private Button submit_btn;
	
	//Method executes whenever object is created.
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate Entering.");
		setContentView(R.layout.forgotpassword);
		intializeViews();
		setListeners();
		Log.i(TAG,"onCreate() Exiting.");
	}
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
		Log.i(TAG,"intializeViews() Entering.");
		emailid_edit_txt_view=(EditText)findViewById(R.id.emailid_edit_txt_view);
		emailid_error_txt_view=(TextView)findViewById(R.id.emailid_error_txt_view);
		page_level_error_txt_view=(TextView)findViewById(R.id.page_level_error_txt_view);
		submit_btn = (Button)findViewById(R.id.submit_btn);
		Log.i(TAG,"intializeViews() Exiting.");
	}

	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		submit_btn.setOnClickListener(this);
		Log.i(TAG,"setListeners() Exiting");
	}

	@Override
	public void onClick(View view){
		Log.i(TAG,"onClick() Entering.");
		switch(view.getId()){
		case R.id.submit_btn:
			Log.i(TAG,"Submit button clicked");
			setErrorTxtViewsGone();
			String emailid=emailid_edit_txt_view.getText().toString();
			boolean isValidated=validate(emailid);
			if(isValidated){
				Log.v(TAG,"ForgetPassword details validated successfully.");
				//sendForgetPasswordReq(emailid);
			}else{
				Log.v(TAG,"forgetPassword validation failure.");
			}
			break;
		default:
			Log.e(TAG,"In Default option");
			break;
		}
		Log.i(TAG,"onClick() Exiting");
	}

	//Method to make Error TextViews Gone.
	private void setErrorTxtViewsGone(){
		Log.i(TAG,"setErrorTxtViewGone() Entering.");
		page_level_error_txt_view.setVisibility(View.GONE);
		emailid_error_txt_view.setVisibility(View.GONE);
		Log.i(TAG,"setErrorTxtViewGone() Exiting.");
	}
	
	//Method to check for Android native validations.
	private boolean validate(String emailid){
		Log.i(TAG,"validate() Entering.");
		emailid_error_txt_view.setVisibility(View.GONE);
		boolean isValidated=true;
		if(emailid==null||emailid.trim().equals("")){
			Log.e(TAG,"Please enter emailid");
			//Attach Error text to View.
			emailid_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
	    Log.i(TAG,"validate() Exiting.");
	    
		return isValidated;
	}
	
	//Method to send ForgetPassword request.
	private void sendForgetPasswordReq(String emailid){
		Log.i(TAG,"sendforgetPasswordReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(ForgetPasswordScreen.this);

		req.forgetPasswordRequest(ForgetPasswordScreen.this,new ResponseHandler(),emailid);
		
		Log.i(TAG,"sendforgetPasswordReq() Exiting.");
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
			ForgetPassword forgetPassword=ForgetPasswordParser.getForgetPasswordParsedResponse(result);
			/*if(forgetPassword!=null){
				Log.v(TAG,"ForgetPassword response parsing success.");
				if(forgetPassword.getErrorCode().equals(ErrorCodes.SQL_EXCEPTION_ERR_CODE)){
					   Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
					   Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.INVALID_SESSION_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.SESSION_EXPIRED_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.COMMUNICATION_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}/*else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_DETS_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_EMAIL_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_MOBNO_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_UNAME_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_UTYPE_NOT_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_EMAIL_NOT_PROP_FMT_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_MOBNO_LENGTH_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_ALREADY_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_UTYPE_INVALID_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_UNAME_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_EMAIL_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_MOBNO_AVAIL_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else if(forgetPassword.getErrorCode().equals(ErrorCodes.forgetPassword_MAX_LENGTH_EXCEEDED_ERR_CODE)){
						Log.e(TAG,"Error Code : "+forgetPassword.getErrorCode());
						Log.e(TAG,"Error Message : "+forgetPassword.getErrorDescription());
					}else{
						Log.v(TAG,"User ForgetPassword Successfullly. Please find the below details.");
						Log.d(TAG,"Text : "+forgetPassword.getErrorDescription());
						Log.d(TAG,"ErrCode : "+forgetPassword.getErrorCode());
						Log.d(TAG,"ID : "+forgetPassword.getUserId());
						Log.d(TAG,"Balance : "+forgetPassword.getBalance());
					}
			}else{
				Log.e(TAG,"forgetPassword response parsing failed.");
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
