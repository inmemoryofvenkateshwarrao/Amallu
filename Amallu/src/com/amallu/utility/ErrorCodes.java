package com.amallu.utility;

public class ErrorCodes {
	
	//Generic Error Codes.
	public static final String SQL_EXCEPTION_ERR_CODE="700001";
	public static final String INVALID_SESSION_ERR_CODE="700002";
	public static final String SESSION_EXPIRED_ERR_CODE="700003";
	public static final String COMMUNICATION_ERR_CODE="700004";
	
	//Generic Exception Codes.
	public static final String FAILED_RESPONSE = "100";
	public static final String OUT_OF_MEMORY_EXCEPTION = "101";
	public static final String IO_EXCEPTION = "102";
	public static final String CONNECTION_EXCEPTION = "103";
	public static final String SECURITY_EXCEPTION = "104";
	
	//Error Codes for User/Admin Signup service.
	public static final String REG_DETS_NOT_AVAIL_ERR_CODE="700101";
	public static final String REG_EMAIL_NOT_AVAIL_ERR_CODE="700102";
	public static final String REG_MOBNO_NOT_AVAIL_ERR_CODE="700103";
	public static final String REG_UNAME_NOT_AVAIL_ERR_CODE="700104";
	public static final String REG_UTYPE_NOT_AVAIL_ERR_CODE="700105";
	public static final String REG_EMAIL_NOT_PROP_FMT_ERR_CODE="700106";
	public static final String REG_MOBNO_LENGTH_ERR_CODE="700107";
	public static final String REG_ALREADY_AVAIL_ERR_CODE="700108";
	public static final String REG_UTYPE_INVALID_ERR_CODE="700109";
	public static final String REG_UNAME_AVAIL_ERR_CODE="700110";
	public static final String REG_EMAIL_AVAIL_ERR_CODE="700111";
	public static final String REG_MOBNO_AVAIL_ERR_CODE="700112";
	public static final String REG_MAX_LENGTH_EXCEEDED_ERR_CODE="700113";
	
	//Error Codes for ValidateOTP service.
	public static final String VALIDATEOTP_OTP_NOT_AVAIL_ERR_CODE="700201";
	public static final String VALIDATEOTP_INVAL_OTP_NOT_AVAIL_ERR_CODE="700202";
	
	//Error Codes for FetchTokens service.
	public static final String TOKENS_NOT_AVAIL_ERR_CODE="700301";
	
	//Error Codes for FetchItems service.
	public static final String ITEMS_NOT_AVAIL_ERR_CODE="700401";
	
	//Error Codes for FetchProfile service.
	public static final String FETCH_PROFILE_USER_NOT_AVAIL_ERR_CODE="700501";
	public static final String FETCH_PROFILE_INVALID_USER_ERR_CODE="700502";
	
	//Error Codes for FetchProfile service.
	public static final String UPDATE_PROFILE_USER_NOT_AVAIL_ERR_CODE="700601";
	public static final String UPDATE_PROFILE_INVALID_USER_ERR_CODE="700602";
}
