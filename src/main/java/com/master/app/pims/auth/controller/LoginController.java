//package com.master.app.pims.auth.controller;
//
//import java.util.Map;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import ch.qos.logback.core.model.Model;
//import jakarta.servlet.http.HttpSession;
//@RestController
//@RequestMapping("/web")
//public class LoginController {
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public ModelAndView doShowLogin(HttpSession httpSession, Model model) {
//
//		ModelAndView modelAndView = new ModelAndView("LoginView");
//		//modelAndView.addObject("parichayEnabled", AppEnvProp.PARICHAY_ENABLE);
//		//Map<String, Object> modelMap = model.asMap();
////		if(modelMap.containsKey("LOGIN_MESSAGE")) {
////			String message = (String) modelMap.get("LOGIN_MESSAGE");
////			System.out.println("success");
////			modelAndView.addObject("MESSAGE", message);
////		}
//		return modelAndView;
//	}
//}
