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

import com.amallu.fragment.FavoritesFragment.FavoriteChannelsAdapter.FavoriteRowViewHolder;
import com.amallu.ui.R;
import com.amallu.utility.ReqResNodes;


public class FavoritesFragment extends Fragment{
	
	private static final String TAG="FavoritesFragment";
	int mCurrentPage;
	private TextView favorite_channels_unavail_txt_view;
	private FavoriteRowViewHolder favoriteRowViewHolder;
	private LayoutInflater favoriteInflater;
	private ListView favorite_channels_list;
	private FavoriteChannelsAdapter favoriteChannelsListAdapter=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate() Entering.");
		Log.i(TAG,"onCreate() Exiting.");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		Log.i(TAG,"onCreateView() Entering.");
		View favoriteView=inflater.inflate(R.layout.favorites,container,false);
		favorite_channels_list=(ListView)favoriteView.findViewById(R.id.favorite_channels_list);
		favorite_channels_unavail_txt_view=(TextView)favoriteView.findViewById(R.id.favorite_channels_unavail_txt_view);
		Log.i(TAG,"onCreateView() Exiting.");		
		return favoriteView;		
	}
	
	//Method to refresh Favorites Channels ListView.
	public void refreshFavoritesList(ArrayList<HashMap<String,Object>> favoritesArrHMList){
	  Log.i(TAG,"refreshFavoritesList() Entering.");
	  
	  if(favoritesArrHMList!=null && !favoritesArrHMList.isEmpty()){
		 Log.v(TAG,"Friends List Available");
		 favorite_channels_unavail_txt_view.setVisibility(View.GONE);
		 populateFavoriteChannelsList(favoritesArrHMList);
		 FavoritesFragment.this.favoriteChannelsListAdapter.notifyDataSetChanged();
	  }else{
		 Log.v(TAG,"Friends List not Available");
		 favorite_channels_unavail_txt_view.setVisibility(View.VISIBLE);
	  }
	  
	  Log.i(TAG,"refreshFavoritesList() Exiting.");
	}
	
	//Populates ListView Data.
	private void populateFavoriteChannelsList(ArrayList<HashMap<String,Object>> favoriteChannelsArrHMList){
	  Log.i(TAG,"populatefavoritesChannelsList() Entering.");
	  favoriteChannelsListAdapter=new FavoriteChannelsAdapter(getContext(),favoriteChannelsArrHMList,R.layout.favorite_channels_row,new String[]{},new int[]{});
	  favorite_channels_list.setAdapter(favoriteChannelsListAdapter);
	  favorite_channels_list.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent,View view,int position,long id){
			HashMap<String,Object> favoriteChannelRowHM=(HashMap<String, Object>)favorite_channels_list.getItemAtPosition(position);
			//String categoryID=commentRowHM.get(ReqResNodes.CATEGORY_ID).toString();
			//Log.d(TAG,"Category ID : "+categoryID);
			//sendChannelsByCategoryReq(categoryID);  
		 }
	   });
	  Log.i(TAG,"populatefavoritesChannelsList() Exiting.");
	}
	
	//Inner Class to make smooth swiping of list view.
	public class FavoriteChannelsAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> favoriteRowArrList;

	   public FavoriteChannelsAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context,items,resource,from,to);
		   favoriteRowArrList=(ArrayList<HashMap<String,Object>>)items;
		   favoriteInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView=favoriteInflater.inflate(R.layout.favorite_channels_row,null);
        	  favoriteRowViewHolder=new FavoriteRowViewHolder();
        	  
        	  favoriteRowViewHolder.channel_name_txt_view=(TextView)convertView.findViewById(R.id.channel_name_txt_view);
        	  favoriteRowViewHolder.category_name_txt_view=(TextView)convertView.findViewById(R.id.category_name_txt_view);
        	  //favoriteRowViewHolder.channel_desc_txt_view=(TextView)convertView.findViewById(R.id.channel_desc_txt_view);
        	  favoriteRowViewHolder.duration_txt_view=(TextView)convertView.findViewById(R.id.duration_txt_view);
        	  favoriteRowViewHolder.views_dt_created_txt_view=(TextView)convertView.findViewById(R.id.views_dt_created_txt_view);
        	  
        	  favoriteRowViewHolder.icon_favorite_channel=(ImageView)convertView.findViewById(R.id.icon_favorite_channel);
        	  convertView.setTag(favoriteRowViewHolder);
            }else{
            	favoriteRowViewHolder=(FavoriteRowViewHolder)convertView.getTag();
            }
            HashMap<String,Object> favoriteRowHM=(HashMap<String,Object>)favoriteRowArrList.get(position);
             
            favoriteRowViewHolder.channel_name_txt_view.setText(favoriteRowHM.get(ReqResNodes.CHANNEL_NAME).toString());
            favoriteRowViewHolder.category_name_txt_view.setText(favoriteRowHM.get(ReqResNodes.CATEGORY_ID).toString());
            //favoriteRowViewHolder.channel_desc_txt_view.setText(trendingRowHM.get(ReqResNodes.DESCRIPTION).toString());
            
            /*String channelViews=trendingRowHM.get(ReqResNodes.VIEWS).toString();
            String timeWatched=trendingRowHM.get(ReqResNodes.TIME_WATCHED).toString();
            long days=GlobalUtil.getDaysFromMillis(Long.parseLong(timeWatched));
            
            trendingRowViewHolder.duration_txt_view.setText("Duration : 2:50");
            
            trendingRowViewHolder.views_dt_created_txt_view.setText(channelViews+" views-"+days+" ago");*/
            
            favoriteRowViewHolder.duration_txt_view.setText(favoriteRowHM.get(ReqResNodes.TIME_WATCHED).toString());
            favoriteRowViewHolder.views_dt_created_txt_view.setText(favoriteRowHM.get(ReqResNodes.VIEWS).toString());
            
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class FavoriteRowViewHolder{
    	    ImageView icon_favorite_channel;
    	    TextView channel_name_txt_view,category_name_txt_view,/*channel_desc_txt_view,*/duration_txt_view,views_dt_created_txt_view;
        }
	        
	}

}
