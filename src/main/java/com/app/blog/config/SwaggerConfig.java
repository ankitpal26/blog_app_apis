package com.app.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
	
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
				
	}

	private ApiInfo getInfo() {
		
		return new ApiInfo(" Blogging Application : Backed" , 
				"This project is developed by learnig purpose", "1.0", "Terms of Service",
				new Contact("Ankit", "apal@gmail.com", "9990989000"), 
				"License of APIs", "API license UPL",
				java.util.Collections.emptyList());
	}
}
