package com.ammu.controller;

import javax.validation.Valid;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.ForgetPasswordDto;
import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.dto.ResetPasswordDto;
import com.ammu.repository.UserRepository;
import com.ammu.response.Response;
import com.ammu.security.utility.JwtToken;
import com.ammu.services.UserService;

import jdk.jshell.Snippet.Status;

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
	@PostMapping(path = "/registration")
	public ResponseEntity<Response> registration(@Valid @RequestBody RegistrationDto registrationDto)
	{
		response = userService.registration(registrationDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	//RESPONSE TYPE METHOD IN WHICH WE CALLING USER LOGIN METHOD.  
	@PostMapping(path = "/login")
	@ResponseBody
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto loginDto)
	{
		response = userService.login(loginDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	//RESPONSE TYPE METHOD IN WHICH WE CALLING FORGET PASSWORD METHOD,
	@PostMapping(path = "/forget-password") 
	public ResponseEntity<Response> forgetPassword(@Valid @RequestBody ForgetPasswordDto forgetPasswordDto)
	{
		response = userService.forgetPassword(forgetPasswordDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//RESPONSE TYPE METHOD IN WHICH WE CALLING RESET PASSWORD METHOD.
	@PutMapping(path = "/reset-password")
	public ResponseEntity<Response> resetPassword( @Valid @RequestBody ResetPasswordDto resetPasswordDto)
	{
		response = userService.resetPassword(resetPasswordDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping(path = "/verification")
	@ResponseBody
	public ResponseEntity<Response> userVerification(@RequestParam(name = "id") String id) 
	{

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
}
