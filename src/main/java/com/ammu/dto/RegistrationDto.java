package com.ammu.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size; 
import org.springframework.stereotype.Component;

@Component
public class RegistrationDto 
{
	//DEFINE FIELDS.
	@NotBlank(message = "Enter first name")
	@Size(min = 3 , max = 15)
	@Pattern(regexp = "[A-Z][a-z]*")
	private String fname;
	
	@NotBlank(message = "Enter last name")
	@Size(min = 3 , max = 15)
	@Pattern(regexp = "[A-Z][a-z]*")
	private String lname;
	
	@NotNull(message = "Enter email")
	@Pattern(regexp = "[a-zA-Z0-9]*@[a-zA-Z]+[.][a-zA-Z]+")
	private String email;
	
	@NotNull(message = "enter contact number")
	@Pattern(regexp = "[7-9][0-9]{9}")
	private String mobileNo;
	
	@NotNull(message = "Enter password")
	@Pattern(regexp = "[0-9A-Za-z]*")
	@Size(min = 6 , max = 10)
	private String password;
	
	//SETTER AND GETTER.
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
