package com.amallu.utility;

import java.util.concurrent.TimeUnit;

import android.util.Log;

public class GlobalUtil{
	
	private static final String TAG="GlobalUtil";
	
	//Method returns Days by taking Milliseconds as input.
	public static long getDaysFromMillis(long milliseconds){
	  Log.i(TAG,"getDaysFromMillis() Entering.");
	  long days=0;
	  days = TimeUnit.MILLISECONDS.toDays(milliseconds);
	  Log.i(TAG,"getDaysFromMillis() Exiting.");
	  return days;
	}

}
