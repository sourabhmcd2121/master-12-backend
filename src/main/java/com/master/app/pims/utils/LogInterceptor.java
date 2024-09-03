//package com.master.app.pims.utils;
//
//import app.pims.session.AdminSession;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//public class LogInterceptor extends HandlerInterceptorAdapter {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		String requestURL = request.getRequestURI().substring(request.getContextPath().length());
//		String queryString = request.getQueryString();
//		String ipAddress = request.getHeader("X-FORWARDED-FOR");
//		if(ipAddress == null) {
//			ipAddress = request.getRemoteAddr();
//		}
//		String loginID = null;
//		AdminSession userSession = (AdminSession) request.getSession().getAttribute("USER_SESSION");
//		if(userSession != null){
//			loginID = userSession.getLoginID();
//		}else{
//			loginID = "NO_SESSION";
//		}
//		String requestHandler = null;
//		if(queryString != null && !queryString.isEmpty()){
//			requestHandler = requestURL + "?" + queryString;
//		}else{
//			requestHandler = requestURL;
//		}
//		LoggerUtil.appLog(loginID, ipAddress, requestHandler, LoggerUtil.PRE_HANDLER);
//		return super.preHandle(request, response, handler);
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		super.postHandle(request, response, handler, modelAndView);
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		String requestURL = request.getRequestURI().substring(request.getContextPath().length());
//		String queryString = request.getQueryString();
//		String ipAddress = request.getHeader("X-FORWARDED-FOR");
//		if(ipAddress == null) {
//			ipAddress = request.getRemoteAddr();
//		}
//		String loginID = null;
//		AdminSession userSession = (AdminSession) request.getSession().getAttribute("USER_SESSION");
//		if(userSession != null){
//			loginID = userSession.getLoginID();
//		}else{
//			loginID = "NO_SESSION";
//		}
//		String requestHandler = null;
//		if(queryString != null && !queryString.isEmpty()){
//			requestHandler = requestURL + "?" + queryString;
//		}else{
//			requestHandler = requestURL;
//		}
//		LoggerUtil.appLog(loginID, ipAddress, requestHandler, LoggerUtil.POST_HANDLER);
//		super.afterCompletion(request, response, handler, ex);
//	}
//}