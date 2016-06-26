package com.amallu.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.amallu.backend.CustomProgressDialog;
import com.amallu.backend.ReqResHandler;
import com.amallu.backend.ResponseHandler;
import com.amallu.exception.AmalluException;
import com.amallu.model.ChangePassword;
import com.amallu.model.Profile;
import com.amallu.parser.ChangePasswordParser;
import com.amallu.parser.LoginParser;
import com.amallu.parser.ProfileParser;
import com.amallu.utility.ErrorCodes;

public class ProfileScreen extends SuperActivity implements OnClickListener,OnItemSelectedListener{

	private static final String TAG="ProfileScreen";
	private View profile_overview_lay,profile_privacy_settings_lay,profile_change_pwd_lay;
	private ImageView icon_left_arrow,overview_expand_collapse,icon_profile,privacy_settings_expand_collapse,
						change_pwd_expand_collapse;
	private TextView overview_label,profile_name_txt_view,change_pwd_page_level_error_txt_view,type_cur_pwd_error_txt_view,
						type_new_pwd_error_txt_view,confirm_new_pwd_error_txt_view;
	private EditText first_name_edit_txt_view,last_name_edit_txt_view,chat_name_edit_txt_view,birth_date_edit_txt_view,
	                 gender_edit_txt_view,type_cur_pwd_edit_txt_view,type_new_pwd_edit_txt_view,confirm_new_pwd_edit_txt_view;
	private Button add_a_friend_btn,submit_btn,save_btn;
	private Spinner add_comment_spinner,like_channel_spinner,add_friend_spinner,birth_date_spinner,friends_spinner;
	
