package com.psl.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import com.psl.service.LearnerService;

@WebMvcTest(LearnerController.class)
public class LearnerControllerTest {
	@Autowired
	MockMvc mvc;
	@MockBean
	LearnerService service;
	@Test
	public void test_home() throws Exception{
		this.mvc.perform(get("/learners/test")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}

}
