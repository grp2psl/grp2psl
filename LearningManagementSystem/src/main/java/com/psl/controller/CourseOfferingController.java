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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.service.CourseOfferingService;
import com.psl.entities.Course;
import com.psl.entities.CourseOffering;

/*Annotation to enable CourseOfferingController to act as a RestController
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
	@PutMapping("/learners/feedback/{courseOfferingId}")
	public void sendfeedback(@PathVariable int courseOfferingId ,@RequestBody CourseOffering CO) {
		System.out.println(CO);
		service.addFeedbackCourseOfferingId(courseOfferingId, CO.getFeedback(), CO.getRatings());
	}
	
	@PutMapping("/learners/feedback/{learnerid}/{tcid}")
	public void sendfeedback(@PathVariable int learnerid, @PathVariable int tcid ,@RequestBody String feedBack) {
		LOGGER.info(logPrefix+"PUT /feedback/{learnerid}/{tcid} called to add a course offering feeback given by learner");
		service.addFeedback(learnerid, tcid, feedBack);
	}
	
	/*
	 * This part handles get requests from url's ending with /Offering/{learnerid} pattern
	 * It retrieves List of Offerings of given learner
	 */
	@GetMapping("/learners/Offering/{learnerid}")
	public List<CourseOffering> getOfferings(@PathVariable int learnerid){
		LOGGER.info(logPrefix+"GET /Offering/{learnerid} called to view offerings by learner ID");
		return service.getCourseOfferings(learnerid);
	}
	
	@GetMapping("/learners/GetOffering/{courseOfferingId}")
	public CourseOffering getOffering(@PathVariable int courseOfferingId) {
		return service.getCourseOffering(courseOfferingId);
	}
	@GetMapping("/learners/GetCourseDetails/{tcId}")
	public Course getCourseDetails(@PathVariable int tcId){
		return service.viewCourseDetailsBytcId(tcId);
	}
}
