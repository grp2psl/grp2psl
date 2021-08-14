package com.psl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningManagementSystemApplication {

	public static final Logger LOGGER = LoggerFactory.getLogger(LearningManagementSystemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LearningManagementSystemApplication.class, args);
		LOGGER.info("Started Learning Management System");
	}

}
