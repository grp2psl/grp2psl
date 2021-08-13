package com.psl.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.entities.CourseOffering;
import com.psl.entities.TeacherCourseMapping;
import com.psl.service.TeacherCourseMappingService;

@RestController
@RequestMapping("/teacherCourseMapping")
@CrossOrigin(origins="http://localhost:3000")
public class TeacherCourseMappingController {
	
	@Autowired
	private TeacherCourseMappingService service;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(TeacherCourseMappingController.class);
	private final String logPrefix = "Teacher Course Mapping Controller - ";
	
	/*
	 *ADDING TEACHER-COURSE MAPPING DETAILS
	 */
	@PostMapping("/register")
	public void addTeacherCourseMapping(@RequestBody TeacherCourseMapping teacherCourseMapping) {
		LOGGER.info(logPrefix+"POST /register called to add teacher course mapping details");
		System.out.println(teacherCourseMapping);
		service.addTeacherCourseMapping(teacherCourseMapping);
	}
	
	/*
	 *ADDING MULTIPLE TEACHER-COURSE MAPPING DETAILS
	 */
	@PostMapping("/register-multiple")
	public void addMultipleTrainers(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		LOGGER.info(logPrefix+"POST /register-multiple called to add multiple teacher-course mappings details");
		service.addMultipleTeacherCourseMapping(csvFilePath);
	}
	
	/*
	 * DOWNLOAD FORMAT OF EXCEL SHEET FOR UPLOADING MULTIPLE LEARNERS
	 */
	@GetMapping("/generate-excel")
	public void downloadFileFromLocal() throws IOException {
		LOGGER.info(logPrefix+"GET /generate-excel called to download excel format for uploading multiple learners");
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		service.generateExcel(file.toString());
		System.out.println(file);
	}
	
	@GetMapping("/learner/{id}/course/{courseid}")
	public List<CourseOffering> getCourseDetailsByCourseIdAndLearnerId(@PathVariable int id, @PathVariable int courseid ) {
		LOGGER.info(logPrefix+"GET /learner/{id}/course/{courseid} called to view Course details by Course ID and learner ID");
		List<CourseOffering> courseOffering = service.getCourseOffering(id, courseid);
		return courseOffering;
	}
	
	/*
	 * GET TRAINER AND COURSE NAMES FOR ALL TRAINER-COURSE MAPPINGS
	 */
	@GetMapping("/trainer-course-names")
	public List<Object> getAllTrainerCourseNames() {
		LOGGER.info(logPrefix+"GET /trainer-course-names called to view trainer and course names for all trainer-course mappings");
		return service.getAllTrainerCourseNames();
	}

}
