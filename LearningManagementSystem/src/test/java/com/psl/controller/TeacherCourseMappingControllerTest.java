package com.psl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import com.psl.security.AdminUserDetails;
import com.psl.security.LearnerUserDetails;
import com.psl.security.RESTAuthenticationEntryPoint;
import com.psl.security.TrainerUserDetails;
import com.psl.service.TeacherCourseMappingService;

@WebMvcTest(TeacherCourseMappingController.class)
public class TeacherCourseMappingControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	TeacherCourseMappingService service;

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
	 * TEST ADDING TEACHER-COURSE MAPPING DETAILS
	 */
	@Test
	public void addTeacherCourseMappingTest() throws Exception {
		String request = "{\"tcid\":4,\"trainerid\":2,\"courseid\":1}";
		this.mvc.perform(post("/managers/teacherCourseMapping/register")
				.contentType(MediaType.APPLICATION_JSON).content(request))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST ADDING MULTIPLE TEACHER-COURSE MAPPING DETAILS
	 */
	@Test
	public void addMultipleTrainersTest() throws Exception {
		Path path = Paths.get("teachercoursemapping.xlsx");
		String name = "file";
		String originalFileName = "teachercoursemapping.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile file = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		this.mvc.perform(multipart("/managers/teacherCourseMapping/register-multiple").file((MockMultipartFile) file))
	    .andExpect(status().isOk());
	}
	
	/*
	 * TEST DOWNLOAD FORMAT OF EXCEL SHEET FOR UPLOADING MULTIPLE LEARNERS
	 */
	@Test
	public void downloadFileFromLocalTest() throws Exception {
		this.mvc.perform(get("/managers/teacherCourseMapping/generate-excel"))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST GET COURSE DETAILS BY COURSE ID AND LEARNER ID
	 */
	@Test
	public void getCourseDetailsByCourseIdAndLearnerIdTest() throws Exception {
		this.mvc.perform(get("/managers/teacherCourseMapping/learner/"+1+"/course/"+2))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST GET TRAINER AND COURSE NAMES FOR ALL TRAINER-COURSE MAPPINGS
	 */
	@Test
	public void getAllTrainerCourseNamesTest() throws Exception {
		this.mvc.perform(get("/managers/teacherCourseMapping/trainer-course-names"))
		.andExpect(status().isOk());
	}
}
