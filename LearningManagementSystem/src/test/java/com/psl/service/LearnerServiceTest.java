package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

import com.psl.entities.Learner;
import com.psl.dao.ICourseAttended;
import com.psl.entities.CourseAttended;
@SpringBootTest
public class LearnerServiceTest {
	@Autowired
	LearnerService service;
	
	
	
	
	
//	@Test
//	public void addLearnerTest() {
//		Learner learner = new Learner();
//		learner.setLearnerid(123);
//		learner.setName("Vaishnavi");
//		learner.setEmail("vaishnavi10march2000@gmail.com");
//		learner.setDepartment("L&D");
//		learner.setPhonenumber("9657892335");
//		service.addLearner(learner);
//		assertNotNull(service.getLearner(123));
//	}
//	
//	@Test
//	public void courseAttendedTest() {
//		 List<CourseAttended> courses = (List<CourseAttended>) service.viewCourseAttended(1);
//		 assertThat(courses).size().isGreaterThan(0);
//		 for(CourseAttended co:courses) {
//			 assertThat(co.getLearnerid()).isEqualTo(1);
//		 }
//	}
	
	@Test
	public void courseAttendedTest() {
		 CourseAttended course = (CourseAttended) service.viewScoreAndStatus(1,1);
		 assertThat(course.getLearnerid()).isEqualTo(1);
		 assertThat(course.getCourseid()).isEqualTo(1);
		 
	}
}
