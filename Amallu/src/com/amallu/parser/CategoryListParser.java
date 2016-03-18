package com.amallu.parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.utility.ReqResNodes;

public class CategoryListParser{
	
	private static final String TAG="CategoryListParser";
	
	//Method Parses the JSON Response of Categories List Web service.
	public static List<HashMap<String,Object>> getCategoriesListParsedResponse(String categoriesStr){
		Log.i(TAG,"getCategoriesListParsedResponse() Entering.");
		List<HashMap<String,Object>> catRowsHMList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> catRowHM;
		try{
			JSONArray categoryJSONArray=new JSONArray(categoriesStr);
			for(int categoryArr=0;categoryArr<categoryJSONArray.length();categoryArr++){
				catRowHM=new HashMap<String,Object>();
				JSONObject categoryDetailJSONObj=(JSONObject)categoryJSONArray.get(categoryArr);
				if(!categoryDetailJSONObj.isNull(ReqResNodes.CATEGORY_ID))
					catRowHM.put(ReqResNodes.CATEGORY_ID,categoryDetailJSONObj.get(ReqResNodes.CATEGORY_ID));
				if(!categoryDetailJSONObj.isNull(ReqResNodes.PARENT_ID))
					catRowHM.put(ReqResNodes.PARENT_ID,categoryDetailJSONObj.get(ReqResNodes.PARENT_ID));
				if(!categoryDetailJSONObj.isNull(ReqResNodes.CATEGORY_NAME))
					catRowHM.put(ReqResNodes.CATEGORY_NAME,categoryDetailJSONObj.get(ReqResNodes.CATEGORY_NAME));
				if(!categoryDetailJSONObj.isNull(ReqResNodes.IS_ACTIVE))
					catRowHM.put(ReqResNodes.IS_ACTIVE,categoryDetailJSONObj.get(ReqResNodes.IS_ACTIVE));
				if(!categoryDetailJSONObj.isNull(ReqResNodes.DISPLAY_ORDER))
					catRowHM.put(ReqResNodes.DISPLAY_ORDER,categoryDetailJSONObj.get(ReqResNodes.DISPLAY_ORDER));
				catRowsHMList.add(catRowHM);
			}
		}catch(JSONException e){
			e.printStackTrace();
			catRowsHMList=null;
		}
		
		Log.i(TAG,"getCategoriesListParsedResponse() Exiting.");
		return catRowsHMList;
	}
	
}	
	
	/*public static List<HashMap<String,Object>> getCategoriesListParsedResponse(String categoriesStr){
		Log.i(TAG,"getCategoriesListParsedResponse() Entering.");
		List<Category> categoriesDetailList=new ArrayList<Category>();
		Category category=null;
		try{
			JSONArray categoryJSONArray=new JSONArray(categoriesStr);
			for(int categoryArr=0;categoryArr<categoryJSONArray.length();categoryArr++){
				category=new Category();
				JSONObject categoryDetailJSONObj=(JSONObject)categoryJSONArray.get(categoryArr);
				if(!categoryDetailJSONObj.isNull(ReqResNodes.CATEGORY_ID))
					category.setCategory_id(categoryDetailJSONObj.get(ReqResNodes.CATEGORY_ID).toString());
				if(!categoryDetailJSONObj.isNull(ReqResNodes.PARENT_ID))
					category.setParent_id(categoryDetailJSONObj.get(ReqResNodes.PARENT_ID).toString());
				if(!categoryDetailJSONObj.isNull(ReqResNodes.CATEGORY_NAME))
					category.setCategory_name(categoryDetailJSONObj.get(ReqResNodes.CATEGORY_NAME).toString());
				if(!categoryDetailJSONObj.isNull(ReqResNodes.IS_ACTIVE))
					category.setIs_active(categoryDetailJSONObj.get(ReqResNodes.IS_ACTIVE).toString());
				if(!categoryDetailJSONObj.isNull(ReqResNodes.DISPLAY_ORDER))
					category.setDisplay_order(categoryDetailJSONObj.get(ReqResNodes.DISPLAY_ORDER).toString());
				categoriesDetailList.add(category);
			}
		}catch(JSONException e){
			e.printStackTrace();
			categoriesDetailList=null;
		}
		
		Log.i(TAG,"getCategoriesListParsedResponse() Exiting.");
		return categoriesDetailList;
	}*/

