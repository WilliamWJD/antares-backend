package com.antares.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {

	private final ResponseMessage m200 = simpleMessage(200, "response.code200");
	private final ResponseMessage m201 = simpleMessage(201, "response.code201");
	private final ResponseMessage m204 = simpleMessage(204, "response.code204");

	private final ResponseMessage m400 = simpleMessage(400, "response.code400");
	private final ResponseMessage m401 = simpleMessage(401, "response.code401");
	private final ResponseMessage m404 = simpleMessage(404, "response.code404");

	private final ResponseMessage m500 = simpleMessage(500, "response.code500");


	private static final String RESOURCE_PACKAGE = "com.antares.resources";

	@Value("${swagger.info.title}")
	private String title;
	@Value("${swagger.info.description}")
	private String description;
	@Value("${swagger.info.version}")
	private String version;
	@Value("${swagger.info.termsOfServiceUrl}")
	private String termsOfServiceUrl;
	@Value("${swagger.info.contactName}")
	private String contactName;
	@Value("${swagger.info.contactUrl}")
	private String contactUrl;
	@Value("${swagger.info.contactEmail}")
	private String contactEmail;
	@Value("${swagger.info.license}")
	private String license;
	@Value("${swagger.info.licenseUrl}")
	private String licenseUrl;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false) // quais respostas dos metodos
				.globalResponseMessage(RequestMethod.GET, Arrays.asList(m200, m400, m401, m404, m500))
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m400, m401, m404, m500))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m200, m204, m401, m400, m404, m500))
				.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204, m400, m401, m404, m500)).select()
				.apis(RequestHandlerSelectors.basePackage(RESOURCE_PACKAGE)).paths(PathSelectors.any()).build()
				// passar o host de acordo com o profile
				// .host("http://elbtestedev-teste-cluster-1254441201.sa-east-1.elb.amazonaws.com:8003")
				.apiInfo(apiInfo());
	}

	private ResponseMessage simpleMessage(final int code, final String msg) {
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(title, description, version, termsOfServiceUrl,
				new Contact(contactName, contactUrl, contactEmail), license, licenseUrl, Collections.emptyList());
	}
}