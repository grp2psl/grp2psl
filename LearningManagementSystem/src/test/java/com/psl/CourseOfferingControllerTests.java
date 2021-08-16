package com.psl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.psl.security.RESTAuthenticationEntryPoint;
//import com.psl.controller.CourseOfferingController;
import com.psl.service.LearnerService;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseOfferingControllerTests {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	RESTAuthenticationEntryPoint authenticationEntryPoint;
	
	@MockBean
	private LearnerService service;
	
	// Test getting a specific course offering
	@Test
	public void getSpecificOfferingTest() throws Exception {
		this.mvc.perform(get("/GetOffering/"+1))
		.andExpect(status().isOk());
	}
	
	// Test getting all course offerings of a specific learner
	@Test
	public void getAllOfferingsTest() throws Exception {
		this.mvc.perform(get("/Offering/"+1))
		.andExpect(status().isOk());
	}
	
	// Test adding feedback to a course offering
	@Test
	public void sendFeedbackTest() throws Exception {
		String courseOffering = "{\"feedback\":\"good course\",\"ratings\":3}";
		this.mvc.perform(put("/feedback/"+1).contentType(MediaType.APPLICATION_JSON).content(courseOffering))
		.andExpect(status().isOk());
	}
}
