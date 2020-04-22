package com.ammu.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class ForgetPasswordDto 
{
	//FIELDS DEFINE.
	@NotNull(message = "Enter email")
	@Pattern(regexp = "[a-zA-Z0-9]*@[a-zA-Z]+[.][a-zA-Z]+")
	private String email;
	
	//SETTER AND GETTER.
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
