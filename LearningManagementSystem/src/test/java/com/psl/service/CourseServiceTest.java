package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.List;

import org.hamcrest.beans.HasProperty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.psl.entities.Course;
import com.psl.service.CourseService;

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
	    assertThat(course.getCourseid()).isEqualTo(369);
	    
	}
	
	@Test
	@Order(3)
	public void testGetCourse() {
	    Course course=service.getCourse(2);
	    assertThat(course.getCourseid()).isEqualTo(2);
	    
	}
	
}
