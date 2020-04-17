package com.ammu.controller;

import java.awt.PageAttributes.MediaType;


import javax.print.attribute.standard.Media;
import javax.ws.rs.core.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.RegistrationDto;
import com.ammu.model.LoginModel;
import com.ammu.model.RegistrationModel;
import com.ammu.repository.UserRepository;

@RestController
@RequestMapping("/user")  // http://localhost:8082/user
public class AppController 
{
	@RequestMapping("/home")
	@ResponseBody
	public String home()
	{
		return "yes its working";
	}
	//ADDED REQUEST MAPPING FOR LOGIN (http://localhost:8082/user/login?name="xyz"&password="***").
	@RequestMapping("/login") 
	public LoginModel login(@RequestParam(value="name")String name1,String password)
	{
		LoginModel loginModel = new LoginModel();
		loginModel.setUserName(name1);
		loginModel.setPassword(password);
		return loginModel;
	}

	//ADDED REQUEST MAPPING FOR REGISTRATION.
	@RequestMapping("/registration")
	public RegistrationModel registration()
	{
		RegistrationModel registrationModel = new RegistrationModel();
		registrationModel.setFname("faisal");
		registrationModel.setLname("shaikh");
		registrationModel.setMail("faisal678@gmail.com");
		registrationModel.setPassword("45678987");
		return registrationModel;
	}

	//AUTOWIRED WHICH GIVE BEAN OBJECT TO UserRepsitory interface.
	@Autowired
	UserRepository user;

	//POST MAPPING TO CHECK THE DATABASE CONNECTIVITY(http://localhost:8082//user/add?name="xyz"&pwd="***"). 
	@PostMapping(path="/add")
	@ResponseBody
	public RegistrationDto addRegistration( String fname, String lname, String mail,String password)
	{
		RegistrationModel model = new RegistrationModel();
		model.setFname(fname);
		model.setLname(lname);
		model.setMail(mail);
		model.setPassword(password);
		user.save(model);
		RegistrationDto dto = new RegistrationDto();
		ModelMapper mapper = new ModelMapper();
		mapper.map(model, dto);
		return dto;	
	}
}
