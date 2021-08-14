package com.psl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.psl.controller.CourseOfferingController;
import com.psl.service.LearnerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseOfferingControllerTests {
	
	@Autowired
	MockMvc mvc;
	
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
