package com.amallu.ui;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;

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
	
	//Applies the Animation to expand the layout.
	private void expandLayout(final View layoutToExpand){
		Log.i(TAG,"expandLayout() Entering.");
		
		if(layoutToExpand.getVisibility()==View.GONE){
		    Animation animation = AnimationUtils.loadAnimation(this,R.anim.slide_in_from_bottom);
		    //use this to make it longer:  animation.setDuration(1000);
		    animation.setDuration(1000);
		    animation.setAnimationListener(new AnimationListener(){
		    	
		        @Override
		        public void onAnimationStart(Animation animation){
		        	layoutToExpand.setVisibility(View.VISIBLE);
		        }
	
		        @Override
		        public void onAnimationRepeat(Animation animation){}
	
		        @Override
		        public void onAnimationEnd(Animation animation){}
		    });
	
		    layoutToExpand.startAnimation(animation);
		}else{
			//Do Nothing.
		}
		
		Log.i(TAG,"expandLayout() Exiting.");
	}
	
	//Applies the Animation to collapse the layout.
	private void collapseLayout(final View layoutToCollapse){
		Log.i(TAG,"collapseLayout() Entering.");
		
		if(layoutToCollapse.getVisibility()==View.VISIBLE){
		    Animation animation = AnimationUtils.loadAnimation(this,R.anim.slide_out_to_bottom);
		    //use this to make it longer:  animation.setDuration(1000);
		    animation.setDuration(1000);
		    animation.setAnimationListener(new AnimationListener(){
		    	
		        @Override
		        public void onAnimationStart(Animation animation){}
	
		        @Override
		        public void onAnimationRepeat(Animation animation){}
	
		        @Override
		        public void onAnimationEnd(Animation animation){
		        	layoutToCollapse.setVisibility(View.GONE);
		        }
		    });
	
		    layoutToCollapse.startAnimation(animation);
		}else{
			//Do Nothing.
		}
		
	    Log.i(TAG,"collapseLayout() Exiting.");
	}

}
