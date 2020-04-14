package com.ammu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.model.LoginModel;
import com.ammu.model.RegistrationModel;

@RestController
public class AppController 
{
	@RequestMapping("/login")
	public LoginModel login(@RequestParam(value="name") String name)
	{
		LoginModel loginModel = new LoginModel();
		loginModel.setUserName("Your name is  "+name );
		loginModel.setPassword("98765");
		return loginModel;
	}
	
	@RequestMapping("/registration")
	public RegistrationModel registration()
	{
		RegistrationModel registrationModel = new RegistrationModel();
		registrationModel.setFname("faisal");
		registrationModel.setLname("shaikh");
		registrationModel.setMail("faisal678@gmail.com");
		registrationModel.setPassword("45678987");
		return registrationModel;
	}
}
