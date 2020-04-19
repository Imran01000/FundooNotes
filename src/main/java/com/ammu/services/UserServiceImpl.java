package com.ammu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ammu.dto.RegistrationDto;
import com.ammu.model.UserModel;
import com.ammu.repository.UserRepository;
import com.ammu.response.Response;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserRepository user;
	
	ModelMapper mapper = new ModelMapper();

	UserModel userModel = new UserModel(); 
	
	@Override
	public Response registration(RegistrationDto registrationDto) 
	{
		
		mapper.map(registrationDto, userModel);
		user.save(userModel);
		return new Response("Details successfully saved" , 200);
	}



}
