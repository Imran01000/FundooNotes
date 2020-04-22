package com.ammu.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class ResetPasswordDto
{
	//DEFINE FIELD.
	@NotNull(message = "Enter email")
	@Pattern(regexp = "[a-zA-Z0-9]*@[a-zA-Z]+[.][a-zA-Z]+")
	private String email;
	
	@NotNull(message = "Enter password")
	@Pattern(regexp = "[0-9A-Za-z]*")
	@Size(min = 6 , max = 10)
	private String newPassword;

	//SETTER AND GETTER.
	public String getNewPassword() {
		return newPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
