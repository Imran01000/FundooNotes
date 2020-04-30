package com.ammu;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FundooNotesApplication
{

	public static void main(String[] args) 
	{
		SpringApplication.run(FundooNotesApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguartion()
	{
		//RETURN A DOCKET INSTANCE.
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/user/*"))
				.build() 
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails()
	{
		//RETURN A ApiInfo INSTANCE.
		return new ApiInfo(
				"Fundoo notes api",
				"Sample API for notes",
				"2.0", 
				"Free to use",
				new springfox.documentation.service.Contact("Imran Shaikh", "http://ammuworld.io", "imshaikh@.com"),
				"API license",
				"http://ammuworld.io",
				Collections.emptyList());
	}
}
