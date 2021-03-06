package com.ammu.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ammu.dto.ForgetPasswordDto;
import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.dto.ResetPasswordDto;
import com.ammu.exceptionHandling.ForgetPasswordException;
import com.ammu.exceptionHandling.LoginException;
import com.ammu.exceptionHandling.RegistrationException;
import com.ammu.exceptionHandling.ResetPasswordException;
import com.ammu.model.UserModel;
import com.ammu.repository.UserRepository;
import com.ammu.response.Response;
import com.ammu.security.utility.JwtToken;
import com.google.common.base.Optional;

@Service
@PropertySource("classpath:message.properties")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository user;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserModel userModel;

	@Autowired
	private Environment enviroment;

	//DEFINE VARIABLE TO STORE TOKEN.
	private String token;

	//DEFINE VARIABLE TO STORE EMAIL.
	private String email;
	
	//IMPLEMENTATION FOR REGISTRATION FORM TO SAVE THE DATA.
	@Override
	public Response registration(RegistrationDto registrationDto) 
	{
		//URL FOR USER VERFICATION.
		String urlRegistration = enviroment.getProperty("url.verfication");

		//CONDITION TO CHECK USER ALREADY PRESENT OR NOT.
		if(user.findByEmail(registrationDto.getEmail()).isPresent())	
			throw new RegistrationException("User is already present", "400");
	

		//EMAIL SERVICE.
		emailService.notificationService(registrationDto.getEmail(), enviroment.getProperty("message.gmail.registration"), 
				enviroment.getProperty("message.gmail.subject")+urlRegistration+JwtToken.generateUserToken(userModel.getId()));

		//STORING DATA BY MODEL MAPPER.
		 mapper.map(registrationDto, userModel);
		
		
		//DATA SAVED USING USER SERVICE REFRENCE VARIABLE.
		user.save(userModel);

		return new Response( enviroment.getProperty("registration.success.text") , enviroment.getProperty("registration.success.code"));
	}

	//IMPLEMENTATION OF METHOD FOR LOGIN.
	@Override
	public Response login(LoginDto loginDto)
	{
		//CONDITION TO CHECK USER EMAIL AMD PASSWORD.
		if(user.findByEmail(loginDto.getEmail()).isPresent() && user.findByPassword(loginDto.getPassword()).isPresent())
		{
			token = JwtToken.generateUserToken(userModel.getId());
			return new Response(enviroment.getProperty("login.success.text") , token);
		}
		else if(!user.findByEmail(loginDto.getEmail()).isPresent())

		{
			throw new LoginException("User is null", "400");
		}

			return  new Response(enviroment.getProperty("login.error.text") , enviroment.getProperty("login.error.code"));

	}

	//IMPLEMENTATION OF METHOD FOR FORGET PASSWORD.
	@Override
	public Response forgetPassword(ForgetPasswordDto forgetPasswordDto)
	{
		//URL FOR USER RESET PASSWORD.
		String urlForgetPassword = enviroment.getProperty("url.forgetpassword");

		//VARIABLE TO STORE TOKEN. 
		token = JwtToken.generateUserToken(userModel.getId());

		//STORING EMAIL.
		email = forgetPasswordDto.getEmail();

		//CONDTION TO CHECK THE VALIDATE USER EMAIL.
		if(user.findByEmail(forgetPasswordDto.getEmail()).isPresent())
		{
			//SENDED MAIL SERVICE TO RESPECTIVE USER.
			emailService.notificationService(forgetPasswordDto.getEmail(), enviroment.getProperty("message.gmail.forget"), 
					urlForgetPassword+token);

			return new Response(enviroment.getProperty("forget.success.text"), enviroment.getProperty("forget.success.code"));
		}
		else if(!user.findByEmail(forgetPasswordDto.getEmail()).isPresent())
		{
			throw new ForgetPasswordException("your email is not valid", "400");
		}

		return new Response(enviroment.getProperty("forget.error.text"), enviroment.getProperty("forget.error.code"));
	}

	//IMPLEMENTATION OF METHOD FOR RESET PASSWORD.
	@Override
	public Response resetPassword(ResetPasswordDto resetPasswordDto , String emailToken) 
	{	
		System.out.println(token);
		System.out.println(emailToken);

		if(emailToken.equals(token))
		{
			//STORING EMAIL.
			Optional<UserModel> userEmail = user.findByEmail(email);

			//CONDITION TO CHECK WHEATHER THE USER IS PRESENT.
			if(userEmail.isPresent())
			{
				//CONDITION TO CHECK PASSWORD IS MATCHED OR NOT.
				if(resetPasswordDto.getNewPassword().equals(resetPasswordDto.getConfirmPassword()))
				{
					//SET PASSWORD FOR RESPECTIVE EMAIL.
					userEmail.get().setPassword(resetPasswordDto.getNewPassword());

					//SAVE THE DATA TO DATABASE.
					user.save(userEmail.get());

					return new Response(enviroment.getProperty("reset.success.text"), enviroment.getProperty("reset.success.code"));
				}
				else
				{
					throw new ResetPasswordException(enviroment.getProperty("reset.error.notMatch.text"), enviroment.getProperty("reset.error.notMatch.code"));
				}
			}
		}
		return new Response(enviroment.getProperty("reset.error.text"), enviroment.getProperty("reset.error.code"));
	}

	@Override
	public Response verification(String emailToken)
	{ 
		//CONDITION TO CHECK THE USER VERFICATION BY TOKEN.
		if(emailToken.equals(token))
		{
			//METHOD TO NOTIFY THAT USER IS VERFIED.
			userModel.setVerified(true);

			//SAVED USERMODEL ENTITY.
			user.save(userModel);

			return new Response(enviroment.getProperty("verification.success.text"), enviroment.getProperty("verification.success.code"));
		} 
		return new Response(enviroment.getProperty("verification.error.text"), enviroment.getProperty("verification.error.code"));
	}
}
