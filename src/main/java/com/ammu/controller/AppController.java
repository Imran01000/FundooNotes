package com.ammu.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.ForgetPasswordDto;
import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.dto.ResetPasswordDto;
import com.ammu.repository.UserRepository;
import com.ammu.response.Response;
import com.ammu.services.UserService;

@RestController   
@RequestMapping(path = "/user")          // http://localhost:8082/user
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
	@RequestMapping(path = "/registration" , method = RequestMethod.POST)
	public RegistrationDto registration(@Valid @RequestBody RegistrationDto registrationDto)
	{
		response = userService.registration(registrationDto);
		return registrationDto;
		
	}

	//RESPONSE TYPE METHOD IN WHICH WE CALLING USER LOGIN METHOD.  
	@RequestMapping(path = "/login" , method = RequestMethod.POST)
	public Response login(@Valid @RequestBody LoginDto loginDto)
	{
		response = userService.login(loginDto);
		return response;
	}

	//RESPONSE TYPE METHOD IN WHICH WE CALLING FORGET PASSWORD METHOD,
	@RequestMapping(path = "/forget-password" , method = RequestMethod.POST)
	public ForgetPasswordDto forgetPassword(@Valid @RequestBody ForgetPasswordDto forgetPasswordDto)
	{
		response = userService.forgetPassword(forgetPasswordDto);
		return forgetPasswordDto;
	}
	
	//RESPONSE TYPE METHOD IN WHICH WE CALLING RESET PASSWORD METHOD.
	@RequestMapping(path = "/reset-password" , method = RequestMethod.POST)
	public Response resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto)
	{
		response = userService.resetPassword(resetPasswordDto);
		return response;
	}
}
