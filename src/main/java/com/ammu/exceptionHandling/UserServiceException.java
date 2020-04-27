package com.ammu.exceptionHandling;

public class UserServiceException extends Exception
{
	//DEFINE serialVersionUID.
	private static final long serialVersionUID = 1892206139887556123L;
	
	//DEFINE UNPARAMATRIZED CONSTRUCTOR.
	public UserServiceException()
	{
		super();
	}
	
	//DEFINE PARAMATRIZED CONSTRUCTOR.
	public UserServiceException(String message)
	{
		super(message);
	}

}
