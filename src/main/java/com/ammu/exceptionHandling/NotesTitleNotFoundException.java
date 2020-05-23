package com.ammu.exceptionHandling;

public class NotesTitleNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	String code;
	public NotesTitleNotFoundException(String message , String code)
	{
		super(message);
		this.code = code;
	}
}
