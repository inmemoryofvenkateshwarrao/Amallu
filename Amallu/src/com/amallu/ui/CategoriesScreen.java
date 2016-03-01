package com.amallu.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.amallu.ui.CategoriesScreen.CategoryAdapter.CatRowViewHolder;

public class CategoriesScreen extends Activity{
	
	private static final String TAG="CategoriesScreen";
	private CatRowViewHolder catRowHolder;
	private LayoutInflater mInflater;
	private ListView categoryList;

	@Override
	protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  Log.i(TAG,"onCreate Entering.");
	  setContentView(R.layout.categories);
	  intializeViews();
	  setData();
	  Log.i(TAG,"onCreate() Exiting.");
	}	 
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
	  Log.i(TAG,"intializeViews() Entering.");
	  categoryList=(ListView)findViewById(R.id.category_list);
	  Log.i(TAG,"intializeViews() Exiting.");
	}
	
	//Populates ListView Data.
	private void setData(){
	  Log.i(TAG,"setData() Entering.");
	  List<HashMap<String,Object>> catRowsHMList=new ArrayList<HashMap<String,Object>>();
	  HashMap<String,Object> catRowHM;
	  catRowHM=new HashMap<String,Object>();
	  catRowHM.put("category_type","Entertainment");
	  catRowsHMList.add(catRowHM);
	  catRowHM=new HashMap<String,Object>();
	  catRowHM.put("category_type","Sports");
	  catRowsHMList.add(catRowHM);
	  catRowHM=new HashMap<String,Object>();
	  catRowHM.put("category_type","News");
	  catRowsHMList.add(catRowHM);
	  catRowHM=new HashMap<String,Object>();
	  catRowHM.put("category_type","Business");
	  catRowsHMList.add(catRowHM);
	  catRowHM=new HashMap<String,Object>();
	  catRowHM.put("category_type","Educational");
	  catRowsHMList.add(catRowHM);
	  CategoryAdapter categoryListAdapter=new CategoryAdapter(this,catRowsHMList,R.layout.categoryrow, new String[] {}, new int[] {});
	  categoryList.setAdapter(categoryListAdapter);
	  categoryList.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int position,long id){
		    startActivity(new Intent(CategoriesScreen.this,ChannelsScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
		 } 
	   });
	  Log.i(TAG,"setData() Exiting.");
	}

	//Inner Class to make smooth swiping of list view.
	public class CategoryAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> catRowArrList;

	   public CategoryAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context, items, resource, from, to);
		   catRowArrList=(ArrayList<HashMap<String,Object>>)items;
		   mInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView = mInflater.inflate(R.layout.categoryrow,null);
        	  catRowHolder=new CatRowViewHolder();
        	  catRowHolder.category_type_icon=(ImageView)convertView.findViewById(R.id.category_type_icon);
        	  catRowHolder.category_name=(TextView)convertView.findViewById(R.id.category_name);
        	  catRowHolder.icon_details=(ImageView)convertView.findViewById(R.id.icon_details);
        	  convertView.setTag(catRowHolder);
            }else{
              catRowHolder=(CatRowViewHolder)convertView.getTag();
            }
            HashMap<String,Object> catRowHM=(HashMap<String,Object>)catRowArrList.get(position);
             
            catRowHolder.category_type_icon.setImageDrawable(getResources().getDrawable(R.drawable.icon_detail));
            catRowHolder.category_name.setText(catRowHM.get("category_type").toString());
            catRowHolder.icon_details.setImageDrawable(getResources().getDrawable(R.drawable.icon_detail));
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class CatRowViewHolder{
    	    ImageView category_type_icon,icon_details;
    	    TextView category_name;
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
