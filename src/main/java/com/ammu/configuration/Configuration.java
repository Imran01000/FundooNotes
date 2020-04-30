package com.ammu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@org.springframework.context.annotation.Configuration
public class Configuration 
{
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer property()
	{
		//RETURN A TYPE OF CLASS INSTANCE.
		return new PropertySourcesPlaceholderConfigurer();
	}
}
