package com.master.app.pims.errors;

public final class ErrorCode {

	private int code;
	private String message;
	
	private ErrorCode() {}
	
	private ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public static final ErrorCode REQUIRED_PARAMETER_EMPTY = new ErrorCode(601, "REQUIRED PARAMETER IS EMPTY");
	public static final ErrorCode PARAMETER_NOT_FOUND = new ErrorCode(602, "PARAMETER NOT FOUND");
	public static final ErrorCode CODE_EXCEPTION = new ErrorCode(603, "CODE EXCEPTION");
	public static final ErrorCode DB_UPDATE_FAILED = new ErrorCode(604, "DATABASE UPDATION FAILED");
	public static final ErrorCode NO_HTTP_SESSION = new ErrorCode(605, "NO HTTP SESSION FOUND");

	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}	
	
	public void setMessage(String message) {
		this.message = message;
	}
}
