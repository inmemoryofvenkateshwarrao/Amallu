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
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.amallu.adapter.OptionsFragmentPagerAdapter;
import com.amallu.backend.CustomProgressDialog;
import com.amallu.backend.ReqResHandler;
import com.amallu.backend.ResponseHandler;
import com.amallu.exception.AmalluException;
import com.amallu.fragment.ActivitiesFragment;
import com.amallu.fragment.CommentsFragment;
import com.amallu.fragment.CommentsFragment.OnItemSelectedListener;
import com.amallu.fragment.FavoritesFragment;
import com.amallu.fragment.FriendsFragment;
import com.amallu.fragment.TrendingFragment;
import com.amallu.fragment.WatchingFragment;
import com.amallu.model.ActivityLog;
import com.amallu.model.ChannelDetail;
import com.amallu.model.ChannelInfo;
import com.amallu.model.Comment;
import com.amallu.model.DisLikeChannel;
import com.amallu.model.FavoriteChannels;
import com.amallu.model.Friend;
import com.amallu.model.LikeChannel;
import com.amallu.model.Login;
import com.amallu.model.Profile;
import com.amallu.parser.ActivityLogParser;
import com.amallu.parser.CategoryListParser;
import com.amallu.parser.ChannelInfoParser;
import com.amallu.parser.ChannelsListParser;
import com.amallu.parser.DisLikeChannelParser;
import com.amallu.parser.FavoriteChannelsParser;
import com.amallu.parser.FriendsListParser;
import com.amallu.parser.LanguageListParser;
import com.amallu.parser.LikeChannelParser;
import com.amallu.parser.LoginParser;
import com.amallu.parser.ProfileParser;
import com.amallu.parser.SignUpParser;
import com.amallu.ui.PlayerScreen.MenuItemAdapter.MenuItemRowViewHolder;
import com.amallu.utility.ErrorCodes;
import com.amallu.utility.GlobalConsts;
import com.amallu.utility.GlobalUtil;
import com.amallu.utility.ReqResNodes;

