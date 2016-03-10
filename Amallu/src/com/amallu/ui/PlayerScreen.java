package com.amallu.ui;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnErrorListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amallu.adapter.NavDrawerListAdapter;
import com.amallu.model.NavDrawerItem;

@SuppressWarnings("deprecation")
public class PlayerScreen extends FragmentActivity implements OnClickListener,OnInfoListener,
					OnBufferingUpdateListener,OnPreparedListener,OnCompletionListener,OnErrorListener{
	
	private static final String TAG="PlayerScreen";
	private String path1 = "rtmp://stream.smcloud.net/live2/vox/vox_360p";
	private String path2 = "rtmp://cp49989.live.edgefcs.net:1935/live/streamRM1@2564";
	private boolean p1=true;
	private Uri uri;
	private VideoView mVideoView;
	private ProgressBar pb;
	private TextView downloadRateView, loadRateView,dislikes_txt_view,likes_txt_view;
	private ImageView icon_play,icon_pause,icon_maximize,icon_volume,icon_like,icon_dislike,icon_next,icon_previous,menuIcon;
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	//Navigation Drawer title
	private CharSequence mDrawerTitle;

	//Used to store Application title
	private CharSequence mTitle;

	//Slide Menu Items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		Vitamio.isInitialized(getApplicationContext());
	    intializeViews();
		setListeners();
		checkChannelPath();
		setData();

		//Setting the Navigation Drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
		mDrawerList.setAdapter(adapter);

		//Enabling Action Bar application icon and behaving it as toggle button
		
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		//LayoutInflater inflater = getLayoutInflater();
        //View actionBarLay=inflater.inflate(R.layout.action_bar_header,null);
        //menuIcon=(ImageView)actionBarLay.findViewById(R.id.icon_three_liner);
		//getActionBar().setCustomView(actionBarLay);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		){
			public void onDrawerClosed(View view){
				getActionBar().setTitle(getResources().getString(R.string.app_name));
				//getActionBar().setTitle(mTitle);
				//Calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView){
				getActionBar().setTitle(getResources().getString(R.string.app_name));
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if(savedInstanceState==null){
			//On first time display view for first Navigation Item
			displayView(0);
		}
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
		mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerList=(ListView)findViewById(R.id.list_slidermenu);
		//menuIcon=(ImageView)findViewById(R.id.icon_three_liner);
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
		//menuBtn.setOnClickListener(this);
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
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
				case R.id.icon_three_liner:
					mDrawerLayout.openDrawer(mDrawerLayout);
					break;
				default:
					Log.e(TAG,"In Default option");
					break;
			}
			Log.i(TAG,"onClick() Exiting");
		}

	//Populates data.
	 private void setData(){
		Log.i(TAG,"setData() Entering.");
		mTitle = mDrawerTitle = getTitle();

		//Load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		//Navigation Drawer icons from resources
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		//Adding Navigation Drawer items to array
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons.getResourceId(9, -1)));
		
		// Recycle the typed array
		navMenuIcons.recycle();
		Log.i(TAG,"setData() Exiting.");
	  }
	
	
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements ListView.OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id){
			//display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		//toggle nav drawer on selecting action bar app icon/title
		if(mDrawerToggle.onOptionsItemSelected(item)){
			return true;
		}
		// Handle action bar actions click
		switch(item.getItemId()){
			case R.id.action_settings:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu){
		//if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position){
		//update the main content by replacing fragments
		Fragment fragment = null;
		switch(position){
			case 0:
				//fragment = new HomeFragment();
				break;
			case 1:
				//fragment = new FindPeopleFragment();
				break;
			case 2:
				//fragment = new PhotosFragment();
				break;
			case 3:
				//fragment = new CommunityFragment();
				break;
			case 4:
				//fragment = new PagesFragment();
				break;
			case 5:
				//fragment = new WhatsHotFragment();
				break;
	
			default:
				break;
		}

		/*if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}*/
	}

	@Override
	public void setTitle(CharSequence title){
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
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

}
