/*
// As we use Spring Security to get the name from the username we provide during logging in, we no need to hard-cord the name
// So, we are removing this Login Service as we dont need this anymore.
 
  package com.sindhu.springboot.web.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

//validating by hardcoding the username and password to login
	public boolean checkValidation(String username, String password) {
		return username.equalsIgnoreCase("sindhuKatta") && password.equalsIgnoreCase("dummy");
	}

}*/
