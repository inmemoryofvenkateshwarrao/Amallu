package com.amallu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.amallu.backend.CustomProgressDialog;
import com.amallu.backend.ReqResHandler;
import com.amallu.backend.ResponseHandler;
import com.amallu.exception.AmalluException;
import com.amallu.model.Profile;
import com.amallu.parser.ProfileParser;

public class ProfileScreen extends Activity implements OnClickListener{

	private static final String TAG="ProfileScreen";
	private View profile_overview_lay,profile_privacy_settings_lay,profile_change_pwd_lay;
	private ImageView icon_left_arrow,overview_expand_collapse,icon_profile,privacy_settings_expand_collapse;
	private TextView overview_label,profile_name_txt_view;
	private EditText first_name_edit_txt_view,last_name_edit_txt_view,chat_name_edit_txt_view,birth_date_edit_txt_view,
	                 gender_edit_txt_view;
	private Button add_a_friend_btn,submit_btn;
	private Spinner add_comment_spinner,like_channel_spinner,add_friend_spinner,birth_date_spinner,friends_spinner;
	
	//Method executes whenever object is created.
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate Entering.");
		setContentView(R.layout.profile);
		intializeViews();
		setListeners();
		Log.i(TAG,"onCreate() Exiting.");
	}
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
		Log.i(TAG,"intializeViews() Entering.");
		icon_left_arrow=(ImageView)findViewById(R.id.icon_left_arrow);
		profile_overview_lay=(View)findViewById(R.id.profile_overview_lay);
		overview_expand_collapse=(ImageView)profile_overview_lay.findViewById(R.id.overview_expand_collapse);
		icon_profile=(ImageView)profile_overview_lay.findViewById(R.id.icon_profile);
		overview_label=(TextView)profile_overview_lay.findViewById(R.id.overview_label);
		profile_name_txt_view=(TextView)profile_overview_lay.findViewById(R.id.profile_name_txt_view);
		first_name_edit_txt_view=(EditText)profile_overview_lay.findViewById(R.id.first_name_edit_txt_view);
		last_name_edit_txt_view=(EditText)profile_overview_lay.findViewById(R.id.last_name_edit_txt_view);
		chat_name_edit_txt_view=(EditText)profile_overview_lay.findViewById(R.id.chat_name_edit_txt_view);
		birth_date_edit_txt_view=(EditText)profile_overview_lay.findViewById(R.id.birth_date_edit_txt_view);
		gender_edit_txt_view=(EditText)profile_overview_lay.findViewById(R.id.gender_edit_txt_view);
		add_a_friend_btn=(Button)profile_overview_lay.findViewById(R.id.add_a_friend_btn);
		
		profile_privacy_settings_lay=(View)findViewById(R.id.profile_privacy_settings_lay);
		privacy_settings_expand_collapse=(ImageView)profile_privacy_settings_lay.findViewById(R.id.privacy_settings_expand_collapse);
		add_comment_spinner=(Spinner)profile_privacy_settings_lay.findViewById(R.id.add_comment_spinner);
		like_channel_spinner=(Spinner)profile_privacy_settings_lay.findViewById(R.id.like_channel_spinner);
		add_friend_spinner=(Spinner)profile_privacy_settings_lay.findViewById(R.id.add_friend_spinner);
		birth_date_spinner=(Spinner)profile_privacy_settings_lay.findViewById(R.id.birth_date_spinner);
		friends_spinner=(Spinner)profile_privacy_settings_lay.findViewById(R.id.friends_spinner);
		submit_btn=(Button)profile_privacy_settings_lay.findViewById(R.id.submit_btn);
		
		
		profile_change_pwd_lay=(View)findViewById(R.id.profile_change_pwd_lay);
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
