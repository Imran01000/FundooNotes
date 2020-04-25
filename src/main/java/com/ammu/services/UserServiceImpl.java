package com.ammu.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
	private JavaMailSender javaMailSender;
	
	@Autowired
	JwtToken jwtToken;
	
	@Autowired
	UserService userService;

	//USES MODEL MAPPER CLASS TO TRANSFER PROPERTIES FROM DTO TO ENTITY.
	ModelMapper mapper = new ModelMapper();

	//CREATED USER ENTITY OBJECT.
	UserModel userModel = new UserModel();

	//IMPLEMENTATION FOR REGISTRATION FORM TO SAVE THE DATA.
	@Override
	public Response registration(RegistrationDto registrationDto) 
	{
		userService.sendMail(registrationDto.getEmail(), "Do Registration", "click to proceed"+JwtToken.generateToken(registrationDto));
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
		userService.sendMail(resetPasswordDto.getConfirmPassword(), "reset password" , "click to proceed"+JwtToken.generateToken(resetPasswordDto));
		userModel = user.findByEmail(resetPasswordDto.getConfirmPassword());
		if(userModel != null)
		{
			mapper.map(resetPasswordDto, userModel);
			user.save(userModel);
			return new Response("Sucessfully set new password", 200);
		}
		return new Response("Please provide correct email", 415);
	}

	@Override
	public void sendMail(String to, String subject, String text) 
	{
		try
		{
			SimpleMailMessage message = new SimpleMailMessage(); 
			message.setTo(to); 
			message.setSubject(subject); 
			message.setFrom("is45934@gmail.com");
			message.setText(text);
			javaMailSender.send(message);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
