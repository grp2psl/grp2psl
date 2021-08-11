/*
 * Defines CourseOfferingController which handles various url requests 
 * related to CourseOffering.
 */
package com.psl.controller;

//Importing required imports for CourseOfferingController Definition.
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.service.CourseOfferingService;
import com.psl.entities.CourseOffering;

/*Annotation to enable CourseOfferingController to act as a RestController
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CourseOfferingController {

	//Autowiring with CourseOfferingService
	@Autowired
	private CourseOfferingService service;
	
	/*
	 * This part handles put requests from url's ending with /feedback/{learnerid}/{tcid} pattern
	 * It adds feedback of given course offering (identified with tcid) by given learner (identified with learnerid)
	 */
	@PutMapping("/feedback/{learnerid}/{tcid}")
	public void sendfeedback(@PathVariable int learnerid, @PathVariable int tcid ,@RequestBody String feedBack) {
		service.AddFeedback(learnerid, tcid, feedBack);
	}
	
	/*
	 * This part handles get requests from url's ending with /Offering/{learnerid} pattern
	 * It retrieves List of Offerings of given learner
	 */
	@GetMapping("/Offering/{learnerid}")
	public List<CourseOffering> getOfferings(@PathVariable int learnerid){
		return service.getCourseOfferings(learnerid);
	}
}
