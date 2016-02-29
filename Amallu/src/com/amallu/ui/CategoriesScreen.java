package com.amallu.ui;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.amallu.ui.CategoriesScreen.SpecialAdapter.ViewHolder;

public class CategoriesScreen extends Activity {
	
	private ViewHolder holder;
	private LayoutInflater mInflater;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categories);
	}	 
	
	public class SpecialAdapter extends SimpleAdapter{

		public SpecialAdapter(Context context, List<HashMap<String, Object>> items, int resource, String[] from, int[] to) {
		          super(context, items, resource, from, to);
		          mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    }
	 		    
		    private void createDataBaseObject(){
		    	   
		     }
		    
	        public View getView(int position,View convertView, ViewGroup parent) {
	             if (convertView == null) {
	            	    convertView = mInflater.inflate(R.layout.categoryrow,null);
	                   /* holder = new ViewHolder();
	                    holder.item_imageIdValue=(ImageView)convertView.findViewById(R.id.item_image);
	                    holder.item_nameIdValue=(TextView)convertView.findViewById(R.id.item_name);
	                    holder.item_addressIdValue=(TextView)convertView.findViewById(R.id.item_address);
	                    holder.item_subIdValue = (TextView) convertView.findViewById(R.id.item_sub);
	                    holder.item_helpIdValue = (TextView) convertView.findViewById(R.id.item_help);
	                    convertView.setTag(holder);
	                    if(position<vectorSize){ 
		                    findSubHelpDrivers(tempItems.get(position));
		                    findHelpDrivers(tempItems.get(position));
		                }*/
	              }else {
	                    //holder = (ViewHolder) convertView.getTag();
	                    
	               }
	                return convertView;
	        }
	        
	        class ViewHolder {
	    	    ImageView item_imageIdValue;
	    	    TextView item_nameIdValue,item_addressIdValue;
	            TextView item_subIdValue,item_helpIdValue;
	        }
	        
	}
	
}
