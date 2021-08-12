package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.psl.entities.Course;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CourseServiceTest {
	@Autowired
	CourseService service;
	
	@Test
	@Order(1)
	public void testListProducts() {
	    List<Course> courses = (List<Course>) service.findAll();
	    assertThat(courses).size().isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	public void testAddCourse() {
		Course c=new Course(369,"C++","Basic programming","OOPs concepts","8 hr");
	    service.addCourse(c);
	     
	    Course course=service.getCourse(369);
	    assertThat(course.getCourseId()).isEqualTo(369);
	    
	}
	
	@Test
	@Order(3)
	public void testGetCourse() {
	    Course course=service.getCourse(2);
	    assertThat(course.getCourseId()).isEqualTo(2);
	    
	}
	
}
