package com.psl.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.entities.CourseOffering;
import com.psl.entities.Manager;
import com.psl.service.CourseService;
import com.psl.service.ManagerService;

@RestController
@RequestMapping("/managers")
public class ManagerController {
	@Autowired
	private ManagerService service;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/{id}")
	public Manager getManager(@PathVariable int id) {
		return service.getManager(id);
	}
	
	@PostMapping("/register")
	public void addManager(@RequestBody Manager m) {
		service.addManager(m);
	}
	
	@PostMapping("/enroll-learner")
	public void enrollLearner(@RequestBody CourseOffering offering) throws ParseException {
		courseService.enrollLearner(offering);
	}
	
	@PostMapping("/enroll-learners")
	public void enrollMultipleLearners(@RequestParam("file") MultipartFile csvFilePath) throws IOException, ParseException {
		courseService.enrollMultipleLearners(csvFilePath);
	}
}
