package com.ammu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ammu.dto.ForgetPasswordDto;
import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.model.UserModel;
import com.ammu.repository.UserRepository;
import com.ammu.response.Response;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserRepository user;

	//USES MODEL MAPPER CLASS TO TRANSFER PROPERTIES FROM DTO TO ENTITY.
	ModelMapper mapper = new ModelMapper();

	//CREATED USER ENTITY OBJECT.
	UserModel userModel;

	@Override
	public Response registration(RegistrationDto registrationDto) 
	{

		mapper.map(registrationDto, userModel);
		user.save(userModel);
		return new Response("Details successfully saved" , 200);
	}

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

	@Override
	public Response forgetPassword(ForgetPasswordDto forgetPasswordDto)
	{
		String pwd;
		userModel = user.findByEmail(forgetPasswordDto.getEmail());
		if(userModel != null)
		{
			pwd = user.findByPassword(userModel.getPassword()).toString();
		}
		else
		{
			return new Response("Please enter correct email", 415);
		}

		return new Response("Your password is" +pwd+" ", 200 );
	}
}
