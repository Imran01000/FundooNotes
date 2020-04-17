package com.ammu.services;

import java.util.Optional;

import com.ammu.model.RegistrationModel;

public interface UserService
{
	public Optional<RegistrationModel> findUserByEmail(String mail);
	public void save(RegistrationModel model);
}
