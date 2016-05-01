package com.amallu.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.amallu.fragment.WatchingFragment.WatchingChannelsAdapter.WatchingRowViewHolder;
import com.amallu.ui.R;
import com.amallu.utility.ReqResNodes;


public class WatchingFragment extends Fragment{
	
	private static final String TAG="WatchingFragment";
	int mCurrentPage;
	//private TextView trending_channels_unavail_txt_view;
	private WatchingRowViewHolder watchingRowViewHolder;
	private LayoutInflater watchingInflater;
	private ListView watching_list;
	ArrayList<HashMap<String,Object>> watchingChannelsArrHMList;
	HashMap<String,Object> wFragHM=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate() Entering.");
		Log.i(TAG,"onCreate() Exiting.");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		Log.i(TAG,"onCreateView() Entering.");
		View watchingView=inflater.inflate(R.layout.watching,container,false);
		watching_list=(ListView)watchingView.findViewById(R.id.watching_channels_list);
		//trending_channels_unavail_txt_view=(TextView)trendingView.findViewById(R.id.trending_channels_unavail_txt_view);
		//sendTrendingChannelsReq();	
		Log.i(TAG,"onCreateView() Exiting.");
		
		watchingChannelsArrHMList=new ArrayList<HashMap<String,Object>>();
		wFragHM=new HashMap<String,Object>();
		wFragHM.put(ReqResNodes.FRIEND_NAME,"Ashish Mera");
		wFragHM.put(ReqResNodes.FRIEND_MUTUAL,"Friends (6) Mutual (3)");
		wFragHM.put(ReqResNodes.FRIEND_VIEWED_TIME,"6:01 pm");
		watchingChannelsArrHMList.add(wFragHM);
		
		wFragHM=new HashMap<String,Object>();
		wFragHM.put(ReqResNodes.FRIEND_NAME,"Suresh Peddu");
		wFragHM.put(ReqResNodes.FRIEND_MUTUAL,"Friends (12) Mutual (5)");
		wFragHM.put(ReqResNodes.FRIEND_VIEWED_TIME,"6:01 pm");
		watchingChannelsArrHMList.add(wFragHM);
		
		wFragHM=new HashMap<String,Object>();
		wFragHM.put(ReqResNodes.FRIEND_NAME,"Ravi Kumar");
		wFragHM.put(ReqResNodes.FRIEND_MUTUAL,"Friends (12) Mutual (5)");
		wFragHM.put(ReqResNodes.FRIEND_VIEWED_TIME,"6:01 pm");
		watchingChannelsArrHMList.add(wFragHM);
		
		populateTrendingChannelsList(watchingChannelsArrHMList);
		
		return watchingView;		
	}
	
	//Populates ListView Data.
	private void populateTrendingChannelsList(ArrayList<HashMap<String,Object>> watchingChannelsArrHMList){
	  Log.i(TAG,"setData() Entering.");
	  WatchingChannelsAdapter watchingChannelsListAdapter=new WatchingChannelsAdapter(getContext(),watchingChannelsArrHMList,R.layout.trending_channels_row,new String[]{},new int[]{});
	  watching_list.setAdapter(watchingChannelsListAdapter);
	  watching_list.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent,View view,int position,long id){
			HashMap<String,Object> watchingRowHM=(HashMap<String, Object>)watching_list.getItemAtPosition(position);
			//String categoryID=commentRowHM.get(ReqResNodes.CATEGORY_ID).toString();
			//Log.d(TAG,"Category ID : "+categoryID);
			//sendChannelsByCategoryReq(categoryID);  
		 }
	   });
	  Log.i(TAG,"setData() Exiting.");
	}
	
	//Inner Class to make smooth swiping of list view.
	public class WatchingChannelsAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> watchingRowArrList;

	   public WatchingChannelsAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context,items,resource,from,to);
		   watchingRowArrList=(ArrayList<HashMap<String,Object>>)items;
		   watchingInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView=watchingInflater.inflate(R.layout.watching_row,null);
        	  watchingRowViewHolder=new WatchingRowViewHolder();
        	  
        	  watchingRowViewHolder.name_txt_view=(TextView)convertView.findViewById(R.id.name_txt_view);
        	  watchingRowViewHolder.friends_mutual_txt_view=(TextView)convertView.findViewById(R.id.friends_mutual_txt_view);
        	  watchingRowViewHolder.time_txt_view=(TextView)convertView.findViewById(R.id.time_txt_view);
        	  
        	  //watchingRowViewHolder.icon_trending_channel=(ImageView)convertView.findViewById(R.id.icon_trending_channel);
        	  convertView.setTag(watchingRowViewHolder);
            }else{
            	watchingRowViewHolder=(WatchingRowViewHolder)convertView.getTag();
            }
            HashMap<String,Object> watchingRowHM=(HashMap<String,Object>)watchingRowArrList.get(position);
             
            watchingRowViewHolder.name_txt_view.setText(watchingRowHM.get(ReqResNodes.FRIEND_NAME).toString());
            watchingRowViewHolder.friends_mutual_txt_view.setText(watchingRowHM.get(ReqResNodes.FRIEND_MUTUAL).toString());
            watchingRowViewHolder.time_txt_view.setText(watchingRowHM.get(ReqResNodes.FRIEND_VIEWED_TIME).toString());
            
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class WatchingRowViewHolder{
    	    ImageView icon_trending_channel;
    	    TextView name_txt_view,friends_mutual_txt_view,time_txt_view;
        }
	        
	}
}
