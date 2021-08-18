package com.psl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Learning Management System", version = "1.0", description = "Platform to automate learning process"))
public class LearningManagementSystemApplication {

	public static final Logger LOGGER = LoggerFactory.getLogger(LearningManagementSystemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LearningManagementSystemApplication.class, args);
		LOGGER.info("Started Learning Management System");
	}

}
