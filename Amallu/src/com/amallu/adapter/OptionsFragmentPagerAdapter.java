package com.amallu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.amallu.fragment.ActivitiesFragment;
import com.amallu.fragment.CommentsFragment;
import com.amallu.fragment.FavoritesFragment;
import com.amallu.fragment.FriendsFragment;
import com.amallu.fragment.TrendingFragment;
import com.amallu.fragment.WatchingFragment;

public class OptionsFragmentPagerAdapter extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = 6;

	/** Constructor of the class */
	public OptionsFragmentPagerAdapter(FragmentManager fm){
		super(fm);
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int fragmentIndex){
		Fragment fg=null;
		switch(fragmentIndex){
			case 0:
				CommentsFragment commentsFragment=new CommentsFragment();
				return commentsFragment;
			case 1:
				TrendingFragment trendingFragment=new TrendingFragment();
				return trendingFragment;
			case 2:
				TrendingFragment favoritesFragment=new TrendingFragment();
				return favoritesFragment;
				/*FavoritesFragment favoritesFragment=new FavoritesFragment();
				return favoritesFragment;*/
			case 3:
				WatchingFragment watchingFragment=new WatchingFragment();
				return watchingFragment;
			case 4:
				/*FriendsFragment friendsFragment=new FriendsFragment();
				return friendsFragment;*/
				WatchingFragment friendsFragment=new WatchingFragment();
				return friendsFragment;
			case 5:
				ActivitiesFragment activitiesFragment=new ActivitiesFragment();
				return activitiesFragment;
		}
		return fg;
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {		
		return PAGE_COUNT;
	}
	
	@Override
	public CharSequence getPageTitle(int position){	
		if(position==0){
			return "Comments";
		}else if(position==1){
		    return "Trending";	
		}else if(position==2){
			return "Favorites";
		}else if(position==3){
			return "Watching";
		}else if(position==4){
			return "Friends";
		}else{
			return "Activities";
		}
	}
	
}
