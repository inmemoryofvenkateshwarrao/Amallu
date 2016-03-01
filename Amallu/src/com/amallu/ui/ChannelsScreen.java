package com.amallu.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.amallu.ui.ChannelsScreen.SpecialAdapter.ChannelRowHolder;

public class ChannelsScreen extends Activity{
	
	private static final String TAG="ChannelsScreen";
	private ChannelRowHolder channelRowHolder;
	private LayoutInflater mInflater;
	private ListView channelList;

	@Override
	protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  Log.i(TAG,"onCreate Entering.");
	  setContentView(R.layout.channels);
	  intializeViews();
	  setData();
	  Log.i(TAG,"onCreate() Exiting.");
	}	 
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
	  Log.i(TAG,"intializeViews() Entering.");
	  channelList=(ListView)findViewById(R.id.channel_list);
	  Log.i(TAG,"intializeViews() Exiting.");
	}
	
	//Populates ListView Data.
	private void setData(){
	  Log.i(TAG,"setData() Entering.");
	  List<HashMap<String,Object>> channelRowsHMList=new ArrayList<HashMap<String,Object>>();
	  HashMap<String,Object> channelRowHM;
	  channelRowHM=new HashMap<String,Object>();
	  channelRowHM.put("channel_name","Sky TG24");
	  channelRowHM.put("channel_type","Polish | Entertainment");
	  channelRowHM.put("channel_views","55k views-230148 sec");
	  channelRowsHMList.add(channelRowHM);
	  channelRowHM=new HashMap<String,Object>();
	  channelRowHM.put("channel_name","Sky TG24");
	  channelRowHM.put("channel_type","Polish | Entertainment");
	  channelRowHM.put("channel_views","55k views-230148 sec");
	  channelRowsHMList.add(channelRowHM);
	  channelRowHM=new HashMap<String,Object>();
	  channelRowHM.put("channel_name","Sky TG24");
	  channelRowHM.put("channel_type","Polish | Entertainment");
	  channelRowHM.put("channel_views","55k views-230148 sec");
	  channelRowsHMList.add(channelRowHM);
	  channelRowHM=new HashMap<String,Object>();
	  channelRowHM.put("channel_name","Sky TG24");
	  channelRowHM.put("channel_type","Polish | Entertainment");
	  channelRowHM.put("channel_views","55k views-230148 sec");
	  channelRowsHMList.add(channelRowHM);
	  channelRowHM=new HashMap<String,Object>();
	  channelRowHM.put("channel_name","Sky TG24");
	  channelRowHM.put("channel_type","Polish | Entertainment");
	  channelRowHM.put("channel_views","55k views-230148 sec");
	  channelRowsHMList.add(channelRowHM);
	  SpecialAdapter categoryListAdapter=new SpecialAdapter(this,channelRowsHMList,R.layout.channelrow,new String[]{},new int[]{});
	  channelList.setAdapter(categoryListAdapter);
	  channelList.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int position,long id){
		    
		 } 
	   });
	  Log.i(TAG,"setData() Exiting.");
	}

	//Inner Class to make smooth swiping of list view.
	public class SpecialAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> channelRowArrList;

	   public SpecialAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context, items, resource, from, to);
		   channelRowArrList=(ArrayList<HashMap<String,Object>>)items;
		   mInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView = mInflater.inflate(R.layout.categoryrow,null);
        	  channelRowHolder=new ChannelRowHolder();
        	  channelRowHolder.icon_channel=(ImageView)convertView.findViewById(R.id.icon_channel);
        	  channelRowHolder.channel_name=(TextView)convertView.findViewById(R.id.channel_name);
        	  channelRowHolder.channel_type=(TextView)convertView.findViewById(R.id.channel_type);
        	  channelRowHolder.channel_views=(TextView)convertView.findViewById(R.id.channel_views);
        	  convertView.setTag(channelRowHolder);
            }else{
              channelRowHolder=(ChannelRowHolder)convertView.getTag();
            }
            HashMap<String,Object> channelRowHM=(HashMap<String,Object>)channelRowArrList.get(position);
             
            channelRowHolder.icon_channel.setImageDrawable(getResources().getDrawable(R.drawable.icon_channel));
            channelRowHolder.channel_name.setText(channelRowHM.get("channel_name").toString());
            channelRowHolder.channel_type.setText(channelRowHM.get("channel_type").toString());
            channelRowHolder.channel_views.setText(channelRowHM.get("channel_views").toString());
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class ChannelRowHolder{
    	    ImageView icon_channel;
    	    TextView channel_name,channel_type,channel_views;
        }
	        
	}
	
	//Method to handle Device back button.
	@Override
	public void onBackPressed(){
	   Log.i(TAG,"onBackPressed Entering.");
	   super.onBackPressed();
	   Log.i(TAG,"onBackPressed Exiting.");
	}
	
}
