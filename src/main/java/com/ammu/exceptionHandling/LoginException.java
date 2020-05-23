package com.ammu.exceptionHandling;

public class LoginException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	String message;
	String code; 
	
	public LoginException(String message , String code)
	{
		super(message);
		this.code = code;
	}
}
