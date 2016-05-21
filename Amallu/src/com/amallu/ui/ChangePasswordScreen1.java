package com.amallu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePasswordScreen1 extends Activity/* implements OnClickListener*/{
	
	private static final String TAG="ChangePasswordScreen";
	private EditText curr_pwd_edit_txt_view,new_pwd_edit_txt_view,re_enter_pwd_edit_txt_view;
	private TextView curr_pwd_error_txt_view,new_pwd_error_txt_view,re_type_new_pwd_txt_view,page_level_error_txt_view;
	private Button change_btn;

	/*@Override
	protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  Log.i(TAG,"onCreate Entering.");
	  setContentView(R.layout.changepassword);
	  intializeViews();
	  setListeners();
	  Log.i(TAG,"onCreate() Exiting.");
	}	 
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
	  Log.i(TAG,"intializeViews() Entering.");
	  curr_pwd_edit_txt_view=(EditText)findViewById(R.id.curr_pwd_edit_txt_view);
	  new_pwd_edit_txt_view=(EditText)findViewById(R.id.new_pwd_edit_txt_view);
	  re_enter_pwd_edit_txt_view=(EditText)findViewById(R.id.re_enter_pwd_edit_txt_view);
	  curr_pwd_error_txt_view=(TextView)findViewById(R.id.curr_pwd_error_txt_view);
	  new_pwd_error_txt_view=(TextView)findViewById(R.id.new_pwd_error_txt_view);
	  re_type_new_pwd_txt_view=(TextView)findViewById(R.id.re_type_new_pwd_txt_view);
	  page_level_error_txt_view=(TextView)findViewById(R.id.page_level_error_txt_view);
	  change_btn=(Button)findViewById(R.id.change_btn);
	  Log.i(TAG,"intializeViews() Exiting.");
	}
	
	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		change_btn.setOnClickListener(this);
		Log.i(TAG,"setListeners() Exiting");
	}

	@Override
	public void onClick(View view){
	  switch(view.getId()){
	  	case R.id.change_btn:
			Log.d(TAG,"Change Button clicked.");
			setErrorTxtViewsGone();
			String currPWD=curr_pwd_edit_txt_view.getText().toString();
		    String newPWD=new_pwd_edit_txt_view.getText().toString();
		    String reEnterNewPWD=re_enter_pwd_edit_txt_view.getText().toString();
			boolean isValidated=validate(currPWD,newPWD,reEnterNewPWD);
			if(isValidated){
				Log.v(TAG,"Change Password details validated successfully.");
				//sendChangePWDReq(emailid,currPWD,newPWD,reEnterNewPWD);
			}else{
				Log.v(TAG,"Change Password validation failure.");
			}
			finish();
			break;
		default:
			break;
		}
	}
	
	//Method to make Error TextViews Gone.
	private void setErrorTxtViewsGone(){
		Log.i(TAG,"setErrorTxtViewGone() Entering.");
		page_level_error_txt_view.setVisibility(View.GONE);
		curr_pwd_error_txt_view.setVisibility(View.GONE);
		new_pwd_error_txt_view.setVisibility(View.GONE);
		re_type_new_pwd_txt_view.setVisibility(View.GONE);
		Log.i(TAG,"setErrorTxtViewGone() Exiting.");
	}

	
	//Method to check for Android native validations.
	private boolean validate(String currPWD,String newPWD,String reEnterNewPWD){
		Log.i(TAG,"validate() Entering.");
		boolean isValidated=true;
		if(currPWD==null||currPWD.trim().equals("")){
			Log.e(TAG,"Please enter Current Password");
			//Attach Error text to View.
			curr_pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(newPWD==null||newPWD.trim().equals("")){
			Log.e(TAG,"Please enter new Password");
			//Attach Error text to View.
			new_pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(reEnterNewPWD==null||reEnterNewPWD.trim().equals("")){
			Log.e(TAG,"Please enter Re type new Password");
			//Attach Error text to View.
			re_type_new_pwd_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if((newPWD!=null&& !newPWD.trim().equals(""))&& (reEnterNewPWD!=null&& !reEnterNewPWD.trim().equals(""))
				&& !(newPWD.equals(reEnterNewPWD))){
			re_type_new_pwd_txt_view.setText(getResources().getString(R.string.pwd_conf_pwd_error_msg));
			Log.e(TAG,"New Password and Re type new Password should be same");
			//Attach Error text to View.
			re_type_new_pwd_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
	    Log.i(TAG,"validate() Exiting.");
	    
		return isValidated;
	}
	
	//Method to handle Device back button.
	@Override
	public void onBackPressed(){
	   Log.i(TAG,"onBackPressed Entering.");
	   super.onBackPressed();
	   Log.i(TAG,"onBackPressed Exiting.");
	}*/
	
	//Sends Languages API Request.
	/*private void sendChangePWDReq(){
		Log.i(TAG,"sendChangePWDReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(ChangePasswordScreen.this);

		req.changePasswordRequest(ChangePasswordScreen.this,new ResponseHandler(),emailid,oldPwd,newPwd,confirmNewPwd);
		
		Log.i(TAG,"sendChangePWDReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void changePWDProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"changePWDProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()){
			Log.v(TAG, "changePWDProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "changePWDProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
		}else{
			List<HashMap<String,Object>> languagesHMList=LanguageListParser.getLanguagesListParsedResponse(result);
			if(languagesHMList!=null&& !languagesHMList.isEmpty()){
				Log.v(TAG,"Password Changed Successfully.");
				LanguagesScreen.languagesList.clear();
				LanguagesScreen.languagesList=languagesHMList;
				startActivity(new Intent(PlayerScreen.this,LanguagesScreen.class));
			}else{
				Log.e(TAG,"Unable to Change Password.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
			}
		}
		
		Log.i(TAG,"changePWDProceedUI() Exiting.");
	}*/
	
}
