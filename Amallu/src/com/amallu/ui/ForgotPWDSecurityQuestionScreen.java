package com.amallu.ui;

import com.amallu.utility.ReqResNodes;

import android.app.Activity;
import android.app.LauncherActivity;
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

public class ForgotPWDSecurityQuestionScreen extends SuperActivity implements OnClickListener,OnEditorActionListener{

	private static final String TAG="ForgotPWDSecurityQuestionScreen";
	private EditText answer_edit_txt_view;
	private TextView answer_error_txt_view;
	private Button next_btn;
	
	//Method executes whenever object is created.
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate Entering.");
		setContentView(R.layout.forgot_pwd_security_question);
		intializeViews();
		setListeners();
		Log.i(TAG,"onCreate() Exiting.");
	}
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
		Log.i(TAG,"intializeViews() Entering.");
		answer_edit_txt_view=(EditText)findViewById(R.id.answer_edit_txt_view);
		answer_error_txt_view=(TextView)findViewById(R.id.answer_error_txt_view);
		next_btn=(Button)findViewById(R.id.next_btn);
		Log.i(TAG,"intializeViews() Exiting.");
	}

	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		next_btn.setOnClickListener(this);
		answer_edit_txt_view.setOnEditorActionListener(this);
		Log.i(TAG,"setListeners() Exiting");
	}

	@Override
	public void onClick(View view){
		Log.i(TAG,"onClick() Entering.");
		switch(view.getId()){
		case R.id.next_btn:
			Log.i(TAG,"Next button clicked");
			handleNextAndDoneBtn();
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
		   handleNextAndDoneBtn();
		}
		return false;
	}
	
	//Handles Next,Keyboard Done and Enter button.
	private void handleNextAndDoneBtn(){
	   Log.i(TAG,"handleNextAndDoneBtn() Entering");
		String answer=answer_edit_txt_view.getText().toString();
		boolean isValidated=validate(answer);
		if(isValidated){
			Log.v(TAG,"ForgotPassword Security Question details validated successfully.");
			Intent forgotPWChPWDIntent=new Intent(ForgotPWDSecurityQuestionScreen.this,ChangePasswordScreen.class)
					.putExtra(ReqResNodes.EMAILID,getIntent().getStringExtra(ReqResNodes.EMAILID))
					.putExtra(ReqResNodes.SECURITY_QUESTION,answer_edit_txt_view.getText().toString())
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			launchNextScreen(forgotPWChPWDIntent);
			/*startActivity(new Intent(ForgotPWDSecurityQuestionScreen.this,ChangePasswordScreen.class)
				.putExtra(ReqResNodes.EMAILID,getIntent().getStringExtra(ReqResNodes.EMAILID))
				.putExtra(ReqResNodes.SECURITY_QUESTION,answer_edit_txt_view.getText().toString())
				.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));*/
		}else{
			Log.v(TAG,"ForgotPassword validation failure.");
		}
	   Log.i(TAG,"handleNextAndDoneBtn() Exiting");
	}

	//Method to check for Android native validations.
	private boolean validate(String answer){
		Log.i(TAG,"validate() Entering.");
		
		answer_error_txt_view.setVisibility(View.GONE);
		boolean isValidated=true;
		if(answer==null||answer.trim().equals("")){
			Log.e(TAG,"Please enter Answer");
			//Attach Error text to View.
			answer_error_txt_view.setVisibility(View.VISIBLE);
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
	   launchPreviousScreen();
	   Log.i(TAG,"onBackPressed Exiting.");
	}

}
