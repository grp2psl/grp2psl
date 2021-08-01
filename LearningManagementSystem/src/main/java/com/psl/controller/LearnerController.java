package com.psl.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.entities.Learner;
import com.psl.service.LearnerService;

@RestController
@RequestMapping("/learners")
public class LearnerController {
	@Autowired
	private LearnerService service;
	
	@GetMapping("/test")
	public String home(){
		return "home";
	}
	
	@GetMapping("/{id}")
	public Learner getLearner(@PathVariable int id) {
		return service.getLearner(id);
	}
	
	@PostMapping("/register")
	public void addLearner(@RequestBody Learner learner) {
		service.addLearner(learner);
	}
	
	/*For testing in postman: Enter url->body->form-data->key:file->select file from dropdown->select file*/
	@PostMapping("/register-multiple")
	public void addMultipleLearners(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		service.addMultipleLearners(csvFilePath);
	}
}
