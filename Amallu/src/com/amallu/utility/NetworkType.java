package com.amallu.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.amallu.ui.R;

public class NetworkType{
	
	private static final String TAG="NetworkType";
	
	public static boolean isWIFI(Context ctx){
         ConnectivityManager cm=(ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo networkInfo=cm.getActiveNetworkInfo();
         boolean connAvaivalue=networkInfo.isConnected();
         Log.i(TAG,"Is network available "+connAvaivalue);
         if(networkInfo!=null&&networkInfo.isConnected()&&
        		networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
        	Log.i(TAG,"Wifi Available");
        	return true;
         }else if(networkInfo!=null&&networkInfo.isConnected()&& 
        		networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
        	Log.i(TAG,"Mobile network Available");
        	return false;
         }
	     return true;
	}
	
	public static int isConnected(Context ctx){
		Log.i(TAG,"isConnected method start");
		String wifi=ctx.getResources().getString(R.string.wifi);
		String mobile=ctx.getResources().getString(R.string.mobilenetwork);
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;
		ConnectivityManager cnctvMngr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netwrkInfo = cnctvMngr.getAllNetworkInfo();
		for (NetworkInfo netInf : netwrkInfo) {
			Log.v(TAG,"Checking network .. " + netInf.getTypeName());
			if (netInf.getTypeName().equalsIgnoreCase(wifi)){
				Log.v(TAG,"Network is wifi");
				if (netInf.isConnected()){
					Log.v(TAG,"Connected to Wifi network");
					haveConnectedWifi = true;
				}					
			}
				
			if (netInf.getTypeName().equalsIgnoreCase(mobile)){
				Log.v(TAG,"Mobile Network available");
				if (netInf.isConnected()){
					Log.v(TAG,"Connected to Mobile Network.");
					haveConnectedMobile = true;
				}
			}				
		}
		if(haveConnectedWifi && haveConnectedMobile){
			Log.v(TAG,"Both Connections available");
			Log.e(TAG,"Not connected to any network");
			Log.i(TAG,"isConnected method stop");
			return 1;
		}else if(haveConnectedMobile){
			Log.e(TAG,"Connected to mobile network");
			Log.i(TAG,"isConnected method stop");
			return 2;
		}else if(haveConnectedWifi){
			Log.e(TAG,"Connected to wifi network");
			Log.i(TAG,"isConnected method stop");
			return 3;
		}else{
			Log.e(TAG,"Not connected to any network");
			Log.i(TAG,"isConnected method stop");
			return 4;
		}
		
		
	}

}
