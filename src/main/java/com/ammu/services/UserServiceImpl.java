package com.ammu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.model.LoginModel;
import com.ammu.model.RegistrationModel;
import com.ammu.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	UserRepository userRepo;

	@Override
	public String loginUser(LoginDto loginDto)
	{
		LoginModel model = new LoginModel();
		ModelMapper mapper = new ModelMapper();
		LoginDto dto = new LoginDto();
		userRepo.save(model);
		mapper.map(dto, model);
		return "Successfully save";
	}







}
