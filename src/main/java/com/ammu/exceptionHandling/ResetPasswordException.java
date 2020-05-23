package com.ammu.exceptionHandling;

public class ResetPasswordException extends RuntimeException
{
	private static final long serialVersionUID = -701922704273416617L;
	
	String message;
	String code;
	
	public ResetPasswordException(String message , String code)
	{
		super(message);
		this.code = code;
	}
}
