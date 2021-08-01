package com.psl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public void addLearner(@RequestBody Learner l) {
		service.addLearner(l);
	}
}
