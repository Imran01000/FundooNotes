package com.ammu.dto;

import org.springframework.stereotype.Component;

@Component
public class ForgetPasswordDto 
{
	//FIELDS DEFINE.
	private String email;
	
	//SETTER AND GETTER.
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
