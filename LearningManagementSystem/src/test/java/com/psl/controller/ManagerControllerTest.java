package com.psl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.multipart.MultipartFile;

import com.psl.security.AdminUserDetails;
import com.psl.security.LearnerUserDetails;
import com.psl.security.RESTAuthenticationEntryPoint;
import com.psl.security.TrainerUserDetails;
import com.psl.service.CourseOfferingService;
import com.psl.service.ManagerService;

@WebMvcTest(ManagerController.class)
public class ManagerControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	ManagerService service;
	
	@MockBean
	CourseOfferingService offeringService;
	
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
	 * TEST GET MANAGER BY ID
	 */
	@Test
	public void getManagerTest() throws Exception {
		int id = service.getNextId();
		this.mvc.perform(get("/managers/"+(id-1))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST ADD MANAGER
	 */
	@Test
	public void addmanagerTest() throws Exception {
		String request = "{\"name\":\"John Radnor\",\"email\":\"group2.learning.management.system@gmail.com\","
				+ "\"password\":\"admin123\",\"phonenumber\":\"9657892335\"}";
		this.mvc.perform(post("/managers/register")
				.contentType(MediaType.APPLICATION_JSON).content(request))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST UPDATE DETAILS OF MANAGER
	 */
	@Test
	public void updateManagerTest() throws Exception {
		String request = "{\"name\":\"John Radnor\",\"email\":\"group2.learning.management.system@gmail.com\","
				+ "\"password\":\"manager123\",\"phonenumber\":\"9657892335\"}";
		this.mvc.perform(put("/managers/update")
				.contentType(MediaType.APPLICATION_JSON).content(request))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST ENROLL A LEARNER TO A COURSE
	 */
	@Test
	public void enrollLearnerTest() throws Exception {
		String request = "{\"learnerid\":7,\"tcid\":1,\"startdate\":\"2021-09-01\",\"enddate\":\"2021-09-05\"}";
		this.mvc.perform(post("/managers/enroll-learner")
				.contentType(MediaType.APPLICATION_JSON).content(request))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST ENROLL MULTIPLE LEARNERS TO A COURSE
	 */
	@Test
	public void enrollLearnersTest() throws Exception {
		Path path = Paths.get("EnrollMultipleLearners.xlsx");
		String name = "file";
		String originalFileName = "EnrollMultipleLearners.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile file = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		this.mvc.perform(multipart("/managers/enroll-learners").file((MockMultipartFile) file))
	    .andExpect(status().isOk());
	}
	
	/*
	 * TEST UPDATE AN INDIVIDUAL TEST SCORE
	 */
	@Test
	public void updateTestScoreTest() throws Exception {
		int id = offeringService.getMaxId();
		String request = "{\"percentage\":67}";
		this.mvc.perform(put("/managers/update-test-scores/"+id)
				.contentType(MediaType.APPLICATION_JSON).content(request))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST UPDATE TEST SCORES OF MULTIPLE
	 */
	@Test
	public void updateMultipleTestScoresTest() throws Exception {
		Path path = Paths.get("update-score.xlsx");
		String name = "file";
		String originalFileName = "update-score.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MockMultipartFile file = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		@SuppressWarnings("deprecation")
		MockMultipartHttpServletRequestBuilder builder =
	            MockMvcRequestBuilders.fileUpload("/managers/update-test-scores");
	    builder.with(new RequestPostProcessor() {
	        @Override
	        public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
	            request.setMethod("PUT");
	            return request;
	        }
	    });
	    mvc.perform(builder
	            .file(file))
	            .andExpect(status().isOk());
	}
	
	/*
	 * TEST VIEW ALL COURSE OFFERINGS
	 */
	@Test
	public void viewCourseOfferingsTest() throws Exception{
		this.mvc.perform(get("/managers/course-offerings")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST DELETE A COURSE OFFERING BY ID
	 */
	@Test
	public void removeCourseOfferingTest() throws Exception {
		int id = offeringService.getMaxId();
		this.mvc.perform(delete("/managers/course-offering/"+id))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST VIEW A TRAINER's DETAILS, COURSES AND RESPECTIVE OFFERINGS TAKEN BY THE TRAINER
	 */
	@Test
	public void viewTrainerDetailsTest() throws Exception {
		this.mvc.perform(get("/managers/trainer/"+1))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST VIEW A COURSE's DETAILS, ITS OFFERINGS AND AVERAGE RATING OF THE TRAINER
	 */
	@Test
	public void viewCourseDetailsTest() throws Exception {
		this.mvc.perform(get("/managers/trainer/"+1+"/course/"+2))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST DOWNLOAD THE EXCEL FORMAT FOR MULTIPLE ENROLMENT
	 */
	@Test
	public void downloadFileFromLocalEnrolmentTest() throws Exception {
		this.mvc.perform(get("/managers/generate-excel-enrolment"))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST DOWNLOAD THE EXCEL FORMAT FOR UPDATING MULTIPLE SCORES
	 */
	@Test
	public void downloadFileFromLocalScoreUpdateTest() throws Exception {
		this.mvc.perform(get("/managers/generate-excel-score-update"))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST VIEW ALL COURSES ATTENDED BY LEARNER
	 */
	@Test
	public void viewCourseAttendedTest() throws Exception {
		this.mvc.perform(get("/managers/course-attended/"+1))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST UPDATE AN INDIVIDUAL TEST SCORE BY COURSE-OFFERING ID
	 */
	@Test
	public void updateTestScoreByCourseOfferingIdTest() throws Exception {
		this.mvc.perform(put("/managers/update-test-score/")
				.param("tcId", ""+0)
				.param("learnerId", ""+10001)
				.param("percentage", "70")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST VIEW COURSE OFFERING DETAILS
	 */
	@Test
	public void viewCourseOfferingsDetailsTest() throws Exception {
		this.mvc.perform(get("/managers/viewCourseOfferingsDetails"))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST VIEW COURSES ATTENDED BY LEARNER
	 */
	@Test
	public void viewCoursesAttendedTest() throws Exception {
		this.mvc.perform(get("/managers/courses-attended/"+1))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST FIND TEACHER-COURSE MAPPINGS BY LEARNER ID
	 */
	@Test
	public void findTeacherCourseMappingsByLearnerIdTest() throws Exception {
		this.mvc.perform(get("/managers/findTeacherCourseMappingsByLearnerId/"+1))
		.andExpect(status().isOk());
	}
	
	/*
	 * TEST FIND LEARNERS BY TCID
	 */
	@Test
	public void findLearnersByTcIdTest() throws Exception {
		this.mvc.perform(get("/managers/findLearnersByTcId/"+1))
		.andExpect(status().isOk());
	}

	/*
	 * TEST MANAGER LOGIN
	 */
	@Test
	public void loginTest() throws Exception {
		this.mvc.perform(get("/managers/findLearnersByTcId/"+1)
				.param("email", "admin@email.com")
				.param("password", "admin@123")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}
