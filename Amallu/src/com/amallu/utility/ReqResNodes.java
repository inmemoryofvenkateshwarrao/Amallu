package com.amallu.utility;

public class ReqResNodes{
	
	//Request properties for Login Service.
	public static final String EMAIL="email";
	
	//Response properties for Login Service
	public static final String ISSUCCESS="isSuccess";
	public static final String MESSAGE="message";
	
	//Request properties for User Sign Up Service.
	public static final String EMAILID = "emailid";
	public static final String FIRSTNAME = "firstname";
	public static final String LASTNAME = "lastname";
	public static final String PASSWORD = "password";
	public static final String CONFPASSWORD = "confPassword";
	public static final String GENDER = "gender";
	public static final String MONTH = "month";
	public static final String YEAR = "year";
	public static final String DAY = "day";
	
	//Request properties for Profile Service.
	public static final String ID = "id";
	
	//Response properties for List of Users Service.
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    //public static final String PASSWORD = "password";
    public static final String TWITTER_NAME = "twitter_username";
    public static final String CHAT_NAME = "chat_name";
    //public static final String FIRSTNAME = "firstname";
    //public static final String LASTNAME = "lastname";
    public static final String COUNTRY_CODE = "country_code";
    public static final String MOBILE_NUMBER = "mobile_number";
    public static final String DOB = "dob";
    //public static final String GENDER = "gender";
    public static final String LOCATION = "location";
    public static final String IS_ADMIN = "is_admin";
    public static final String DT_CREATED = "dt_created";
    public static final String DT_MODIFIED = "dt_modified";
    public static final String SIGNUP_SOURCE = "signup_source";
    public static final String USER_UNIQUE_ID = "user_unique_id";
    public static final String PROFILE_URL = "profile_url";
    public static final String MY_FRIENDS = "my_friends";
    
    //Response properties for Channel
    public static final String CHANNEL_ID="channel_id";
    public static final String CHANNEL_CODE="channel_code";
    public static final String CATEGORY_ID="category_id";
    public static final String CHANNEL_NAME="channel_name";
    public static final String LANGUAGE_ID="language_id";
    public static final String DESCRIPTION="description";
    public static final String RTMP_LINK="rtmp_link";
    public static final String FOLLOWERS="followers";
    public static final String VIEWS="views";
    public static final String DISPLAY_CHANNEL="display_channel";
    public static final String DEFAULT_CHANNEL="default_channel";
    public static final String TIME_WATCHED="time_watched";
    public static final String THUMBNAIL="thumbnail";
    public static final String ALREADYLIKE="alreadylike";
    
    //Request properties for NextChannel
    public static final String CHANNELID="channelid";
    
    //Response properties for Comment
    public static final String COMMENT_ID="comment_id";
    public static final String COMMENT="comment";
    public static final String PREFERENCE_TYPE="preference_type";
    public static final String HIDE_COMMENT="hide_comment";
    public static final String PREFERENCE_ID="preference_id";
    
    //Response properties for ChannelDetail
    public static final String CHANNELDETAIL="channeldetail";
    public static final String NOOFWATCHINGMEMBERS="noofwatchingmembers";
    public static final String LIKECOUNT="likecount";
    public static final String DISLIKECOUNT="dislikecount";
    public static final String COMMENTS="comments";
    
    //Response properties for Categories
    public static final String PARENT_ID="parent_id";
    public static final String CATEGORY_NAME="category_name";
    public static final String IS_ACTIVE="is_active";
    public static final String DISPLAY_ORDER="display_order";
    
    //Response properties for Language
    public static final String LANGUAGE="language";
    
    //Response properties for Change Password
    public static final String OLDPASSWORD="oldPassword";
    public static final String NEWPASSWORD="newPassword";
    public static final String CONFIRMPASSWORD="confirmPassword";
    
    //Response properties for ChannelsByCategory
    public static final String CHANNELS="channels";
    
    //Request properties for AddFriend API
    public static final String FRIENDID="friendid ";
    
    //Request properties for ActivityLog API
    public static final String TIME="time ";
    
    //Response properties for ActivityLog API
    public static final String LOG_ID="log_id";
    public static final String ACTIVITY_ID="activity_id";
    public static final String LOG_MSG="log_msg";
    public static final String TIME_DIFF="time_diff";
    
    //Response properties for LikeChannel API
    public static final String ACTION="action";
    
    //Common
    public static final String GET="get";
    public static final String POST="post";
    public static final String SUCCESS="1";
    public static final String FAILURE="0";
	
}

