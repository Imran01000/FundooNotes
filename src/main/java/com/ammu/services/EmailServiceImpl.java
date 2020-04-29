package com.ammu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService
{
	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendMail(String to, String subject, String text) 
	{
		SimpleMailMessage message = new SimpleMailMessage();
		try 
		{
			message.setTo(to);
			message.setFrom("is45934@gmail.com");
			message.setSubject(subject);
			message.setText(text);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
