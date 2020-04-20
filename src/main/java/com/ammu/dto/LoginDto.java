package com.ammu.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginDto 
{
	//DEFINE FIELDS.
	private String email;
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
