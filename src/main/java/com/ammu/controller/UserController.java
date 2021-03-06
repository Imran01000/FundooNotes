package com.ammu.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.ForgetPasswordDto;
import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.dto.ResetPasswordDto;
import com.ammu.response.Response;
import com.ammu.services.UserService;


@RestController   
@RequestMapping(path = "/user")          // http://localhost:8082/user
@CrossOrigin(origins = "*")
public class UserController 
{
	//USER SERVICE AUTOWIRED TO GET THE OBJECT FROM JAVA BEAN CLASS.
	@Autowired
	private UserService userService;

	private Response response;
	
	//RESPONSE TYPE METHOD IN WHICH WE CALLING USER REGISTRATION METHOD.  
	@PostMapping(value = "/registration") 
	public ResponseEntity<Response> registration(@Valid @RequestBody RegistrationDto registrationDto)																																
	{	
		
		response = userService.registration(registrationDto);	
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	//RESPONSE TYPE METHOD IN WHICH WE CALLING USER LOGIN METHOD.  
	@PostMapping(path = "/login")
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto loginDto) 
	{
		response = userService.login(loginDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	//RESPONSE TYPE METHOD IN WHICH WE CALLING FORGET PASSWORD METHOD,
	@PostMapping(path = "/forgetpassword") 
	public ResponseEntity<Response> forgetPassword(@Valid @RequestBody ForgetPasswordDto forgetPasswordDto)
	{
		response = userService.forgetPassword(forgetPasswordDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//RESPONSE TYPE METHOD IN WHICH WE CALLING RESET PASSWORD METHOD.
	@PutMapping(path = "/resetpassword/{token}")
	public ResponseEntity<Response> resetPassword( @Valid @RequestBody ResetPasswordDto resetPasswordDto , @PathVariable(name = "token") String emailToken)
	{
		response = userService.resetPassword(resetPasswordDto , emailToken);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/verification/{token}")
	public ResponseEntity<Response> userVerification(@PathVariable("token") String emailToken) 
	{
		response = userService.verification(emailToken);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
}
