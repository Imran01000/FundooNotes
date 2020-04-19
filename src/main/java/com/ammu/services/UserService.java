package com.ammu.services;

import com.ammu.dto.RegistrationDto;
import com.ammu.response.Response;

public interface UserService 
{
	public Response registration(RegistrationDto registrationDto);
}
