package com.psl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.service.CourseOfferingService;
import com.psl.entities.CourseOffering;

@RestController
public class CourseOfferingController {
	@Autowired
	private CourseOfferingService service;
	
	@PutMapping("/feedback/{learnerid}/{tcid}")
	public void sendfeedback(@PathVariable int learnerid, @PathVariable int tcid ,@RequestBody String feedBack) {
		service.AddFeedback(learnerid, tcid, feedBack);
	}
	
	@GetMapping("/Offering/{learnerid}")
	public List<CourseOffering> getOfferings(@PathVariable int learnerid){
		return service.getCourseOfferings(learnerid);
	}
}
