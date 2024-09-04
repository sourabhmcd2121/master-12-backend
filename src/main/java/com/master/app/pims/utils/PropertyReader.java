package com.master.app.pims.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader implements Cloneable {

    /*private static Properties APP_ENV = null;*/
    /*private static Properties DB_ENV = null;*/
    private static Properties LDAP_ENV = null;
    private static Properties APP_CORE_MESSAGE = null;
    private static Properties FORM_MESSAGE = null;
    private static Properties FORM_IMPL = null;
    private static Properties APP_CONSTATNT = null;

    private PropertyReader() {
    }
    public static String getFormMessage(String key) {
        if (FORM_MESSAGE == null) {
            synchronized (PropertyReader.class) {
                if (FORM_MESSAGE == null) {
                    String fileName = "Form-Message.properties";
                    try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(fileName)) {
                        if (inputStream == null) {
                            throw new FileNotFoundException("Property file '" + fileName + "' not found in the classpath");
                        }
                        FORM_MESSAGE = new Properties();
                        FORM_MESSAGE.load(inputStream);
                    } catch (IOException e) {
                        e.printStackTrace(); // Log the exception for debugging
                    }
                }
            }
        }
        if (FORM_MESSAGE != null) {
            String string = FORM_MESSAGE.getProperty(key);
            if (string != null && !string.trim().isEmpty()) {
                return string.trim();
            }
        }
        return null;
    }

//    public static String getLdapEnv(String key) {
//        if (LDAP_ENV == null) {
//            synchronized (PropertyReader.class) {
//                if (LDAP_ENV == null) {
//                    String filePath = "app" + File.separator + "pims" + File.separator + "config" + File.separator + "prop" + File.separator + "ldap-ENV.properties";
//                    InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(filePath);
//                    try {
//                        LDAP_ENV = new Properties();
//                        LDAP_ENV.load(inputStream);
//                    } catch (IOException e) {
//                        //	System.out.println("EXCEPTION READING ldap-ENV.properties FILE");
//                    } finally {
//                        if (inputStream != null) {
//                            try {
//                                inputStream.close();
//                            } catch (IOException e) {
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        if (LDAP_ENV != null) {
//            String string = LDAP_ENV.getProperty(key.trim());
//            if (!Util.isNullOrEmpty(string)) {
//                return string.trim();
//            }
//        }
//        return null;
//    }

//    public static String getAppCoreMessage(String key) {
//        if (APP_CORE_MESSAGE == null) {
//            synchronized (PropertyReader.class) {
//                if (APP_CORE_MESSAGE == null) {
//                    String filePath = "app" + File.separator + "pims" + File.separator + "config" + File.separator + "prop" + File.separator + "App-Core-Message.properties";
//                    InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(filePath);
//                    try {
//                        APP_CORE_MESSAGE = new Properties();
//                        APP_CORE_MESSAGE.load(inputStream);
//                    } catch (IOException e) {
//                        //		System.out.println("EXCEPTION READING App-Core-Message.properties FILE");
//                    } finally {
//                        if (inputStream != null) {
//                            try {
//                                inputStream.close();
//                            } catch (IOException e) {
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        if (APP_CORE_MESSAGE != null) {
//            String string = APP_CORE_MESSAGE.getProperty(key.trim());
//            if (!Util.isNullOrEmpty(string)) {
//                return string.trim();
//            }
//        }
//        return null;
//    }
//
//    public static String getFormMessage(String key) {
//        if (FORM_MESSAGE == null) {
//            synchronized (PropertyReader.class) {
//                if (FORM_MESSAGE == null) {
//                    String filePath = "app" + File.separator + "pims" + File.separator + "config" + File.separator + "prop" + File.separator + "Form-Message.properties";
//                    InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(filePath);
//                    try {
//                        FORM_MESSAGE = new Properties();
//                        FORM_MESSAGE.load(inputStream);
//                    } catch (IOException e) {
//                        //		System.out.println("EXCEPTION READING Form-Message.properties FILE");
//                    } finally {
//                        if (inputStream != null) {
//                            try {
//                                inputStream.close();
//                            } catch (IOException e) {
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        if (FORM_MESSAGE != null) {
//            String string = FORM_MESSAGE.getProperty(key.trim());
//            if (!Util.isNullOrEmpty(string)) {
//                return string.trim();
//            }
//        }
//        return null;
//    }
//
//	/*public static String getFormImpl(String key) {
//		if(FORM_IMPL == null) {
//			synchronized (PropReader.class) {
//				if(FORM_IMPL == null) {
//					String filePath = "app" + File.separator + "pims" + File.separator + "config" + File.separator  + "prop" + File.separator + "Form-Impl.properties";
//					InputStream inputStream = PropReader.class.getClassLoader().getResourceAsStream(filePath);
//					try {
//						FORM_IMPL = new Properties();
//						FORM_IMPL.load(inputStream);
//					} catch (IOException e) {
//						System.out.println("EXCEPTION READING database-ENV.properties FILE");
//					} finally {
//						if(inputStream != null){
//							try {
//								inputStream.close();
//							} catch (IOException e) {}
//						}
//					}
//				}
//			}
//		}
//		if(FORM_IMPL != null) {
//			String string = FORM_IMPL.getProperty(key.trim());
//			if(!Util.isNullOrEmpty(string)){
//				return string.trim();
//			}
//		}
//		return null;
//	}*/
//
//    public static String getAppConstant(String key) {
//        if (APP_CONSTATNT == null) {
//            synchronized (PropertyReader.class) {
//                if (APP_CONSTATNT == null) {
//                    String filePath = "app" + File.separator + "pims" + File.separator + "config" + File.separator + "prop" + File.separator + "App-Constant.properties";
//                    InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(filePath);
//                    try {
//                        APP_CONSTATNT = new Properties();
//                        APP_CONSTATNT.load(inputStream);
//                    } catch (IOException e) {
//                        //	System.out.println("EXCEPTION READING App-Constant.properties FILE");
//                    } finally {
//                        if (inputStream != null) {
//                            try {
//                                inputStream.close();
//                            } catch (IOException e) {
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        if (APP_CONSTATNT != null) {
//            String string = APP_CONSTATNT.getProperty(key.trim());
//            if (!Util.isNullOrEmpty(string)) {
//                return string.trim();
//            }
//        }
//        return null;
//    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CloneNotSupportedException("OBJECT CLONING OF CLASS PropReader NOT ALLOWED");
    }
}
