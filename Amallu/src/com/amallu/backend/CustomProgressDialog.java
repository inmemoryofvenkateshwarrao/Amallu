package com.amallu.backend;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;

import com.amallu.ui.R;

public class CustomProgressDialog extends Dialog {

	private static final String TAG="CustomProgressDialog";
	static CustomProgressDialog dialog = null;
	public static boolean isCanceled = false;
	private static WebServClient client = null;

	public static CustomProgressDialog show(Context context) {
		if (dialog == null) {
			Log.i(TAG, "show..");
			dialog = new CustomProgressDialog(context);
			dialog.addContentView(new ProgressBar(context), new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			isCanceled = false;
			client = null;
			dialog.setCancelable(false);
			dialog.setOnCancelListener(new OnCancelListener(){
				@Override
				public void onCancel(DialogInterface dialog) {
					isCanceled = true;
					CustomProgressDialog.dialog = null;
					if (client != null) {
						//client.cancelConnection();
						client = null;
					}
				}
			});
			dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface dialog, int keyCode,
						KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_SEARCH
							&& event.getRepeatCount() == 0) {
						return true;
					}
					return false;
				}
			});

			dialog.show();
			return dialog;
		}
		return null;
	}

	public static void setClient(WebServClient client) {
		CustomProgressDialog.client = client;
	}

	public static boolean IsShowing() {
		Log.i(TAG,"IsShowing..");
		if (dialog != null)
			return dialog.isShowing();
		else
			return false;
	}

	public static void Dismiss() {
		Log.i(TAG,"Dismiss..");
			dialog.dismiss();
			dialog = null;
			client = null;
			isCanceled = false;
		
	}

	private CustomProgressDialog(Context context) {
		super(context, R.style.CustomProgressDialog);
	}

}
