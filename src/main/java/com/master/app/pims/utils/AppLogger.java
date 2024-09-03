package com.master.app.pims.utils;


import com.master.app.pims.exceptions.AppException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class AppLogger {

    private AppLogger() {
    }

    public static void trace(String loginID, String ipAddress,
                             String requestHandler, String requestHandlerEvent) {
        StringBuilder loggerMessage = new StringBuilder();
        loggerMessage.append(ipAddress);
        loggerMessage.append("|");
        loggerMessage.append(loginID);
        loggerMessage.append("|");
        loggerMessage.append(requestHandler);
        loggerMessage.append("|");
        loggerMessage.append(requestHandlerEvent);
        log.trace(loggerMessage.toString());
    }

    public static void error(String loginID, String ipAddress, Exception exception) {
        StringBuilder loggerMessage = new StringBuilder();
        loggerMessage.append(ipAddress);
        loggerMessage.append("|");
        loggerMessage.append(loginID);
        loggerMessage.append("|");
        if (exception != null) {
            loggerMessage.append(exception.getStackTrace()[0].getClassName() + "-"
                    + exception.getStackTrace()[0].getMethodName() + "-" + exception.getStackTrace()[0].getLineNumber());
        } else {
            loggerMessage.append("");
        }
        loggerMessage.append("|");
        loggerMessage.append(exception.toString());
        log.error(loggerMessage.toString());
    }

    public static void error(String loginID, String ipAddress, AppException exception) {
        StringBuilder loggerMessage = new StringBuilder();
        loggerMessage.append(ipAddress);
        loggerMessage.append("|");
        loggerMessage.append(loginID);
        loggerMessage.append("|");
        if (exception != null) {
            loggerMessage.append(exception.getStackTrace()[0].getClassName() + "-"
                    + exception.getStackTrace()[0].getMethodName() + "-" + exception.getStackTrace()[0].getLineNumber());
        } else {
            loggerMessage.append("");
        }
        loggerMessage.append("|");
        loggerMessage.append(exception.getErrorCode().getCode() + "-" + exception.getErrorCode().getMessage());
        log.error(loggerMessage.toString());
    }

    public static void error(String loginID, String ipAddress, Exception exception, String message) {
        StringBuilder loggerMessage = new StringBuilder();
        loggerMessage.append(ipAddress);
        loggerMessage.append("|");
        loggerMessage.append(loginID);
        loggerMessage.append("|");
        if (exception != null) {
            loggerMessage.append(exception.getStackTrace()[0].getClassName() + "-"
                    + exception.getStackTrace()[0].getMethodName() + "-" + exception.getStackTrace()[0].getLineNumber());
        } else {
            loggerMessage.append("");
        }
        loggerMessage.append("|");
        loggerMessage.append(message);
        log.error(loggerMessage.toString());
    }

}
