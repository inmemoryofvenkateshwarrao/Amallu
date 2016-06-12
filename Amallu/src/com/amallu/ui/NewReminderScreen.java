package com.amallu.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class NewReminderScreen extends SuperActivity implements OnClickListener,OnItemSelectedListener,OnEditorActionListener{
	
	private static final String TAG="NewReminderScreen";
	private ImageView icon_left_arrow;
	private EditText reminder_name_txt_view,share_with_txt_view;
	private Spinner select_channel_spinner,select_timezone_spinner,reminder_type_spinner,reminder_date_time_spinner;
	private TextView reminder_name_error_txt_view,select_channel_error_txt_view,select_timezone_error_txt_view,
						reminder_type_error_txt_view,reminder_date_time_error_txt_view,share_with_error_txt_view,
						page_level_error_txt_view;
	private Button submit_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  Log.i(TAG,"onCreate Entering.");
	  setContentView(R.layout.new_reminder);
	  intializeViews();
	  setListeners();
	  setSpinnersData();
	  Log.i(TAG,"onCreate() Exiting.");
	}	 
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
	  Log.i(TAG,"intializeViews() Entering.");
	  icon_left_arrow=(ImageView)findViewById(R.id.icon_left_arrow);
	  reminder_name_txt_view=(EditText)findViewById(R.id.reminder_name_txt_view);
	  select_channel_spinner=(Spinner)findViewById(R.id.select_channel_spinner);
	  select_timezone_spinner=(Spinner)findViewById(R.id.select_timezone_spinner);
	  reminder_type_spinner=(Spinner)findViewById(R.id.reminder_type_spinner);
	  reminder_date_time_spinner=(Spinner)findViewById(R.id.reminder_date_time_spinner);
	  share_with_txt_view=(EditText)findViewById(R.id.share_with_txt_view);
	  submit_btn=(Button)findViewById(R.id.submit_btn);
	  
	  reminder_name_error_txt_view=(TextView)findViewById(R.id.reminder_name_error_txt_view);
	  select_channel_error_txt_view=(TextView)findViewById(R.id.select_channel_error_txt_view);
	  select_timezone_error_txt_view=(TextView)findViewById(R.id.select_timezone_error_txt_view);
	  reminder_type_error_txt_view=(TextView)findViewById(R.id.reminder_type_error_txt_view);
	  reminder_date_time_error_txt_view=(TextView)findViewById(R.id.reminder_date_time_error_txt_view);
	  share_with_error_txt_view=(TextView)findViewById(R.id.share_with_error_txt_view);
	  page_level_error_txt_view=(TextView)findViewById(R.id.page_level_error_txt_view);
	  Log.i(TAG,"intializeViews() Exiting.");
	}
	
	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
	  Log.i(TAG,"setListeners() Entering.");
	  icon_left_arrow.setOnClickListener(this);
	  select_channel_spinner.setOnItemSelectedListener(this);
	  select_timezone_spinner.setOnItemSelectedListener(this);
	  reminder_type_spinner.setOnItemSelectedListener(this);
	  reminder_date_time_spinner.setOnItemSelectedListener(this);
	  reminder_name_txt_view.setOnEditorActionListener(this);
	  submit_btn.setOnClickListener(this);
	  Log.i(TAG,"setListeners() Exiting");
	}
	
	//Populates spinner data.
   private void setSpinnersData(){
	 Log.i(TAG,"setSpinnersData() Entering.");
	 
	 List<String> selectChannel = new ArrayList<String>();
     selectChannel.add("Sky TG24");
     ArrayAdapter<String> selectChannelAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,selectChannel);
     selectChannelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     select_channel_spinner.setAdapter(selectChannelAdapter);
     
     List<String> selectTimezone = new ArrayList<String>();
     selectChannel.add("UTC+5:30");
     ArrayAdapter<String> selectTimezoneAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,selectTimezone);
     selectTimezoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     select_timezone_spinner.setAdapter(selectTimezoneAdapter);
     
     List<String> reminderType = new ArrayList<String>();
     selectChannel.add("Test Type");
     ArrayAdapter<String> reminderTypeAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,reminderType);
     reminderTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     reminder_type_spinner.setAdapter(reminderTypeAdapter);
     
     List<String> reminderDateTime = new ArrayList<String>();
     selectChannel.add("06/07/2016 18:35");
     ArrayAdapter<String> reminderDateTimeAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,reminderDateTime);
     reminderDateTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     reminder_date_time_spinner.setAdapter(reminderDateTimeAdapter);
	 
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
   
   //Handles Keyboard Done and Enter button and initiates SignUp API Call.
   @Override
   public boolean onEditorAction(TextView v,int actionId, KeyEvent event){
 	 if((event!=null&&(event.getKeyCode()==KeyEvent.KEYCODE_ENTER))||(actionId==EditorInfo.IME_ACTION_DONE)){
 	   Log.v(TAG, "Enter or Done button pressed");
 	   handleSubmitBtn();
 	 }
 	  return false;
 	}

	@Override
	public void onClick(View view){
	  switch(view.getId()){
	  	case R.id.icon_left_arrow:
			Log.d(TAG,"Left arrow in header clicked.");
			finish();
			launchPreviousScreen();
			break;
	  	case R.id.submit_btn:
	  		handleSubmitBtn();
	  		break;
		default:
			break;
		}
	}
	
	//Handles Submit,Keyboard Done and Enter button.
	private void handleSubmitBtn(){
	   Log.i(TAG,"handleSubmitBtn() Entering");
	   setErrorTxtViewsGone();
	   String reminderNameText=reminder_name_txt_view.getText().toString();
	   String channelSpinnerText=select_channel_spinner.getSelectedItem().toString();
	   String timezoneSpinnerText=select_timezone_spinner.getSelectedItem().toString();
	   String reminderTypeSpinnerText=reminder_type_spinner.getSelectedItem().toString();
	   String reminderDateTimeSpinnerText=reminder_date_time_spinner.getSelectedItem().toString();
	   String shareWithText=share_with_txt_view.getText().toString();
	   boolean isValidated=validate(reminderNameText,channelSpinnerText,timezoneSpinnerText,reminderTypeSpinnerText,
			   				reminderDateTimeSpinnerText,shareWithText);
	   if(isValidated){
		  Log.v(TAG,"New Reminder details validated successfully.");
		  launchPreviousScreen();
		}else{
		  Log.v(TAG,"New Reminder validation failure.");
		}
	   Log.i(TAG,"handleSubmitBtn() Exiting");
	}
	
	//Method to check for Android native validations.
	private boolean validate(String reminderName,String channelSpinner,String timeZoneSpinner,String reminderTypeSpinner,
			String reminderDateTimeSpinner,String shareWith){
		Log.i(TAG,"validate() Entering.");
		boolean isValidated=true;
		if(reminderName==null||reminderName.trim().equals("")){
			Log.e(TAG,"Please enter Reminder Name");
			//Attach Error text to View.
			reminder_name_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(channelSpinner==null||channelSpinner.trim().equals("")){
			Log.e(TAG,"Please Select Channel Spinner");
			//Attach Error text to View.
			select_channel_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(timeZoneSpinner==null||timeZoneSpinner.trim().equals("")){
			Log.e(TAG,"Please Select Time Zone");
			//Attach Error text to View.
			select_timezone_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(reminderTypeSpinner==null||reminderTypeSpinner.trim().equals("")){
			Log.e(TAG,"Please Select Reminder Type");
			//Attach Error text to View.
			reminder_type_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(reminderDateTimeSpinner==null||reminderDateTimeSpinner.trim().equals("")){
			Log.e(TAG,"Please Select Reminder Date and Time");
			//Attach Error text to View.
			reminder_date_time_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		if(shareWith==null||shareWith.trim().equals("")){
			Log.e(TAG,"Please enter Share With");
			//Attach Error text to View.
			share_with_error_txt_view.setVisibility(View.VISIBLE);
			isValidated=false;
		}
		Log.i(TAG,"validate() Exiting.");
		return isValidated;
	}

	//Method to make Error TextViews Gone.
	private void setErrorTxtViewsGone(){
		Log.i(TAG,"setErrorTxtViewGone() Entering.");
		page_level_error_txt_view.setVisibility(View.GONE);
		reminder_name_error_txt_view.setVisibility(View.GONE);
		select_channel_error_txt_view.setVisibility(View.GONE);
		select_timezone_error_txt_view.setVisibility(View.GONE);
		reminder_type_error_txt_view.setVisibility(View.GONE);
		reminder_date_time_error_txt_view.setVisibility(View.GONE);
		share_with_error_txt_view.setVisibility(View.GONE);
		Log.i(TAG,"setErrorTxtViewGone() Exiting.");
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

