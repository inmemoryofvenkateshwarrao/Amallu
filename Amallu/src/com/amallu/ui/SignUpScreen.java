package com.amallu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.amallu.backend.CustomProgressDialog;
import com.amallu.backend.ReqResHandler;
import com.amallu.backend.ResponseHandler;
import com.amallu.exception.AmalluException;
import com.amallu.model.SignUp;
import com.amallu.parser.SignUpParser;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class SignUpScreen extends Activity implements OnClickListener{

	private static final String TAG="SignUpScreen";
	private EditText first_name_edit_txt_view,last_name_edit_txt_view,email_edit_txt_view,pwd_edit_txt_view,
					 re_enter_pwd_edit_txt_view,dob_edit_txt_view;
	private TextView first_name_error_txt_view,last_name_error_txt_view,email_error_txt_view,pwd_error_txt_view,
					 re_enter_pwd_error_txt_view,dob_error_txt_view,page_level_error_txt_view,gender_error_txt_view;
	private RadioButton female_radio_btn,male_radio_btn;
	private Button signup_btn,login_btn;
	
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
		page_level_error_txt_view=(TextView)findViewById(R.id.page_level_error_txt_view);
		first_name_edit_txt_view=(EditText)findViewById(R.id.first_name_edit_txt_view);
		last_name_edit_txt_view=(EditText)findViewById(R.id.last_name_edit_txt_view);
		email_edit_txt_view=(EditText)findViewById(R.id.email_edit_txt_view);
		pwd_edit_txt_view=(EditText)findViewById(R.id.pwd_edit_txt_view);
		re_enter_pwd_edit_txt_view=(EditText)findViewById(R.id.re_enter_pwd_edit_txt_view);
		dob_edit_txt_view=(EditText)findViewById(R.id.dob_edit_txt_view);
		first_name_error_txt_view=(TextView)findViewById(R.id.first_name_error_txt_view);
		last_name_error_txt_view=(TextView)findViewById(R.id.last_name_error_txt_view);
		email_error_txt_view=(TextView)findViewById(R.id.email_error_txt_view);
		pwd_error_txt_view=(TextView)findViewById(R.id.pwd_error_txt_view);
		re_enter_pwd_error_txt_view=(TextView)findViewById(R.id.re_enter_pwd_error_txt_view);
		dob_error_txt_view=(TextView)findViewById(R.id.dob_error_txt_view);
		gender_error_txt_view=(TextView)findViewById(R.id.gender_error_txt_view);
		female_radio_btn=(RadioButton)findViewById(R.id.female_radio_btn);
		male_radio_btn=(RadioButton)findViewById(R.id.male_radio_btn);
		signup_btn=(Button)findViewById(R.id.signup_btn);
		login_btn=(Button)findViewById(R.id.login_btn);
		Log.i(TAG,"intializeViews() Exiting.");
	}

	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		signup_btn.setOnClickListener(this);
		login_btn.setOnClickListener(this);
		Log.i(TAG,"setListeners() Exiting");
	}

	@Override
	public void onClick(View view){
		Log.i(TAG,"onClick() Entering.");
		switch(view.getId()){
		case R.id.signup_btn:
			Log.i(TAG,"Signup button clicked");
			setErrorTxtViewsGone();
			String emailid=email_edit_txt_view.getText().toString();
			String firstname=first_name_edit_txt_view.getText().toString();
			String lastname=last_name_edit_txt_view.getText().toString();
			String password=pwd_edit_txt_view.getText().toString();
			String confPassword=re_enter_pwd_edit_txt_view.getText().toString();
			String dob=dob_edit_txt_view.getText().toString();
			//String dobArr[]=dob.split("/");
			//String day=dobArr[0];
			//String month=dobArr[1];
			//String year=dobArr[2];
			boolean isValidated=validate(emailid,firstname,lastname,password,confPassword,dob);
			if(isValidated){
				Log.v(TAG,"SignUp details validated successfully.");
				String selGender=female_radio_btn.isChecked()?"F":"M";
				sendSignUpReq(emailid,firstname,lastname,password,confPassword,selGender,dob);
			}else{
				Log.v(TAG,"SignUp validation failure.");
			}
			break;
		case R.id.login_btn:
			startActivity(new Intent(SignUpScreen.this,LoginScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
		first_name_error_txt_view.setVisibility(View.GONE);
		last_name_error_txt_view.setVisibility(View.GONE);
		email_error_txt_view.setVisibility(View.GONE);
		pwd_error_txt_view.setVisibility(View.GONE);
		re_enter_pwd_error_txt_view.setVisibility(View.GONE);
		dob_error_txt_view.setVisibility(View.GONE);
		gender_error_txt_view.setVisibility(View.GONE);
		Log.i(TAG,"setErrorTxtViewGone() Exiting.");
	}
	
	//Method to check for Android native validations.
	private boolean validate(String emailid,String firstname,String lastname,String password,
			String confPassword,String dob){
		Log.i(TAG,"validate() Entering.");
		boolean isValidated=true;
		if(emailid==null||emailid.trim().equals("")){
			Log.e(TAG,"Please enter emailid");
			//Attach Error text to View.
			email_error_txt_view.setText(getResources().getString(R.string.emailid_error_msg));
			email_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if((emailid!=null)&&(!emailid.trim().equals(""))&&(!emailid.contains("@"))){
			Log.e(TAG,"Email not in proper format");
			//Attach Error text to View.
			email_error_txt_view.setText(getResources().getString(R.string.emailid_prop_fmt_error_msg));
			email_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(firstname==null||firstname.trim().equals("")){
			Log.e(TAG,"Please enter firstname");
			//Attach Error text to View.
			first_name_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(lastname==null||lastname.trim().equals("")){
			Log.e(TAG,"Please enter lastname");
			//Attach Error text to View.
			last_name_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(password==null||password.trim().equals("")){
			Log.e(TAG,"Please enter password");
			//Attach Error text to View.
			pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(confPassword==null||confPassword.trim().equals("")){
			Log.e(TAG,"Please enter confPassword");
			//Attach Error text to View.
			re_enter_pwd_error_txt_view.setText(getResources().getString(R.string.re_enter_pwd_error_msg));
			re_enter_pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if((password!=null) && (!password.trim().equals("")) && 
				(confPassword!=null) && (!confPassword.trim().equals("")) && 
				(!password.equals(confPassword))){
			Log.e(TAG,"Password and Re enter Password should be same");
			//Attach Error text to View.
			re_enter_pwd_error_txt_view.setText(getResources().getString(R.string.pwd_conf_pwd_error_msg));
			re_enter_pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(!female_radio_btn.isChecked()&&!male_radio_btn.isChecked()){
			Log.e(TAG,"Please select Gender");
			//Attach Error text to View.
			gender_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(dob==null||dob.trim().equals("")){
			Log.e(TAG,"Please select DOB");
			//Attach Error text to View.
			dob_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		Log.i(TAG,"validate() Exiting.");
		return isValidated;
	}
	
	//Method to send SignUp API request.
	private void sendSignUpReq(String emailid,String firstname,String lastname,String password,String confPassword,
	         String gender,String dob){
		Log.i(TAG,"sendSignUpReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(SignUpScreen.this);

		req.signUpRequest(SignUpScreen.this,new ResponseHandler(),emailid,firstname,lastname,password,confPassword,
		         gender,dob);
		
		Log.i(TAG,"sendSignUpReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void signUpProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"proceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "proceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e("proceedUI", "Exception Case");
			page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			page_level_error_txt_view.setVisibility(View.VISIBLE);
		}else{
			SignUp signUp=SignUpParser.getSignUpParsedResponse(result);
			if(signUp!=null){
				Log.v(TAG,"signUp!=null.");
				if(signUp.getIsSuccess().equals(ErrorCodes.ISFAILURE)){
				   Log.e(TAG,"isSuccess Value : "+signUp.getIsSuccess());
				   //Log.e(TAG,"Error Message : "+signUp.getMessage());
				   if(signUp.getFirstname()!=null){
					  first_name_error_txt_view.setText(signUp.getFirstname());
					  first_name_error_txt_view.setVisibility(View.VISIBLE);  
				   }
				   if(signUp.getLastname()!=null){
				      last_name_error_txt_view.setText(signUp.getLastname());
				      last_name_error_txt_view.setVisibility(View.VISIBLE);
				   }
				   if(signUp.getUsername()!=null){
					  email_error_txt_view.setText(signUp.getUsername());
					  email_error_txt_view.setVisibility(View.VISIBLE);
					}
				   if(signUp.getPassword()!=null){
					  pwd_error_txt_view.setText(signUp.getPassword());
					  pwd_error_txt_view.setVisibility(View.VISIBLE);
					}
				   if(signUp.getConfPassword()!=null){
					  re_enter_pwd_error_txt_view.setText(signUp.getConfPassword());
					  re_enter_pwd_error_txt_view.setVisibility(View.VISIBLE);
					}
				   if(signUp.getGender()!=null){
					  gender_error_txt_view.setText(signUp.getGender());
					  gender_error_txt_view.setVisibility(View.VISIBLE);
					}
				   if(signUp.getDob()!=null){
					  dob_error_txt_view.setText(signUp.getDob());
					  dob_error_txt_view.setVisibility(View.VISIBLE);
				   }
				}else{
					Log.v(TAG,"User Sign Up Successfully. Please find the below details.");
					Log.d(TAG,"isSuccess : "+signUp.getIsSuccess());
					Log.d(TAG,"userid : "+signUp.getUserid());
					Log.d(TAG,"username : "+signUp.getUsername());
					Log.d(TAG,"message : "+signUp.getMessage());
					startActivity(new Intent(SignUpScreen.this,PlayerScreen.class)
					.putExtra(ReqResNodes.USERID,signUp.getUserid())
					.putExtra(ReqResNodes.USERNAME,signUp.getUsername()));
				}
			}else{
				Log.e(TAG,"SignUp response parsing failed.");
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
