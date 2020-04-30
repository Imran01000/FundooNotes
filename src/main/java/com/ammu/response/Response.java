package com.ammu.response;

import org.springframework.stereotype.Component;

@Component
public class Response 
{
	//DEFINE FIELDS.
	private String messageStatus;
	private String messageCode;
	
	public Response()
	{
		super();
	}
	
	//DEFINE PARAMETRIZED CONSTRUCTOR.
	public Response(String messageStatus, String messageCode) {
		super();
		this.messageStatus = messageStatus;
		this.messageCode = messageCode;
	}
	
	//SETTER AND GETTER.
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
}
