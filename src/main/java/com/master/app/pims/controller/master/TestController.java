//package com.master.app.pims.controller.master;
//
//import java.io.IOException;
//
//import org.json.JSONObject;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@RestController
//public class TestController {
//		
//	@GetMapping("/code")
//	public String parichayLoginSuccess(HttpSession httpSession, HttpServletRequest request, 
//			HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		System.out.println("Inside parichayLoginSuccess");
//		try {
//			response.getWriter().append("Served at: ").append(request.getContextPath());
//			JSONObject jsonObject = null;
//			String encrString = request.getParameter("code");
//			if(encrString != null) {
//				return encrString;
//			}else {
//				return "Not Found";
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "Not Found";
//		
//	}
//	
//}
