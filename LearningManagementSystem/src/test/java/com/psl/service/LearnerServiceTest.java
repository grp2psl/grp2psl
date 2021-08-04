package com.psl.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.psl.controller.LearnerController;
import com.psl.entities.Learner;

@SpringBootTest
public class LearnerServiceTest {
	@Autowired
	LearnerService service;
	
	@Test
	public void addLearnerTest() {
		Learner learner = new Learner();
		learner.setLearnerid(123);
		learner.setName("Vaishnavi");
		learner.setEmail("vaishnavi10march2000@gmail.com");
		learner.setDepartment("L&D");
		learner.setPhonenumber("9657892335");
		service.addLearner(learner);
		assertNotNull(service.getLearner(123));
	}
}
