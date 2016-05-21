package com.amallu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.amallu.utility.GlobalConsts;

public class SplashScreen extends SuperActivity{

	private static final String TAG="SplashScreen";
	
	//Method executes whenever object is created.
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate Entering.");
		setContentView(R.layout.splash);
		new Handler().postDelayed(new Runnable(){
	        @Override
	        public void run(){
	          Intent loginIntent=new Intent(SplashScreen.this,LoginScreen.class);
	          launchNextScreen(loginIntent);
	          finish();
	        }
	       },GlobalConsts.SPLASHTIME);
		Log.i(TAG,"onCreate Exiting.");
	}
	
	//Method to handle Device back button.
	@Override
	public void onBackPressed(){
	   Log.i(TAG,"onBackPressed Entering.");
	   //super.onBackPressed();
	   Log.i(TAG,"onBackPressed Exiting.");
	}

}
