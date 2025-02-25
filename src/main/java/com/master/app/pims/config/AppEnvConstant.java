package com.master.app.pims.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.master.app.pims.utils.LoggerUtil;
import com.master.app.pims.utils.PropertyReader;
import com.master.app.pims.utils.Util;

public final class AppEnvConstant implements Cloneable {
	private static Properties APP_ENV = null;

    public static boolean JAN_PARICHAY_ENABLE = false;
    public static String  JAN_PARICHAY_SERVICE_NAME = null;
    public static String  JAN_PARICHAY_HMAC_URL = null;
    public static String  JAN_PARICHAY_ENCR_URL = null;
    public static String  JAN_PARICHAY_HANDSHAKING_URL = null;
    public static String  JAN_PARICHAY_DEC_URL = null;
    public static String  JAN_PARICHAY_LOGOUT_URL = null;
    public static String  JAN_PARICHAY_TOKEN_VALIDATE = null;
    public static String  JAN_PARICHAY_SESS_TIMEOUT = null;
    public static String  JAN_PARICHAY_URL = null;
    public static String JAN_PARICHAY_SERVICE_API_KEY =null;
    public static String JAN_PARICHAY_TOKEN_VALID = null;
    public static String  JAN_PARICHAY_HMAC_LOGOUT_URL = null;
	    
	
	
	    public static String getAppEnv(String key) {
			if(APP_ENV == null) {
				synchronized (PropertyReader.class) {
					if(APP_ENV == null) {
						String filePath = "app" + File.separator + "env" + File.separator +"app-ENV.properties";
						InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(filePath);
						try {
							APP_ENV = new Properties();
							APP_ENV.load(inputStream);
						} catch (IOException e) {
							LoggerUtil.errorLog("", "", 
									"IOException", "AppEnvConstant getAppEnv");
						} finally {
							if(inputStream != null){
								try {
									inputStream.close();
								} catch (IOException e) {
									LoggerUtil.errorLog("", "", 
											"IOException", "AppEnvConstant getAppEnv");
								}
							}
						}
					}
				}
			}
			if(APP_ENV != null) {
				String string = APP_ENV.getProperty(key.trim());
				if(!Util.isNullOrEmpty(string)){
					return string.trim();
				}
			}
			return null;
		}
		
		
	private AppEnvConstant() {
		
	}
		
		static {

			 
			if (!Util.isNullOrEmpty(getAppEnv("jan.parichay.enable")) && getAppEnv("jan.parichay.enable").equals("1"))
				JAN_PARICHAY_ENABLE = true;
			if (JAN_PARICHAY_ENABLE) {
				JAN_PARICHAY_SERVICE_NAME = getAppEnv("jan.prichay.service");  
				JAN_PARICHAY_HMAC_URL = getAppEnv("jan.prichay.hmac_url");      
				JAN_PARICHAY_ENCR_URL = getAppEnv("jan.prichay.encr_url");      
				JAN_PARICHAY_HANDSHAKING_URL = getAppEnv("jan.prichay.handshaking_url");
				JAN_PARICHAY_DEC_URL = getAppEnv("jan.prichay.dec_url");       
				JAN_PARICHAY_LOGOUT_URL = getAppEnv("jan.prichay.logout_url");    
				JAN_PARICHAY_TOKEN_VALIDATE = getAppEnv("jan.prichay.token_validate");
				JAN_PARICHAY_SESS_TIMEOUT = getAppEnv("jan.prichay.sess_timeout");  
				JAN_PARICHAY_URL = getAppEnv("jan.prichay.parichay_url"); 
				JAN_PARICHAY_HMAC_LOGOUT_URL=getAppEnv("jan.prichay.hmac_logout_url");
				JAN_PARICHAY_SERVICE_API_KEY =getAppEnv("");
				JAN_PARICHAY_TOKEN_VALID =getAppEnv("");
			}
			
		}
		
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return new CloneNotSupportedException("OBJECT CLONING OF CLASS AppEnvConstant NOT ALLOWED");
		}




	






}
