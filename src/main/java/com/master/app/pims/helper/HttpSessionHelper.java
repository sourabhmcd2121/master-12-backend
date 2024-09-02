//package com.master.app.pims.helper;
//
//import com.master.app.pims.exceptions.AppRuntimeException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Component;
//
//import java.net.UnknownHostException;
//
//@Component
//public class HttpSessionHelper {
//    private static final String USER_SESSION_KEY = "USER_SESSION_KEY";
//    private static final String SEARCHED_USER_SESSION_KEY = "SEARCHED_USER_SESSION_KEY";
//
//    public static final String getSessionIP(HttpServletRequest servletRequest) throws UnknownHostException {
//        HttpSession httpSession = servletRequest.getSession(false);  // Changed getSession() to getSession(false)
//        if (httpSession != null) {
//            return (String) httpSession.getAttribute("SESSION_IP");
//        }
//        throw new AppRuntimeException("EXCEPTION WHILE GETTING SESSION IP");
//    }
//
//    // Changed method signature to accept HttpServletRequest and return boolean
//    public static final boolean setSessionIP(HttpServletRequest servletRequest, String sessionIP) throws UnknownHostException {
//        HttpSession httpSession = servletRequest.getSession(false);  // Changed getSession() to getSession(false)
//        if (httpSession != null) {
//            httpSession.setAttribute("SESSION_IP", sessionIP);
//            return true;
//        }
//        throw new AppRuntimeException("EXCEPTION WHILE SETTING SESSION IP");
//    }
//
//    // Changed method signature to accept HttpServletRequest
//    public static UserSessionParam getUserSession(HttpServletRequest servletRequest) {
//        HttpSession httpSession = servletRequest.getSession(false);  // Changed getSession() to getSession(false)
//        if (httpSession != null) {
//            return (UserSessionParam) httpSession.getAttribute(USER_SESSION_KEY);
//        }
//        throw new AppRuntimeException("EXCEPTION WHILE GETTING USER SESSION");
//    }
//
//    // Changed method signature to accept HttpServletRequest
//    public static void setUserSession(HttpServletRequest servletRequest, UserSessionParam userSessionParam) {
//        HttpSession httpSession = servletRequest.getSession(true);  // Changed getSession() to getSession(true)
//        httpSession.setAttribute(USER_SESSION_KEY, userSessionParam);
//    }
//}
