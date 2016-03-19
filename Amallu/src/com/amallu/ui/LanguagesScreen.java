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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.amallu.ui.LanguagesScreen.LanguageAdapter.LanguageRowViewHolder;
import com.amallu.utility.ReqResNodes;

public class LanguagesScreen extends Activity implements OnClickListener{
	
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
		    //startActivity(new Intent(LanguagesScreen.this,ChannelsScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
	
	//Method to handle Device back button.
	@Override
	public void onBackPressed(){
	   Log.i(TAG,"onBackPressed Entering.");
	   super.onBackPressed();
	   Log.i(TAG,"onBackPressed Exiting.");
	}
	
	//Sends Languages API Request.
	/*private void sendLanguagesReq(){
		Log.i(TAG,"sendLanguagesReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.languagesListRequest(PlayerScreen.this,new ResponseHandler());
		
		Log.i(TAG,"sendLanguagesReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void languageProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"languageProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()){
			Log.v(TAG, "languageProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "languageProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
		}else{
			List<HashMap<String,Object>> languagesHMList=LanguageListParser.getLanguagesListParsedResponse(result);
			if(languagesHMList!=null&& !languagesHMList.isEmpty()){
				Log.v(TAG,"Languages Available.");
				LanguagesScreen.languagesList.clear();
				LanguagesScreen.languagesList=languagesHMList;
				startActivity(new Intent(PlayerScreen.this,LanguagesScreen.class));
			}else{
				Log.e(TAG,"Languages not Available.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
			}
		}
		
		Log.i(TAG,"languageProceedUI() Exiting.");
	}*/
	
}
