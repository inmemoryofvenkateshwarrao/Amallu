package com.amallu.backend;

import com.amallu.exception.AmalluException;


public interface AsyncCallback{
	public void updateAsyncResult(String result,AmalluException exception);
}
