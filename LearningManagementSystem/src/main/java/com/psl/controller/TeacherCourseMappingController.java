package com.psl.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.entities.TeacherCourseMapping;
import com.psl.service.TeacherCourseMappingService;

@RestController
@RequestMapping("/teacherCourseMapping")
@CrossOrigin(origins="http://localhost:3000")
public class TeacherCourseMappingController {
	
	@Autowired
	private TeacherCourseMappingService service;
	
	/*
	 *ADDING TEACHER-COURSE MAPPING DETAILS
	 */
	@PostMapping("/register")
	public void addTeacherCourseMapping(@RequestBody TeacherCourseMapping teacherCourseMapping) {
		System.out.println(teacherCourseMapping);
		service.addTeacherCourseMapping(teacherCourseMapping);
	}
	
	/*
	 *ADDING MULTIPLE TEACHER-COURSE MAPPING DETAILS
	 */
	@PostMapping("/register-multiple")
	public void addMultipleTrainers(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		service.addMultipleTeacherCourseMapping(csvFilePath);
	}

}
