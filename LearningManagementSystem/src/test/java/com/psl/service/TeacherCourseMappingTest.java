package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.psl.entities.TeacherCourseMapping;

@SpringBootTest
public class TeacherCourseMappingTest {
	@Autowired 
	TeacherCourseMappingService service;
	
	@Test
	public void testAddTeacherCourseMapping() {
		TeacherCourseMapping c=new TeacherCourseMapping(2,2,3);
	    service.addTeacherCourseMapping(c);
	     
	    TeacherCourseMapping tcmapping=service.getByTrainerIdAndCourseId(2,2);
	    assertThat(tcmapping.getCourseId()).isEqualTo(2);
	    
	}
}