	//Method executes whenever object is created.
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate Entering.");
		setContentView(R.layout.profile);
		intializeViews();
		setListeners();
		setSpinnersData();
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
		change_pwd_expand_collapse=(ImageView)profile_change_pwd_lay.findViewById(R.id.change_pwd_expand_collapse);
		change_pwd_page_level_error_txt_view=(TextView)profile_change_pwd_lay.findViewById(R.id.change_pwd_page_level_error_txt_view);
		type_cur_pwd_error_txt_view=(TextView)profile_change_pwd_lay.findViewById(R.id.type_cur_pwd_error_txt_view);
		type_new_pwd_error_txt_view=(TextView)profile_change_pwd_lay.findViewById(R.id.type_new_pwd_error_txt_view);
		confirm_new_pwd_error_txt_view=(TextView)profile_change_pwd_lay.findViewById(R.id.confirm_new_pwd_error_txt_view);
		type_cur_pwd_edit_txt_view=(EditText)profile_change_pwd_lay.findViewById(R.id.type_cur_pwd_edit_txt_view);
		type_new_pwd_edit_txt_view=(EditText)profile_change_pwd_lay.findViewById(R.id.type_new_pwd_edit_txt_view);
		confirm_new_pwd_edit_txt_view=(EditText)profile_change_pwd_lay.findViewById(R.id.confirm_new_pwd_edit_txt_view);
		save_btn=(Button)profile_change_pwd_lay.findViewById(R.id.save_btn);
		Log.i(TAG,"intializeViews() Exiting.");
	}

	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		icon_left_arrow.setOnClickListener(this);
		overview_expand_collapse.setOnClickListener(this);
		privacy_settings_expand_collapse.setOnClickListener(this);
		change_pwd_expand_collapse.setOnClickListener(this);
		add_a_friend_btn.setOnClickListener(this);
		submit_btn.setOnClickListener(this);
		save_btn.setOnClickListener(this);
		Log.i(TAG,"setListeners() Exiting");
	}
	
	//Populates spinner data.
   private void setSpinnersData(){
	 Log.i(TAG,"setSpinnersData() Entering.");
	 
	 List<String> addComment = new ArrayList<String>();
	 addComment.add("Friends");
	 addComment.add("Private");
	 addComment.add("All");
     ArrayAdapter<String> addCommentAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,addComment);
     addCommentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     add_comment_spinner.setAdapter(addCommentAdapter);
     
     List<String> likeChannel = new ArrayList<String>();
     likeChannel.add("Friends");
     likeChannel.add("Private");
     likeChannel.add("All");
     ArrayAdapter<String> likeChannelAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,likeChannel);
     likeChannelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     like_channel_spinner.setAdapter(likeChannelAdapter);
     
     List<String> addFriend = new ArrayList<String>();
     addFriend.add("Friends");
     addFriend.add("Private");
     addFriend.add("All");
     ArrayAdapter<String> addFriendAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,addFriend);
     addFriendAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     add_friend_spinner.setAdapter(addFriendAdapter);
     
     List<String> birthDate = new ArrayList<String>();
     birthDate.add("Friends");
     birthDate.add("Private");
     birthDate.add("All");
     ArrayAdapter<String> birthDateAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,birthDate);
     birthDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     birth_date_spinner.setAdapter(birthDateAdapter);
     
     List<String> friends = new ArrayList<String>();
     friends.add("Friends");
     friends.add("Private");
     friends.add("All");
     ArrayAdapter<String> friendsAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,friends);
     friendsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     friends_spinner.setAdapter(friendsAdapter);
	 
	 Log.i(TAG,"setSpinnersData() Exiting.");
   }
		
   @Override
   public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
      //On selecting a spinner item
      String item = parent.getItemAtPosition(position).toString();
      //Showing selected spinner item
      //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
   }
   public void onNothingSelected(AdapterView<?> arg0){}
	   

	@Override
	public void onClick(View view){
		Log.i(TAG,"onClick() Entering.");
		switch(view.getId()){
		case R.id.icon_left_arrow:
			Log.d(TAG,"Left arrow in header clicked.");
			finish();
			launchPreviousScreen();
			break;
		case R.id.overview_expand_collapse:
			break;
		case R.id.privacy_settings_expand_collapse:
			break;
		case R.id.change_pwd_expand_collapse:
			break;
		case R.id.add_a_friend_btn:
			break;
		case R.id.submit_btn:
			break;
			//first_name_edit_txt_view,last_name_edit_txt_view,chat_name_edit_txt_view,birth_date_edit_txt_view,
            //gender_edit_txt_view,
		case R.id.save_btn:
			Log.v(TAG,"Save button Clicked.");
			setErrorTxtViewsGone();
			String currPWD=type_cur_pwd_edit_txt_view.getText().toString();
		    String newPWD=type_new_pwd_edit_txt_view.getText().toString();
		    String reEnterNewPWD=confirm_new_pwd_edit_txt_view.getText().toString();
			boolean isValidated=validateChangePWD(currPWD,newPWD,reEnterNewPWD);
			if(isValidated){
				Log.v(TAG,"Change Password details validated successfully.");
				sendChangePWDReq(LoginParser.getEmail(),newPWD,reEnterNewPWD);
			}else{
				Log.v(TAG,"Change Password validation failure.");
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
		change_pwd_page_level_error_txt_view.setVisibility(View.GONE);
		type_cur_pwd_error_txt_view.setVisibility(View.GONE);
		type_new_pwd_error_txt_view.setVisibility(View.GONE);
		confirm_new_pwd_error_txt_view.setVisibility(View.GONE);
		Log.i(TAG,"setErrorTxtViewGone() Exiting.");
	}

	//Method to check for Android native validations.
	private boolean validateChangePWD(String currPWD,String newPWD,String reEnterNewPWD){
		Log.i(TAG,"validate() Entering.");
		boolean isValidated=true;
		if(currPWD==null||currPWD.trim().equals("")){
			Log.e(TAG,"Please enter Current Password");
			//Attach Error text to View.
			type_cur_pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(newPWD==null||newPWD.trim().equals("")){
			Log.e(TAG,"Please enter new Password");
			//Attach Error text to View.
			type_new_pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(reEnterNewPWD==null||reEnterNewPWD.trim().equals("")){
			Log.e(TAG,"Please enter Re type new Password");
			//Attach Error text to View.
			confirm_new_pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if((newPWD!=null&& !newPWD.trim().equals(""))&& (reEnterNewPWD!=null&& !reEnterNewPWD.trim().equals(""))
				&& !(newPWD.equals(reEnterNewPWD))){
			confirm_new_pwd_error_txt_view.setText(getResources().getString(R.string.pwd_conf_pwd_error_msg));
			Log.e(TAG,"New Password and Re type new Password should be same");
			//Attach Error text to View.
			confirm_new_pwd_error_txt_view.setVisibility(View.VISIBLE);
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
	
	//Method to send ChangePassword request.
	private void sendChangePWDReq(String email,String newPassword,String confirmNewPassword){
		Log.i(TAG,"sendChangePWDReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(ProfileScreen.this);

		req.changePasswordRequest(ProfileScreen.this,new ResponseHandler(),email,newPassword,confirmNewPassword);
		
		Log.i(TAG,"sendChangePWDReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void changePWDProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"changePWDProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()){
		   Log.v(TAG,"changePWDProceedUI progress dialog dismissing..");
		   CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e("changePWDProceedUI", "Exception Case");
			change_pwd_page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_change_pwd));
			change_pwd_page_level_error_txt_view.setVisibility(View.VISIBLE);
		}else{
			ChangePassword changePWD=ChangePasswordParser.getChangePWDParsedResponse(result);
			if(changePWD!=null){
				Log.v(TAG,"Change Password response parsing success.");
				if(changePWD.getIsSuccess().equals(ErrorCodes.ISFAILURE)){
				   Log.e(TAG,"isSuccess Value : "+changePWD.getIsSuccess());
				   Log.e(TAG,"Error Message : "+changePWD.getMessage());
				   change_pwd_page_level_error_txt_view.setText(changePWD.getMessage());
				   change_pwd_page_level_error_txt_view.setVisibility(View.VISIBLE);
				}else{
				   Log.v(TAG,"User Changed Password Successfully. Please find the below details.");
				   Log.d(TAG,"isSuccess : "+changePWD.getIsSuccess());
				   Log.d(TAG,"message : "+changePWD.getMessage());
				   finish();
				   launchPreviousScreen();
				}
			}else{
				Log.e(TAG,"Change Password response parsing failed.");
				change_pwd_page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				change_pwd_page_level_error_txt_view.setVisibility(View.VISIBLE);
			}
		}
		
		Log.i(TAG,"changePWDProceedUI() Exiting.");
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
