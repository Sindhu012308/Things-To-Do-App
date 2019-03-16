package com.sindhu.springboot.web.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

//import com.sindhu.springboot.web.springboot.service.LoginService;

@Controller
//@SessionAttributes("name")
// SessionAttributes in Logincontroller is used to send the value of name from this session to next session in TodoController. 
public class WelcomeController {
	//@Autowired
	//LoginService loginService;
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.addAttribute("name", getLoggedinUserName());
		return "welcome";
	}
	
	/*
	  @RequestMapping(path="/login",method=RequestMethod.POST)
	
	public String welcomeMessage(@RequestParam String name,@RequestParam String password, ModelMap model ) {
		boolean isValidUser = loginService.checkValidation(name, password);
		if(!isValidUser) {
			String message = "Please enter correct Credentials";
			model.addAttribute("message", message);
			return "login";
		}
		model.addAttribute("name", name);
		model.addAttribute("password", password);
		return "welcome";
	}
	 */

	private String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	
	}
}