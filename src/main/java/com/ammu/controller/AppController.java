package com.ammu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.model.LoginModel;
import com.ammu.model.RegistrationModel;
import com.ammu.repository.UserRepository;

@RestController
@RequestMapping("/user")  // http://localhost:8082/user
public class AppController 
{
	//ADDED REQUEST MAPPING FOR LOGIN (http://localhost:8082/user/login?name="xyz"&password="***").
	@RequestMapping("/login") 
	public LoginModel login(@RequestParam(value="name")String name1,String password)
	{
		LoginModel loginModel = new LoginModel();
		loginModel.setUserName(name1);
		loginModel.setPassword(password);
		return loginModel;
	}
	
	//ADDED REQUEST MAPPING FOR REGISTRATION.
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
	
	//AUTOWIRED WHICH GIVE BEAN OBJECT TO UserRepsitory interface.
	@Autowired
	UserRepository user;
	
	//POST MAPPING TO CHECK THE DATABASE CONNECTIVITY(http://localhost:8082//user/add?name="xyz"&pwd="***"). 
	@PostMapping(path="/add")
	@ResponseBody
	public String addLoginUser(@RequestParam(value="name") String name,@RequestParam(value="pwd") String password)
	{
		LoginModel loginModel = new LoginModel();
		loginModel.setUserName(name);
		loginModel.setPassword(password);
		user.save(loginModel);
		return "saved";
	}
	
	
	
}
