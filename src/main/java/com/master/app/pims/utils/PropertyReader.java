package com.master.app.pims.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader implements Cloneable {
    private static Properties LDAP_ENV = null;
    private static Properties APP_CORE_MESSAGE = null;
    private static Properties FORM_MESSAGE = null;
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


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CloneNotSupportedException("OBJECT CLONING OF CLASS PropReader NOT ALLOWED");
    }
}
