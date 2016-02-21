package com.amallu.exception;

public class AmalluException extends Exception{

	private static final long serialVersionUID = -6603365618236274677L;
	transient final private String message;
	transient final private String errorCode;
	transient final private String errorCause;
	transient final private String suggestion;

	@Override
	public String getMessage(){
		return message;
	}

	public String getErrorCause(){
		return errorCause;
	}

	public String getSuggestion(){
		return suggestion;
	}


	public String getErrorCode(){
		return errorCode;
	}

	public AmalluException(final String errorCode, final String errorCause,final String suggestion) {
		final StringBuilder messageBuilder = new StringBuilder(errorCode);
		messageBuilder.append("|_|Cause: ").append(errorCause).append(
				"|_|Suggestion: ").append(suggestion).append("|_|");

		message = messageBuilder.toString();
		this.errorCode = errorCode;
		this.errorCause = errorCause;
		this.suggestion = suggestion;
	}

	public String toString() {
		final String className = getClass().getName();
		final String message = getMessage();
		return (message == null) ? className : (className + ": " + message);
	}

}
