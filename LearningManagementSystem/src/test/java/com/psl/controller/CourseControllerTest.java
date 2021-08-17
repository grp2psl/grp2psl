package com.psl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.psl.security.AdminUserDetails;
import com.psl.security.LearnerUserDetails;
import com.psl.security.RESTAuthenticationEntryPoint;
import com.psl.security.TrainerUserDetails;
import com.psl.service.CourseService;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	CourseService service;

	@MockBean
	RESTAuthenticationEntryPoint authenticationEntryPoint;

	@MockBean
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@MockBean
	AdminUserDetails adminUserDetails;

	@MockBean
	LearnerUserDetails learnerUserDetails;
	
	@MockBean
	TrainerUserDetails trainerUserDetails;
	
	/*
	 * TEST VIEW ALL COURSES AVAILABLE
	 */
	@Test
	public void getCoursesTest() throws Exception {
		this.mvc.perform(get("/managers/course/allcourses"))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST VIEW A COURSE BY COURSEID
	 */
	@Test
	public void getCourseTest() throws Exception {
		this.mvc.perform(get("/managers/course/"+1))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST ADDING A COURSE DETAILS
	 */
	@Test
	public void addCourseTest() throws Exception {
		String request = "{\"coursename\":\"C++\",\"prerequisite\":\"Basic Programming\","
				+ "\"syllabus\":\"OOPs concepts\",\"duration\":\"8 hr\"}";
		this.mvc.perform(post("/managers/course/addcourse")
				.contentType(MediaType.APPLICATION_JSON).content(request))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST UPDATE COURSE BY ID
	 */
	@Test
	public void updateCourseTest() throws Exception {
		String request = "{\"coursename\":\"C++\",\"prerequisite\":\"Basic Programming\","
				+ "\"syllabus\":\"OOPs concepts\",\"duration\":\"12 hr\"}";
		this.mvc.perform(put("/managers/course/update")
				.contentType(MediaType.APPLICATION_JSON).content(request))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST DELETE COURSE BY ID
	 */
	@Test
	public void removeCourseTest() throws Exception {
		this.mvc.perform(delete("/managers/course/delete/"+1))
		.andExpect(status().isOk());
	}
}
