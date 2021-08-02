package com.psl.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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
public class TeacherCourseMappingController {
	
	@Autowired
	private TeacherCourseMappingService service;
	
	@PostMapping("/register")
	public void addTeacherCourseMapping(@RequestBody TeacherCourseMapping teacherCourseMapping) {
		service.addTeacherCourseMapping(teacherCourseMapping);
	}
	
	@PostMapping("/register-multiple")
	public void addMultipleTrainers(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		service.addMultipleTeacherCourseMapping(csvFilePath);
	}

}
