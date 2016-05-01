package com.amallu.backend;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;

import com.amallu.exception.AmalluException;
import com.amallu.fragment.TrendingFragment;
import com.amallu.ui.CategoriesScreen;
import com.amallu.ui.ForgetPasswordScreen;
import com.amallu.ui.LanguagesScreen;
import com.amallu.ui.LoginScreen;
import com.amallu.ui.PlayerScreen;
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
			((LoginScreen)context).loginProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.SIGNUP)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "SIGNUP Exception caught.");
			}
			((SignUpScreen)context).signUpProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.FORGETPASSWORD)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "FORGETPASSWORD Exception caught.");
			}
			((ForgetPasswordScreen)context).forgetPasswordProceedUI(result,ex);
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
		if(uiHandler.equals(CommonHandlerType.NEXTCHANNEL)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "CHANNEL Exception caught.");
			}
			((PlayerScreen)context).nextChannelProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.COMMENT)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "COMMENT Exception caught.");
			}
			//((UsersScreen)context).proceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.LIKECHANNEL)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "LIKECHANNEL Exception caught.");
			}
			((PlayerScreen)context).likeChannelProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.DISLIKECHANNEL)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "DISLIKECHANNEL Exception caught.");
			}
			((PlayerScreen)context).disLikeChannelProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.CREATECOMMENT)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "CREATECOMMENT Exception caught.");
			}
			//((UsersScreen)context).proceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.EDITCOMMENT)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "EDITCOMMENT Exception caught.");
			}
			//((UsersScreen)context).proceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.DELETECOMMENT)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "DELETECOMMENT Exception caught.");
			}
			//((UsersScreen)context).proceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.CHANNELINFO)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "CHANNELINFO Exception caught.");
			}
			if(context instanceof LoginScreen){
			   Log.v(TAG,"context instanceof LoginScreen");
			   ((LoginScreen)context).channelInfoProceedUI(result, ex);
			}else if(context instanceof SignUpScreen){
			   ((SignUpScreen)context).channelInfoProceedUI(result,ex);	
			}
		}
		if(uiHandler.equals(CommonHandlerType.CATEGORY)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "CATEGORY Exception caught.");
			}
			((PlayerScreen)context).categoriesProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.CHANNEL)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "CHANNEL Exception caught.");
			}
			((PlayerScreen)context).channelsProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.LANGUAGE)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "LANGUAGE Exception caught.");
			}
			((PlayerScreen)context).languageProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.CHANGEPASSWORD)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "CHANGEPASSWORD Exception caught.");
			}
			//((ChangePasswordScreen)context).changePWDProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.CHANNELSBYCATEGORY)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "CHANNELSBYCATEGORY Exception caught.");
			}
			((CategoriesScreen)context).channelsByCategoryProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.CHANNELSBYLANGUAGE)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "CHANNELSBYLANGUAGE Exception caught.");
			}
			((LanguagesScreen)context).channelsByLanguageProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.TRENDINGCHANNELS)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "TRENDINGCHANNELS Exception caught.");
			}
			//((TrendingFragment)).trendingChannelsProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.ADDFRIEND)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "ADDFRIEND Exception caught.");
			}
			//((LanguagesScreen)context).addFriendProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.ACTIVITYLOG)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "ACTIVITYLOG Exception caught.");
			}
			//((ActivityLogScreen)context).activityLogProceedUI(result,ex);
		}
		if(uiHandler.equals(CommonHandlerType.FAVORITECHANNELS)){
			if(result.equalsIgnoreCase("Exception")){
				Log.e(TAG, "FAVORITECHANNELS Exception caught.");
			}
			//((FavoriteChannelsScreen)context).favoriteChannelsProceedUI(result,ex);
		}
	}

	@Override
	public void updateTrendingFragmentResponse(TrendingFragment fragmentCtx,String result,CommonHandlerType uiHandler, 
				AmalluException ex){
		if(uiHandler.equals(CommonHandlerType.TRENDINGCHANNELS)){
			if(result.equalsIgnoreCase("Exception")){
			   Log.e(TAG, "TRENDINGCHANNELS Exception caught.");
			}
			fragmentCtx.trendingChannelsProceedUI(result,ex);
		}
	}
	
	
}
