package com.ammu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.LoginDto;
import com.ammu.dto.RegistrationDto;
import com.ammu.model.RegistrationModel;
import com.ammu.repository.UserRepository;

@RestController
@RequestMapping("/user")  // http://localhost:8082/user
public class AppController 
{

	//AUTOWIRED WHICH GIVE BEAN OBJECT TO UserRepsitory interface.
	@Autowired
	UserRepository user;

	//ADDED REQUEST MAPPING FOR LOGIN (http://localhost:8082/user/login?name="xyz"&password="***").
	@RequestMapping("/login") 
	public LoginDto login(@RequestParam(value="name")String name,String password)
	{
		LoginDto loginDto = new LoginDto();
		loginDto.setUserName(name);
		loginDto.setPassword(password);
		return loginDto;
	}

	//POST MAPPING TO CHECK THE DATABASE CONNECTIVITY(http://localhost:8082//user/?name="xyz"&pwd="***"). 
	@PostMapping(path="/registration")
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
