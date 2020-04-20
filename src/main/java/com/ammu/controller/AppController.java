package com.ammu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.repository.UserRepository;
import com.ammu.response.Response;
import com.ammu.services.UserService;

@RestController 
@RequestMapping(path = "/user")    // http://localhost:8082/user
public class AppController 
{
	//USER REPOSITORY AUTOWIRED TO GET THE OBJECT FROM JAVA BEAN CLASS.
	@Autowired
	UserRepository userRepository;
	
	//USER SERVICE AUTOWIRED TO GET THE OBJECT FROM JAVA BEAN CLASS.
	@Autowired
	UserService userService;
	
	Response response;
	
	//RESPONSE TYPE METHOD IN WHICH WE CALLING USER REGISTRATION METHOD.  
	@PostMapping(path = "/registration")
	@ResponseBody
	public Response registration(RegistrationDto registrationDto)
	{
		 response = userService.registration(registrationDto);
		return response;
	}
	
	//RESPONSE TYPE METHOD IN WHICH WE CALLING USER LOGIN METHOD.  
	@RequestMapping(path = "/login" , method = RequestMethod.POST)
	public Response login(LoginDto loginDto)
	{
		response = userService.login(loginDto);
		return response;
	}
}