@SuppressWarnings("deprecation")
public class PlayerScreen extends FragmentActivity implements OnClickListener,OnInfoListener,
					OnBufferingUpdateListener,OnPreparedListener,OnCompletionListener,OnErrorListener,OnPageChangeListener,
					OnItemSelectedListener{
	
	private static final String TAG="PlayerScreen";
	private Uri uri;
	private VideoView mVideoView;
	private ProgressBar pb;
	private TextView downloadRateView, loadRateView,dislikes_txt_view,likes_txt_view,channel_name_txt_view,channel_Type_txt_view,
						facebook_count_txt_view,google_count_txt_view,twitter_count_txt_view;
	private ImageView icon_play,icon_pause,icon_maximize,icon_minimize,icon_volume,icon_like,icon_dislike,icon_next,icon_previous,menuIcon,
						icon_facebook,icon_google,icon_twitter;
	private RelativeLayout rel_like,rel_dislike,rel_edit,rel_delete,rel_profile,rel_cancel,rel_controls,rel_videoview;
	private View likedislike;
	public static ChannelInfo channelInfo=null;
	public static ChannelDetail channelDetail=null;
	public static ArrayList<HashMap<String,Object>> commentsHMArrList=null;
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	//Navigation Drawer title
	private CharSequence mDrawerTitle;

	//Used to store Application title
	private CharSequence mTitle;

	
	private MenuItemRowViewHolder menuItemRowHolder;
	private LayoutInflater mInflater;
	
	private AlertDialog.Builder builder;
	private AlertDialog alertDialog;
	private LayoutInflater logoutLayoutInflater;
	private View layout,swiping_tabs,vitemeo_controls,landscape_views;
	//Either from Login or Sign Up to inject user name.
	public static Context fromContext;
	private OptionsFragmentPagerAdapter optionsFragmentPagerAdapter;
	private Fragment currentTabbedFragment;
	private View bottomOptionsView;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		Vitamio.isInitialized(getApplicationContext());
	    intializeViews();
		setListeners();
		setData();
		initializeOptionsViews();
		
		//Enabling Action Bar application icon and behaving it as toggle button
		//getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		ActionBar actionBar=getActionBar();
		actionBar.hide();
		/*LayoutInflater inflater=getLayoutInflater();
        View actionBarLay=inflater.inflate(R.layout.action_bar_header,null);
        menuIcon=(ImageView)actionBarLay.findViewById(R.id.icon_three_liner);
		getActionBar().setCustomView(actionBarLay);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		menuIcon.setOnClickListener(this);*/

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
			//displayView(0);
		}
	  }
	
	  //Method to initialize the Views of XML file.
	  private void intializeViews(){
		Log.i(TAG,"intializeViews() Entering.");
		menuIcon=(ImageView)findViewById(R.id.icon_three_liner);
		mVideoView=(VideoView)findViewById(R.id.buffer);
	    pb=(ProgressBar)findViewById(R.id.probar);
	    downloadRateView=(TextView)findViewById(R.id.download_rate);
	    loadRateView=(TextView)findViewById(R.id.load_rate);
	    dislikes_txt_view=(TextView)findViewById(R.id.dislikes_txt_view);
	    likes_txt_view=(TextView)findViewById(R.id.likes_txt_view);
	    channel_name_txt_view=(TextView)findViewById(R.id.channel_name_txt_view);
	    channel_Type_txt_view=(TextView)findViewById(R.id.channel_Type_txt_view);
		icon_play=(ImageView)findViewById(R.id.icon_play);
		icon_pause=(ImageView)findViewById(R.id.icon_pause);
		icon_volume=(ImageView)findViewById(R.id.icon_volume);
		icon_maximize=(ImageView)findViewById(R.id.icon_maximize);
		icon_minimize=(ImageView)findViewById(R.id.icon_minimize);
		icon_like=(ImageView)findViewById(R.id.icon_like);
		icon_dislike=(ImageView)findViewById(R.id.icon_dislike);
		icon_next=(ImageView)findViewById(R.id.icon_next);
		icon_previous=(ImageView)findViewById(R.id.icon_previous);
		mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerList=(ListView)findViewById(R.id.list_slidermenu);
		likedislike=(View)findViewById(R.id.likedislike);
        icon_facebook=(ImageView)likedislike.findViewById(R.id.icon_facebook);
        icon_google=(ImageView)likedislike.findViewById(R.id.icon_google);
        icon_twitter=(ImageView)likedislike.findViewById(R.id.icon_twitter);
        facebook_count_txt_view=(TextView)likedislike.findViewById(R.id.facebook_count_txt_view);
        google_count_txt_view=(TextView)likedislike.findViewById(R.id.google_count_txt_view);
        twitter_count_txt_view=(TextView)likedislike.findViewById(R.id.twitter_count_txt_view);
		swiping_tabs=(View)findViewById(R.id.swiping_tabs);
		vitemeo_controls=(View)findViewById(R.id.vitemeo_controls);
		bottomOptionsView=(View)findViewById(R.id.list_row_options);
		landscape_views=(View)findViewById(R.id.landscape_views);
		rel_controls=(RelativeLayout)findViewById(R.id.rel_controls);
		rel_videoview=(RelativeLayout)findViewById(R.id.rel_videoview);
		rel_like=(RelativeLayout)bottomOptionsView.findViewById(R.id.rel_like);
		rel_dislike=(RelativeLayout)bottomOptionsView.findViewById(R.id.rel_dislike);
		rel_edit=(RelativeLayout)bottomOptionsView.findViewById(R.id.rel_edit);
		rel_delete=(RelativeLayout)bottomOptionsView.findViewById(R.id.rel_delete);
		rel_profile=(RelativeLayout)bottomOptionsView.findViewById(R.id.rel_profile);
		rel_cancel=(RelativeLayout)bottomOptionsView.findViewById(R.id.rel_cancel);
		/** Getting a reference to the ViewPager defined the layout file */
		Log.i(TAG,"intializeViews() Exiting.");
	  }
	  
	  //Initializes Options
	  private void initializeOptionsViews(){
		Log.i(TAG,"initializeOptionsViews() Entering.");
		/** Getting a reference to the ViewPager defined the layout file */
		prepareCommentsHMArrayList();
		CommentsFragment.commentsArrList=null;
		CommentsFragment.commentsArrList=commentsHMArrList;
		PagerTabStrip pagerTabStrip=(PagerTabStrip)findViewById(R.id.pager_tab_strip);
		pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.swipe_tabbar_indicator));
        ViewPager pager=(ViewPager)findViewById(R.id.pager);
        /** Getting fragment manager */
        FragmentManager fm=getSupportFragmentManager();
        /** Instantiating FragmentPagerAdapter */
        //OptionsFragmentPagerAdapter optionsFragmentPagerAdapter=new OptionsFragmentPagerAdapter(fm);
        optionsFragmentPagerAdapter=new OptionsFragmentPagerAdapter(fm);
        /** Setting the pagerAdapter to the pager object */
        pager.setAdapter(optionsFragmentPagerAdapter);
        
        //Used to detect the Fragment when user swipes or switches between the tabs.
        pager.addOnPageChangeListener(this);
        
		 Log.i(TAG,"initializeOptionsViews() Exiting.");
	  }

	  //Method to set the Listeners to the Views of XML file.
	  private void setListeners(){
		Log.i(TAG,"setListeners() Entering.");
		mVideoView.setOnInfoListener(this);
		menuIcon.setOnClickListener(this);
	    mVideoView.setOnBufferingUpdateListener(this);
	    mVideoView.setOnPreparedListener(this);
	    mVideoView.setOnCompletionListener(this);
	    mVideoView.setOnErrorListener(this);
		icon_play.setOnClickListener(this);
		icon_pause.setOnClickListener(this);
		icon_volume.setOnClickListener(this);
		icon_maximize.setOnClickListener(this);
		icon_minimize.setOnClickListener(this);
		icon_like.setOnClickListener(this);
		icon_dislike.setOnClickListener(this);
		icon_next.setOnClickListener(this);
		icon_previous.setOnClickListener(this);
		rel_like.setOnClickListener(this);
		rel_dislike.setOnClickListener(this);
		rel_edit.setOnClickListener(this);
		rel_delete.setOnClickListener(this);
		rel_profile.setOnClickListener(this);
		rel_cancel.setOnClickListener(this);
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		Log.i(TAG,"setListeners() Exiting");
	  }
	  
	  //Starts Live Streaming.
	  private void startLiveStreaming(String path){
		 Log.i(TAG,"startLiveStreaming() Entering.");
		 if(path==null || path.equals("")){
		      Log.e(TAG,"path is null or empty");
		      //Toast.makeText(this,"Unable to fetch next Video URL",Toast.LENGTH_LONG).show();
		      displayToast("Unable to fetch next Video URL");
		      return;
		  }else{
		      channel_name_txt_view.setText(channelDetail.getChannel_name());
			  channel_Type_txt_view.setText(channelDetail.getDescription());
			  likes_txt_view.setText(channelInfo.getLikecount());
			  dislikes_txt_view.setText(channelInfo.getDislikecount());
			  //Update Comments as they are specific to Channel.
			  //updateFragmentsUI(0);
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
				//likedislike.setVisibility(View.GONE);
		        //swiping_tabs.setVisibility(View.GONE);
				break;
			case R.id.icon_minimize:
				Log.i(TAG,"Minimize Icon clicked");
				//likedislike.setVisibility(View.VISIBLE);
		        //swiping_tabs.setVisibility(View.VISIBLE);
				break;
			case R.id.icon_like:
				Log.i(TAG,"Like Icon clicked");
				checkIfChannelAlreadyLikeOrDislike(true);
				break;
			case R.id.icon_dislike:
				Log.i(TAG,"Dislike Icon clicked");
				checkIfChannelAlreadyLikeOrDislike(false);
				break;
			case R.id.icon_next:
				Log.i(TAG,"Next Icon clicked");
				mVideoView.clearFocus();
				int currentChannelIDNext=0;
				currentChannelIDNext=Integer.parseInt(channelDetail.getChannel_id())+1;
				//Toast.makeText(this,"Current Channel ID : "+channelDetail.getChannel_id(),Toast.LENGTH_LONG).show();
				displayToast("Current Channel ID : "+channelDetail.getChannel_id());
				sendNextChannelInfoReq(Integer.toString(currentChannelIDNext));
				break;
			case R.id.icon_previous:
				Log.i(TAG,"Previous Icon clicked");
				mVideoView.clearFocus();
				int currentChannelIDPrev=1;
				currentChannelIDPrev=Integer.parseInt(channelDetail.getChannel_id())-1;
				if(currentChannelIDPrev<=0){
				   currentChannelIDPrev=Integer.parseInt(channelDetail.getChannel_id())+1;
				}
				//Toast.makeText(this,"Current Channel ID : "+channelDetail.getChannel_id(),Toast.LENGTH_LONG).show();
				displayToast("Current Channel ID : "+channelDetail.getChannel_id());
				sendNextChannelInfoReq(Integer.toString(currentChannelIDPrev));
				break;
			case R.id.icon_three_liner:
				boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
				if(drawerOpen){
					mDrawerLayout.closeDrawer(mDrawerList);
				}else{
					if(bottomOptionsView.getVisibility()==View.VISIBLE){
					   slideOutOptionsView();
					}
					mDrawerLayout.openDrawer(mDrawerList);
				}
				break;
			case R.id.rel_like:
				Log.v(TAG,"Rel Like clicked");
				slideOutOptionsView();
				break;
			case R.id.rel_dislike:
				Log.v(TAG,"Rel Dislike clicked");
				slideOutOptionsView();
				break;
			case R.id.rel_edit:
				Log.v(TAG,"Rel Edit clicked");
				slideOutOptionsView();
				break;
			case R.id.rel_delete:
				Log.v(TAG,"Rel Delete clicked");
				slideOutOptionsView();
				break;
			case R.id.rel_profile:
				Log.v(TAG,"Rel Profile clicked");
				slideOutOptionsView();
				break;
			case R.id.rel_cancel:
				Log.v(TAG,"Rel Cancel clicked");
				slideOutOptionsView();
				break;
			default:
				Log.e(TAG,"In Default option");
				break;
		}
		Log.i(TAG,"onClick() Exiting");
	}
	  
	//Populates Menu Items ListView Data.
	private void setData(){
	  Log.i(TAG,"setMenuItemData() Entering.");
	  
	  setChannelInfoData();
		
	  mTitle = mDrawerTitle = getTitle();
	  
	  ArrayList<HashMap<String,Object>> menuItemsList=new ArrayList<HashMap<String,Object>>();
	  HashMap<String,Object> menuItemHM=null;
	  
	  menuItemHM=new HashMap<String,Object>();
	  
	  if(fromContext instanceof LoginScreen){
		 menuItemHM.put(ReqResNodes.MENUITEMNAME,LoginParser.getUserName());
	  }else{
		 menuItemHM.put(ReqResNodes.MENUITEMNAME,SignUpParser.getUserName()); 
	  }
	  
	  menuItemsList.add(menuItemHM);
	  
	  /*menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_CHANNELS);
	  menuItemsList.add(menuItemHM);*/
	  
	  menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_CATEGORIES);
	  menuItemsList.add(menuItemHM);
	  
	  menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_LANGUAGE);
	  menuItemsList.add(menuItemHM);
	  
	  /*menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_FRIENDS);
	  menuItemsList.add(menuItemHM);
	  
	  menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_ACTIVITIES);
	  menuItemsList.add(menuItemHM);*/
	  
	  menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_SHARE);
	  menuItemsList.add(menuItemHM);
	  
	  menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_REMINDER);
	  menuItemsList.add(menuItemHM);
	  
	  menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_ABOUT);
	  menuItemsList.add(menuItemHM);
	  
	  /*menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_ADVERTISE);
	  menuItemsList.add(menuItemHM);
	  
	  menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_TERMS);
	  menuItemsList.add(menuItemHM);*/
	  
	  menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_PRIVACYPOLICY);
	  menuItemsList.add(menuItemHM);
	  
	  menuItemHM=new HashMap<String,Object>();
	  menuItemHM.put(ReqResNodes.MENUITEMNAME,ReqResNodes.MENUITEM_PROFILE);
	  menuItemsList.add(menuItemHM);
	  
	  MenuItemAdapter menuItemListAdapter=new MenuItemAdapter(this,menuItemsList,R.layout.categoryrow,new String[]{},new int[]{});
	  mDrawerList.setAdapter(menuItemListAdapter);
	  mDrawerList.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent,View view,int position,long id){
			HashMap<String,Object> menuItemRowHM=(HashMap<String, Object>)mDrawerList.getItemAtPosition(position);
			String menuItemName=menuItemRowHM.get(ReqResNodes.MENUITEMNAME).toString();
			Log.d(TAG,"menuItemName : "+menuItemName);
			/*if(menuItemName.equals(ReqResNodes.MENUITEM_CHANNELS)){
				mDrawerLayout.closeDrawer(mDrawerList);
				sendChannelsReq();
            }else */if(menuItemName.equals(ReqResNodes.MENUITEM_CATEGORIES)){
            	mDrawerLayout.closeDrawer(mDrawerList);
				sendCategoriesReq();
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_LANGUAGE)){
            	mDrawerLayout.closeDrawer(mDrawerList);
				sendLanguagesReq();
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_SHARE)){
            	mDrawerLayout.closeDrawer(mDrawerList);
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_REMINDER)){
            	mDrawerLayout.closeDrawer(mDrawerList);
            	Intent reminderIntent=new Intent(PlayerScreen.this,ReminderScreen.class);
            	startActivity(reminderIntent);
          	    //slide from right to left
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }/*else if(menuItemName.equals(ReqResNodes.MENUITEM_FRIENDS)){
            	mDrawerLayout.closeDrawer(mDrawerList);
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_ACTIVITIES)){
            	mDrawerLayout.closeDrawer(mDrawerList);           	
            }*/else if(menuItemName.equals(ReqResNodes.MENUITEM_ABOUT)){
            	mDrawerLayout.closeDrawer(mDrawerList);
            	Intent aboutUsIntent=new Intent(PlayerScreen.this,AboutUsScreen.class);
            	startActivity(aboutUsIntent);
          	    //slide from right to left
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }/*else if(menuItemName.equals(ReqResNodes.MENUITEM_ADVERTISE)){            	
            	mDrawerLayout.closeDrawer(mDrawerList);
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_TERMS)){
            	mDrawerLayout.closeDrawer(mDrawerList);
            }*/else if(menuItemName.equals(ReqResNodes.MENUITEM_PRIVACYPOLICY)){
            	mDrawerLayout.closeDrawer(mDrawerList);
            	Intent termsPrivacyPolicyIntent=new Intent(PlayerScreen.this,TermsPrivacyPolicyScreen.class);
            	startActivity(termsPrivacyPolicyIntent);
          	    //slide from right to left
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_PROFILE)){
            	mDrawerLayout.closeDrawer(mDrawerList);
            	Intent profileIntent=new Intent(PlayerScreen.this,ProfileScreen.class);
            	startActivity(profileIntent);
          	    //slide from right to left
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            	//sendProfileReq(LoginParser.getUserID());
            }		 
		 }
	   });
	  Log.i(TAG,"setMenuItemData() Exiting.");
	}

	//Inner Class to make smooth swiping of list view.
	public class MenuItemAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> menuItemRowArrList;

	   public MenuItemAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context, items, resource, from, to);
		   menuItemRowArrList=(ArrayList<HashMap<String,Object>>)items;
		   mInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView = mInflater.inflate(R.layout.menuitemrow,null);
        	  menuItemRowHolder=new MenuItemRowViewHolder();
        	  menuItemRowHolder.menu_item_icon=(ImageView)convertView.findViewById(R.id.menu_item_icon);
        	  menuItemRowHolder.menu_item_name=(TextView)convertView.findViewById(R.id.menu_item_name);
        	  menuItemRowHolder.logout_btn=(Button)convertView.findViewById(R.id.logout_btn);
        	  menuItemRowHolder.menu_items_divider=(View)convertView.findViewById(R.id.menu_items_divider);
        	  menuItemRowHolder.vertical_line=(View)convertView.findViewById(R.id.vertical_line);
        	  convertView.setTag(menuItemRowHolder);
            }else{
            	menuItemRowHolder=(MenuItemRowViewHolder)convertView.getTag();
            }
            HashMap<String,Object> mItemRowHM=(HashMap<String,Object>)menuItemRowArrList.get(position);
            String menuItemName=mItemRowHM.get(ReqResNodes.MENUITEMNAME).toString();
            /*if(menuItemName.equals(ReqResNodes.MENUITEM_CHANNELS)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_channels));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.GONE);
            }else */if(menuItemName.equals(ReqResNodes.MENUITEM_CATEGORIES)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_categories));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_LANGUAGE)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_language));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
            }/*else if(menuItemName.equals(ReqResNodes.MENUITEM_FRIENDS)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_friends));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.GONE);
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_ACTIVITIES)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_activities));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
            }*/else if(menuItemName.equals(ReqResNodes.MENUITEM_SHARE)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_share));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_REMINDER)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_calender));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_ABOUT)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_about));
                menuItemRowHolder.menu_item_name.setText(menuItemName);   
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
            }/*else if(menuItemName.equals(ReqResNodes.MENUITEM_ADVERTISE)){            	
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_advertise));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_TERMS)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_terms));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
            }*/else if(menuItemName.equals(ReqResNodes.MENUITEM_PRIVACYPOLICY)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_privacy));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
            //}else if(LoginParser.getUserName()!=null && !LoginParser.getUserName().equals("")){
            }else if(menuItemName.equals(ReqResNodes.MENUITEM_PROFILE)){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile));
                menuItemRowHolder.menu_item_name.setText(menuItemName);
                menuItemRowHolder.logout_btn.setVisibility(View.INVISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.GONE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
            }else if(fromContext instanceof LoginScreen){
            	Log.v(TAG,"fromContext LoginParser");
            	if(menuItemName.equals(LoginParser.getUserName())){
            		menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile));
                    menuItemRowHolder.menu_item_name.setText(LoginParser.getUserName());
                    menuItemRowHolder.logout_btn.setVisibility(View.VISIBLE);
                    menuItemRowHolder.vertical_line.setVisibility(View.VISIBLE);
                    menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
                    menuItemRowHolder.logout_btn.setOnClickListener(new OnClickListener(){
    					@Override
    					public void onClick(View v){
    						mDrawerLayout.closeDrawer(mDrawerList);
    						//startActivity(new Intent(PlayerScreen.this,LoginScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    						displayDialog(PlayerScreen.this,1);
    					}
    				});
            	}
            //}else if(SignUpParser.getUserName()!=null || !SignUpParser.getUserName().equals("")){
            }else if(fromContext instanceof SignUpScreen){
            	Log.v(TAG,"fromContext SignUpParser");
            	if(menuItemName.equals(SignUpParser.getUserName())){
            		menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile));
                    menuItemRowHolder.menu_item_name.setText(SignUpParser.getUserName());
                    menuItemRowHolder.logout_btn.setVisibility(View.VISIBLE);
                    menuItemRowHolder.vertical_line.setVisibility(View.VISIBLE);
                    menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
                    menuItemRowHolder.logout_btn.setOnClickListener(new OnClickListener(){
    					@Override
    					public void onClick(View v){
    						mDrawerLayout.closeDrawer(mDrawerList);
    						//startActivity(new Intent(PlayerScreen.this,LoginScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    						displayDialog(PlayerScreen.this,1);
    					}
    				});
             	}
            }
            else if(menuItemName.equals(LoginParser.getUserName())){
            	menuItemRowHolder.menu_item_icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile));
                menuItemRowHolder.menu_item_name.setText(LoginParser.getUserName());
                menuItemRowHolder.logout_btn.setVisibility(View.VISIBLE);
                menuItemRowHolder.vertical_line.setVisibility(View.VISIBLE);
                menuItemRowHolder.menu_items_divider.setVisibility(View.VISIBLE);
                menuItemRowHolder.logout_btn.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v){
						mDrawerLayout.closeDrawer(mDrawerList);
						//startActivity(new Intent(PlayerScreen.this,LoginScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
						displayDialog(PlayerScreen.this,1);
					}
				});
            }
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class MenuItemRowViewHolder{
    	    ImageView menu_item_icon;
    	    TextView menu_item_name;
    	    Button logout_btn;
    	    View menu_items_divider,vertical_line;
        }
	        
	}
	 
	 private void setChannelInfoData(){
		Log.i(TAG,"setChannelInfoData() Entering.");
		String path=channelDetail.getRtmp_link();
		startLiveStreaming(path);
		/*channel_name_txt_view.setText(channelDetail.getChannel_name());
		channel_Type_txt_view.setText(channelDetail.getDescription());
		likes_txt_view.setText(channelInfo.getLikecount());
		dislikes_txt_view.setText(channelInfo.getDislikecount());*/
		Log.i(TAG,"setChannelInfoData() Exiting.");
	 }
	 
	 //Method to prepare Comments in the form of HashMaps in ArrayList.
	 private void prepareCommentsHMArrayList(){
		Log.i(TAG,"prepareCommentsHMArrayList() Entering.");
		commentsHMArrList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> commentHM=null;
		List<Comment> commentsList=channelInfo.getCommentsList();
		if(commentsList!=null && commentsList.size()>0){
		  Log.v(TAG,"Comments Available for this Channel");
		  for(int c=0;c<commentsList.size();c++){
			 Comment comment=commentsList.get(c);
			 commentHM=new HashMap<String,Object>();
			 commentHM.put(ReqResNodes.COMMENT_ID,comment.getComment_id());
			 commentHM.put(ReqResNodes.USERID,comment.getUserid());
			 commentHM.put(ReqResNodes.CHANNEL_ID,comment.getChannel_id());
			 commentHM.put(ReqResNodes.COMMENT,comment.getComment());
			 commentHM.put(ReqResNodes.PREFERENCE_ID,comment.getPreference_type());
			 commentHM.put(ReqResNodes.HIDE_COMMENT,comment.getHide_comment());
			 commentHM.put(ReqResNodes.USERNAME,comment.getUsername());
			 long days=GlobalUtil.getDaysFromMillis(Long.parseLong(comment.getDt_created()));
			 Log.v(TAG,"milliseconds : "+comment.getDt_created());
			 Log.v(TAG,"milliseconds in days : "+days+" days ago");
			 commentHM.put(ReqResNodes.DT_CREATED,days +" days ago");
			 
			 //Check if hide_comment is 1 then show else hide.
			 if(comment.getHide_comment().equals("1")){
				commentsHMArrList.add(commentHM); 
			 }
		  }
		}else{
		  Log.v(TAG,"Comments not Available for this Channel");
		}
		Log.i(TAG,"prepareCommentsHMArrayList() Exiting.");
		//return commentsHMArrList;
	 }
	
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements ListView.OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id){
			//display view for selected nav drawer item
			//displayView(position);
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
	    //Checks the orientation of the screen
	    if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
	        //Toast.makeText(this,"Landscape",Toast.LENGTH_SHORT).show();
	        likedislike.setVisibility(View.GONE);
	        swiping_tabs.setVisibility(View.GONE);
	        landscape_views.setVisibility(View.VISIBLE);
	        //icon_maximize.setVisibility(View.GONE);
	        //icon_minimize.setVisibility(View.GONE);
	        /*swiping_tabs.setVisibility(View.GONE);
	        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)likedislike.getLayoutParams();
	        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	        likedislike.setLayoutParams(params);*/ //causes layout update
	    }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
	        //Toast.makeText(this,"Portrait",Toast.LENGTH_SHORT).show();
	        likedislike.setVisibility(View.VISIBLE);
	        swiping_tabs.setVisibility(View.VISIBLE);
	        landscape_views.setVisibility(View.GONE);
	        //icon_maximize.setVisibility(View.VISIBLE);
	        //icon_minimize.setVisibility(View.VISIBLE);
	    }
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
		//Toast.makeText(this,"Unable to stream video",Toast.LENGTH_LONG).show();
		displayToast("Unable to stream video");
	    Log.i(TAG,"onError() Exiting.");
	  	return false;
	  }
	  
	//Removes Horizontal Scrollbars in HorizontalScrollView programmatically.
	@SuppressWarnings("unused")
	private void disableHorizontalScrollBars(){
	   //view.setVerticalScrollBarEnabled(false); 
	   //view.setHorizontalScrollBarEnabled(false);
	}
	
	
	//Method to check whether the Current Channel is already liked.
	private void checkIfChannelAlreadyLikeOrDislike(boolean isLikeReq){
		Log.i(TAG,"checkIfChannelAlreadyLikeOrDislike() Entering.");
		
		
		if(isLikeReq){
			Log.v(TAG,"Sending Like Request");
			sendLikeChannelReq(LoginParser.getUserID(),channelDetail.getChannel_id());
		  }else{
			Log.v(TAG,"sending DisLike Request");
			sendDisLikeChannelReq(LoginParser.getUserID(),channelDetail.getChannel_id());
		  }
		
		/*int alreadyLikeValue=Integer.parseInt(channelInfo.getAlreadylike());
		Log.v(TAG,"Channel AlreadyLike value : "+alreadyLikeValue);
		
		if(alreadyLikeValue==GlobalConsts.NEUTRAL){
		  Log.v(TAG,"User neither liked nor disliked previous");
		  if(isLikeReq){
			Log.v(TAG,"Sending Like Request");
			sendLikeChannelReq(LoginParser.getUserID(),channelDetail.getChannel_id());
		  }else{
			Log.v(TAG,"sending DisLike Request");
			sendDisLikeChannelReq(LoginParser.getUserID(),channelDetail.getChannel_id());
		  }
		}else if(alreadyLikeValue==GlobalConsts.ALREADYLIKE && isLikeReq){
			Log.e(TAG,"User already liked and trying to send Like Request again");
			displayToast("Already liked, cannot like it again");
		}else if(alreadyLikeValue==GlobalConsts.ALREADYDISLIKE && !isLikeReq){
			Log.e(TAG,"User already disliked and trying to send DisLike Request again");
			displayToast("Already disliked, cannot dislike it again");
		}else if(alreadyLikeValue==GlobalConsts.ALREADYLIKE && !isLikeReq){
			Log.v(TAG,"User already liked and trying to send DisLike Request");
			sendDisLikeChannelReq(LoginParser.getUserID(),channelDetail.getChannel_id());
		}else if(alreadyLikeValue==GlobalConsts.ALREADYDISLIKE && isLikeReq){
			Log.v(TAG,"User already disliked and trying to send Like Request");
			sendLikeChannelReq(LoginParser.getUserID(),channelDetail.getChannel_id());
		}*/
		
		Log.i(TAG,"checkIfChannelAlreadyLikeOrDislike() Exiting.");
	}
	  
	//Method to send Next Channel API request.
	private void sendNextChannelInfoReq(String channelNo){
		Log.i(TAG,"sendNextChannelInfoReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);
		req.nextChannelRequest(PlayerScreen.this,new ResponseHandler(),channelNo);
		
		Log.i(TAG,"sendNextChannelInfoReq() Exiting.");
	}
	
	//Methods handles the Next ChannelInfo response from Server.
	public void nextChannelProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"nextChannelProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "nextChannelProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e("proceedUI", "Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			displayToast("Exception occurred while fetching NextChannel");
		}else{
			ChannelInfo channelInfo=ChannelInfoParser.getChannelInfoParsedResponse(result);
			if(channelInfo!=null){
				Log.v(TAG,"ChannelInfo response parsing success.");
				if(channelInfo.getIsSuccess().equals(ErrorCodes.ISFAILURE)){
				   Log.e(TAG,"isSuccess Value : "+channelInfo.getIsSuccess());
				   Log.e(TAG,"Error Message : "+channelInfo.getMessage());
				   //page_level_error_txt_view.setText(channelInfo.getMessage());
				   //page_level_error_txt_view.setVisibility(View.VISIBLE);
				   displayToast("NextChannel details unavailable");
				}else{
					Log.v(TAG,"ChannelInfo fetched Successfully. Please find the below details.");
					Log.v(TAG,"ChannelDetail Details.");
					ChannelDetail channelDetail=channelInfo.getChannelDetail();
					String channelID=channelDetail.getChannel_id();
					String channelCode=channelDetail.getChannel_code();
					String categoryID=channelDetail.getCategory_id();
					String channelName=channelDetail.getChannel_name();
					String languageID=channelDetail.getLanguage_id();
					String description=channelDetail.getDescription();
					String rtmpLink=channelDetail.getRtmp_link();
					String followers=channelDetail.getFollowers();
					String views=channelDetail.getViews();
					String displayChannel=channelDetail.getDisplay_channel();
					String defaultChannel=channelDetail.getDefault_channel();
					String timeWatched=channelDetail.getTime_watched();
					String thumbnail=channelDetail.getThumbnail();
					
					Log.d(TAG,"channelID : "+channelID);
					Log.d(TAG,"channelCode : "+channelCode);
					Log.d(TAG,"categoryID : "+categoryID);
					Log.d(TAG,"channelName : "+channelName);
					Log.d(TAG,"languageID : "+languageID);
					Log.d(TAG,"description : "+description);
					Log.d(TAG,"rtmpLink : "+rtmpLink);
					Log.d(TAG,"followers : "+followers);
					Log.d(TAG,"views : "+views);
					Log.d(TAG,"displayChannel : "+displayChannel);
					Log.d(TAG,"defaultChannel : "+defaultChannel);
					Log.d(TAG,"timeWatched : "+timeWatched);
					Log.d(TAG,"thumbnail : "+thumbnail);
					
					Log.v(TAG,"Comment Details.");
					List<Comment> commentList=channelInfo.getCommentsList();
					for(int c=0;c<commentList.size();c++){
					   Log.v(TAG,"Iteration : "+c);
					   Comment comment=commentList.get(c);
					   String commentID=comment.getComment_id();
					   String userID=comment.getUserid();
					   String channelID1=comment.getChannel_id();
					   String com=comment.getComment();
					   String prefType=comment.getPreference_type();
					   String hideComment=comment.getHide_comment();
					   String dateCreated=comment.getDt_created();
					   String username=comment.getUsername();
					   Log.d(TAG,"comment_id : "+commentID);
					   Log.d(TAG,"userid : "+userID);
					   Log.d(TAG,"channel_id : "+channelID1);
					   Log.d(TAG,"comment : "+com);
					   Log.d(TAG,"preference_type : "+prefType);
					   Log.d(TAG,"hide_comment : "+hideComment);
					   Log.d(TAG,"dt_created : "+dateCreated);
					   Log.d(TAG,"username : "+username);
					}
					if(rtmpLink==null || rtmpLink.equals("")){
					   Log.e(TAG,"path is null or empty");
					   //Toast.makeText(this,"Unable to fetch next Video URL",Toast.LENGTH_LONG).show();
					   displayToast("Unable to fetch next Video URL");
					   return;
					 }else{
					   PlayerScreen.channelInfo=null;
					   PlayerScreen.channelInfo=channelInfo;
					   PlayerScreen.channelDetail=null;
					   PlayerScreen.channelDetail=channelDetail;
					   //Loads new Channel from API call
					   setChannelInfoData();
					   updateFragmentsUI(0);
					 }
				}
			}else{
				Log.e(TAG,"ChannelInfo response parsing failed.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				displayToast("Parsing error while fetching NextChannel");
			}
		}
		
		Log.i(TAG,"nextChannelProceedUI() Exiting.");
	}
	  
	//Sends Channels API Request.
	private void sendChannelsReq(){
		Log.i(TAG,"sendChannelsReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.channelsListRequest(PlayerScreen.this,new ResponseHandler());
		
		Log.i(TAG,"sendChannelsReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void channelsProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"channelsProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "channelsProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "channelsProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			displayToast("Exception occurred while fetching Channels List");
		}else{
			List<HashMap<String,Object>> channelsHMList=ChannelsListParser.getChannelsListParsedResponse(result);
			if(channelsHMList!=null&& !channelsHMList.isEmpty()){
				Log.v(TAG,"Channels Available.");
				ChannelsScreen.channelsList.clear();
				ChannelsScreen.channelsList=channelsHMList;
				startActivity(new Intent(PlayerScreen.this,ChannelsScreen.class));
				overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}else{
				Log.e(TAG,"Channels not Available.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				displayToast("Channels List unavailable");
			}
		}
		
		Log.i(TAG,"channelsProceedUI() Exiting.");
	}

	//Sends Channels API Request.
	private void sendCategoriesReq(){
		Log.i(TAG,"sendCategoriesReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.categoriesListRequest(PlayerScreen.this,new ResponseHandler());
		
		Log.i(TAG,"sendCategoriesReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void categoriesProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"categoriesProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			Log.v(TAG, "categoriesProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "channelsProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			displayToast("Exception occurred while fetching Categories");
		}else{
			List<HashMap<String,Object>> categoriesHMList=CategoryListParser.getCategoriesListParsedResponse(result);
			if(categoriesHMList!=null&& !categoriesHMList.isEmpty()){
				Log.v(TAG,"Categories Available.");
				CategoriesScreen.categoriesList.clear();
				CategoriesScreen.categoriesList=categoriesHMList;
				startActivity(new Intent(PlayerScreen.this,CategoriesScreen.class));
				overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}else{
				Log.e(TAG,"Categories not Available.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				displayToast("Categories unavailable");
			}
		}
		
		Log.i(TAG,"categoriesProceedUI() Exiting.");
	}
	
	//Sends Languages API Request.
	private void sendLanguagesReq(){
		Log.i(TAG,"sendLanguagesReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.languagesListRequest(PlayerScreen.this,new ResponseHandler());
		
		Log.i(TAG,"sendLanguagesReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void languageProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"languageProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()){
			Log.v(TAG, "languageProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "languageProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			displayToast("Exception occurred while fetching Languages");
		}else{
			List<HashMap<String,Object>> languagesHMList=LanguageListParser.getLanguagesListParsedResponse(result);
			if(languagesHMList!=null&& !languagesHMList.isEmpty()){
				Log.v(TAG,"Languages Available.");
				LanguagesScreen.languagesList.clear();
				LanguagesScreen.languagesList=languagesHMList;
				startActivity(new Intent(PlayerScreen.this,LanguagesScreen.class));
				overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}else{
				Log.e(TAG,"Languages not Available.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				displayToast("Languages unavailable");
			}
		}
		
		Log.i(TAG,"languageProceedUI() Exiting.");
	}
	
	//Method to send Profile API request.
	private void sendProfileReq(String id){
		Log.i(TAG,"sendProfileReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.profileRequest(PlayerScreen.this,new ResponseHandler(),id);
		
		Log.i(TAG,"sendProfileReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void profileProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"profileProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()) {
			   Log.v(TAG,"profileProceedUI progress dialog dismissing..");
			   CustomProgressDialog.Dismiss();
			}
			if(result.equalsIgnoreCase("Exception")){
				Log.e("profileProceedUI", "Exception Case");
				//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
			}else{
				Profile profile=ProfileParser.getProfileParsedResponse(result);
				if(profile!=null){
					Log.v(TAG,"Profile response parsing success.");
					if(profile.getIsSuccess().equals(ErrorCodes.ISFAILURE)){
					   Log.e(TAG,"isSuccess Value : "+profile.getIsSuccess());
					   Log.e(TAG,"Error Message : "+profile.getMessage());
					   //page_level_error_txt_view.setText(login.getMessage());
					   //page_level_error_txt_view.setVisibility(View.VISIBLE);
					}else{
						Log.v(TAG,"Profile details fetched Successfully. Please find the below details.");
						Log.d(TAG,"isSuccess : "+profile.getIsSuccess());
						Log.d(TAG,"userid : "+profile.getUserid());
						Log.d(TAG,"email : "+profile.getEmailid());
						Intent profileIntent=new Intent(PlayerScreen.this,ProfileScreen.class);
		            	startActivity(profileIntent);
		          	    //slide from right to left
		                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
					}
				}else{
					Log.e(TAG,"login response parsing failed.");
					//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
					//page_level_error_txt_view.setVisibility(View.VISIBLE);
				}
			}
		
		Log.i(TAG,"profileProceedUI() Exiting.");
	}

	//Sends Like Channel API Request.
	private void sendLikeChannelReq(String userID,String channelID){
		Log.i(TAG,"sendLikeChannelReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.likeChannelRequest(PlayerScreen.this,new ResponseHandler(),userID,channelID);
		
		Log.i(TAG,"sendLikeChannelReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void likeChannelProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"likeChannelProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()){
			Log.v(TAG, "likeChannelProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "likeChannelProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			displayToast("Exception occurred while Liking Channel");
		}else{
			LikeChannel likeChannel=LikeChannelParser.getLikeChannelParsedResponse(result);
			if(likeChannel!=null){
				Log.v(TAG,"Like Channel Successfull.");
				if(likeChannel.getLikecount()!=null && likeChannel.getDislikecount()!=null){
					likes_txt_view.setText(likeChannel.getLikecount());
					dislikes_txt_view.setText(likeChannel.getDislikecount());
				}else{
					Log.e(TAG,"Like and DisLike count is Null");
				}
				//Update AlreadyLike in ChannelInfo Model object.
				//channelInfo.setAlreadylike(Integer.toString(GlobalConsts.ALREADYLIKE));
			}else{
				Log.e(TAG,"Unable to Like Channel.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				displayToast("Channel Already Liked");
			}
		}
		
		Log.i(TAG,"likeChannelProceedUI() Exiting.");
	}

	//Sends DisLike Channel API Request.
	private void sendDisLikeChannelReq(String userID,String channelID){
		Log.i(TAG,"sendDisLikeChannelReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.dislikeChannelRequest(PlayerScreen.this,new ResponseHandler(),userID,channelID);
		
		Log.i(TAG,"sendDisLikeChannelReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void disLikeChannelProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"disLikeChannelProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()){
			Log.v(TAG, "disLikeChannelProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "disLikeChannelProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			displayToast("Exception occurred while Disliking Channel");
		}else{
			DisLikeChannel disLikeChannel=DisLikeChannelParser.getDisLikeChannelParsedResponse(result);
			if(disLikeChannel!=null){
				Log.v(TAG,"DisLike Channel Successfull.");
				if(disLikeChannel.getLikecount()!=null && disLikeChannel.getDislikecount()!=null){
					likes_txt_view.setText(disLikeChannel.getLikecount());
					dislikes_txt_view.setText(disLikeChannel.getDislikecount());
				}else{
					Log.e(TAG,"Like and DisLike count is Null");
				}
				//Update AlreadyLike in ChannelInfo Model object.
				//channelInfo.setAlreadylike(Integer.toString(GlobalConsts.ALREADYDISLIKE));
			}else{
				Log.e(TAG,"Unable to DisLike Channel.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				displayToast("Channel Already Disliked");
			}
		}
		
		Log.i(TAG,"disLikeChannelProceedUI() Exiting.");
	}
	
	//Method to send Favorite API request.
	private void sendFavoriteChannelsReq(){
		Log.i(TAG,"sendFavoriteChannelsReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.favoriteChannelsRequest(PlayerScreen.this,new ResponseHandler(),LoginParser.getUserID());
		
		Log.i(TAG,"sendFavoriteChannelsReq() Exiting.");
	}
	
	//Method handles the response from Server.
	public void favoriteChannelsProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"favoriteChannelsProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()){
			Log.v(TAG,"favoriteChannelsProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG,"favoriteChannelsProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			displayToast("Exception occurred while fetching Favorite Channels List");
		}else{
			FavoriteChannels favorite=FavoriteChannelsParser.getFavoriteChannelsParsedResponse(result);
			if(favorite!=null && favorite.getFavoriteChannelsList()!=null && !favorite.getFavoriteChannelsList().isEmpty()){
				Log.v(TAG,"Favorite Channel List Available.");
				if(currentTabbedFragment!=null)
				  ((FavoritesFragment)currentTabbedFragment).refreshFavoritesList(favorite.getFavoriteChannelsList());
				//startActivity(new Intent(PlayerScreen.this,LanguagesScreen.class));
				//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}else{
				Log.e(TAG,"Favorite Channel List not Available.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				if(currentTabbedFragment!=null)
				  ((FavoritesFragment)currentTabbedFragment).refreshFavoritesList(null);
			}
		}
		
		Log.i(TAG,"favoriteChannelsProceedUI() Exiting.");
	}
	
	//Sends FriendsList API Request.
	private void sendFriendsListReq(){
		Log.i(TAG,"sendFriendsListReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.friendsListRequest(PlayerScreen.this,new ResponseHandler(),LoginParser.getUserID());
		
		Log.i(TAG,"sendFriendsListReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void friendsListProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"friendsListProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()){
			Log.v(TAG, "friendsListProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "friendsListProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			displayToast("Exception occurred while fetching FriendsList");
		}else{
			Friend friend=FriendsListParser.getFriendsListParsedResponse(result);
			if(friend!=null && friend.getFriendsHMList()!=null && !friend.getFriendsHMList().isEmpty()){
				Log.v(TAG,"Friends List Available.");
				if(currentTabbedFragment!=null)
				   ((FriendsFragment)currentTabbedFragment).refreshFriendsList(friend.getFriendsHMList());
				//startActivity(new Intent(PlayerScreen.this,LanguagesScreen.class));
				//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}else{
				Log.e(TAG,"Friends List not Available.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				if(currentTabbedFragment!=null)
				   ((FriendsFragment)currentTabbedFragment).refreshFriendsList(null);
			}
		}
		
		Log.i(TAG,"friendsListProceedUI() Exiting.");
	}
	
	//Sends ActivitiesList API Request.
	private void sendActivitiesListReq(){
		Log.i(TAG,"sendActivitiesListReq() Entering.");
		
		ReqResHandler req = new ReqResHandler();
		CustomProgressDialog.show(PlayerScreen.this);

		req.activitiesListRequest(PlayerScreen.this,new ResponseHandler(),LoginParser.getUserID());
		
		Log.i(TAG,"sendActivitiesListReq() Exiting.");
	}
	
	//Methods handles the response from Server.
	public void activitiesListProceedUI(String result,AmalluException amalluEx){
		Log.i(TAG,"activitiesListProceedUI() Entering.");
		
		if(CustomProgressDialog.IsShowing()){
			Log.v(TAG,"activitiesListProceedUI progress dialog dismissing..");
			CustomProgressDialog.Dismiss();
		}
		if(result.equalsIgnoreCase("Exception")){
			Log.e(TAG, "activitiesListProceedUI Exception Case");
			//page_level_error_txt_view.setText(getResources().getString(R.string.unable_to_login));
			//page_level_error_txt_view.setVisibility(View.VISIBLE);
			displayToast("Exception occurred while fetching Activity Log");
		}else{
			ActivityLog activityLog=ActivityLogParser.getActivityLogParsedResponse(result);
			if(activityLog!=null && activityLog.getActivityLogHMList()!=null && !activityLog.getActivityLogHMList().isEmpty()){
				Log.v(TAG,"Activity List Available.");
				if(currentTabbedFragment!=null)
				  ((ActivitiesFragment)currentTabbedFragment).refreshActivitiesList(activityLog.getActivityLogHMList());
				//startActivity(new Intent(PlayerScreen.this,LanguagesScreen.class));
				//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}else{
				Log.e(TAG,"Activity List not Available.");
				//page_level_error_txt_view.setText(getResources().getString(R.string.internal_error));
				//page_level_error_txt_view.setVisibility(View.VISIBLE);
				if(currentTabbedFragment!=null)
				  ((ActivitiesFragment)currentTabbedFragment).refreshActivitiesList(activityLog.getActivityLogHMList());
			}
		}
		
		Log.i(TAG,"activitiesListProceedUI() Exiting.");
	}

	private void displayToast(String toastText){
	  Log.i(TAG,"displayToast() Entering.");
	  //Toast.makeText(this,toastText,Toast.LENGTH_LONG).show();
	  Log.i(TAG,"displayToast() Exiting");
	}
	
	public void displayDialog(Context callerContext,int dialogConstant){
		showDialog(dialogConstant);
	}
	
	@Override
	protected Dialog onCreateDialog(int id){
		Log.i(TAG,"onCreateDialog Entering.");
		Log.v(TAG,"id "+id);
		Button yesBtn,noBtn;
		logoutLayoutInflater=(LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		layout=logoutLayoutInflater.inflate(R.layout.logout_alert,(ViewGroup)findViewById(R.id.confirmalert));
		builder=new AlertDialog.Builder(this);
		yesBtn=(Button)layout.findViewById(R.id.yes_btn);
		noBtn=(Button)layout.findViewById(R.id.no_btn);
		yesBtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				alertDialog.dismiss();
				LoginParser.login.setUsername("");
				SignUpParser.signUp.setUsername("");
				Intent intent=new Intent(PlayerScreen.this,LoginScreen.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
			}
		});
		noBtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				alertDialog.dismiss();
			}
		});
		alertDialog=builder.create();
		alertDialog.setCancelable(false);
		alertDialog.setView(layout, 0, 0, 0, 0);
		alertDialog.show();
		return alertDialog;		
	}
	
	//Start: Used for View Page tabs.
	@Override
	public void onPageScrollStateChanged(int arg0){
	  Log.i(TAG,"onPageScrollStateChanged() Entering.");
	  Log.d(TAG,"onPageScrollStateChanged arg0 : "+arg0);
	  Log.i(TAG,"onPageScrollStateChanged() Exiting.");	
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2){
	  Log.i(TAG,"onPageScrolled() Entering.");
	  Log.i(TAG,"onPageScrolled() Exiting.");
	}

	@Override
	public void onPageSelected(int position){
	  Log.i(TAG,"onPageSelected() Entering.");
	  Log.d(TAG,"onPageSelected Position : "+position);
	  updateFragmentsUI(position);
	  Log.i(TAG,"onPageSelected onPageSelected() Exiting.");
	}
	//End: Used for View Page tabs.
	
	//Generic method to Control all the Fragments UI.
	private void updateFragmentsUI(int position){
	  Log.i(TAG,"updateFragmentsUI() Entering.");
	  
	  //FragmentManager fragmentManager = getSupportFragmentManager();
	  currentTabbedFragment=optionsFragmentPagerAdapter.getItem(position);

      if(currentTabbedFragment instanceof CommentsFragment){
    	 //prepareCommentsHMArrayList();
    	 //((CommentsFragment)fmt).updateCommentsFragmentUI(commentsHMArrList);
    	  if(currentTabbedFragment!=null){
    		 prepareCommentsHMArrayList();
			 ((CommentsFragment)currentTabbedFragment).refreshCommentsList(commentsHMArrList);
    	  }
      }else if(currentTabbedFragment instanceof TrendingFragment){
    	  
      }else if(currentTabbedFragment instanceof FavoritesFragment){
    	  Log.v(TAG,"currentTabbedFragment instanceof FavoritesFragment");
    	  sendFavoriteChannelsReq();
      }else if(currentTabbedFragment instanceof WatchingFragment){
    	  
      }else if(currentTabbedFragment instanceof FriendsFragment){
    	  Log.v(TAG,"currentTabbedFragment instanceof FriendsFragment");
    	  sendFriendsListReq();
      }else if(currentTabbedFragment instanceof ActivitiesFragment){
    	  Log.v(TAG,"currentTabbedFragment instanceof ActivitiesFragment");
    	  sendActivitiesListReq();
      }
	  
	  Log.i(TAG,"updateFragmentsUI() Exiting.");
	}
	
	//Abstract method implementation to communicate from CommentsFragment to Hosted Activity.
	public void onCommentsItemSelected(HashMap<String,Object> commentRowHM){
		Log.i(TAG,"onCommentsItemSelected() Entering.");
		slideInOptionsView();
		Log.i(TAG,"onCommentsItemSelected() Entering..");
	}
	
	//Animates view from bottom to top and make it visible.
	private void slideInOptionsView(){
		Log.i(TAG,"slideOutOptionsView() Entering.");
		
		if(bottomOptionsView.getVisibility()==View.GONE){
		    Animation animation = AnimationUtils.loadAnimation(this,R.anim.slide_in_from_bottom);
		    //use this to make it longer:  animation.setDuration(1000);
		    animation.setDuration(1000);
		    animation.setAnimationListener(new AnimationListener(){
		    	
		        @Override
		        public void onAnimationStart(Animation animation){
		        	bottomOptionsView.setVisibility(View.VISIBLE);
		        }
	
		        @Override
		        public void onAnimationRepeat(Animation animation){}
	
		        @Override
		        public void onAnimationEnd(Animation animation){}
		    });
	
		    bottomOptionsView.startAnimation(animation);
		}else{
			//Do Nothing.
		}
	}
	
	//Animates popup from top to bottom and makes it Gone.
	private void slideOutOptionsView(){
		Log.i(TAG,"slideOutOptionsView() Entering.");
		
		if(bottomOptionsView.getVisibility()==View.VISIBLE){
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
		        	bottomOptionsView.setVisibility(View.GONE);
		        }
		    });
	
		    bottomOptionsView.startAnimation(animation);
		}else{
			//Do Nothing.
		}
	    Log.i(TAG,"slideOutOptionsView() Exiting.");
	}
	
	//Method to handle Device back button.
	@Override
	public void onBackPressed(){
	   Log.i(TAG,"onBackPressed Entering.");
	   boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		if(drawerOpen){
		   mDrawerLayout.closeDrawer(mDrawerList);
		}else if(bottomOptionsView.getVisibility()==View.VISIBLE){
			slideOutOptionsView();
		}else{
		   displayDialog(PlayerScreen.this,GlobalConsts.LOGOUTFLAG);
		}
	   Log.i(TAG,"onBackPressed Exiting.");
	}
	
}
