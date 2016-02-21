package com.amallu.backend;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;
import android.util.Log;

import com.amallu.exception.AmalluException;
import com.amallu.utility.ReqResNodes;

public class WebServClient{
	
	private static final String TAG="WebServClient";
	
	//Method initiates WeService request.
	public String getWebServiceResponse(String url,Map<String, String> kvPairs,Context ctx,String requestType)
					throws AmalluException, KeyStoreException, NoSuchAlgorithmException, 
					CertificateException, KeyManagementException, UnrecoverableKeyException{
		
		String retValue=null;
		HttpClient httpClient = new DefaultHttpClient();
		
		try{
			if(requestType.equals(ReqResNodes.GET)){
				HttpGet httpGet = new HttpGet(url);
				//Execute HTTP Get Request
	            HttpResponse response = httpClient.execute(httpGet);
	 
	            InputStream is = response.getEntity().getContent();
	            BufferedInputStream bis = new BufferedInputStream(is);
	            ByteArrayBuffer baf = new ByteArrayBuffer(20);
	 
	            int current = 0;
	             
	            while((current = bis.read())!=-1){
	                baf.append((byte)current);
	            }  
	 
	            /*Convert the Bytes read to a String. */
	            retValue = new String(baf.toByteArray());
			}else{
				HttpPost httpPost = new HttpPost(url);
				List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
	            
	            String paramKey,paramValue;
				Iterator<String> itKeys = kvPairs.keySet().iterator();
				while(itKeys.hasNext()){
					Log.v(TAG,"inside while");
					paramKey=itKeys.next();
					paramValue=kvPairs.get(paramKey);
					nameValuePairs.add(new BasicNameValuePair(paramKey,paramValue));
				}
	            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));  
	 
	            //Execute HTTP Post Request
	            HttpResponse response = httpClient.execute(httpPost);
	 
	            InputStream is = response.getEntity().getContent();
	            BufferedInputStream bis = new BufferedInputStream(is);
	            ByteArrayBuffer baf = new ByteArrayBuffer(20);
	 
	            int current = 0;
	             
	            while((current = bis.read())!=-1){
	                baf.append((byte)current);
	            }  
	 
	            /*Convert the Bytes read to a String. */
	            retValue = new String(baf.toByteArray());
			}
        }catch(ClientProtocolException e){
        	Log.e(TAG,"ClientProtocolException : "+e);
        }catch (IOException e) {
            Log.e(TAG,"IOException : "+e);
        }
		return retValue;
	}
	
}
