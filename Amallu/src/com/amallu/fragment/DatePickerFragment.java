package com.amallu.fragment;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.amallu.ui.SignUpScreen;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
	
	private static final String TAG="DatePickerFragment";
	private static DatePickerDialog datePickerDialogInstance=null;
	private int currentYear,currentMonth,currentDay;
	public static SignUpScreen signUpInstance;//Stores SignUpScreen instance.

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		//Use the current date as the default date in the picker
		Log.i(TAG,"onCreateDialog(.)");
		final Calendar c=Calendar.getInstance();
		currentYear=c.get(Calendar.YEAR);
		currentMonth=c.get(Calendar.MONTH);
		currentDay=c.get(Calendar.DAY_OF_MONTH);
			
		if(datePickerDialogInstance!=null){
			return datePickerDialogInstance;
		}else{
			//Create a new instance of DatePickerDialog and return it
			datePickerDialogInstance=new DatePickerDialog(getActivity(),this,currentYear,currentMonth,currentDay);
			return datePickerDialogInstance;
		}		
	}
	
	public void onDateSet(DatePicker datePickerView,int selectedYear,int selectedMonth,int selectedDay){
	    //Do something with the date chosen by the user
		//if(datePickerView.isShown()){}
		Log.i(TAG,"onDateSet(....) Entering");
		Log.v(TAG,"onDateSet() selectedYear : "+selectedYear);
		Log.v(TAG,"onDateSet() selectedMonth : "+selectedMonth);
		Log.v(TAG,"onDateSet() selectedDay : "+selectedDay);
		
		if(currentYear-selectedYear>=18 && currentMonth>=selectedMonth && currentDay>=selectedDay){
			signUpInstance.showSelectedDate(selectedYear,selectedMonth,selectedDay);
		}else{
		   Log.e(TAG,"User cannot SignUp, because selected Date is less than 18 Years");
		   signUpInstance.selectedDateErrorMsg();
		}
		
		Log.i(TAG,"onDateSet(....) Entering");
	}
	
	
}
