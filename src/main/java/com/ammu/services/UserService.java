package com.ammu.services;

import org.springframework.mail.SimpleMailMessage;

import com.ammu.dto.ForgetPasswordDto;
import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.dto.ResetPasswordDto;
import com.ammu.response.Response;

public interface UserService 
{
	//DECLARED METHODS.
	public Response registration(RegistrationDto registrationDto);
	public Response login(LoginDto loginDto);
	public Response forgetPassword(ForgetPasswordDto forgetPasswordDto);
	public Response resetPassword(ResetPasswordDto resetPasswordDto);
	public void sendMail(SimpleMailMessage email);
}
