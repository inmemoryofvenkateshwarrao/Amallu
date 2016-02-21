package com.amallu.backend;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amallu.exception.AmalluException;

public class AsyncServiceRequest extends AsyncTask<String, Void, String>{

	private static final String TAG="AsyncServiceRequest";
	private AsyncCallback object;
	private Context mCtx;
	public Map<String, String> paramsMap;
	private String requestType;

	public static boolean isOutOfMemory = false;
	public static boolean isException = false;
	private AmalluException amalluEx = null;

	public AsyncServiceRequest(AsyncCallback caller, Map<String, String> paramsMap,Context uiContext,String requestType){
		object = caller;
		this.paramsMap = paramsMap;
		this.mCtx = uiContext;
		this.requestType=requestType;
	}

	@Override
	protected void onPreExecute(){
		Log.i(TAG,"onPreExecute() Entering.");
		Log.i(TAG,"onPreExecute() Exiting.");
	}

	@Override
	protected String doInBackground(String... strings){
		
		String result = null;
		WebServClient wsClient = new WebServClient();
		try{
			result = wsClient.getWebServiceResponse(strings[0],this.paramsMap,this.mCtx,requestType);
		}catch(AmalluException ex){
			AsyncServiceRequest.isException = true;
			amalluEx = ex;
		}catch(KeyManagementException e){
			Log.e("Exception", e.getMessage());
		}catch(UnrecoverableKeyException e) {
			Log.e("Exception", e.getMessage());
		}catch(KeyStoreException e){
			Log.e("Exception", e.getMessage());
		}catch(NoSuchAlgorithmException e) {
			Log.e("Exception", e.getMessage());
		}catch(CertificateException e){
			Log.e("Exception", e.getMessage());
		}
		return result;
	}

	@Override
	protected void onPostExecute(String result){
		Log.i(TAG,"onPostExecute() Entering.");
		if(result!=null){
		   Log.i("XML Response:", result);
		   if(result.contains("ERRORCODE")){
			  Log.e("HTTP Error Result:",result);
			  amalluEx = new AmalluException("500","Request Failed. Error response from Server: "+ result,
					  	"Please try again later.");
			}
			object.updateAsyncResult(result,amalluEx);
		}else if(AsyncServiceRequest.isOutOfMemory){
			AsyncServiceRequest.isOutOfMemory=false;
		}else if(AsyncServiceRequest.isException){
			Log.e("ServiceRequest", "TripMNException::true?alertdialog-"+ amalluEx.getErrorCause());
			AsyncServiceRequest.isException = false;
			object.updateAsyncResult("Exception",amalluEx);
		}else{
			Log.e("ServiceRequest", "Request Failed. No HTTP response.");
			amalluEx = new AmalluException("105","Request Failed. No HTTP response","Please try again later.");
			object.updateAsyncResult("Exception",amalluEx);
		}
		Log.i(TAG,"onPostExecute() Entering.");
	}
}
