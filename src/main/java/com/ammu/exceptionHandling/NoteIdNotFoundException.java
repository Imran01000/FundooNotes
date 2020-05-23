package com.ammu.exceptionHandling;

public class NoteIdNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	String message;
	String code;
	
	public NoteIdNotFoundException(String message , String code)
	{
		super(message);
		this.code = code;
	}
}
