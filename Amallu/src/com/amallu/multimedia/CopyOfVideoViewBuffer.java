package com.amallu.multimedia;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnErrorListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amallu.adapter.OptionsPagerAdapter;
import com.amallu.ui.R;

//http://www.techotopia.com/index.php/An_Android_Studio_VideoView_and_MediaController_Tutorial
//Sky TG24 --- rtmp://cp49989.live.edgefcs.net:1935/live/streamRM1@2564
//Music VOX TV - rtmp://stream.smcloud.net/live2/vox/vox_360p
public class CopyOfVideoViewBuffer extends FragmentActivity implements OnClickListener,OnInfoListener,
								OnBufferingUpdateListener,OnPreparedListener,OnCompletionListener,OnErrorListener,
								ActionBar.TabListener{
	
  private ViewPager viewPager;
  private OptionsPagerAdapter mAdapter;
  private ActionBar actionBar;
  // Tab titles
  private String[] tabs = { "Comment", "Trending", "Favorites"};

  private static final String TAG="VideoViewBuffer";
  private String path1 = "rtmp://stream.smcloud.net/live2/vox/vox_360p";
  private String path2 = "rtmp://cp49989.live.edgefcs.net:1935/live/streamRM1@2564";
  private boolean p1=true;
  private Uri uri;
  private VideoView mVideoView;
  private ProgressBar pb;
  private TextView downloadRateView, loadRateView,dislikes_txt_view,likes_txt_view;
  private ImageView icon_play,icon_pause,icon_maximize,icon_volume,icon_like,icon_dislike,icon_next,icon_previous;
  //private int originalLikes=500;
  //private int originalDisLikes=12;

  @Override
  public void onCreate(Bundle icicle){
    super.onCreate(icicle);
    Log.i(TAG,"onCreate() Entering.");
    getActionBar().setDisplayShowHomeEnabled(false);
    getActionBar().setDisplayShowTitleEnabled(false);
	Vitamio.isInitialized(getApplicationContext());
    setContentView(R.layout.videobuffer);
    intializeViews();
	setListeners();
	checkChannelPath();
	
	
	 viewPager = (ViewPager) findViewById(R.id.pager);
	    actionBar = getActionBar();
	    mAdapter = new OptionsPagerAdapter(getSupportFragmentManager());

	    viewPager.setAdapter(mAdapter);
	    actionBar.setHomeButtonEnabled(false);
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        

	    // Adding Tabs
	    for (String tab_name : tabs) {
	        actionBar.addTab(actionBar.newTab().setText(tab_name)
	                .setTabListener(this));
	    }

	    /**
	     * on swiping the viewpager make respective tab selected
	     * */
	    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

	        @Override
	        public void onPageSelected(int position) {
	            // on changing the page
	            // make respected tab selected
	            actionBar.setSelectedNavigationItem(position);
	        }

	        @Override
	        public void onPageScrolled(int arg0, float arg1, int arg2) {
	        }

	        @Override
	        public void onPageScrollStateChanged(int arg0) {
	        }
	    });
	
	
    Log.i(TAG,"onCreate() Exiting.");
  }
  
  //Method to initialize the Views of XML file.
  private void intializeViews(){
	Log.i(TAG,"intializeViews() Entering.");
	mVideoView=(VideoView)findViewById(R.id.buffer);
    pb=(ProgressBar)findViewById(R.id.probar);
    downloadRateView=(TextView)findViewById(R.id.download_rate);
    loadRateView=(TextView)findViewById(R.id.load_rate);
    dislikes_txt_view=(TextView)findViewById(R.id.dislikes_txt_view);
    likes_txt_view=(TextView)findViewById(R.id.likes_txt_view);
	icon_play=(ImageView)findViewById(R.id.icon_play);
	icon_pause=(ImageView)findViewById(R.id.icon_pause);
	icon_volume=(ImageView)findViewById(R.id.icon_volume);
	icon_maximize=(ImageView)findViewById(R.id.icon_maximize);
	icon_like=(ImageView)findViewById(R.id.icon_like);
	icon_dislike=(ImageView)findViewById(R.id.icon_dislike);
	icon_next=(ImageView)findViewById(R.id.icon_next);
	icon_previous=(ImageView)findViewById(R.id.icon_previous);
	Log.i(TAG,"intializeViews() Exiting.");
  }

  //Method to set the Listeners to the Views of XML file.
  private void setListeners(){
	Log.i(TAG,"setListeners() Entering.");
	mVideoView.setOnInfoListener(this);
    mVideoView.setOnBufferingUpdateListener(this);
    mVideoView.setOnPreparedListener(this);
    mVideoView.setOnCompletionListener(this);
    mVideoView.setOnErrorListener(this);
	icon_play.setOnClickListener(this);
	icon_pause.setOnClickListener(this);
	icon_volume.setOnClickListener(this);
	icon_maximize.setOnClickListener(this);
	icon_like.setOnClickListener(this);
	icon_dislike.setOnClickListener(this);
	icon_next.setOnClickListener(this);
	icon_previous.setOnClickListener(this);
	Log.i(TAG,"setListeners() Exiting");
  }
  
  private void checkChannelPath(){
    if(p1){
	   startLiveStreaming(path1);
	   p1=false;
	}else{
	   startLiveStreaming(path2);
	   p1=true;
	}
  }
  
  //Starts Live Streaming.
  private void startLiveStreaming(String path){
	 Log.i(TAG,"startLiveStreaming() Entering.");
	 if(path==""){
	      Log.v(TAG,"path is empty");
	      return;
	    }else{
	      uri=Uri.parse(path);
	      mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
	      mVideoView.setVideoURI(uri);
	      /* Enable MediaController Controls(Android default)
	       * MediaController mediaController=new MediaController(this);
	       * mediaController.setAnchorView(mVideoView);
	       * mVideoView.setMediaController(mediaController);
	      */
	      mVideoView.requestFocus();
	    }
	 Log.i(TAG,"startLiveStreaming() Exiting.");
  }

  @Override
	public void onClick(View view){
		Log.i(TAG,"onClick() Entering.");
		switch(view.getId()){
			case R.id.icon_play:
				Log.i(TAG,"Play Icon clicked");
				icon_play.setVisibility(View.INVISIBLE);
				icon_pause.setVisibility(View.VISIBLE);
				mVideoView.start();
				break;
			case R.id.icon_pause:
				Log.i(TAG,"Pause Icon clicked");
				icon_pause.setVisibility(View.INVISIBLE);
				icon_play.setVisibility(View.VISIBLE);
				if(mVideoView.isPlaying()){
				   mVideoView.pause();
				}
				break;
			case R.id.icon_volume:
				Log.i(TAG,"Volume Icon clicked");
				break;
			case R.id.icon_maximize:
				Log.i(TAG,"Maximize Icon clicked");
				break;
			case R.id.icon_like:
				Log.i(TAG,"Like Icon clicked");
				int likeCount=Integer.parseInt(likes_txt_view.getText().toString());
				//if(originalLikes==likeCount){
				   likes_txt_view.setText(Integer.toString(likeCount+1));
				//}
				break;
			case R.id.icon_dislike:
				Log.i(TAG,"Dislike Icon clicked");
				int disLikeCount=Integer.parseInt(dislikes_txt_view.getText().toString());
				//if(originalDisLikes==disLikeCount){
					dislikes_txt_view.setText(Integer.toString(disLikeCount-1));
				//}
				break;
			case R.id.icon_next:
				Log.i(TAG,"Next Icon clicked");
				mVideoView.clearFocus();
				checkChannelPath();
				break;
			case R.id.icon_previous:
				Log.i(TAG,"Previous Icon clicked");
				mVideoView.clearFocus();
				checkChannelPath();
				break;
			default:
				Log.e(TAG,"In Default option");
				break;
		}
		Log.i(TAG,"onClick() Exiting");
	}
	

  @Override
  public boolean onInfo(MediaPlayer mp, int what, int extra){
    switch(what){
    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
      Log.v(TAG,"MEDIA_INFO_BUFFERING_START");
      if(mVideoView.isPlaying()){
        mVideoView.pause();
        pb.setVisibility(View.VISIBLE);
        downloadRateView.setText("");
        loadRateView.setText("");
        downloadRateView.setVisibility(View.VISIBLE);
        loadRateView.setVisibility(View.VISIBLE);
      }
      break;
    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
   	  Log.v(TAG,"MEDIA_INFO_BUFFERING_END");
      mVideoView.start();
      pb.setVisibility(View.GONE);
      downloadRateView.setVisibility(View.GONE);
      loadRateView.setVisibility(View.GONE);
      break;
    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
      Log.v(TAG,"MEDIA_INFO_BUFFERING_END");
      downloadRateView.setText("" + extra + "kb/s" + "  ");
      break;
    }
    return true;
  }
  
  //Called when video is loaded and ready to play.
  @Override
  public void onPrepared(MediaPlayer mediaPlayer){
  	Log.i(TAG,"onPrepared() Entering.");
    //optional need Vitamio 4.0
    mediaPlayer.setPlaybackSpeed(1.0f);
    icon_pause.setVisibility(View.VISIBLE);
  	Log.i(TAG,"onPrepared() Exiting.");
  }

  @Override
  public void onBufferingUpdate(MediaPlayer mp, int percent){
	Log.i(TAG,"onBufferingUpdate() Entering.");
    loadRateView.setText(percent + "%");
    Log.i(TAG,"onBufferingUpdate() Exiting.");
  }
  
  @Override
  public void onCompletion(MediaPlayer mediaPlayer){
	 Log.i(TAG,"onCompletion() Entering.");
	 Log.i(TAG,"onCompletion() Exiting.");
  }
  
  @Override
  public boolean onError(MediaPlayer mediaPlayer, int what, int extra){
	Log.i(TAG,"onError() Entering.");
	Toast.makeText(this,"Unable to stream video",Toast.LENGTH_LONG).show();
    Log.i(TAG,"onError() Exiting.");
  	return false;
  }
  
  @Override
  public void onTabReselected(Tab tab, FragmentTransaction ft) {
  }

  @Override
  public void onTabSelected(Tab tab, FragmentTransaction ft) {
      // on tab selected
      // show respected fragment view
      viewPager.setCurrentItem(tab.getPosition());
  }

  @Override
  public void onTabUnselected(Tab tab, FragmentTransaction ft) {
  }
  
  //Method to handle Device back button.
  @Override
  public void onBackPressed(){
     Log.i(TAG,"onBackPressed Entering.");
     super.onBackPressed();
     Log.i(TAG,"onBackPressed Exiting.");
  }

}
