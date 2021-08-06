/*
 * Defines LearnerController which handles various url requests 
 * related to Learner.
 */
package com.psl.controller;

//Importing required imports for Learner Controller Definition.
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.entities.Learner;
import com.psl.service.LearnerService;

/*Annotation to enable LearnerController to act as a RestController 
 * Which handles all requests on url's having "/learners" prefix
 */
@RestController
@RequestMapping("/learners")
//Definition of the class
public class LearnerController {
	
	//Autowiring with LearnerService
	@Autowired
	private LearnerService service;
	
	/*
	 * This part handles get requests from url's having /learners/id pattern
	 * Where id is learner Id
	 * It gets learner details with given learnerId.
	 */
	@GetMapping("/{id}")
	public Learner getLearner(@PathVariable int id) {
		return service.getLearner(id);
	}
	
	/*
	 * This part handles post requests from url's ending with /learners/register pattern
	 * It registers a new Learner using addLearner function
	 */
	@PostMapping("/register")
	public void addLearner(@RequestBody Learner l) {
		service.addLearner(l);
	}
	
	/*
	 * This part handles put requests from url's ending with /learners/update/id pattern
	 * Where id is learner Id.
	 * It updates credentials of Learner with given id.
	 */
	@PutMapping("/update/{id}")
	public void updateLearner(@PathVariable int id, @RequestBody Map<String, String> credentials) {
		service.updateLearner(id, credentials.get("email"), credentials.get("password"));
	}
	
}
