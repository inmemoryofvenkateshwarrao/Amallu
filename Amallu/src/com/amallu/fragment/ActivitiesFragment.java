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

import com.amallu.fragment.ActivitiesFragment.ActivitiesListAdapter.ActivitiesRowViewHolder;
import com.amallu.ui.R;
import com.amallu.utility.ReqResNodes;


public class ActivitiesFragment extends Fragment{
	
	private static final String TAG="ActivitiesFragment";
	int mCurrentPage;
	private TextView activities_unavail_txt_view;
	private ActivitiesRowViewHolder activitiesRowViewHolder;
	private LayoutInflater activitiesInflater;
	private ListView activitylog_list;
	private ActivitiesListAdapter activitiesListAdapter=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate() Entering.");
		Log.i(TAG,"onCreate() Exiting.");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		Log.i(TAG,"onCreateView() Entering.");
		View activitiesView=inflater.inflate(R.layout.activities,container,false);
		activitylog_list=(ListView)activitiesView.findViewById(R.id.activitylog_list);
		activities_unavail_txt_view=(TextView)activitiesView.findViewById(R.id.activities_unavail_txt_view);
		Log.i(TAG,"onCreateView() Exiting.");
		return activitiesView;		
	}
	
	//Method to refresh ActivitiesList ListView.
	public void refreshActivitiesList(ArrayList<HashMap<String,Object>> activitiesArrHMList){
	  Log.i(TAG,"refreshActivitiesList() Entering.");
	  
	  if(activitiesArrHMList!=null && !activitiesArrHMList.isEmpty()){
		 Log.v(TAG,"Activities List Available");
		 activities_unavail_txt_view.setVisibility(View.GONE);
		 populateActivitiesList(activitiesArrHMList);
		 ActivitiesFragment.this.activitiesListAdapter.notifyDataSetChanged();
	  }else{
		 Log.v(TAG,"Activities List not Available");
		 activities_unavail_txt_view.setVisibility(View.VISIBLE);
	  }
	  
	  Log.i(TAG,"refreshActivitiesList() Exiting.");
	}
	
	//Populates ListView Data.
	private void populateActivitiesList(ArrayList<HashMap<String,Object>> activitiesArrHMList){
	  Log.i(TAG,"populateActivitiesList() Entering.");
	  activitiesListAdapter=new ActivitiesListAdapter(getContext(),activitiesArrHMList,R.layout.activitylogrow,new String[]{},new int[]{});
	  activitylog_list.setAdapter(activitiesListAdapter);
	  activitylog_list.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent,View view,int position,long id){
			HashMap<String,Object> activityRowHM=(HashMap<String, Object>)activitylog_list.getItemAtPosition(position);
			//String categoryID=commentRowHM.get(ReqResNodes.CATEGORY_ID).toString();
			//Log.d(TAG,"Category ID : "+categoryID);
			//sendChannelsByCategoryReq(categoryID);  
		 }
	   });
	  Log.i(TAG,"populateActivitiesList() Exiting.");
	}
	
	//Inner Class to make smooth swiping of list view.
	public class ActivitiesListAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> activityArrList;

	   public ActivitiesListAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context,items,resource,from,to);
		   activityArrList=(ArrayList<HashMap<String,Object>>)items;
		   activitiesInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView=activitiesInflater.inflate(R.layout.activitylogrow,null);
        	  activitiesRowViewHolder=new ActivitiesRowViewHolder();
        	  
        	  activitiesRowViewHolder.uname_txt_view=(TextView)convertView.findViewById(R.id.uname_txt_view);
        	  activitiesRowViewHolder.time_txt_view=(TextView)convertView.findViewById(R.id.time_txt_view);
        	  activitiesRowViewHolder.activity_txt_view=(TextView)convertView.findViewById(R.id.activity_txt_view);
        	  
        	  //activitiesRowViewHolder.icon_user=(ImageView)convertView.findViewById(R.id.icon_user);
        	  convertView.setTag(activitiesRowViewHolder);
            }else{
            	activitiesRowViewHolder=(ActivitiesRowViewHolder)convertView.getTag();
            }
            HashMap<String,Object> activityRowHM=(HashMap<String,Object>)activityArrList.get(position);
             
            activitiesRowViewHolder.uname_txt_view.setText(activityRowHM.get(ReqResNodes.FIRSTNAME).toString()+
            						" "+activityRowHM.get(ReqResNodes.LASTNAME).toString());
            activitiesRowViewHolder.time_txt_view.setText(activityRowHM.get(ReqResNodes.TIME_DIFF).toString());
            activitiesRowViewHolder.activity_txt_view.setText("Watching the Sky TG24 channel");
            
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class ActivitiesRowViewHolder{
    	    ImageView icon_user;
    	    TextView uname_txt_view,time_txt_view,activity_txt_view;
        }
	        
	}
}
