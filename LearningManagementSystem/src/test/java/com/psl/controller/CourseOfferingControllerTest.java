package com.psl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.psl.security.AdminUserDetails;
import com.psl.security.LearnerUserDetails;
import com.psl.security.RESTAuthenticationEntryPoint;
import com.psl.security.TrainerUserDetails;
//import com.psl.controller.CourseOfferingController;
import com.psl.service.LearnerService;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseOfferingControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	RESTAuthenticationEntryPoint authenticationEntryPoint;

	@MockBean
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@MockBean
	AdminUserDetails adminUserDetails;

	@MockBean
	LearnerUserDetails learnerUserDetails;
	
	@MockBean
	TrainerUserDetails trainerUserDetails;
	
	@MockBean
	private LearnerService service;
	
	// Test getting a specific course offering
	@Test
	public void getSpecificOfferingTest() throws Exception {
		this.mvc.perform(get("/learners/GetOffering/"+1))
		.andExpect(status().isOk());
	}
	
	// Test getting all course offerings of a specific learner
	@Test
	public void getAllOfferingsTest() throws Exception {
		this.mvc.perform(get("/learners/Offering/"+1))
		.andExpect(status().isOk());
	}
	
	// Test adding feedback to a course offering
	@Test
	public void sendFeedbackTest() throws Exception {
		String courseOffering = "{\"feedback\":\"good course\",\"ratings\":3}";
		this.mvc.perform(put("/learners/feedback/"+1).contentType(MediaType.APPLICATION_JSON).content(courseOffering))
		.andExpect(status().isOk());
	}
}
