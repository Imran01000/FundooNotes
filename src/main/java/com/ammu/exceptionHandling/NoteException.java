package com.ammu.exceptionHandling;

public class NoteException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	 String code;
	 String message;
	  public NoteException(String message , String code)
	  {
		  super(message);
		  this.code = code;
	  }
}
