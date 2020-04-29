package com.ammu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ammu.dto.ForgetPasswordDto;
import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.dto.ResetPasswordDto;
import com.ammu.model.UserModel;
import com.ammu.repository.UserRepository;
import com.ammu.response.Response;
import com.ammu.security.utility.JwtToken;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository user;

	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;

	ModelMapper mapper = new ModelMapper();

	UserModel userModel = new UserModel();
	
	//IMPLEMENTATION FOR REGISTRATION FORM TO SAVE THE DATA.
	@Override
	public Response registration(RegistrationDto registrationDto) 
	{
		emailService.notificationService(registrationDto.getEmail(), "Thanks for registration!", 
				"Hey thankyou to connect with us hope you are doing good  "+JwtToken.generateToken(registrationDto));
		mapper.map(registrationDto, userModel);
		user.save(userModel);
		return new Response("Details successfully saved" , 200);
	}

	//IMPLEMENTATION OF METHOD FOR LOGIN.
	@Override
	public Response login(LoginDto loginDto) 
	{
		userModel = user.findByEmail(loginDto.getEmail());

		if(userModel == null)
		{
			return  new Response("Not valid",415);
		}
		return new Response("You Logged in", 202);
	}

	//IMPLEMENTATION OF METHOD FOR FORGET PASSWORD.
	@Override
	public Response forgetPassword(ForgetPasswordDto forgetPasswordDto)
	{
		userModel = user.findByEmail(forgetPasswordDto.getEmail());
		if(userModel != null)
		{
			return new Response("Its valid", 200);
		}
		return new Response("Please provide correct email", 415);
	}

	//IMPLEMENTATION OF METHOD FOR RESET PASSWORD.
	@Override
	public Response resetPassword(ResetPasswordDto resetPasswordDto)
	{
		userModel = user.findByEmail(resetPasswordDto.getConfirmPassword());
		if(userModel != null)
		{
			mapper.map(resetPasswordDto, userModel);
			user.save(userModel);
			return new Response("Sucessfully set new password", 200);
		}
		return new Response("Please provide correct email", 415);
	}

	
}
