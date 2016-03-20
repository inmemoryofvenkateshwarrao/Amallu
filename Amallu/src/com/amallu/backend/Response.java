package com.amallu.backend;

import android.content.Context;

import com.amallu.exception.AmalluException;

public interface Response{
	enum CommonHandlerType {
		LOGIN,SIGNUP,FORGETPASSWORD,PROFILE,USERS,CHANNEL,COMMENT,CREATECOMMENT,EDITCOMMENT,DELETECOMMENT,
		LIKECHANNEL,DISLIKECHANNEL,CHANNELINFO,NEXTCHANNEL,CATEGORY,LANGUAGE,CHANGEPASSWORD,CHANNELSBYCATEGORY,
		CHANNELSBYLANGUAGE
	};

	public void updateResponse(Context context, String result,CommonHandlerType handlerType, 
								AmalluException exception);
}
