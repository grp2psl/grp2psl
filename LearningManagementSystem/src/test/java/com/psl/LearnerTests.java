/*
 * Contains tests for learner and courseoffering entities
 */
package com.psl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;

import com.psl.dao.ICourseOfferingDAO;
import com.psl.dao.ILearnerDAO;
import com.psl.entities.CourseOffering;
import com.psl.entities.Learner;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class LearnerTests {

	@Test
	void contextLoads() {
	}
	
	//Autowires dao to learner dao interfacr
	@Autowired 
	private ILearnerDAO dao;
	
	//Autowires dao2 to courseoffering dao interface
	@Autowired
	private ICourseOfferingDAO dao2;
	
	//Testing addition of learner
	@Test
	@Rollback(false)
	@Order(1)
	public void testAddLearner() {		
		Learner savedLearner = dao.save(new Learner(12, "Shiva", "3454654342", "Mathematics", "shiva2@gmail.com", "shivapass")); 
	    assertThat(savedLearner.getLearnerId()).isGreaterThan(0);
	}

	//Tests retrieval of learner
	@Test
	@Order(2)
	public void testFindLearnerById() {
		Learner l = dao.findById(12).get();
		assertThat(l.getName()).isEqualTo("Shiva");
	}
	
	//Tests course offering
	@Test
	@Rollback(false)
	@Order(3)
	public void testListCourseOffering() {
		CourseOffering c1 = new CourseOffering(null, 3, new Date(), new Date(), 12, 1,
				"enrolled", 75);
		CourseOffering c2 = new CourseOffering("learnt to apply new problem solving techniques", 4, new Date(), new Date(), 12, 2,
				"registered", 65);
		List<CourseOffering> offerings = new ArrayList<CourseOffering>();
		offerings.add(c1);
		offerings.add(c2);
		dao2.save(c1);
		dao2.save(c2);
		List<CourseOffering> offeringsRetrieved = dao2.findByLearnerId(12);
		assertThat(offerings).isEqualTo(offeringsRetrieved);
	}
	
	//Test send feedback operation
	@Test
	@Order(4)
	public void testSendFeedback() {
		CourseOffering c1 = dao2.findByTcIdAndLearnerId(1, 12);
		String feedback = "It was a good experience learnt many things in the course";
		c1.setFeedback(feedback);
		dao2.save(c1);
		
		CourseOffering updatedc1 = dao2.findByTcIdAndLearnerId(1, 12);
		assertThat(updatedc1.getFeedback()).isEqualTo(feedback);
	}
	
	//Testing change credentials operation
	@Test
	@Order(5)
	@Rollback(false)
	public void changeCredentials() {
		Learner l = dao.findById(12).get();
		l.setEmail("newemail@test.com");
		l.setPassword("new password");
		
		dao.save(l);
		
		Learner updatedl = dao.findById(12).get();
		assertThat(updatedl.getEmail()).isEqualTo("newemail@test.com");
		assertThat(updatedl.getPassword()).isEqualTo("new password");		
	}
	
	//Testing the delete operation
	@Test
	@Rollback(false)
	@Order(6)
	public void testDeleteLearner() {
	    Learner l = dao.findById(12).get();
	    dao.deleteById(l.getLearnerId());
	    assertFalse(dao.existsById(l.getLearnerId()));
	}
	
}
