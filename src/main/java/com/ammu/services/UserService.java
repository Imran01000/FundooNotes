package com.ammu.services;

import com.ammu.dto.ForgetPasswordDto;
import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.dto.ResetPasswordDto;
import com.ammu.exceptionHandling.UserServiceException;
import com.ammu.response.Response;


public interface UserService 
{
	//DECLARED METHODS.
	public Response registration(RegistrationDto registrationDto) throws UserServiceException;
	public Response login(LoginDto loginDto) throws UserServiceException;
	public Response forgetPassword(ForgetPasswordDto forgetPasswordDto) throws UserServiceException;
	public Response resetPassword(ResetPasswordDto resetPasswordDto , String emailToken) throws UserServiceException;
	public Response verification(String emailToken);
}
