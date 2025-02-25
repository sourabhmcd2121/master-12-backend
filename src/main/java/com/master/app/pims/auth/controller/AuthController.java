//package com.master.app.pims.auth.controller;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.json.JSONObject;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.master.app.pims.config.AppEnvConstant;
//import com.master.app.pims.sso.janparichay.JanParichayEncryptDecrypt;
//import com.master.app.pims.sso.janparichay.JanParichayResponse;
//import com.master.app.pims.sso.janparichay.JanParichayUtil;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//public class AuthController {
//	
//	
//	
//	
//	
//	
//	
//	@RequestMapping({"/jnpcy/parichayLogin"})
//	public String janParichayLogin(HttpSession httpSession, HttpServletRequest httpServletRequest,
//			HttpServletResponse httpServletResponse, RedirectAttributes redirectAttributes) {
//		try { 
//			System.out.println("INSIDE JAN PARICHAY LOGIN REDIRECT HANDLER.......................");
//			JanParichayResponse sessionParichayResponse = (JanParichayResponse) httpSession
//					.getAttribute("JAN_PARICHAY_RESPONSE");
//			if (sessionParichayResponse == null) {
//				String encrString = httpServletRequest.getParameter("string");		
//				if(encrString!=null) {
//					return "redirect:/web/citizen/jnpcy/parichaySuccess";
//				}
//				System.out.println(
//						"REDIRECT TO JAN PARICHAY LOGIN PAGE AS APPLICATION PARICHAY SUCCESS HANDLER ACCESSED WITHOUT PARICHAY SESSION");
//				return "redirect:" + JanParichayUtil.createLoginURL();
//			}else {
//				return "redirect:/web/citizen/jnpcy/parichaySuccess";
//			}
//		} catch (BadCredentialsException badCredentialsException) {
//			SecurityContextHolder.clearContext();
//			return "redirect:/web/citizen/info";
//		} catch (Exception exception) {
//			SecurityContextHolder.clearContext();
//			return "redirect:/web/citizen/info";
//		}
//	}
//
//	@SuppressWarnings("unused")
//	@RequestMapping({"/jnpcy/parichaySuccess"})
//	protected String parichayLoginSuccess(HttpSession httpSession, HttpServletRequest request, 
//			HttpServletResponse response, RedirectAttributes redirectAttributes)			
//	throws InterruptedException,
//	IOException,
//	Throwable {
//		System.out.println("Inside parichayLoginSuccess");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		JSONObject jsonObject = null;
//		String encrString = request.getParameter("string");
//		if (null == encrString) {
//			response.sendRedirect(JanParichayUtil.createLoginURL());
//		} else {
//			String handshakResponse = JanParichayUtil.handShakingFunc(encrString);
//			String dcrptResponse = "";
//			try {
//				dcrptResponse = JanParichayEncryptDecrypt.decryptFunc(handshakResponse);
//			} catch(Exception e) {
//				// TODO: handle exception
//			}
//			JSONObject obj = new JSONObject(dcrptResponse);
//			JSONObject data = (JSONObject) obj.get("data");
//			JSONObject signature = (JSONObject) data.get("signature");
//			String userName = (String) signature.get("userName");
//			String firstName = signature.has("firstName") && !signature.isNull("firstName") ? (String) signature.get("firstName") : "";
//			String lastName = signature.has("lastName") && !signature.isNull("lastName") ? (String) signature.get("lastName") : "";
//			String fullName = signature.has("fullName") && !signature.isNull("fullName") ? (String) signature.get("fullName") : "";
//			String mobileNumber = signature.has("mobileNo") && !signature.isNull("mobileNo") ? (String) signature.get("mobileNo") : "";
//			String employeeCode = signature.has("employeeCode") && !signature.isNull("employeeCode") ? (String) signature.get("employeeCode") : "";
//			String sessionId = signature.has("sessionId") && !signature.isNull("sessionId") ? (String) signature.get("sessionId") : "";
//			String subService = signature.has("subservice") && !signature.isNull("subservice") ? (String) signature.get("subservice") : "";
//			String designation = signature.has("designation") && !signature.isNull("designation") ? (String) signature.get("designation") : "";
//			String ipAddress = signature.has("ip") && !signature.isNull("ip") ? (String) signature.get("ip") : "";
//			String browserId = signature.has("browserId") && !signature.isNull("browserId") ? (String) signature.get("browserId") : "";
//			String localTokenId = signature.has("localTokenId") && !signature.isNull("localTokenId") ? (String) signature.get("localTokenId") : "";
//			String status = signature.has("status") && !signature.isNull("status") ? (String) signature.get("status") : "";
//			String emailId = signature.has("email") && !signature.isNull("email") ? (String) signature.get("email") : "";
//			String userId = signature.has("userId") && !signature.isNull("userId") ? (String) signature.get("userId") : "";
//			String loginId = signature.has("loginId") && !signature.isNull("loginId") ? (String) signature.get("loginId") : "";
//			String ua = signature.has("ua") && !signature.isNull("ua") ? (String) signature.get("ua") : "";
//			String parichayId = signature.has("parichayId") && !signature.isNull("parichayId") ? (String) signature.get("parichayId") : "";
//			String serviceName = signature.has("ParichayClient") && !signature.isNull("ParichayClient") ? (String) signature.get("ParichayClient") : "";
//			
//			String authTypeCode ="MOB_OTP";
//			
//			if(ValidationUtil.isValidOfficerLogin(loginId)) {
//				authTypeCode = "USER_LOGIN";
//			}else if(!ValidationUtil.isValidMobileNumber(loginId)) {
//				authTypeCode = "EMAIL_LOGIN";
//			}else {
//				authTypeCode = "MOB_OTP";
//			}
//			
//			
//			
//			
//			CitizenRegistration citizenRegistration = new CitizenRegistration();
//			citizenRegistration.setFirstName(firstName);
//			citizenRegistration.setMobileNo(mobileNumber);
//			citizenRegistration.setEmailId(emailId != null && !emailId.isEmpty() ? emailId : userId);
//			citizenRegistration.setAuthTypeCode(authTypeCode);
//			citizenRegistration.setAuthTypeCodeHidden("");
//			citizenRegistration.setCitizenTypeGuid("0f28ccd1-8d66-4bf6-8e1d-249f8ea76ecf");
//			citizenRegistration.setCitizenTypeCode(authTypeCode);
//			citizenRegistration.setConfirmPassword("");
//			citizenRegistration.setCountryCode("91");
//			citizenRegistration.setOtpValueEmail("");
//			citizenRegistration.setLoginId("");
//			
//			
//			JanParichayResponse sessionJanParichayResponse = new JanParichayResponse();
//			sessionJanParichayResponse.setFirstName(firstName);
//			sessionJanParichayResponse.setLastName(lastName);
//			sessionJanParichayResponse.setFullName(fullName);
//			sessionJanParichayResponse.setEmail(emailId != null && !emailId.isEmpty() ? emailId : "");
//			sessionJanParichayResponse.setDesignation(designation);
//			sessionJanParichayResponse.setIpAddr(ipAddress);
//			sessionJanParichayResponse.setMobileNumber(mobileNumber);	
//			sessionJanParichayResponse.setEmployeeCode(employeeCode);
//			sessionJanParichayResponse.setEncrpytedToken("");
//			sessionJanParichayResponse.setLocalTokenID(localTokenId);
//			sessionJanParichayResponse.setSessionID(sessionId);
//			sessionJanParichayResponse.setStatus(status);
//			sessionJanParichayResponse.setSubService(subService);
//			sessionJanParichayResponse.setUserAgent(ua);
//			sessionJanParichayResponse.setUserID(userId);
//			sessionJanParichayResponse.setLoginId(loginId);
//			sessionJanParichayResponse.setUserName(userName);
//			sessionJanParichayResponse.setBrowserID(browserId);
//			System.out.println("loginId = "+loginId);
//			List<UserRoles> userSessionForRole = authService.getUserRoles(loginId);
//			httpSession.setAttribute("JAN_PARICHAY_RESPONSE", sessionJanParichayResponse);
//			try {
//				System.out.println("userSessionForRole = "+userSessionForRole);
//				if(userSessionForRole == null || userSessionForRole.isEmpty()) {
//					httpSession.setAttribute("JAN_PARICHAY_RESPONSE", sessionJanParichayResponse);
//					return "redirect:/web/citizen/signUp";
//				}
//								
//				System.out.println("INSIDE JAN PARICHAY SUCESS HANDLER.......................");
//				UserSessionNew userSessionNew = new UserSessionNew();
//				UserSessionNew userSessionNewOfficer = new UserSessionNew();
//				Optional<UserRoles> defaultRole ;
//				defaultRole = userSessionForRole.stream().filter(e -> e.getIsDefaultRole()).findFirst();
//				if(!defaultRole.isPresent()) {
//					defaultRole = userSessionForRole.stream().findFirst();
//				}
////				if (!defaultRole.isPresent()) {
////					defaultRole = Optional
////							.of(new UserRoles(Long.valueOf(userSessionForRole.size()), "", "C", "C", true));
////				}
//				///will be remove after final development
////				if (!userSessionForRole.stream().filter(e -> e.getRoleCode().equals("C")).findFirst().isPresent()) {
////					userSessionForRole.add(new UserRoles(Long.valueOf(userSessionForRole.size()), "", "C", "C", true));
////				}
//				
//				setSecurityContext(userName, "JAN_PARICHAY_AUTH_PASSWORD", request, response,
//						userSessionForRole);
//				
//				if(true ) {
//					if (defaultRole.get().getRoleCode().equals("C")) {
//						userSessionNew = authService.getUserSession("91" + mobileNumber, AppConstant.LOGIN_TYPE_CITIZEN,
//								null);
//					} else if(defaultRole.get().getRoleCode().equals("PTR_ADMIN")) {
//							userSessionNewOfficer = authService.getUserSession(loginId, AppConstant.LOGIN_TYPE_USER,
//									null);
//							if (userSessionNewOfficer == null) {
//								redirectAttributes.addFlashAttribute("LOGIN_MESSAGE",
//										new Message(false, "User not found."));
//								httpSession.removeAttribute("JAN_PARICHAY_RESPONSE");
//								return "redirect:/web/citizen/info";
//							}
//							setSecurityContext(loginId, "JAN_PARICHAY_AUTH_PASSWORD", request, response,
//									userSessionNewOfficer.getUserRoles());
//							httpSession.setAttribute("JAN_PARICHAY_RESPONSE", sessionJanParichayResponse);
//							if (true) {
//								SessionHelper.setUserSession(httpSession, userSessionNewOfficer);
//								return "redirect:/web/citizen/property/adminUpicList";
//							}
//						
//					}else if(defaultRole.get().getRoleCode().equals("RWA")){
//						if(authTypeCode.equals("MOB_OTP")) {
//							loginId = "91" + mobileNumber;
//						}
//						userSessionNewOfficer = authService.getUserSession(loginId, AppConstant.LOGIN_TYPE_RWA,
//								null);
//						setSecurityContext(loginId, "JAN_PARICHAY_AUTH_PASSWORD", request, response,
//								userSessionForRole);
//						httpSession.setAttribute("JAN_PARICHAY_RESPONSE", sessionJanParichayResponse);
//						if (true) {
//							SessionHelper.setUserSession(httpSession, userSessionNewOfficer);
//							return "redirect:/web/citizen/property/rwa/home";
//						}
//					}
//				} else {//userSessionForRole.get(0).getRoleCode()
//
//					if (userSessionForRole.get(0).getRoleCode().equals("C")) {
//						userSessionNew = authService.getUserSession("91" + mobileNumber, AppConstant.LOGIN_TYPE_CITIZEN,
//								null);
//					} else if(userSessionForRole.get(0).getRoleCode().equals("PTR_ADMIN")) {
//							userSessionNewOfficer = authService.getUserSession(loginId, AppConstant.LOGIN_TYPE_USER,
//									null);
//							if (userSessionNewOfficer == null) {
//								redirectAttributes.addFlashAttribute("LOGIN_MESSAGE",
//										new Message(false, "User not found."));
//								httpSession.removeAttribute("JAN_PARICHAY_RESPONSE");
//								return "redirect:/web/citizen/info";
//							}
//							setSecurityContext(loginId, "JAN_PARICHAY_AUTH_PASSWORD", request, response,
//									userSessionNewOfficer.getUserRoles());
//							httpSession.setAttribute("JAN_PARICHAY_RESPONSE", sessionJanParichayResponse);
//							if (true) {
//								SessionHelper.setUserSession(httpSession, userSessionNewOfficer);
//								return "redirect:/web/citizen/property/adminUpicList";
//							}
//						
//					}else if(userSessionForRole.get(0).getRoleCode().equals("RWA")){
//						userSessionNewOfficer = authService.getUserSession(loginId, AppConstant.LOGIN_TYPE_RWA,
//								null);
//						
//						setSecurityContext(loginId, "JAN_PARICHAY_AUTH_PASSWORD", request, response,
//								userSessionNewOfficer.getUserRoles());
//						httpSession.setAttribute("JAN_PARICHAY_RESPONSE", sessionJanParichayResponse);
//						if (true) {
//							SessionHelper.setUserSession(httpSession, userSessionNewOfficer);
//							return "redirect:/web/citizen/property/rwa/home";
//						}
//					}
//				
//				}
//				if (true) {
//					SessionHelper.setUserSession(httpSession, userSessionNew);
//					return "redirect:/web/citizen/property/home";
//				}
//				
//			} catch(BadCredentialsException badCredentialsException) {
//				SecurityContextHolder.clearContext();
//				httpSession.removeAttribute("PARICHAY_RESPONSE");
//				return "redirect:/web/citizen/info";
//			} catch(Exception exception) {
//				httpSession.removeAttribute("PARICHAY_RESPONSE");
//				SecurityContextHolder.clearContext();
//				return "redirect:/web/citizen/info";
//			}
//		}
//		return null;
//	}
//
//		
//	
//	 @PostMapping("/jnpcy/parichayLogout") 
//	 protected void logoutAdmin(HttpSession httpSession,HttpServletRequest request, HttpServletResponse response) throws IOException,
//	  InterruptedException {
//		
//		    System.out.println("Logout Func called.");
//		    long timeStamp = System.currentTimeMillis();
//			System.out.println("timeStamp : "+timeStamp);
//			String service = AppEnvConstant.JAN_PARICHAY_SERVICE_NAME;
//			System.out.println("Service Name : " + service);
//			String janParichayUrl = AppEnvConstant.JAN_PARICHAY_URL;
//			System.out.println("ParichayUrl : " + janParichayUrl);
//			
//			JanParichayResponse ssoResponse = (JanParichayResponse)httpSession.getAttribute("JAN_PARICHAY_RESPONSE");
//	        String sessionId=ssoResponse.getSessionID();
//			String clientToken=ssoResponse.getLocalTokenID();
//			String browserId=ssoResponse.getBrowserID();
//			String ua=ssoResponse.getUserAgent();
//
//			String hmac_sign = JanParichayUtil.hmacFunc_logout(timeStamp,service,janParichayUrl,clientToken,sessionId);
//			
//			String logOutURL = janParichayUrl+"/v1/salt/api/client/logout?clientToken="+clientToken+"&sid="+service+"&sessionId="+sessionId+"&browserId="+browserId+"&ua="+ua+"&tid="+timeStamp+"&cs="+hmac_sign ;
//			System.out.println(logOutURL);
//			String logOutURLtrim = logOutURL.trim();
//	        System.out.println("logOutURL trim2 : " + logOutURLtrim); 
//	        SecurityContextHolder.clearContext();
//		    httpSession.invalidate();
//	        response.sendRedirect(logOutURLtrim);
//		 
//	
//	 }
//	
//	
//
//}
