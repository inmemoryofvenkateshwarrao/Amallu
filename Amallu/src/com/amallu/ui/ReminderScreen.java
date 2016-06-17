package com.amallu.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.amallu.ui.ReminderScreen.ReminderAdapter.ReminderRowHolder;
import com.amallu.utility.ReqResNodes;

public class ReminderScreen extends SuperActivity implements OnClickListener{
	
	private static final String TAG="ReminderScreen";
	public static List<HashMap<String,Object>> remindersList=new ArrayList<HashMap<String,Object>>();
	private ReminderRowHolder reminderRowHolder;
	private LayoutInflater mInflater;
	private ListView reminder_list;
	private ImageView icon_left_arrow,icon_plus_sign;
	private ReminderAdapter reminderListAdapter;
	private TextView reminders_unavail_error_txt_view;

	@Override
	protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  Log.i(TAG,"onCreate Entering.");
	  setContentView(R.layout.reminder);
	  intializeViews();
	  setListeners();
	  //setData();
	  Log.i(TAG,"onCreate() Exiting.");
	}	 
	
	@Override
	protected void onResume(){
		Log.i(TAG,"onResume() Entering.");
		setData();
		reminderListAdapter.notifyDataSetChanged();
		super.onResume();
		Log.i(TAG,"onResume() Exiting.");
	}
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
	  Log.i(TAG,"intializeViews() Entering.");
	  reminder_list=(ListView)findViewById(R.id.reminder_list);
	  reminders_unavail_error_txt_view=(TextView)findViewById(R.id.reminders_unavail_error_txt_view);
	  icon_left_arrow=(ImageView)findViewById(R.id.icon_left_arrow);
	  icon_plus_sign=(ImageView)findViewById(R.id.icon_plus_sign);
	  Log.i(TAG,"intializeViews() Exiting.");
	}
	
	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
	  Log.i(TAG,"setListeners() Entering.");
	  icon_left_arrow.setOnClickListener(this);
	  icon_plus_sign.setOnClickListener(this);
	  Log.i(TAG,"setListeners() Exiting");
	}
	
	@Override
	public void onClick(View view){
	  switch(view.getId()){
	  	case R.id.icon_left_arrow:
			Log.d(TAG,"Left arrow in header clicked.");
			finish();
			launchPreviousScreen();
			break;
	  	case R.id.icon_plus_sign:
			Log.d(TAG,"Plus Sign in header clicked.");
			Intent newReminderIntent=new Intent(ReminderScreen.this,NewReminderScreen.class);
			launchNextScreen(newReminderIntent);
			break;
		default:
			break;
		}
	}
	
	//Populates ListView Data.
	private void setData(){
	  Log.i(TAG,"setData() Entering.");
	  if(remindersList.size()>0){
		reminders_unavail_error_txt_view.setVisibility(View.GONE); 
	  }else{
		reminders_unavail_error_txt_view.setVisibility(View.VISIBLE);
	  }
	  reminderListAdapter=new ReminderAdapter(this,remindersList,R.layout.common_row,new String[]{},new int[]{});
	  reminder_list.setAdapter(reminderListAdapter);
	  reminder_list.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int position,long id){
		    //HashMap (HashMap<String,Object>)view.getTag();
		 } 
	   });
	  Log.i(TAG,"setData() Exiting.");
	}

	//Inner Class to make smooth swiping of list view.
	public class ReminderAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> reminderRowArrList;

	   public ReminderAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context,items,resource,from,to);
		   reminderRowArrList=(ArrayList<HashMap<String,Object>>)items;
		   mInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView = mInflater.inflate(R.layout.common_row,null);
        	  reminderRowHolder=new ReminderRowHolder();
        	  reminderRowHolder.col1=(ImageView)convertView.findViewById(R.id.col1);
        	  reminderRowHolder.col2_row1=(TextView)convertView.findViewById(R.id.col2_row1);
        	  reminderRowHolder.col2_row2=(TextView)convertView.findViewById(R.id.col2_row2);
        	  reminderRowHolder.col2_row3=(TextView)convertView.findViewById(R.id.col2_row3);
        	  reminderRowHolder.col2_row4=(TextView)convertView.findViewById(R.id.col2_row4);
        	  convertView.setTag(reminderRowHolder);
            }else{
            	reminderRowHolder=(ReminderRowHolder)convertView.getTag();
            }
            HashMap<String,Object> reminderRowHM=(HashMap<String,Object>)reminderRowArrList.get(position);
             
            reminderRowHolder.col1.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_channel));
            reminderRowHolder.col2_row1.setText(reminderRowHM.get(ReqResNodes.REMINDER_NAME).toString());
            reminderRowHolder.col2_row2.setText(reminderRowHM.get(ReqResNodes.REMINDER_SHARE_WITH).toString());
            reminderRowHolder.col2_row3.setText(reminderRowHM.get(ReqResNodes.REMINDER_CHANNEL_NAME).toString());
            String reminder_date_time_zone=reminderRowHM.get(ReqResNodes.REMINDER_DATE_TIME_ZONE).toString()+
            		                       " "+reminderRowHM.get(ReqResNodes.REMINDER_TIME_ZONE).toString();
            reminderRowHolder.col2_row4.setText(reminder_date_time_zone);
            
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class ReminderRowHolder{
        	ImageView col1;
    	    TextView col2_row1,col2_row2,col2_row3,col2_row4;
        }
	        
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
