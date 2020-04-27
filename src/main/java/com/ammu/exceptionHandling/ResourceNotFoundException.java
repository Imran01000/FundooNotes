package com.ammu.exceptionHandling;

public class ResourceNotFoundException extends Exception
{
	private static final long serialVersionUID = -4448340173602385841L;
	
	public ResourceNotFoundException()
	{
		super();
	}
	
	public ResourceNotFoundException(String message) 
	{
		super(message);
	}
	
	
}
