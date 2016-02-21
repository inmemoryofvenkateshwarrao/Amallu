package com.amallu.backend;
import android.content.Context;
import android.util.Log;

import com.amallu.exception.AmalluException;
import com.amallu.ui.ForgetPasswordScreen;
import com.amallu.ui.ProfileScreen;
import com.amallu.ui.SignUpScreen;
import com.amallu.ui.UsersScreen;
public class ResponseHandler implements Response{
	
	private static final String TAG="ResponseHandler";

	@Override
	public void updateResponse(Context context, String result,CommonHandlerType uiHandler, AmalluException ex){
		if(uiHandler.equals(CommonHandlerType.LOGIN)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "LOGIN Exception caught.");
			}
			//((LoginScreen)context).proceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.SIGNUP)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "SIGNUP Exception caught.");
			}
			((SignUpScreen)context).proceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.FORGETPASSWORD)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "FORGETPASSWORD Exception caught.");
			}
			((ForgetPasswordScreen)context).proceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.PROFILE)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "PROFILE Exception caught.");
			}
			((ProfileScreen)context).proceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.USERS)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "USERS Exception caught.");
			}
			((UsersScreen)context).proceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.CHANNEL)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "CHANNEL Exception caught.");
			}
			//((UsersScreen)context).proceedUI(result,ex);
		}
	}
}
