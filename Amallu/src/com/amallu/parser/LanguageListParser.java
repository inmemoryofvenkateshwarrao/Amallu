package com.amallu.parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.amallu.utility.ReqResNodes;

public class LanguageListParser{
	
	private static final String TAG="LanguageListParser";
	
	//Method Parses the JSON Response of Languages List Web service.
	public static List<HashMap<String,Object>> getLanguagesListParsedResponse(String languagesStr){
		Log.i(TAG,"getLanguagesListParsedResponse() Entering.");
		List<HashMap<String,Object>> languageRowsHMList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> languageRowHM;
		try{
			JSONArray languageJSONArray=new JSONArray(languagesStr);
			for(int languageArr=0;languageArr<languageJSONArray.length();languageArr++){
				languageRowHM=new HashMap<String,Object>();
				JSONObject languageDetailJSONObj=(JSONObject)languageJSONArray.get(languageArr);
				if(!languageDetailJSONObj.isNull(ReqResNodes.LANGUAGE_ID))
					languageRowHM.put(ReqResNodes.LANGUAGE_ID,languageDetailJSONObj.get(ReqResNodes.LANGUAGE_ID));
				if(!languageDetailJSONObj.isNull(ReqResNodes.LANGUAGE))
					languageRowHM.put(ReqResNodes.LANGUAGE,languageDetailJSONObj.get(ReqResNodes.LANGUAGE));
				languageRowsHMList.add(languageRowHM);
			}
		}catch(JSONException e){
			e.printStackTrace();
			languageRowsHMList=null;
		}
		
		Log.i(TAG,"getLanguagesListParsedResponse() Exiting.");
		return languageRowsHMList;
	}

}
	
	/*public static List<Language> getLanguagesListParsedResponse(String languagesStr){
		Log.i(TAG,"getLanguagesListParsedResponse() Entering.");
		List<Language> languagesList=new ArrayList<Language>();
        Language language=null;
		try{
			JSONArray languageJSONArray=new JSONArray(languagesStr);
			for(int languageArr=0;languageArr<languageJSONArray.length();languageArr++){
				language=new Language();
				JSONObject languageDetailJSONObj=(JSONObject)languageJSONArray.get(languageArr);
				if(!languageDetailJSONObj.isNull(ReqResNodes.LANGUAGE_ID))
					language.setLanguage_id(languageDetailJSONObj.get(ReqResNodes.LANGUAGE_ID).toString());
				if(!languageDetailJSONObj.isNull(ReqResNodes.LANGUAGE))
					language.setLanguage_id(languageDetailJSONObj.get(ReqResNodes.LANGUAGE).toString());
				languagesList.add(language);
			}
		}catch(JSONException e){
			e.printStackTrace();
			languagesList=null;
		}
		
		Log.i(TAG,"getLanguagesListParsedResponse() Exiting.");
		return languagesList;
	}*/
	
