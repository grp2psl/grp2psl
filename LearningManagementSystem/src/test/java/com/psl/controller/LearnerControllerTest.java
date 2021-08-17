package com.psl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
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

import com.psl.entities.Learner;
import com.psl.security.AdminUserDetails;
import com.psl.security.LearnerUserDetails;
import com.psl.security.RESTAuthenticationEntryPoint;
import com.psl.security.TrainerUserDetails;
import com.psl.service.LearnerService;

@WebMvcTest(LearnerController.class)
public class LearnerControllerTest {
	@Autowired
	MockMvc mvc;
	@MockBean
	LearnerService service;
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
	 * TEST GET DETAILS OF LEARNER BY ID
	 */
	@Test
	public void getLearnerTest() throws Exception {
		int learnerid = service.getNextId();
		Learner learner = new Learner(learnerid, "Krishna Raj", "HR", "9876543212", "krishna@email.com", "shfgr"); // Password will be replaced
		service.addLearner(learner);
		this.mvc.perform(get("/learners/"+learnerid)
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	/*
	 * TEST GET DETAILS OF ALL LEARNERS
	 */
	@Test
	public void getAllLearnersTest() throws Exception{
		this.mvc.perform(get("/learners/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	/*
	 * TEST REGISTER LEARNER
	 */
	@Test
	public void addLearnerTest() throws Exception {
		String request = "{\"name\":\"John Radnor\",\"email\":\"krishna@email.com\","
				+ "\"department\":\"HR\",\"phonenumber\":\"9876543212\"}";
		this.mvc.perform(post("/managers/learners/register")
				.contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(status().isOk());
	}

	/*
	 * TEST REGISTER MULTIPLE LEARNERS BY UPLOADING EXCEL FILE
	 */
	@Test
	public void addMultipleLearnersTest() throws Exception {
		String basePath = new File("").getAbsolutePath();
		basePath = new File(basePath).getParent();
		Path path = Paths.get(basePath + "\\learners.xlsx");
		String name = "file";
		String originalFileName = "learners.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile file = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		this.mvc.perform(multipart("/managers/learners/register-multiple").file((MockMultipartFile) file))
	      .andExpect(status().isOk());		
	}
	
	/*
	 * TEST DELETE LEARNER BY ID
	 */
	@Test
	public void removeLearnerTest() throws Exception {
		int id = service.getNextId() - 1;
		this.mvc.perform(delete("/managers/learners/"+id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	/*
	 * TEST UPDATE LEARNER BY ID
	 */
	@Test
	public void updateLearnerTest() throws Exception {
		String request = "{\"name\":\"John Radnor\",\"email\":\"krishna@email.com\","
				+ "\"department\":\"HR\",\"phonenumber\":\"9876543212\"}";
		this.mvc.perform(put("/managers/learners/update")
				.contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(status().isOk());
	}

	/*
	 * TEST DOWNLOAD FORMAT OF EXCEL SHEET FOR UPLOADING MULTIPLE LEARNERS
	 */
	@Test
	public void downloadFileFromLocalTest() throws Exception {
		this.mvc.perform(get("/managers/learners/generate-excel")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	/*
	 * TEST LEARNER LOGIN
	 */
	@Test
	public void loginTest() throws Exception {
		this.mvc.perform(get("/learners/findLearnersByTcId/"+1)
				.param("email", "learner@email.com")
				.param("password", "learner@123")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}
