package com.amallu.backend;

import android.content.Context;

import com.amallu.exception.AmalluException;

public interface Response{
	enum CommonHandlerType {
		SIGNUP,FORGETPASSWORD,PROFILE,USERS
	};

	public void updateResponse(Context context, String result,CommonHandlerType handlerType, 
								AmalluException exception);
}
