/*
 * Contains tests for learner and courseoffering entities
 */
package com.psl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;

import com.psl.dao.ICourseOfferingDAO;
import com.psl.dao.ILearnerDAO;
import com.psl.entities.CourseOffering;
import com.psl.entities.Learner;

@ComponentScan(basePackages = "com.psl")
//@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LearnerServiceTest2 {

	@Test
	void contextLoads() {
	}
	
	//Autowires service variable to LearnerService interface
	@Autowired
	private LearnerService service;
	
	//Declares a mock bean dao of type learnerDAO interface) 
	@MockBean
	private ILearnerDAO dao;
	
	
	//Autowires service2 variable to CourseOfferingService interface
	@Autowired
	private CourseOfferingService service2;
	
	//Declares a mock bean dao2 of type ICourseOfferingDAO interface
	@MockBean
	private ICourseOfferingDAO dao2;
	
	//Testing addition of learner
	@Test
	@Rollback(false)
	@Order(1)
	public void testAddLearner() {		
		Learner l = new Learner(12, "Shiva", "3454654342", "Mathematics", "shiva2@gmail.com", "shivapass");
		when(dao.save(l)).thenReturn(l);
		when(dao.findById(l.getLearnerId())).thenReturn(Optional.of(l));
		assertEquals(l,service.getLearner(l.getLearnerId()));
	}

	//Tests retrieval of learner
	@Test
	@Order(2)
	public void testFindLearnerById() {
		Learner l = new Learner(12, "Shiva", "3454654342", "Mathematics", "shiva2@gmail.com", "shivapass");
		System.out.println(l.getLearnerId());
		when(dao.findById(l.getLearnerId())).thenReturn(Optional.of(l));
		assertEquals(l,service.getLearner(l.getLearnerId()));
	}
	
	//Tests function which lists all course offerings
	@Test
	@Rollback(false)
	@Order(3)
	public void testListCourseOffering() {
		CourseOffering c1 = new CourseOffering(1, null, 3, new Date(), new Date(), 12, 1,
				"enrolled", 75);
		CourseOffering c2 = new CourseOffering(2, "learnt to apply new problem solving techniques", 4, new Date(), new Date(), 12, 2,
				"registered", 65);
		List<CourseOffering> offerings = new ArrayList<CourseOffering>();
		offerings.add(c1);
		offerings.add(c2);
		dao2.save(c1);
		dao2.save(c2);

		when(dao2.findByLearnerId(12)).thenReturn(offerings);
		assertEquals(offerings, service2.getCourseOfferings(12));
	}
	
	//Test send feedback operation
	@Test
	@Order(4)
	public void testSendFeedback() {
		String feedback = "It was a good experience learnt many things in the course";
		int rating = 4;
		
		CourseOffering c1 = new CourseOffering();
		c1.setLearnerId(12);
		c1.setTcId(1);
		c1.setPercentage(0);
		c1.setRatings(0);		
				
		//when(dao2.findByTcIdAndLearnerId(c1.getTcId(), c1.getLearnerId())).thenReturn(c1);
		when(dao2.findById(c1.getTcId())).thenReturn(Optional.of(c1));
		when(dao2.save(c1)).thenReturn(c1);
		assertEquals(service2.addFeedbackCourseOfferingId(1, feedback, rating).getFeedback(), feedback);
		assertEquals(service2.addFeedbackCourseOfferingId(1, feedback, rating).getRatings(), rating);		
	}
	
	//Testing change credentials operation
	@Test
	@Order(5)
	@Rollback(false)
	public void changeCredentials() {
		Learner l = new Learner();
		l.setLearnerId(12);
		l.setName("Raj");
		l.setEmail("rag@gmail.com");
		l.setPassword("RajPass");
		
//		String email = "newemail@test.com";
		String password = "new password";
		
		when(dao.findById(l.getLearnerId())).thenReturn(Optional.of(l));
		when(dao.save(l)).thenReturn(l);
		l.setPassword("new password");
		
		assertEquals(service.updateLearnerPassword(12, password), l);
	}
	
	//Testing the delete operation
	@Test
	@Rollback(false)
	@Order(6)
	public void testDeleteLearner() {
		Learner l = new Learner();
		l.setLearnerId(12);
		l.setName("Raj");
		l.setEmail("rag@gmail.com");
		l.setPassword("RajPass");
		
		service.deleteLearner(l.getLearnerId());
	    verify(dao, times(1)).deleteById(l.getLearnerId());
	}
	
}
