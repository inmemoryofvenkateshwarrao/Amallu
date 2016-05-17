package com.amallu.model;

public class Comment{
	
	private String comment_id;
	private String userid;
	private String channel_id;
	private String comment;
	private String preference_type;
	private String hide_comment;
	private String dt_created;
	private String username;
	private String likecount;
	private String dislikecount;
	
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPreference_type() {
		return preference_type;
	}
	public void setPreference_type(String preference_type) {
		this.preference_type = preference_type;
	}
	public String getHide_comment() {
		return hide_comment;
	}
	public void setHide_comment(String hide_comment) {
		this.hide_comment = hide_comment;
	}
	public String getDt_created() {
		return dt_created;
	}
	public void setDt_created(String dt_created) {
		this.dt_created = dt_created;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLikecount() {
		return likecount;
	}
	public void setLikecount(String likecount) {
		this.likecount = likecount;
	}
	public String getDislikecount() {
		return dislikecount;
	}
	public void setDislikecount(String dislikecount) {
		this.dislikecount = dislikecount;
	}
	
}
