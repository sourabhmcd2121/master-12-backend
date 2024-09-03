package com.master.app.pims.exceptions;



public class AppException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode;
	
	public AppException(){
		
	}
	public AppException(ErrorCode errorCode){
		this.errorCode = errorCode;
	}
	
	public AppException(String message, ErrorCode errorCode){
		super(message);
		this.errorCode = errorCode;
	}
	
	public AppException(Throwable cause, ErrorCode errorCode){
		super(cause);
		this.errorCode = errorCode;
	}

	public AppException(String message, Throwable cause, ErrorCode errorCode){
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	public AppException(String message){
		super(message);
	}

	public AppException(Throwable cause){
		super(cause);
	}

	public AppException(String message, Throwable cause){
		super(message, cause);
	}

	public AppException(String message, Throwable cause,
                        boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public ErrorCode getErrorCode() {
		return this.errorCode;
	}
}
