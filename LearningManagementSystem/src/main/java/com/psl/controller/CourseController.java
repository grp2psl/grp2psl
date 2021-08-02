package com.psl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.entities.Course;
import com.psl.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService service;
	
	@GetMapping("/")
	public String home(){
		return "home";
	}
	
	@GetMapping("/allcourses")
    public List<Course> getCourse () {
        return service.findAll();
    }
	
	@GetMapping("/{id}")
	public Course getCourse(@PathVariable int id) {
		return service.getCourse(id);
	}
	
	@PostMapping("/addcourse")
	public Course addCourse(@RequestBody Course course) {
		return service.addCourse(course);
	}
	
}
