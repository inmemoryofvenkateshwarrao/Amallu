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
import com.amallu.parser.ChannelsByCategoryParser;
import com.amallu.ui.CategoriesScreen.CategoryAdapter.CatRowViewHolder;
import com.amallu.utility.ReqResNodes;

public class CategoriesScreen extends SuperActivity implements OnClickListener{
	
	private static final String TAG="CategoriesScreen";
	public static List<HashMap<String,Object>> categoriesList=new ArrayList<HashMap<String,Object>>();
	private CatRowViewHolder catRowHolder;
	private LayoutInflater mInflater;
	private ListView categoryList;
	private ImageView icon_left_arrow;

	@Override
	protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  Log.i(TAG,"onCreate Entering.");
	  setContentView(R.layout.categories);
	  intializeViews();
	  setListeners();
	  setData();
	  Log.i(TAG,"onCreate() Exiting.");
	}	 
	
	//Method to initialize the Views of XML file.
	private void intializeViews(){
	  Log.i(TAG,"intializeViews() Entering.");
	  categoryList=(ListView)findViewById(R.id.category_list);
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
	  CategoryAdapter categoryListAdapter=new CategoryAdapter(this,categoriesList,R.layout.categoryrow, new String[] {}, new int[] {});
	  categoryList.setAdapter(categoryListAdapter);
	  categoryList.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int position,long id){
			HashMap<String,Object> categoryRowHM=(HashMap<String, Object>)categoryList.getItemAtPosition(position);
			String categoryID=categoryRowHM.get(ReqResNodes.CATEGORY_ID).toString();
			Log.d(TAG,"Category ID : "+categoryID);
			sendChannelsByCategoryReq(categoryID);  
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
             
            catRowHolder.category_type_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_activities));
            catRowHolder.category_name.setText(catRowHM.get(ReqResNodes.CATEGORY_NAME).toString());
            catRowHolder.icon_details.setImageDrawable(getResources().getDrawable(R.drawable.ic_send));
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class CatRowViewHolder{
    	    ImageView category_type_icon,icon_details;
    	    TextView category_name;
        }
	        
	}
	
	//Sends Channels API Request for Category.
	private void sendChannelsByCategoryReq(String categoryID){
		Log.i(TAG,"sendChannelsByCategoryReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(CategoriesScreen.this);

		req.channelsByCategoryRequest(CategoriesScreen.this,new ResponseHandler(),categoryID);
		
		Log.i(TAG,"sendChannelsByCategoryReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void channelsByCategoryProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"channelsByCategoryProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "channelsByCategoryProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "channelsByCategoryProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			Toast.makeText(this,"Exception",Toast.LENGTH_LONG).show();
		}else{
			List<HashMap<String,Object>> channelsHMList=ChannelsByCategoryParser.getChannelsByCategoryParsedResponse(result);
			if(channelsHMList!=null&& !channelsHMList.isEmpty()){
				Log.v(TAG,"Channels Available for selected Category.");
				ChannelsByCategoryScreen.channelsList.clear();
				ChannelsByCategoryScreen.channelsList=channelsHMList;
				startActivity(new Intent(CategoriesScreen.this,ChannelsByCategoryScreen.class)
								.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			}else{
				Log.e(TAG,"Channels not Available for selected Category.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				Toast.makeText(this,"Channels not available for selected Category",Toast.LENGTH_LONG).show();
			}
		}
		
		Log.i(TAG,"channelsByCategoryProceedUI() Exiting.");
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
