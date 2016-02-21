package com.amallu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreen extends Activity{

	private static final String TAG="SplashScreen";
	
	//Method executes whenever object is created.
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i(TAG,"onCreate Entering.");
		setContentView(R.layout.splash);
		Log.i(TAG,"onCreate Exiting.");
	}
	
	//Method to handle Device back button.
	@Override
	public void onBackPressed(){
	   Log.i(TAG,"onBackPressed Entering.");
	   super.onBackPressed();
	   Log.i(TAG,"onBackPressed Exiting.");
	}

}
