package com.ammu.exceptionHandling;

public class ForgetPasswordException extends RuntimeException
{
	private static final long serialVersionUID = 4654883253672292512L;

	String message;
	String code;
	
	public ForgetPasswordException(String message , String code)
	{
		super(message);
		this.code = code;
	}
}
