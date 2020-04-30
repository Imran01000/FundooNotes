package com.ammu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource("classpath:message.properties")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository user;

	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	UserModel userModel;
	
	@Autowired
	Environment enviroment;
	
	//IMPLEMENTATION FOR REGISTRATION FORM TO SAVE THE DATA.
	@Override
	public Response registration(RegistrationDto registrationDto) 
	{
		emailService.notificationService(registrationDto.getEmail(), "Thanks for registration!", 
				"Hey thankyou to connect with us hope you are doing good  "+JwtToken.generateToken(registrationDto));
		mapper.map(registrationDto, userModel);
		user.save(userModel);
		return new Response( enviroment.getProperty("registration.success.text") , enviroment.getProperty("registration.success.code") );
	}

	//IMPLEMENTATION OF METHOD FOR LOGIN.
	@Override
	public Response login(LoginDto loginDto) 
	{
		userModel = user.findByEmail(loginDto.getEmail());

		if(userModel == null)
		{
			return  new Response(enviroment.getProperty("login.error.text") , enviroment.getProperty("login.error.code"));
		}
		return new Response(enviroment.getProperty("login.success.text") , enviroment.getProperty("login.success.code"));
	}

	//IMPLEMENTATION OF METHOD FOR FORGET PASSWORD.
	@Override
	public Response forgetPassword(ForgetPasswordDto forgetPasswordDto)
	{
		userModel = user.findByEmail(forgetPasswordDto.getEmail());
		if(userModel != null)
		{
			return new Response(enviroment.getProperty("forget.success.text"), enviroment.getProperty("forget.success.code"));
		}
		return new Response(enviroment.getProperty("forget.error.text"), enviroment.getProperty("forget.error.code"));
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
			return new Response(enviroment.getProperty("reset.success.text"), enviroment.getProperty("reset.success.code"));
		}
		return new Response(enviroment.getProperty("reset.error.text"), enviroment.getProperty("reset.error.code"));
	}

	
}
