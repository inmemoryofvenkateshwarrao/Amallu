package com.amallu.model;

import java.util.List;

public class ChannelInfo {
	
	private String isSuccess;
	private String message;
	private ChannelDetail channelDetail;
	private List<Comment> commentsList;
	private String noofwatchingmembers;
	private String likecount;
	private String dislikecount;
	
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ChannelDetail getChannelDetail() {
		return channelDetail;
	}
	public void setChannelDetail(ChannelDetail channelDetail) {
		this.channelDetail = channelDetail;
	}
	public List<Comment> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}
	public String getNoofwatchingmembers() {
		return noofwatchingmembers;
	}
	public void setNoofwatchingmembers(String noofwatchingmembers) {
		this.noofwatchingmembers = noofwatchingmembers;
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
