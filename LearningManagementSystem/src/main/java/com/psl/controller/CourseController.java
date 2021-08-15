package com.psl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.entities.Course;
import com.psl.service.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins="http://localhost:3000")
public class CourseController {
	@Autowired
	private CourseService service;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
	private final String logPrefix = "Course Controller - ";

	/*
	 *VIEW ALL COURSES AVAILABLE
	 */
	@GetMapping("/allcourses")
    public List<Course> getCourse () {
		LOGGER.info(logPrefix+"GET /allcourses called to view all courses");
		return service.findAll();
    }
	
	/*
	 *VIEW A COURSE BY COURSEID
	 */
	@PreAuthorize("hasAnyRole('ROLE_TRAINER', 'ROLE_ADMIN', 'ROLE_LEARNER')")
	@GetMapping("/{courseId}")
	public Course getCourse(@PathVariable int courseId) {
		LOGGER.info(logPrefix+"GET /{courseId} called view details of a course by ID");
		return service.getCourse(courseId);
	}
	
	/*
	 *ADDING A COURSE DETAILS
	 */
	@PostMapping("/addcourse")
	public Course addCourse(@RequestBody Course course) {
		LOGGER.info(logPrefix+"POST /addcourse called to add a course");
		return service.addCourse(course);
	}
	
	/*
	 * DELETE COURSE BY ID
	 */
	@DeleteMapping("/delete/{courseid}")
	public void removeCourse(@PathVariable int courseid) {
		LOGGER.info(logPrefix+"DELETE /delete/{courseid} called to delete a course by ID");
		service.removeCourse(courseid);
	}

	/*
	 * UPDATE COURSE BY ID
	 */
	@PutMapping("/update")
	public void updateCourse(@RequestBody Course course) {
		LOGGER.info(logPrefix+"PUT /update called to update details of a course by ID");
		service.updateCourse(course);
	}
	
}
