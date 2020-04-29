package com.ammu.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService
{
	@Autowired
	JavaMailSender mailSender;

	@Override
	public void notificationService(String to, String subject, String text) 
	{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper;
			try 
			{
				helper = new MimeMessageHelper(message , true);
				helper.setTo(to);
				helper.setFrom("imshaikh01000@gmail.com");
				helper.setText(text);
			} 
			catch (MessagingException e) 
			{

				e.printStackTrace();
			}
			
			mailSender.send(message);	
	}

	
}
