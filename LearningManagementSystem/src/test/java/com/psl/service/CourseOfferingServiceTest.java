package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psl.entities.CourseOffering;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CourseOfferingServiceTest {

	@Autowired
	CourseOfferingService service;
	
	@Test
	@Order(1)
	public void testEnrollLearner() throws ParseException, JsonProcessingException, JsonMappingException{
		int id = service.getMaxId();
		String request = "{\"learnerid\":12,\"tcid\":1,\"startdate\":\"2021-09-01\",\"enddate\":\"2021-09-05\"}";
		ObjectMapper mapper = new ObjectMapper();
		CourseOffering offering = mapper.readValue(request, CourseOffering.class);
		service.enrollLearner(offering);
		CourseOffering result = service.getCourseOffering(service.getMaxId());
		assertThat(result.getLearnerid()).isEqualTo(12);
		assertThat(result.getTcid()).isEqualTo(1);
		assertThat(result.getCourseofferingid()).isEqualTo(id+1);
	}
	
	@Test
	@Order(2)
	public void testUpdateTestScore() {
		service.updateTestScore(service.getMaxId(), 67);
		CourseOffering result = service.getCourseOffering(service.getMaxId());
		assertThat(result.getLearnerid()).isEqualTo(12);
		assertThat(result.getPercentage()).isEqualTo(67);
		assertThat(result.getFeedback()).isEqualTo(null);
		assertThat(result.getStatus()).isEqualTo("FAIL,FEEDBACK_PENDING");
	}
	
	@Test
	@Order(3)
	public void testViewCourseOfferings() {
		List<CourseOffering> offerings = service.viewCourseOfferings();
		assertThat(offerings).size().isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	public void testRemoveCourseOffering() {
		int id = service.getMaxId();
		service.removeCourseOffering(id);
		CourseOffering offering = service.getCourseOffering(service.getMaxId());
		assertThat(offering.getCourseofferingid()).isEqualTo(id-1);
	}
}
