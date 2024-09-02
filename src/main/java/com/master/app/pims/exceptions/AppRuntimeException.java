package com.master.app.pims.exceptions;

import com.master.app.pims.errors.ErrorCode;

public class AppRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private ErrorCode errorCode;
	public AppRuntimeException(){
		
	}
	public AppRuntimeException(ErrorCode errorCode){
		this.errorCode = errorCode;
	}
	public AppRuntimeException(String message){
		super(message);
	}

	public AppRuntimeException(Throwable cause){
		super(cause);
	}

	public AppRuntimeException(String message, Throwable cause){
		super(message, cause);
	}

	public AppRuntimeException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
