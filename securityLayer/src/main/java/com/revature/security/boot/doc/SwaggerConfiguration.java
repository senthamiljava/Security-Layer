package com.revature.security.boot.doc;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select().apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error"))).build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Security Layer REST API", "Security REST service made with Spring Boot in Java", "1.0",
				"Terms of service",
				new Contact("Revature", "https://revature.in/", "senthamild@revature.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}

}