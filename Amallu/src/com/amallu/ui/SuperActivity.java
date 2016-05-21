package com.amallu.ui;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class SuperActivity extends Activity{
	
	private static final String TAG="SuperActivity";
	
	//slide from right to left
    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    //push from bottom to top
    //overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    //push from top to bottom
    //overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    //slide from left to right
    //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	
	//Launches a New Screen.
	protected void launchNextScreen(Intent intent){
	  Log.i(TAG,"launchNextScreen() Entering.");
	  startActivity(intent);
	  //slide from right to left
      overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
	  Log.i(TAG,"launchNextScreen() Exiting.");
	}
	
	//Launches Previous screen.
	protected void launchPreviousScreen(){
	  Log.i(TAG,"launchPreviousScreen() Entering.");
	  //startActivity(intent);
	  //slide from left to right
      overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
	  Log.i(TAG,"launchPreviousScreen() Exiting.");
	}
	
	//Launches specific screen from BackStack.
	protected void launchSpecificScreen(Intent intent){
	  Log.i(TAG,"launchSpecificScreen() Entering.");
	  startActivity(intent);
	  Log.i(TAG,"launchSpecificScreen() Exiting.");
	}

}
