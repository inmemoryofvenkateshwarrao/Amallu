package com.amallu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.amallu.fragment.ActivitiesFragment;
import com.amallu.fragment.CommentsFragment;
import com.amallu.fragment.FriendsFragment;
import com.amallu.fragment.TrendingFragment;
import com.amallu.fragment.WatchingFragment;
import com.amallu.utility.GlobalConsts;

public class OptionsFragmentPagerAdapter extends FragmentPagerAdapter{
	
	final int PAGE_COUNT=6;
	private static final String TAG="OptionsFragmentPagerAdapter";
	Fragment commentsFragment,friendsFragment,activitiesFragment;

	/** Constructor of the class */
	public OptionsFragmentPagerAdapter(FragmentManager fm){
		super(fm);
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int fragmentIndex){
		switch(fragmentIndex){
			case 0:
				Log.v(TAG,"Clicked on Comments Tab");
				CommentsFragment commentsFragment=new CommentsFragment();
				return commentsFragment;
			case 1:
				Log.v(TAG,"Clicked on Trending Tab");
				TrendingFragment trendingFragment=new TrendingFragment();
				return trendingFragment;
			case 2:
				Log.v(TAG,"Clicked on Favorites Tab");
				TrendingFragment favoritesFragment=new TrendingFragment();
				return favoritesFragment;
				/*FavoritesFragment favoritesFragment=new FavoritesFragment();
				return favoritesFragment;*/
			case 3:
				Log.v(TAG,"Clicked on Watching Tab");
				WatchingFragment watchingFragment=new WatchingFragment();
				return watchingFragment;
			case 4:
				Log.v(TAG,"Clicked on Friends Tab");
				if(friendsFragment==null){
				   friendsFragment=new FriendsFragment();
				   Log.v(TAG,"Intantiating FriendsFragment.");
				}else{
				   Log.v(TAG,"FriendsFragment instance already exists.");
				}
				return friendsFragment;
			case 5:
				Log.v(TAG,"Clicked on Activities Tab");
				if(activitiesFragment==null){
				   activitiesFragment=new ActivitiesFragment();
				   Log.v(TAG,"Intantiating ActivitiesFragment.");
				}else{
				   Log.v(TAG,"ActivitiesFragment instance already exists.");
				}
				return activitiesFragment;
		}
		return null;
	}

	/** Returns the number of pages */
	@Override
	public int getCount(){		
		return PAGE_COUNT;
	}
	
	@Override
	public CharSequence getPageTitle(int position){	
		if(position==0){
			return GlobalConsts.COMMENTS;
		}else if(position==1){
		    return GlobalConsts.TRENDING;	
		}else if(position==2){
			return GlobalConsts.FAVORITES;
		}else if(position==3){
			return GlobalConsts.WATCHING;
		}else if(position==4){
			return GlobalConsts.FRIENDS;
		}else{
			return GlobalConsts.ACTIVITIES;
		}
	}
	
}
