package com.psl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		
	/*
	 *VIEW ALL COURSES AVAILABLE
	 */
	@GetMapping("/allcourses")
    public List<Course> getCourse () {
        return service.findAll();
    }
	
	/*
	 *VIEW A COURSE BY COURSEID
	 */
	@GetMapping("/{courseId}")
	public Course getCourse(@PathVariable int courseId) {
		return service.getCourse(courseId);
	}
	
	/*
	 *ADDING A COURSE DETAILS
	 */
	@PostMapping("/addcourse")
	public Course addCourse(@RequestBody Course course) {
		return service.addCourse(course);
	}
	
}
