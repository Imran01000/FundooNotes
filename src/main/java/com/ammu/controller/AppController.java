package com.ammu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	//RESPONSE TYPE METHOD IN WHICH WE CALLING ONE OF USER SERVICE IMPLEMENTATION METHOD.  
	@PostMapping(path = "/registration")
	@ResponseBody
	public Response registration(RegistrationDto registrationDto)
	{
		Response response = userService.registration(registrationDto);
		return response;
	}
	

	@GetMapping(path="/login")
	public LoginDto login(LoginDto loginDto)
	{
		return loginDto;
	}
}
