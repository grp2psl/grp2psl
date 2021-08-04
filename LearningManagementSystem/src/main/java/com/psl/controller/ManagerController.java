package com.psl.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.entities.CourseOffering;
import com.psl.entities.Manager;
import com.psl.service.CourseOfferingService;
import com.psl.service.ManagerService;

@RestController
@RequestMapping("/managers")
public class ManagerController {
	@Autowired
	private ManagerService service;
	
	@Autowired
	private CourseOfferingService offeringService;
		
	@GetMapping("/{id}")
	public Manager getManager(@PathVariable int id) {
		return service.getManager(id);
	}
	
	@PostMapping("/register")
	public void addManager(@RequestBody Manager m) {
		service.addManager(m);
	}
	
	@PostMapping("/enroll-learner")
	public void enrollLearner(@RequestBody CourseOffering offering) throws ParseException {
		offeringService.enrollLearner(offering);
	}
	
	@PostMapping("/enroll-learners")
	public void enrollMultipleLearners(@RequestParam("file") MultipartFile csvFilePath) throws IOException, ParseException {
		offeringService.enrollMultipleLearners(csvFilePath);
	}
	
	@PutMapping("/update-test-scores/{id}")
	public void updateTestScore(@RequestBody CourseOffering offering, @PathVariable int id) {
		offeringService.updateTestScore(id, offering.getPercentage());
	}

	@PutMapping("/update-test-scores")
	public void updateMultipleTestScores(@RequestParam("file") MultipartFile csvFilePath) throws IOException, ParseException {
		offeringService.updateMultipleTestScores(csvFilePath);
	}
	
	@GetMapping("/course-offerings")
	public List<CourseOffering> viewCourseOfferings(){
		return offeringService.viewCourseOfferings();
	}
	
	@DeleteMapping("/course-offering/{id}")
	public void removeCourseOffering(@PathVariable int id) {
		offeringService.removeCourseOffering(id);		
	}
	
	@GetMapping("/generate-excel-enrolment")
	public void downloadFileFromLocalEnrolment() throws IOException {
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		offeringService.generateExcelForEnrolment(file.toString());
		System.out.println(file);
	}	

	@GetMapping("/generate-excel-score-update")
	public void downloadFileFromLocalScoreUpdate() throws IOException {
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		offeringService.generateExcelForScoreUpdate(file.toString());
		System.out.println(file);
	}
}
