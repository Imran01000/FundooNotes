package com.ammu.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

public class LoginDto 
{
	//DEFINE FIELDS.
	@NotNull(message = "Enter email")
	@Pattern(regexp = "[a-zA-Z0-9]*@[a-zA-Z]+[.][a-zA-Z]+")
	private String email;
	
	@NotNull(message = "Enter password")
	@Pattern(regexp = "[0-9A-Za-z]*")
	@Size(min = 6 , max = 10)
	private String password;
	
	//SETTER AND GETTER.
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
