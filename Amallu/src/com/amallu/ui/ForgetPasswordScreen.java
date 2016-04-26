package com.amallu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.amallu.backend.CustomProgressDialog;
import com.amallu.backend.ReqResHandler;
import com.amallu.backend.ResponseHandler;
import com.amallu.exception.AmalluException;
import com.amallu.model.ForgetPassword;
import com.amallu.parser.ForgetPasswordParser;
import com.amallu.utility.ErrorCodes;

public class ForgetPasswordScreen extends Activity implements OnClickListener,OnEditorActionListener{

	private static final String TAG="ForgetPasswordScreen";
	private EditText emailid_edit_txt_view;
	private TextView emailid_error_txt_view,page_level_error_txt_view;
	private Button submit_btn,cancel_btn;
	
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
		cancel_btn = (Button)findViewById(R.id.cancel_btn);
		Log.i(TAG,"intializeViews() Exiting.");
	}

	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		submit_btn.setOnClickListener(this);
		cancel_btn.setOnClickListener(this);
		emailid_edit_txt_view.setOnEditorActionListener(this);
		Log.i(TAG,"setListeners() Exiting");
	}

	@Override
	public void onClick(View view){
		Log.i(TAG,"onClick() Entering.");
		switch(view.getId()){
		case R.id.submit_btn:
			Log.i(TAG,"Submit button clicked");
			handleSubmitAndDoneBtn();
			break;
		case R.id.cancel_btn:
			finish();
		break;
		default:
			Log.e(TAG,"In Default option");
			break;
		}
		Log.i(TAG,"onClick() Exiting");
	}
	
	//Handles Keyboard Done and Enter button and initiates ForgotPassword API Call.
	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
		if((event!=null&&(event.getKeyCode()==KeyEvent.KEYCODE_ENTER))||(actionId==EditorInfo.IME_ACTION_DONE)){
		   Log.v(TAG, "Enter or Done button pressed");
		   handleSubmitAndDoneBtn();
		}
		return false;
	}
	
	//Handles Submit,Keyboard Done and Enter button.
	private void handleSubmitAndDoneBtn(){
	   Log.i(TAG,"handleLoginAndDoneBtn() Entering");
	   setErrorTxtViewsGone();
		String emailid=emailid_edit_txt_view.getText().toString();
		boolean isValidated=validate(emailid);
		if(isValidated){
			Log.v(TAG,"ForgetPassword details validated successfully.");
			sendForgetPasswordReq(emailid);
		}else{
			Log.v(TAG,"forgetPassword validation failure.");
		}
	   Log.i(TAG,"handleLoginAndDoneBtn() Exiting");
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
			emailid_error_txt_view.setText(getResources().getString(R.string.emailid_error_msg));
			emailid_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if((emailid!=null)&&(!emailid.trim().equals(""))&&(!emailid.contains("@"))){
			Log.e(TAG,"Email not in proper format");
			//Attach Error text to View.
			emailid_error_txt_view.setText(getResources().getString(R.string.emailid_prop_fmt_error_msg));
			emailid_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
	    Log.i(TAG,"validate() Exiting.");
	    
		return isValidated;
	}
	
	//Method to Clear Email ID
	private void clearEmailID(){
		Log.i(TAG,"clearEmaiID() Entering.");
		emailid_edit_txt_view.setText("");
		Log.i(TAG,"clearEmaiID() Exiting.");
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
	public void forgetPasswordProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"proceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "proceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.v("proceedUI", "Exception Case");
			page_level_error_txt_view.setText("");
			page_level_error_txt_view.setVisibility(View.VISIBLE);
		}else{
			ForgetPassword forgetPassword=ForgetPasswordParser.getForgetPasswordParsedResponse(result);
			if(forgetPassword!=null){
				Log.v(TAG,"forgetPassword!=null.");
				if(forgetPassword.getIsSuccess().equals(ErrorCodes.ISFAILURE)){
				   Log.e(TAG,"isSuccess Value : "+forgetPassword.getIsSuccess());
				   Log.e(TAG,"Error Message : "+forgetPassword.getMessage());
				   page_level_error_txt_view.setText(forgetPassword.getMessage());
				   page_level_error_txt_view.setVisibility(View.VISIBLE);
				   clearEmailID();
				}else{
					Log.v(TAG,"ForgetPassword Successfully. Please find the below details.");
					Log.d(TAG,"isSuccess : "+forgetPassword.getIsSuccess());
					Log.d(TAG,"password : "+forgetPassword.getPassword());
					Log.d(TAG,"message : "+forgetPassword.getMessage());
					startActivity(new Intent(ForgetPasswordScreen.this,LoginScreen.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
				}
			}else{
				Log.e(TAG,"forgetPassword response parsing failed.");
				page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				page_level_error_txt_view.setVisibility(View.VISIBLE);
			}
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
