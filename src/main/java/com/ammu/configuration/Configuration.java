package com.ammu.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@org.springframework.context.annotation.Configuration
public class Configuration 
{
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer property()
	{
		//RETURN A TYPE OF CLASS INSTANCE.
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
}
