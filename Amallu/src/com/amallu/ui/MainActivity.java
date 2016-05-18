package com.amallu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private View view;
	private Button showBtn,hideBtn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view=(View)findViewById(R.id.list_row_options);
		showBtn=(Button)findViewById(R.id.show_view_btn);
		showBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showView(view);
				
			}
		});
		hideBtn=(Button)findViewById(R.id.hide_view_btn);
		hideBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hideView(view);
				
			}
		});
		//showView(view);
		//hideView(view);
	}
	
	
	
	private void showView(final View view){
	    Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_bottom);
	    //use this to make it longer:  animation.setDuration(1000);
	    animation.setAnimationListener(new AnimationListener() {
	        @Override
	        public void onAnimationStart(Animation animation) {
	        	view.setVisibility(View.VISIBLE);
	        }

	        @Override
	        public void onAnimationRepeat(Animation animation) {}

	        @Override
	        public void onAnimationEnd(Animation animation) {
	             //view.setVisibility(View.GONE);
	        }
	    });

	    view.startAnimation(animation);
	}
	
	private void hideView(final View view){
	    Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_out_to_bottom);
	    //use this to make it longer:  animation.setDuration(1000);
	    animation.setAnimationListener(new AnimationListener() {
	        @Override
	        public void onAnimationStart(Animation animation) {}

	        @Override
	        public void onAnimationRepeat(Animation animation) {}

	        @Override
	        public void onAnimationEnd(Animation animation) {
	             view.setVisibility(View.GONE);
	        }
	    });

	    view.startAnimation(animation);
	}

}
