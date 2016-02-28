package com.amallu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.amallu.ui.ActivitiesScreen;
import com.amallu.ui.CommentScreen;
import com.amallu.ui.FavoritesScreen;
import com.amallu.ui.FriendsScreen;
import com.amallu.ui.TrendingScreen;
import com.amallu.ui.WatchingScreen;
 
public class OptionsPagerAdapter extends FragmentPagerAdapter {
 
    public OptionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Comment Rated fragment activity
            return new CommentScreen();
        case 1:
            // Trending fragment activity
            return new TrendingScreen();
        case 2:
            // Favorites fragment activity
            return new FavoritesScreen();
        case 3:
            // Watching fragment activity
            return new WatchingScreen();
        case 4:
            // Friends fragment activity
            return new FriendsScreen();
        case 5:
            // Activities fragment activity
            return new ActivitiesScreen();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}
