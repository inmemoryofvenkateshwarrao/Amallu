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

import com.amallu.backend.CustomProgressDialog;
import com.amallu.backend.ReqResHandler;
import com.amallu.backend.ResponseHandler;
import com.amallu.exception.AmalluException;
import com.amallu.fragment.TrendingFragment.TrendingChannelsAdapter.TrendingRowViewHolder;
import com.amallu.model.TrendingChannels;
import com.amallu.parser.TrendingChannelsParser;
import com.amallu.ui.R;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.ReqResNodes;


public class TrendingFragment extends Fragment{
	
	private static final String TAG="TrendingFragment";
	int mCurrentPage;
	private TextView trending_channels_unavail_txt_view;
	private TrendingRowViewHolder trendingRowViewHolder;
	private LayoutInflater trendingInflater;
	private ListView trending_list;
	ArrayList<HashMap<String,Object>> trendingChannelsArrHMList;
	HashMap<String,Object> tFragHM=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate() Entering.");
		Log.i(TAG,"onCreate() Exiting.");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		Log.i(TAG,"onCreateView() Entering.");
		View trendingView=inflater.inflate(R.layout.trending,container,false);
		trending_list=(ListView)trendingView.findViewById(R.id.trending_channels_list);
		trending_channels_unavail_txt_view=(TextView)trendingView.findViewById(R.id.trending_channels_unavail_txt_view);
		//sendTrendingChannelsReq();	
		Log.i(TAG,"onCreateView() Exiting.");
		
		trendingChannelsArrHMList=new ArrayList<HashMap<String,Object>>();
		tFragHM=new HashMap<String,Object>();
		tFragHM.put(ReqResNodes.CHANNEL_NAME,"Rytas TV");
		tFragHM.put(ReqResNodes.CATEGORY_ID,"German, Entertainment");
		tFragHM.put(ReqResNodes.DESCRIPTION,"Printing and Type setting Industry");
		tFragHM.put(ReqResNodes.DURATION,"Duration : 2:50");
		tFragHM.put(ReqResNodes.VIEWS,"55k views- 2 weeks ago");
		trendingChannelsArrHMList.add(tFragHM);
		
		tFragHM=new HashMap<String,Object>();
		tFragHM.put(ReqResNodes.CHANNEL_NAME,"Rytas TV");
		tFragHM.put(ReqResNodes.CATEGORY_ID,"German, Entertainment");
		tFragHM.put(ReqResNodes.DESCRIPTION,"Printing and Type setting Industry");
		tFragHM.put(ReqResNodes.DURATION,"Duration : 2:50");
		tFragHM.put(ReqResNodes.VIEWS,"22k views- 5 weeks ago");
		trendingChannelsArrHMList.add(tFragHM);
		
		populateTrendingChannelsList(trendingChannelsArrHMList);
		
