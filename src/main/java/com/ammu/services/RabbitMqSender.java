package com.ammu.services;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender 
{
	@Autowired
	private AmqpTemplate rabbitTemplates;
	
	@Value("${spring.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${spring.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(String mail) {
		rabbitTemplates.convertAndSend(exchange, routingkey, mail);
		System.out.println("Mail has been send");
	}
}
