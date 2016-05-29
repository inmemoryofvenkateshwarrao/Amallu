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

import com.amallu.fragment.FriendsFragment.FriendsListAdapter.FriendsRowViewHolder;
import com.amallu.ui.R;
import com.amallu.utility.ReqResNodes;

public class FriendsFragment extends Fragment{
	
	private static final String TAG="FriendsFragment";
	int mCurrentPage;
	private TextView friends_unavail_txt_view;
	private FriendsRowViewHolder friendsRowViewHolder;
	private LayoutInflater friendsInflater;
	private ListView friends_list;
	ArrayList<HashMap<String,Object>> friendsArrHMList;
	HashMap<String,Object> fFragHM=null;
	FriendsListAdapter friendsListAdapter=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate() Entering.");
		Log.i(TAG,"onCreate() Exiting.");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		Log.i(TAG,"onCreateView() Entering.");
		View friendsView=inflater.inflate(R.layout.friends,container,false);
		friends_list=(ListView)friendsView.findViewById(R.id.friends_list);
		friends_unavail_txt_view=(TextView)friendsView.findViewById(R.id.friends_unavail_txt_view);
		Log.i(TAG,"onCreateView() Exiting.");
		
		/*friendsArrHMList=new ArrayList<HashMap<String,Object>>();
		fFragHM=new HashMap<String,Object>();
		fFragHM.put(ReqResNodes.FRIEND_NAME,"Ashish Mera");
		fFragHM.put(ReqResNodes.FRIEND_MUTUAL,"Friends (6) Mutual (3)");
		fFragHM.put(ReqResNodes.CHANNEL_NAME,"Sky TG24");
		friendsArrHMList.add(fFragHM);
		
		fFragHM=new HashMap<String,Object>();
		fFragHM.put(ReqResNodes.FRIEND_NAME,"Suresh Peddu");
		fFragHM.put(ReqResNodes.FRIEND_MUTUAL,"Friends (12) Mutual (5)");
		fFragHM.put(ReqResNodes.CHANNEL_NAME,"Wawa TV");
		friendsArrHMList.add(fFragHM);
		
		fFragHM=new HashMap<String,Object>();
		fFragHM.put(ReqResNodes.FRIEND_NAME,"Ravi Kumar");
		fFragHM.put(ReqResNodes.FRIEND_MUTUAL,"Friends (12) Mutual (5)");
		fFragHM.put(ReqResNodes.CHANNEL_NAME,"BP TV 2");
		friendsArrHMList.add(fFragHM);*/
		
		//refreshFriendsList(friendsArrHMList);
		
		/*if(friendsArrHMList!=null && !friendsArrHMList.isEmpty()){
		   Log.v(TAG,"Friends List Available");
		   friends_unavail_txt_view.setVisibility(View.GONE);
		   populateFriendsList(friendsArrHMList);
		}else{
		   Log.v(TAG,"Friends List not Available");
		   friends_unavail_txt_view.setVisibility(View.VISIBLE);
		}*/
		
		return friendsView;		
	}
	
	//Method to refresh FriendsList ListView.
	public void refreshFriendsList(ArrayList<HashMap<String,Object>> friendsArrHMList){
	  Log.i(TAG,"refreshFriendsList() Entering.");
	  
	  if(friendsArrHMList!=null && !friendsArrHMList.isEmpty()){
		 Log.v(TAG,"Friends List Available");
		 friends_unavail_txt_view.setVisibility(View.GONE);
		 populateFriendsList(friendsArrHMList);
		 FriendsFragment.this.friendsListAdapter.notifyDataSetChanged();
	  }else{
		 Log.v(TAG,"Friends List not Available");
		 friends_unavail_txt_view.setVisibility(View.VISIBLE);
	  }
	  
	  Log.i(TAG,"refreshFriendsList() Exiting.");
	}
	
	//Populates ListView Data.
	private void populateFriendsList(ArrayList<HashMap<String,Object>> friendsArrHMList){
	  Log.i(TAG,"populateFriendsList() Entering.");
	  friendsListAdapter=new FriendsListAdapter(getContext(),friendsArrHMList,R.layout.friend_row,new String[]{},new int[]{});
	  friends_list.setAdapter(friendsListAdapter);
	  friends_list.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent,View view,int position,long id){
			HashMap<String,Object> watchingRowHM=(HashMap<String, Object>)friends_list.getItemAtPosition(position);
			//String categoryID=commentRowHM.get(ReqResNodes.CATEGORY_ID).toString();
			//Log.d(TAG,"Category ID : "+categoryID);
			//sendChannelsByCategoryReq(categoryID);  
		 }
	   });
	  Log.i(TAG,"populateFriendsList() Exiting.");
	}
	
	//Inner Class to make smooth swiping of list view.
	public class FriendsListAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> friendArrList;

	   public FriendsListAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context,items,resource,from,to);
		   friendArrList=(ArrayList<HashMap<String,Object>>)items;
		   friendsInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView=friendsInflater.inflate(R.layout.friend_row,null);
        	  friendsRowViewHolder=new FriendsRowViewHolder();
        	  
        	  friendsRowViewHolder.name_txt_view=(TextView)convertView.findViewById(R.id.name_txt_view);
        	  friendsRowViewHolder.friends_mutual_txt_view=(TextView)convertView.findViewById(R.id.friends_mutual_txt_view);
        	  friendsRowViewHolder.channel_name_txt_view=(TextView)convertView.findViewById(R.id.channel_name_txt_view);
        	  friendsRowViewHolder.extra_text_txt_view=(TextView)convertView.findViewById(R.id.extra_text_txt_view);
        	  
        	  //friendsRowViewHolder.icon_friend=(ImageView)convertView.findViewById(R.id.icon_friend);
        	  convertView.setTag(friendsRowViewHolder);
            }else{
            	friendsRowViewHolder=(FriendsRowViewHolder)convertView.getTag();
            }
            HashMap<String,Object> friendRowHM=(HashMap<String,Object>)friendArrList.get(position);
             
            friendsRowViewHolder.name_txt_view.setText(friendRowHM.get(ReqResNodes.CHAT_NAME).toString());
            //friendsRowViewHolder.friends_mutual_txt_view.setText(friendRowHM.get(ReqResNodes.FRIEND_MUTUAL).toString());
            friendsRowViewHolder.friends_mutual_txt_view.setText("Friends (6) Mutual (3)");
            //friendsRowViewHolder.channel_name_txt_view.setText(friendRowHM.get(ReqResNodes.CHANNEL_NAME).toString());
            friendsRowViewHolder.channel_name_txt_view.setText("Sky TG24");
            //friendsRowViewHolder.extra_text_txt_view.setText(friendRowHM.get(ReqResNodes.FRIEND_VIEWED_TIME).toString());
            
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class FriendsRowViewHolder{
    	    ImageView icon_friend;
    	    TextView name_txt_view,friends_mutual_txt_view,channel_name_txt_view,extra_text_txt_view;
        }
	        
	}
}
