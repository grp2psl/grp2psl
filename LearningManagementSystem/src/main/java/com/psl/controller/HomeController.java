package com.psl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.entity.Learner;
import com.psl.service.LearnerService;

@RestController
public class HomeController {
	
	@Autowired
	private LearnerService service;
	
	@GetMapping("/homepage")
	public String sayHello(){
		return "Hello World!";
	}
	
	@GetMapping("/learners/{id}")
	public Learner getLearner(@PathVariable int id) {
		return service.getLearner(id);
	}
	
	@PostMapping("/learners/register")
	public void addLearner(@RequestBody Learner l) {
		service.addLearner(l);
	}
}
