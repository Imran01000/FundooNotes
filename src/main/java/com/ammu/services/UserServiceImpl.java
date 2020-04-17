package com.ammu.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ammu.model.RegistrationModel;
import com.ammu.repository.UserRepository;

public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<RegistrationModel> findUserByEmail(String mail) 
	{
		return userRepository.findByEmail(mail);
	}

	@Override
	public void save(RegistrationModel model)
	{
		userRepository.save(model);
	}

}
