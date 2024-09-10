package com.master.app.pims.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LoggerUtil {
//	static {
//		IS_LOG_ENABLED = PropertyReader.getAppConstant("app.logger.enable").equals("1") ? true : false;
//	}
	public static Boolean IS_LOG_ENABLED; 
	public static String PRE_HANDLER = "PRE_HANDLER";
	public static String POST_HANDLER = "POST_HANDLER";
	

	public static void appLog(String loginID, String ipAddress, String handler, String handling){
		if(IS_LOG_ENABLED){
			Log logger = LogFactory.getLog("APP_LOG");
			StringBuilder loggerMessage = new StringBuilder();
			loggerMessage.append(ipAddress);
			loggerMessage.append("|");
			loggerMessage.append(loginID);
			loggerMessage.append("|");
			loggerMessage.append(handler);
			loggerMessage.append("|");
			loggerMessage.append(handling);
			logger.info(loggerMessage.toString());
		}		
	}
	
	public static void errorLog(String loginID, String ipAddress, String exceptionHandler, String exceptionMessage){
		if(IS_LOG_ENABLED){
			Log logger = LogFactory.getLog("ERROR_LOG");
			StringBuilder loggerMessage = new StringBuilder();
			loggerMessage.append(ipAddress);
			loggerMessage.append("|");
			loggerMessage.append(loginID);
			loggerMessage.append("|");
			loggerMessage.append(exceptionHandler);
			loggerMessage.append("|");
			loggerMessage.append(exceptionMessage);
			logger.error(loggerMessage.toString());
		}		
	}	
}
