package com.amallu.ui;

import java.util.List;

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
import com.amallu.model.ChangePassword;
import com.amallu.model.ChannelDetail;
import com.amallu.model.ChannelInfo;
import com.amallu.model.Comment;
import com.amallu.parser.ChangePasswordParser;
import com.amallu.parser.ChannelInfoParser;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;

public class ChangePasswordScreen extends SuperActivity implements OnClickListener,OnEditorActionListener{
	
	private static final String TAG="ChangePasswordScreen";
	private EditText change_pwd_edit_txt_view,conf_pwd_edit_txt_view;
	private TextView change_pwd_error_txt_view,conf_pwd_error_txt_view,page_level_error_txt_view;
	private Button submit_btn,cancel_btn;

	@Override
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
	  change_pwd_edit_txt_view=(EditText)findViewById(R.id.change_pwd_edit_txt_view);
	  conf_pwd_edit_txt_view=(EditText)findViewById(R.id.conf_pwd_edit_txt_view);
	  change_pwd_error_txt_view=(TextView)findViewById(R.id.change_pwd_error_txt_view);
	  conf_pwd_error_txt_view=(TextView)findViewById(R.id.conf_pwd_error_txt_view);
	  page_level_error_txt_view=(TextView)findViewById(R.id.page_level_error_txt_view);
	  submit_btn=(Button)findViewById(R.id.submit_btn);
	  cancel_btn=(Button)findViewById(R.id.cancel_btn);
	  Log.i(TAG,"intializeViews() Exiting.");
	}
	
	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		submit_btn.setOnClickListener(this);
		cancel_btn.setOnClickListener(this);
		conf_pwd_edit_txt_view.setOnEditorActionListener(this);
		Log.i(TAG,"setListeners() Exiting");
	}

	@Override
	public void onClick(View view){
	  switch(view.getId()){
	  	case R.id.submit_btn:
			Log.d(TAG,"Change Button clicked.");
			handleSubmitAndDoneBtn();
			break;
	  	case R.id.cancel_btn:
	  		finish();
	  		break;
		default:
			break;
		}
	}
	
	//Handles Keyboard Done and Enter button and initiates Change Password API Call.
	@Override
	public boolean onEditorAction(TextView v,int actionId,KeyEvent event){
		if((event!=null&&(event.getKeyCode()==KeyEvent.KEYCODE_ENTER))||(actionId==EditorInfo.IME_ACTION_DONE)){
		   Log.v(TAG, "Enter or Done button pressed");
		   handleSubmitAndDoneBtn();
		}
		return false;
	}
	
	//Handles Login and Keyboard Done and Enter button.
	private void handleSubmitAndDoneBtn(){
	   Log.i(TAG,"handleSubmitAndDoneBtn() Entering");
	   setErrorTxtViewsGone();
	    String newPWD=change_pwd_edit_txt_view.getText().toString();
	    String reEnterNewPWD=conf_pwd_edit_txt_view.getText().toString();
		boolean isValidated=validate(newPWD,reEnterNewPWD);
		if(isValidated){
			Log.v(TAG,"Change Password details validated successfully.");
			sendChangePWDReq(getIntent().getStringExtra(ReqResNodes.EMAILID),newPWD,reEnterNewPWD);
		}else{
			Log.v(TAG,"Change Password validation failure.");
		}
	   Log.i(TAG,"handleSubmitAndDoneBtn() Exiting");
	}
	
	//Method to make Error TextViews Gone.
	private void setErrorTxtViewsGone(){
		Log.i(TAG,"setErrorTxtViewGone() Entering.");
		change_pwd_error_txt_view.setVisibility(View.GONE);
		conf_pwd_error_txt_view.setVisibility(View.GONE);
		page_level_error_txt_view.setVisibility(View.GONE);
		Log.i(TAG,"setErrorTxtViewGone() Exiting.");
	}
	
	//Method to check for Android native validations.
	private boolean validate(String newPWD,String reEnterNewPWD){
		Log.i(TAG,"validate() Entering.");
		boolean isValidated=true;
		if(newPWD==null||newPWD.trim().equals("")){
			Log.e(TAG,"Please enter new Password");
			//Attach Error text to View.
			change_pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(reEnterNewPWD==null||reEnterNewPWD.trim().equals("")){
			Log.e(TAG,"Please enter Re type new Password");
			//Attach Error text to View.
			conf_pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if((newPWD!=null&& !newPWD.trim().equals(""))&& (reEnterNewPWD!=null&& !reEnterNewPWD.trim().equals(""))
				&& !(newPWD.equals(reEnterNewPWD))){
			conf_pwd_error_txt_view.setText(getResources().getString(R.string.pwd_conf_pwd_error_msg));
			Log.e(TAG,"New Password and Re type new Password should be same");
			//Attach Error text to View.
			conf_pwd_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
	    Log.i(TAG,"validate() Exiting.");
	    
		return isValidated;
	}
	
	//Method to send ChangePassword request.
	private void sendChangePWDReq(String email,String newPassword,String confirmNewPassword){
		Log.i(TAG,"sendChangePWDReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(ChangePasswordScreen.this);

		req.changePasswordRequest(ChangePasswordScreen.this,new ResponseHandler(),email,newPassword,confirmNewPassword);
		
		Log.i(TAG,"sendChangePWDReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void changePWDProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"changePWDProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
		   Log.v(TAG, "changePWDProceedUI progress dialog dismissing..");
		   CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e("changePWDProceedUI", "Exception Case");
			page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_change_pwd));
			page_level_error_txt_view.setVisibility(View.VISIBLE);
		}else{
			ChangePassword changePWD=ChangePasswordParser.getChangePWDParsedResponse(result);
			if(changePWD!=null){
				Log.v(TAG,"Change Password response parsing success.");
				if(changePWD.getIsSuccess().equals(ErrorCodes.ISFAILURE)){
				   Log.e(TAG,"isSuccess Value : "+changePWD.getIsSuccess());
				   Log.e(TAG,"Error Message : "+changePWD.getMessage());
				   page_level_error_txt_view.setText(changePWD.getMessage());
				   page_level_error_txt_view.setVisibility(View.VISIBLE);
				}else{
				   Log.v(TAG,"User Changed Password Successfully. Please find the below details.");
				   Log.d(TAG,"isSuccess : "+changePWD.getIsSuccess());
				   Log.d(TAG,"message : "+changePWD.getMessage());
				   //sendDefaultChannelInfoReq(userid);
				   Intent intent=new Intent(ChangePasswordScreen.this,LoginScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				   launchNextScreen(intent);
				   /*startActivity(new Intent(ChangePasswordScreen.this,LoginScreen.class)
								.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));*/
				}
			}else{
				Log.e(TAG,"Change Password response parsing failed.");
				page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				page_level_error_txt_view.setVisibility(View.VISIBLE);
			}
		}
		
		Log.i(TAG,"changePWDProceedUI() Exiting.");
	}
	
	//Method to send ChannelInfo API request.
	private void sendDefaultChannelInfoReq(String userID){
		Log.i(TAG,"sendDefaultChannelInfoReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(ChangePasswordScreen.this);

		req.defaultChannelInfoRequest(ChangePasswordScreen.this,new ResponseHandler(),userID);
		
		Log.i(TAG,"sendDefaultChannelInfoReq() Exiting.");
	}
	
	//Methods handles the ChannelInfo response from Server.
	public void channelInfoProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"channelInfoProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "channelInfoProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e("proceedUI", "Exception Case");
			page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			page_level_error_txt_view.setVisibility(View.VISIBLE);
		}else{
			ChannelInfo channelInfo=ChannelInfoParser.getChannelInfoParsedResponse(result);
			if(channelInfo!=null){
				Log.v(TAG,"ChannelInfo response parsing success.");
				if(channelInfo.getIsSuccess().equals(ErrorCodes.ISFAILURE)){
				   Log.e(TAG,"isSuccess Value : "+channelInfo.getIsSuccess());
				   Log.e(TAG,"Error Message : "+channelInfo.getMessage());
				   page_level_error_txt_view.setText(channelInfo.getMessage());
				   page_level_error_txt_view.setVisibility(View.VISIBLE);
				}else{
					Log.v(TAG,"ChannelInfo fetched Successfully. Please find the below details.");
					Log.v(TAG,"ChannelDetail Details.");
					ChannelDetail channelDetail=channelInfo.getChannelDetail();
					String channelID=channelDetail.getChannel_id();
					String channelCode=channelDetail.getChannel_code();
					String categoryID=channelDetail.getCategory_id();
					String channelName=channelDetail.getChannel_name();
					String languageID=channelDetail.getLanguage_id();
					String description=channelDetail.getDescription();
					String rtmpLink=channelDetail.getRtmp_link();
					String followers=channelDetail.getFollowers();
					String views=channelDetail.getViews();
					String displayChannel=channelDetail.getDisplay_channel();
					String defaultChannel=channelDetail.getDefault_channel();
					String timeWatched=channelDetail.getTime_watched();
					String thumbnail=channelDetail.getThumbnail();
					
					Log.d(TAG,"channelID : "+channelID);
					Log.d(TAG,"channelCode : "+channelCode);
					Log.d(TAG,"categoryID : "+categoryID);
					Log.d(TAG,"channelName : "+channelName);
					Log.d(TAG,"languageID : "+languageID);
					Log.d(TAG,"description : "+description);
					Log.d(TAG,"rtmpLink : "+rtmpLink);
					Log.d(TAG,"followers : "+followers);
					Log.d(TAG,"views : "+views);
					Log.d(TAG,"displayChannel : "+displayChannel);
					Log.d(TAG,"defaultChannel : "+defaultChannel);
					Log.d(TAG,"timeWatched : "+timeWatched);
					Log.d(TAG,"thumbnail : "+thumbnail);
					
					Log.v(TAG,"Comment Details.");
					List<Comment> commentList=channelInfo.getCommentsList();
					for(int c=0;c<commentList.size();c++){
					   Log.v(TAG,"Iteration : "+c);
					   Comment comment=commentList.get(c);
					   String commentID=comment.getComment_id();
					   String userID=comment.getUserid();
					   String channelID1=comment.getChannel_id();
					   String com=comment.getComment();
					   String prefType=comment.getPreference_type();
					   String hideComment=comment.getHide_comment();
					   String dateCreated=comment.getDt_created();
					   String username=comment.getUsername();
					   Log.d(TAG,"comment_id : "+commentID);
					   Log.d(TAG,"userid : "+userID);
					   Log.d(TAG,"channel_id : "+channelID1);
					   Log.d(TAG,"comment : "+com);
					   Log.d(TAG,"preference_type : "+prefType);
					   Log.d(TAG,"hide_comment : "+hideComment);
					   Log.d(TAG,"dt_created : "+dateCreated);
					   Log.d(TAG,"username : "+username);	
					}
					PlayerScreen.channelInfo=null;
					PlayerScreen.channelInfo=channelInfo;
					PlayerScreen.channelDetail=null;
					PlayerScreen.channelDetail=channelDetail;
					//PlayerScreen.fromContext=LoginScreen.this;
					//startActivity(new Intent(LoginScreen.this,PlayerScreen.class));
				}
			}else{
				Log.e(TAG,"ChannelInfo response parsing failed.");
				page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				page_level_error_txt_view.setVisibility(View.VISIBLE);
			}
		}
		
		Log.i(TAG,"channelInfoProceedUI() Exiting.");
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

