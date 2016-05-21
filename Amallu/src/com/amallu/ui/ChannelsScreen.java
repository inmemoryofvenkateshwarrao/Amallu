package com.amallu.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
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

import com.amallu.ui.ChannelsScreen.ChannelAdapter.ChannelRowHolder;
import com.amallu.utility.ReqResNodes;

public class ChannelsScreen extends SuperActivity implements OnClickListener{
	
	private static final String TAG="ChannelsScreen";
	public static List<HashMap<String,Object>> channelsList=new ArrayList<HashMap<String,Object>>();
	private ChannelRowHolder channelRowHolder;
	private LayoutInflater mInflater;
	private ListView channelList;
	private ImageView icon_left_arrow;

	@Override
	protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  Log.i(TAG,"onCreate Entering.");
	  setContentView(R.layout.channels);
	  intializeViews();
	  setListeners();
	  setData();
	  Log.i(TAG,"onCreate() Exiting.");
	}	 
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
	  Log.i(TAG,"intializeViews() Entering.");
	  channelList=(ListView)findViewById(R.id.channel_list);
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
	  ChannelAdapter channelListAdapter=new ChannelAdapter(this,channelsList,R.layout.channelrow,new String[]{},new int[]{});
	  channelList.setAdapter(channelListAdapter);
	  channelList.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int position,long id){
		    //HashMap (HashMap<String,Object>)view.getTag();
		 } 
	   });
	  Log.i(TAG,"setData() Exiting.");
	}

	//Inner Class to make smooth swiping of list view.
	public class ChannelAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> channelRowArrList;

	   public ChannelAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context, items, resource, from, to);
		   channelRowArrList=(ArrayList<HashMap<String,Object>>)items;
		   mInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView = mInflater.inflate(R.layout.channelrow,null);
        	  channelRowHolder=new ChannelRowHolder();
        	  channelRowHolder.icon_channel=(ImageView)convertView.findViewById(R.id.icon_channel);
        	  channelRowHolder.icon_right=(ImageView)convertView.findViewById(R.id.icon_right);
        	  channelRowHolder.channel_name=(TextView)convertView.findViewById(R.id.channel_name);
        	  channelRowHolder.channel_type=(TextView)convertView.findViewById(R.id.channel_type);
        	  channelRowHolder.channel_views=(TextView)convertView.findViewById(R.id.channel_views);
        	  convertView.setTag(channelRowHolder);
            }else{
              channelRowHolder=(ChannelRowHolder)convertView.getTag();
            }
            HashMap<String,Object> channelRowHM=(HashMap<String,Object>)channelRowArrList.get(position);
             
            channelRowHolder.icon_channel.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_channel));
            channelRowHolder.icon_right.setImageDrawable(getResources().getDrawable(R.drawable.ic_send));
            channelRowHolder.channel_name.setText(channelRowHM.get(ReqResNodes.CHANNEL_NAME).toString());
            channelRowHolder.channel_type.setText(channelRowHM.get(ReqResNodes.DESCRIPTION).toString());
            //55k views-230148 sec
            String channelViews=channelRowHM.get(ReqResNodes.VIEWS).toString()+" "+
            					"views"+"-"+channelRowHM.get(ReqResNodes.TIME_WATCHED).toString()+" "+"sec";
            channelRowHolder.channel_views.setText(channelViews);
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class ChannelRowHolder{
    	    ImageView icon_channel,icon_right;
    	    TextView channel_name,channel_type,channel_views;
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
	
	//Method to send Channels List request.
	/*private void sendChannelsReq(){
		Log.i(TAG,"sendChannelsReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.ChannelsListRequest(PlayerScreen.this,new ResponseHandler());
		
		Log.i(TAG,"sendChannelsReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void channelsProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"channelsProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "channelsProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "channelsProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
		}else{
			List<HashMap<String,Object>> channelsHMList=ChannelsListParser.getChannelsListParsedResponse(result);
			if(channelsHMList!=null&& !channelsHMList.isEmpty()){
				Log.v(TAG,"Channels Available.");
				//ChannelsScreen.channelsList.clear();
				//ChannelsScreen.channelsList=channelsHMList;
				//startActivity(new Intent(PlayerScreen.this,ChannelsScreen.class));
			}else{
				Log.e(TAG,"Channels not Available.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
			}
		}
		
		Log.i(TAG,"channelsProceedUI() Exiting.");
	}*/
	
}
