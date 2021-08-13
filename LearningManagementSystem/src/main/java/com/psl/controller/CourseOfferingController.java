/*
 * Defines CourseOfferingController which handles various url requests 
 * related to CourseOffering.
 */
package com.psl.controller;

//Importing required imports for CourseOfferingController Definition.
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CourseOfferingController {

	//Autowiring with CourseOfferingService
	@Autowired
	private CourseOfferingService service;

	public static final Logger LOGGER = LoggerFactory.getLogger(CourseOfferingController.class);
	private final String logPrefix = "Course Offering Controller - ";
	
	/*
	 * This part handles put requests from url's ending with /feedback/{learnerid}/{tcid} pattern
	 * It adds feedback of given course offering (identified with tcid) by given learner (identified with learnerid)
	 */
	@PutMapping("/feedback/{learnerid}/{tcid}")
	public void sendfeedback(@PathVariable int learnerid, @PathVariable int tcid ,@RequestBody String feedBack) {
		LOGGER.info(logPrefix+"PUT /feedback/{learnerid}/{tcid} called to add a course offering feeback given by learner");
		service.AddFeedback(learnerid, tcid, feedBack);
	}
	
	/*
	 * This part handles get requests from url's ending with /Offering/{learnerid} pattern
	 * It retrieves List of Offerings of given learner
	 */
	@GetMapping("/Offering/{learnerid}")
	public List<CourseOffering> getOfferings(@PathVariable int learnerid){
		LOGGER.info(logPrefix+"GET /Offering/{learnerid} called to view offerings by learner ID");
		return service.getCourseOfferings(learnerid);
	}
}
