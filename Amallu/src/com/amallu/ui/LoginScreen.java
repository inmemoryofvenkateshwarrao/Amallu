package com.amallu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amallu.backend.CustomProgressDialog;
import com.amallu.backend.ReqResHandler;
import com.amallu.backend.ResponseHandler;
import com.amallu.exception.AmalluException;
import com.amallu.model.Login;
import com.amallu.parser.LoginParser;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class LoginScreen extends Activity implements OnClickListener{

	private static final String TAG="LoginScreen";
	private EditText user_name_edit_txt_view,password_edit_txt_view;
	private TextView page_level_error_txt_view,user_name_error_txt_view,password_error_txt_view,forgot_your_pwd_txt_view;
	private Button login_btn,signup_btn;
	private ImageView facebook_icon,twitter_icon,gmail_icon;
	private Toast toast;
	private TextView toastText;
	
	//Method executes whenever object is created.
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate Entering.");
		setContentView(R.layout.login);
		intializeViews();
		setListeners();
		Log.i(TAG,"onCreate() Exiting.");
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		Log.i(TAG,"onPause() Entering.");
		clearUserCredentials();
		Log.i(TAG,"onPause() Exiting.");
	}
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
		Log.i(TAG,"intializeViews() Entering.");
		user_name_edit_txt_view=(EditText)findViewById(R.id.user_name_edit_txt_view);
		password_edit_txt_view=(EditText)findViewById(R.id.password_edit_txt_view);
		page_level_error_txt_view=(TextView)findViewById(R.id.page_level_error_txt_view);
		user_name_error_txt_view=(TextView)findViewById(R.id.user_name_error_txt_view);
		password_error_txt_view=(TextView)findViewById(R.id.password_error_txt_view);
		forgot_your_pwd_txt_view=(TextView)findViewById(R.id.forgot_your_pwd_txt_view);
		login_btn=(Button)findViewById(R.id.login_btn);
		signup_btn=(Button)findViewById(R.id.signup_btn);
		facebook_icon=(ImageView)findViewById(R.id.facebook_icon);
		twitter_icon=(ImageView)findViewById(R.id.twitter_icon);
		gmail_icon=(ImageView)findViewById(R.id.gmail_icon);
		Log.i(TAG,"intializeViews() Exiting.");
	}

	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		login_btn.setOnClickListener(this);
		signup_btn.setOnClickListener(this);
		facebook_icon.setOnClickListener(this);
		twitter_icon.setOnClickListener(this);
		gmail_icon.setOnClickListener(this);
		forgot_your_pwd_txt_view.setOnClickListener(this);
		Log.i(TAG,"setListeners() Exiting");
	}

	@Override
	public void onClick(View view){
		Log.i(TAG,"onClick() Entering.");
		switch(view.getId()){
			case R.id.login_btn:
				Log.i(TAG,"Login button clicked");
				setErrorTxtViewsGone();
				String username=user_name_edit_txt_view.getText().toString();
			    String password=password_edit_txt_view.getText().toString();
				boolean isValidated=validate(username,password);
				if(isValidated){
					Log.v(TAG,"Login details validated successfully.");
					sendLoginReq(username,password);
				}else{
					Log.v(TAG,"Login validation failure.");
				}
				break;
			case R.id.signup_btn:
				Log.i(TAG,"Signup button clicked");
				startActivity(new Intent(LoginScreen.this,SignUpScreen.class));
				break;
			case R.id.forgot_your_pwd_txt_view:
				Log.i(TAG,"Forgot Password Link clicked");
				startActivity(new Intent(LoginScreen.this,ForgetPasswordScreen.class));
				break;
			case R.id.facebook_icon:
				Log.i(TAG,"Facebook Icon clicked");
				checkForToast();
				//startActivity(new Intent(LoginScreen.this,ForgetPasswordScreen.class));
				break;
			case R.id.twitter_icon:
				Log.i(TAG,"Twitter Icon clicked");
				checkForToast();
				//startActivity(new Intent(LoginScreen.this,ForgetPasswordScreen.class));
				break;
			case R.id.gmail_icon:
				Log.i(TAG,"Gmail Icon clicked");
				checkForToast();
				//startActivity(new Intent(LoginScreen.this,ForgetPasswordScreen.class));
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
		user_name_error_txt_view.setVisibility(View.GONE);
		password_error_txt_view.setVisibility(View.GONE);
		Log.i(TAG,"setErrorTxtViewGone() Exiting.");
	}

	
	//Method to check for Android native validations.
	private boolean validate(String username,String password){
		Log.i(TAG,"validate() Entering.");
		boolean isValidated=true;
		if(username==null||username.trim().equals("")){
			Log.e(TAG,"Please enter Username");
			//Attach Error text to View.
			user_name_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(password==null||password.trim().equals("")){
			Log.e(TAG,"Please enter Password");
			//Attach Error text to View.
			password_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
	    Log.i(TAG,"validate() Exiting.");
	    
		return isValidated;
	}
	
	//Method to clear User Credentials
	private void clearUserCredentials(){
		Log.i(TAG,"clearUserCredentials() Entering.");
		user_name_edit_txt_view.setText("");
		password_edit_txt_view.setText("");
		user_name_edit_txt_view.requestFocus();
		Log.i(TAG,"clearUserCredentials() Exiting");
	}
	
	//Method to display Toast message for unimplemented features for 2 Seconds.
 	protected void displayToast() {	
 		Log.d(TAG,"In displayToast Method");
 		 LayoutInflater inflater = getLayoutInflater();
         View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout));
         toastText = (TextView)layout.findViewById(R.id.toast_text_1);
         toastText.setText("Coming Soon !");
         toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL,0,0);
         toast.setDuration(Toast.LENGTH_SHORT);
         toast.setView(layout);
         toast.show();
 	}
 	
 	// Method to Cancel Toast it is showing. This will be used when we navigate to other screens.
 	protected void cancelToast(){
 		Log.d(TAG,"cancelToast");
 		Log.d(TAG,"Toast Object is : "+toast);
 		if(toast!=null && toast.getView().isShown()){
 			Log.d(TAG,"Toast not equal to null : Cancelling Toast");
 			toast.cancel();
 			Log.e(TAG,"toast object after cancelling"+toast);
 		}else{
 			Log.d(TAG,"Toast is equal to null");
 		}
 	}
 	
 	// Checks for Existence of Toast. If exists do nothing, if not exists displays Toast.
 	protected void checkForToast(){
 		Log.d(TAG,"checkForToast");
 		if(toast!=null){
 			Log.e(TAG,"Already Toast object is there Dont create Toast object");
 			if(toast.getView().isShown()){
 				Log.d(TAG,"Toast is showing using isShown method : "+true);
 			}else{
 				Log.d(TAG,"Show Toast : isShown :"+false);
 				displayToast();
 			}
 		}else{
 			Log.e(TAG,"Please create Toast object");
 			toast = new Toast(getApplicationContext());
 			displayToast();
 		}
 		
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
	public void loginProceedUI(String result,AmalluException amalluEx){
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
			Login login=LoginParser.getLoginParsedResponse(result);
			if(login!=null){
				Log.v(TAG,"login response parsing success.");
				if(login.getIsSuccess().equals(ErrorCodes.ISFAILURE)){
				   Log.e(TAG,"isSuccess Value : "+login.getIsSuccess());
				   Log.e(TAG,"Error Message : "+login.getMessage());
				   page_level_error_txt_view.setText(login.getMessage());
				   page_level_error_txt_view.setVisibility(View.VISIBLE);
				   clearUserCredentials();
				}else{
					Log.v(TAG,"User login Successfully. Please find the below details.");
					Log.d(TAG,"isSuccess : "+login.getIsSuccess());
					Log.d(TAG,"userid : "+login.getUserid());
					Log.d(TAG,"username : "+login.getUsername());
					Log.d(TAG,"message : "+login.getMessage());
					startActivity(new Intent(LoginScreen.this,PlayerScreen.class)
					.putExtra(ReqResNodes.USERID,login.getUserid())
					.putExtra(ReqResNodes.USERNAME,login.getUsername()));
				}
			}else{
				Log.e(TAG,"login response parsing failed.");
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
