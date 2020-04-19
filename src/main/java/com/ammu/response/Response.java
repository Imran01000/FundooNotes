package com.ammu.response;

public class Response 
{
	private String messageStatus;
	private int messageCode;
	
	public Response(String messageStatus, int messageCode) {
		super();
		this.messageStatus = messageStatus;
		this.messageCode = messageCode;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	public int getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}
	
}
