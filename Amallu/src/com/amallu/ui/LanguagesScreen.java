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
import android.widget.Toast;

import com.amallu.backend.CustomProgressDialog;
import com.amallu.backend.ReqResHandler;
import com.amallu.backend.ResponseHandler;
import com.amallu.exception.AmalluException;
import com.amallu.parser.ChannelsByLanguageParser;
import com.amallu.ui.LanguagesScreen.LanguageAdapter.LanguageRowViewHolder;
import com.amallu.utility.ReqResNodes;

public class LanguagesScreen extends SuperActivity implements OnClickListener{
	
	private static final String TAG="LanguagesScreen";
	public static List<HashMap<String,Object>> languagesList=new ArrayList<HashMap<String,Object>>();
	private LanguageRowViewHolder languageRowHolder;
	private LayoutInflater mInflater;
	private ListView languageList;
	private ImageView icon_left_arrow;

	@Override
	protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  Log.i(TAG,"onCreate Entering.");
	  setContentView(R.layout.languages);
	  intializeViews();
	  setListeners();
	  setData();
	  Log.i(TAG,"onCreate() Exiting.");
	}	 
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
	  Log.i(TAG,"intializeViews() Entering.");
	  languageList=(ListView)findViewById(R.id.language_list);
	  icon_left_arrow=(ImageView)findViewById(R.id.icon_left_arrow);
	  Log.i(TAG,"intializeViews() Exiting.");
	}
	
	//Method to set the Listeners to the Views of XML file.
	private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		icon_left_arrow.setOnClickListener(this);
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
		default:
			break;
		}
	}
	
	//Populates ListView Data.
	private void setData(){
	  Log.i(TAG,"setData() Entering.");
	  LanguageAdapter languageListAdapter=new LanguageAdapter(this,languagesList,R.layout.categoryrow, new String[] {}, new int[] {});
	  languageList.setAdapter(languageListAdapter);
	  languageList.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int position,long id){
			 HashMap<String,Object> languageRowHM=(HashMap<String, Object>)languageList.getItemAtPosition(position);
			 String languageID=languageRowHM.get(ReqResNodes.LANGUAGE_ID).toString();
			 Log.d(TAG,"Language ID : "+languageID);
			 sendChannelsByLanguageReq(languageID);  
		 } 
	   });
	  Log.i(TAG,"setData() Exiting.");
	}

	//Inner Class to make smooth swiping of list view.
	public class LanguageAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> languageRowArrList;

	   public LanguageAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context, items, resource, from, to);
		   languageRowArrList=(ArrayList<HashMap<String,Object>>)items;
		   mInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView = mInflater.inflate(R.layout.languagerow,null);
        	  languageRowHolder=new LanguageRowViewHolder();
        	  languageRowHolder.language_type_icon=(ImageView)convertView.findViewById(R.id.language_type_icon);
        	  languageRowHolder.language_name=(TextView)convertView.findViewById(R.id.language_name);
        	  languageRowHolder.icon_details=(ImageView)convertView.findViewById(R.id.icon_details);
        	  convertView.setTag(languageRowHolder);
            }else{
            	languageRowHolder=(LanguageRowViewHolder)convertView.getTag();
            }
            HashMap<String,Object> languageRowHM=(HashMap<String,Object>)languageRowArrList.get(position);
             
            languageRowHolder.language_type_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_activities));
            languageRowHolder.language_name.setText(languageRowHM.get(ReqResNodes.LANGUAGE).toString());
            languageRowHolder.icon_details.setImageDrawable(getResources().getDrawable(R.drawable.ic_send));
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class LanguageRowViewHolder{
    	    ImageView language_type_icon,icon_details;
    	    TextView language_name;
        }
	        
	}
	
	//Sends Channels API Request for Language.
	private void sendChannelsByLanguageReq(String languageID){
		Log.i(TAG,"sendChannelsByLanguageReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(LanguagesScreen.this);

		req.channelsByLanguageRequest(LanguagesScreen.this,new ResponseHandler(),languageID);
		
		Log.i(TAG,"sendChannelsByLanguageReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void channelsByLanguageProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"channelsByLanguageProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "channelsByLanguageProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "channelsByLanguageProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			Toast.makeText(this,"Exception",Toast.LENGTH_LONG).show();
		}else{
			List<HashMap<String,Object>> channelsHMList=ChannelsByLanguageParser.getChannelsByLanguageParsedResponse(result);
			if(channelsHMList!=null&& !channelsHMList.isEmpty()){
				Log.v(TAG,"Channels Available for selected Language.");
				ChannelsByLanguageScreen.channelsList.clear();
				ChannelsByLanguageScreen.channelsList=channelsHMList;
				Intent intent=new Intent(LanguagesScreen.this,ChannelsByLanguageScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				launchNextScreen(intent);
				/*startActivity(new Intent(LanguagesScreen.this,ChannelsByLanguageScreen.class)
								.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));*/
			}else{
				Log.e(TAG,"Channels not Available for selected Language.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				Toast.makeText(this,"Channels not available for selected Language",Toast.LENGTH_LONG).show();
			}
		}
		
		Log.i(TAG,"channelsByLanguageProceedUI() Exiting.");
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
