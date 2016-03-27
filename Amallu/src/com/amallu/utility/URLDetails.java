package com.amallu.utility;

public class URLDetails{
	
	//http://stackoverflow.com/questions/5806220/how-to-connect-to-my-http-localhost-web-server-from-android-emulator-in-eclips
	//public static final String GATEWAY_HOST="10.0.2.2";
	//http://www.app.amallu.com/api/user/signup?_format=json
	
	//public static final String PORT="8090";
	public static final String PROTOCOL= "http";
	public static final String HOST="www.app.amallu.com";
	public static final String COMMON_URL= "api";
	
	public static final String LOGIN="user/login?_format=json";
	public static final String SIGNUP="user/signup?_format=json";
	public static final String FORGETPASSWORD="user/forgetpassword?_format=json";
	public static final String CHANGEPASSWORD="user/changepassword?_format=json";
	public static final String PROFILE="profile?_format=json";
	public static final String USERS="user?_format=json";
	public static final String CATEGORY="category";
	public static final String CHANNEL="channel";
	public static final String LANGUAGE="language";
	//public static final String NEXTCHANNEL="channel/nextchannel?_format=json";
	public static final String NEXTCHANNEL="channel/channelinfo?_format=json";
	//public static final String PREVIOUSCHANNEL="previouschannel?_format=json";
	public static final String LIKECHANNEL="channel/likechannel?_format=json";
	public static final String DISLIKECHANNEL="channel/dislikechannel?_format=json";
	public static final String COMMENT="comment?_format=json";
	public static final String CHANNELINFO="channel/channelinfo?_format=json";
	public static final String CHANNELSBYCATEGORY="category/channelsbycategory?_format=json";
	public static final String CHANNELSBYLANGUAGE="language/channelsbylanguage?_format=json";
	public static final String TRENDINGCHANNELS="channel/trendingchannels?_format=json";
	public static final String ADDFRIEND="friend/addfriend?_format=json";
	public static final String ACTIVITYLOG="activity/friendsactivitylog?_format=json";
	public static final String FAVORITECHANNELS="channel/favoritechannels?_format=json";
}
