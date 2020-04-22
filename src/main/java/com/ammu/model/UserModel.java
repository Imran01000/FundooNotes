package com.ammu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserModel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id" , unique = true , updatable = false, nullable = false )
	private int id;
	
	@Column(name = "first_name" , nullable = false)
	private String fname;
	
	@Column(name = "last_name" , nullable = false)
	private String lname;
	
	@Column(name = "pwd" ,nullable = false)
	private String password;
	
	@Column(name = "email" , nullable = true , unique = true)
	private String email;
	
	@Column(name = "contact_no" , nullable = false , unique = true)
	private long mobileNo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNO() {
		return mobileNo;
	}
	public void setMobileNO(long mobileNO) {
		this.mobileNo= mobileNO;
	}
	
	
}
