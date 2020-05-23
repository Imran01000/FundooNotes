package com.ammu.exceptionHandling;

public class RegistrationException extends RuntimeException 
{
	
	private static final long serialVersionUID = -3932049665551953807L;

	String message;
	String code;
	
	public RegistrationException(String message , String code)
	{
		super(message);
		this.code = code;
	}
}
