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

import com.psl.entities.Trainer;
import com.psl.security.AdminUserDetails;
import com.psl.security.LearnerUserDetails;
import com.psl.security.RESTAuthenticationEntryPoint;
import com.psl.security.TrainerUserDetails;
import com.psl.service.CourseService;
import com.psl.service.TrainerService;

@WebMvcTest(TrainerController.class)
public class TrainerControllerTest {
	@Autowired
	MockMvc mvc;
	@MockBean
	TrainerService service;
	@MockBean
	CourseService courseService;
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
	 * TEST GET DETAILS OF TRAINER BY ID
	 */
	@Test
	public void getTrainerTest() throws Exception {
		int trainerid = service.getNextId();
		Trainer trainer = new Trainer(trainerid, "Krishna Raj", "HR", "9876543212", "krishna@email.com", "shfgr"); // Password will be replaced
		service.addTrainer(trainer);
		this.mvc.perform(get("/trainers/"+trainerid)
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	/*
	 * TEST GET DETAILS OF ALL TRAINERS
	 */
	@Test
	public void getAllTrainersTest() throws Exception{
		this.mvc.perform(get("/trainers/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	/*
	 * TEST REGISTER TRAINER
	 */
	@Test
	public void addTrainerTest() throws Exception {
		String request = "{\"name\":\"John Radnor\",\"email\":\"krishna@email.com\","
				+ "\"department\":\"HR\",\"phonenumber\":\"9876543212\"}";
		this.mvc.perform(post("/managers/trainers/register")
				.contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(status().isOk());
	}

	/*
	 * TEST REGISTER MULTIPLE TRAINERS BY UPLOADING EXCEL FILE
	 */
	@Test
	public void addMultipleTrainersTest() throws Exception {
		String basePath = new File("").getAbsolutePath();
		basePath = new File(basePath).getParent();
		Path path = Paths.get(basePath + "\\trainers.xlsx");
		String name = "file";
		String originalFileName = "trainers.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile file = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		this.mvc.perform(multipart("/managers/trainers/register-multiple").file((MockMultipartFile) file))
	      .andExpect(status().isOk());
		
	}
	
	/*
	 * TEST DELETE TRAINER BY ID
	 */
	@Test
	public void removeTrainerTest() throws Exception {
		int id = service.getNextId() - 1;
		this.mvc.perform(delete("/managers/trainers/"+id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	/*
	 * TEST UPDATE TRAINER BY ID
	 */
	@Test
	public void updateTrainerTest() throws Exception {
		String request = "{\"name\":\"John Radnor\",\"email\":\"krishna@email.com\","
				+ "\"department\":\"HR\",\"phonenumber\":\"9876543212\"}";
		this.mvc.perform(put("/managers/trainers/update")
				.contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(status().isOk());
	}

	/*
	 * TEST DOWNLOAD FORMAT OF EXCEL SHEET FOR UPLOADING MULTIPLE TRAINERS
	 */
	@Test
	public void downloadFileFromLocalTest() throws Exception {
		this.mvc.perform(get("/managers/trainers/generate-excel")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


}
