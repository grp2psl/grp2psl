package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.psl.entities.TeacherCourseMapping;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TeacherCourseMappingTest {
	@Autowired 
	TeacherCourseMappingService service;
	
	@Test
	@Order(1)
	public void testAddTeacherCourseMapping() {
		TeacherCourseMapping c=new TeacherCourseMapping(2,2,3);
	    service.addTeacherCourseMapping(c);
	     
	    TeacherCourseMapping tcmapping=service.getByTrainerIdAndCourseId(2,2);
	    assertThat(tcmapping.getCourseId()).isEqualTo(2);
	    
	}
}
