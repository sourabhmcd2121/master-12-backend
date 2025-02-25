//package com.master.app.pims.sso.janparichay;
//import java.io.IOException; 
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.logging.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//
//public class JanParichayFilter implements Filter {
//  private static final Logger logger = LogManager.getLogger();
//  
//  FilterConfig filterConfig;
//  
//  private List<String> excludePaths = new ArrayList<>();
//  
//  public void init(FilterConfig filterConfig) throws ServletException {
//    this.filterConfig = filterConfig;
//    this.excludePaths = Arrays.asList(WebSecurityConfigConstant.EXCLUDE_URLS_JAN_PARICHAY_FILTER);
//    /*this.excludePaths.add("/static");
//    this.excludePaths.add("/web/citizen/info");
//    this.excludePaths.add("/web/citizen/parichayLogin");
//    this.excludePaths.add("/web/citizen/doAuth");*/
//    System.out.println("PARICHAY AUTHENTICATION ENABLED");
//  }
//  
//  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//    HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
//    HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
//    HttpSession httpSession = httpServletRequest.getSession();
//    UserSessionNew userSessionParam = SessionHelper.getUserSession(httpSession);
//    if((userSessionParam!=null && userSessionParam.getLoginType()!=null 
//    		&& userSessionParam.getLoginType().equals(AppConstant.LOGIN_TYPE_USER)) || userSessionParam == null)
//    {
//    	String requestUrl = httpServletRequest.getRequestURL().toString();
//        //System.out.println("Req Uri "+requestUrl);
//        String contextPath = httpServletRequest.getContextPath();
//        String appURL = String.valueOf(requestUrl.substring(0, requestUrl.indexOf(contextPath))) + contextPath;
//        String urlPath = requestUrl.substring(appURL.length(), requestUrl.length());
//        boolean excludePathStatus = false;
//        for (String path : this.excludePaths) {
//          if (urlPath.startsWith(path)|| urlPath.equals("/") || urlPath.contains("/web/citizen/property/rest")  ||  urlPath.contains("/web/citizen/property/ucharges")  ||  urlPath.contains("/web/citizen/property/ucharges/webapp/userChargePaymentRebate") || urlPath.contains("/web/citizen/property/recon")|| urlPath.contains("/web/citizen/property/dnc/") ||urlPath.contains("/web/citizen/property/")||urlPath.contains("/web/citizen/property/mutation")) {
//            excludePathStatus = true;
//            break;
//          } 
//        } 
//        if (!excludePathStatus) {
//          JanParichayResponse sessionParichayResponse = (JanParichayResponse)httpSession.getAttribute("PARICHAY_RESPONSE");
//          if (sessionParichayResponse == null) {
//            if (requestUrl.endsWith("/parichaySuccess")) {
//            	System.out.println("requestUrl ends with /web/citizen/parichaySuccess "+requestUrl);
//              String encrytParichayToken = httpServletRequest.getParameter("string");
//              JanParichayResponse parichayResponse = JanParichayUtil.parseResponse(encrytParichayToken);
//              if (parichayResponse != null && parichayResponse.isAuthenticated()) {
//                String parichayClientTokenValidateURL = JanParichayUtil.createClientTokenValidatorURL(parichayResponse.getEncrpytedToken());
//                String clientTokenValidateResponse = JanParichayUtil.getURLResponse(parichayClientTokenValidateURL);
//                if (clientTokenValidateResponse != null && !clientTokenValidateResponse.isEmpty()) {
//                  JanParichayResponse parichayRespOnClientTokenValidation = JanParichayUtil.parseResponse(clientTokenValidateResponse);
//                  if (parichayRespOnClientTokenValidation != null && parichayRespOnClientTokenValidation.isAuthenticated()) {
//                    httpSession.setAttribute("PARICHAY_RESPONSE", parichayRespOnClientTokenValidation);
//                    String parichaySuccessHandler = String.valueOf(appURL) + "/web/citizen/parichaySuccess";
//                    System.out.println("REDIRECTING TO APPLICATION PARICHAY SUCCESS HANDLER AFTER SUCCESSFULL LOGIN IN PARICHAY");
//                    httpServletResponse.sendRedirect(parichaySuccessHandler);
//                    return;
//                  } 
//                  System.out.println("REDIRECTING TO JAN PARICHAY LOGIN PAGE AS TOKEN NOT VALID RECEIVED FROM PARICHAY");
//                  httpServletResponse.sendRedirect(JanParichayUtil.createLoginURL());
//                  return;
//                } 
//                System.out.println("REDIRECTING TO JAN PARICHAY LOGIN PAGE AS TOKEN VALID RESPONSE EMPTY");
//                httpServletResponse.sendRedirect(JanParichayUtil.createLoginURL());
//                return;
//              } 
//              System.out.println("REDIRECTING TO JAN PARICHAY LOGIN PAGE AS USER NOT AUTHENTICATED");
//              httpServletResponse.sendRedirect(JanParichayUtil.createLoginURL());
//              return;
//            } 
//            System.out.println("REDIRECTING TO JAN PARICHAY LOGIN PAGE AS APPLICATION SESSION NOT FOUND");
//            httpServletResponse.sendRedirect(JanParichayUtil.createLoginURL());
//            return;
//          } 
//          String tokenValidatorURL = JanParichayUtil.createTokenValidURL(sessionParichayResponse.getLocalTokenID(), 
//              sessionParichayResponse.getUserName(), sessionParichayResponse.getBrowserID(), sessionParichayResponse.getSessionID());
//          long startTime = System.currentTimeMillis();
//          String tokenValidateResponse = JanParichayUtil.getURLResponse(tokenValidatorURL);
//          long endTime = System.currentTimeMillis();
//          long elapsedTime = endTime - startTime;
//          long elapsedSecs = elapsedTime / 1000L;
//          System.out.println("RESPONSE TIME OF TOKEN VALIDATING : (" + elapsedTime + "milliseconds / " + elapsedSecs + " second)");
//          if (tokenValidateResponse != null) {
//            JanParichayTokenValidResponse parichayTokenValidResponse = JanParichayUtil.parseTokenValidResponse(tokenValidateResponse);
//            if (parichayTokenValidResponse != null && !parichayTokenValidResponse.isTokenValid()) {
//              httpSession.removeAttribute("SPRING_SECURITY_CONTEXT");
//              httpSession.removeAttribute("PARICHAY_RESPONSE");
//              httpSession.invalidate();
//              SecurityContextHolder.clearContext();
//              System.out.println("REDIRECTING TO PARICHAY LOGIN PAGE WITH DESTROYING APPLICATION SESSION AS CLIENT TOKEN IS INVALIDATED");
//              httpServletResponse.sendRedirect(JanParichayUtil.createLoginURL());
//              return;
//            } 
//          } 
//        } 
//    }
//    filterChain.doFilter(servletRequest, servletResponse);
//  }
//  
//  public void destroy() {}
//}