		return trendingView;		
	}
	
	//Method to send Trending API request.
	private void sendTrendingChannelsReq(){
		Log.i(TAG,"sendTrendingChannelsReq() Entering.");
		
		trending_channels_unavail_txt_view.setVisibility(View.GONE);
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(getActivity());

		req.trendingChannelsRequest(getActivity(),new ResponseHandler());
		
		Log.i(TAG,"sendTrendingChannelsReq() Exiting.");
	}
	
	//Method handles the response from Server.
	public void trendingChannelsProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"trendingChannelsProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "trendingChannelsProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e("trendingChannelsProceedUI", "Exception Case");
			trending_channels_unavail_txt_view.setText(getResources().getString(R.string.unable_to_fetch_trending_channels));
			trending_channels_unavail_txt_view.setVisibility(View.VISIBLE);
		}else{
			//Login login=LoginParser.getLoginParsedResponse(result);
			TrendingChannels trendingChannels=TrendingChannelsParser.getTrendingChannelsListParsedResponse(result);
			if(trendingChannels!=null){
				Log.v(TAG,"Trending Channels response parsing success.");
				if(trendingChannels.getIsSuccess().equals(ErrorCodes.ISFAILURE)){
				   Log.e(TAG,"isSuccess Value : "+trendingChannels.getIsSuccess());
				   Log.e(TAG,"Error Message : "+trendingChannels.getMessage());
				   trending_channels_unavail_txt_view.setText(getResources().getString(R.string.trending_channels_unavailable));
				   trending_channels_unavail_txt_view.setVisibility(View.VISIBLE);
				}else{
					Log.v(TAG,"Fetched Trending Channels Successfully. Please find the below details.");
					ArrayList<HashMap<String,Object>> trendingChannelsArrHMList=trendingChannels.getTrendingChannelsHMList();
					populateTrendingChannelsList(trendingChannelsArrHMList);
				}
			}else{
				Log.e(TAG,"Trending Channels response parsing failed.");
				trending_channels_unavail_txt_view.setText(getResources().getString(R.string.unable_to_fetch_trending_channels));
				trending_channels_unavail_txt_view.setVisibility(View.VISIBLE);
			}
		}
		
		Log.i(TAG,"trendingChannelsProceedUI() Exiting.");
	}
	
	//Populates ListView Data.
	private void populateTrendingChannelsList(ArrayList<HashMap<String,Object>> trendingChannelsArrHMList){
	  Log.i(TAG,"setData() Entering.");
	  TrendingChannelsAdapter trendingChannelsListAdapter=new TrendingChannelsAdapter(getContext(),trendingChannelsArrHMList,R.layout.trending_channels_row,new String[]{},new int[]{});
	  trending_list.setAdapter(trendingChannelsListAdapter);
	  trending_list.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent,View view,int position,long id){
			HashMap<String,Object> trendingRowHM=(HashMap<String, Object>)trending_list.getItemAtPosition(position);
			//String categoryID=commentRowHM.get(ReqResNodes.CATEGORY_ID).toString();
			//Log.d(TAG,"Category ID : "+categoryID);
			//sendChannelsByCategoryReq(categoryID);  
		 }
	   });
	  Log.i(TAG,"setData() Exiting.");
	}
	
	//Inner Class to make smooth swiping of list view.
	public class TrendingChannelsAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> trendingRowArrList;

	   public TrendingChannelsAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context,items,resource,from,to);
		   trendingRowArrList=(ArrayList<HashMap<String,Object>>)items;
		   trendingInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView=trendingInflater.inflate(R.layout.trending_channels_row,null);
        	  trendingRowViewHolder=new TrendingRowViewHolder();
        	  
        	  trendingRowViewHolder.channel_name_txt_view=(TextView)convertView.findViewById(R.id.channel_name_txt_view);
        	  trendingRowViewHolder.category_name_txt_view=(TextView)convertView.findViewById(R.id.category_name_txt_view);
        	  trendingRowViewHolder.channel_desc_txt_view=(TextView)convertView.findViewById(R.id.channel_desc_txt_view);
        	  trendingRowViewHolder.duration_txt_view=(TextView)convertView.findViewById(R.id.duration_txt_view);
        	  trendingRowViewHolder.views_dt_created_txt_view=(TextView)convertView.findViewById(R.id.views_dt_created_txt_view);
        	  
        	  trendingRowViewHolder.icon_trending_channel=(ImageView)convertView.findViewById(R.id.icon_trending_channel);
        	  convertView.setTag(trendingRowViewHolder);
            }else{
            	trendingRowViewHolder=(TrendingRowViewHolder)convertView.getTag();
            }
            HashMap<String,Object> trendingRowHM=(HashMap<String,Object>)trendingRowArrList.get(position);
             
            trendingRowViewHolder.channel_name_txt_view.setText(trendingRowHM.get(ReqResNodes.CHANNEL_NAME).toString());
            trendingRowViewHolder.category_name_txt_view.setText(trendingRowHM.get(ReqResNodes.CATEGORY_ID).toString());
            trendingRowViewHolder.channel_desc_txt_view.setText(trendingRowHM.get(ReqResNodes.DESCRIPTION).toString());
            
            /*String channelViews=trendingRowHM.get(ReqResNodes.VIEWS).toString();
            String timeWatched=trendingRowHM.get(ReqResNodes.TIME_WATCHED).toString();
            long days=GlobalUtil.getDaysFromMillis(Long.parseLong(timeWatched));
            
            trendingRowViewHolder.duration_txt_view.setText("Duration : 2:50");
            
            trendingRowViewHolder.views_dt_created_txt_view.setText(channelViews+" views-"+days+" ago");*/
            
            trendingRowViewHolder.duration_txt_view.setText(trendingRowHM.get(ReqResNodes.DURATION).toString());
            trendingRowViewHolder.views_dt_created_txt_view.setText(trendingRowHM.get(ReqResNodes.VIEWS).toString());
            
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class TrendingRowViewHolder{
    	    ImageView icon_trending_channel;
    	    TextView channel_name_txt_view,category_name_txt_view,channel_desc_txt_view,duration_txt_view,views_dt_created_txt_view;
        }
	        
	}

}
