package com.psl.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.psl.entities.CourseOffering;
import com.psl.entities.Learner;
import com.psl.entities.Manager;
import com.psl.entities.TeacherCourseMapping;
import com.psl.service.CourseOfferingService;
import com.psl.service.ManagerService;

@RestController
@RequestMapping("/managers")
@CrossOrigin(origins="http://localhost:3000")
public class ManagerController {
	@Autowired
	private ManagerService service;
	
	@Autowired
	private CourseOfferingService offeringService;
	
	
		
	/*
	 * GET DETAILS OF MANAGER BY ID
	 */
	@GetMapping("/{id}")
	public Manager getManager(@PathVariable int id) {
		return service.getManager(id);
	}
	
	/*
	 * ADD A MANAGER
	 */
	@PostMapping("/register")
	public void addManager(@RequestBody Manager m) {
		service.addManager(m);
	}
	
	/*
	 * UPDATE DETAILS OF MANAGER
	 */
	@PutMapping("/update")
	public void updateManager(@RequestBody Manager manager) {
		service.updateManager(manager);
	}
	
	/*
	 * ENROLL A LEARNER TO A COURSE
	 * REQUEST BODY CONTENTS : {leanerid, tcid, startdate, enddate}
	 */
	@PostMapping("/enroll-learner")
	public void enrollLearner(@RequestBody CourseOffering offering) throws ParseException {
		offeringService.enrollLearner(offering);
	}
	
	/*
	 * ENROLL MULTIPLE LEARNERS TO A COURSE
	 * REQUEST PARAM WILL BE AN EXCEL FILE, FOR FILE CONTENTS USE /generate-excel-enrolment
	 */
	@PostMapping("/enroll-learners")
	public void enrollMultipleLearners(@RequestParam("file") MultipartFile csvFilePath) throws IOException, ParseException {
		offeringService.enrollMultipleLearners(csvFilePath);
	}
	
	/*
	 * UPDATE AN INDIVIDUAL TEST SCORE
	 * REQUEST BODY CONTENTS : {percentage}
	 */
	@PutMapping("/update-test-scores/{id}")
	public void updateTestScore(@RequestBody CourseOffering offering, @PathVariable int id) {
		offeringService.updateTestScore(id, offering.getPercentage());
	}

	/*
	 * UPDATE TEST SCORES OF MULTIPLE
	 * REQUEST PARAM WILL BE AN EXCEL FILE, FOR FILE CONTENTS USE /generate-excel-score-update
	 */
	@PutMapping("/update-test-scores")
	public void updateMultipleTestScores(@RequestParam("file") MultipartFile csvFilePath) throws IOException, ParseException {
		offeringService.updateMultipleTestScores(csvFilePath);
	}
	
	/*
	 * VIEW ALL COURSE OFFERINGS
	 */
	@GetMapping("/course-offerings")
	public List<CourseOffering> viewCourseOfferings(){
		return offeringService.viewCourseOfferings();
	}
	
	/*
	 * DELETE A COURSE OFFERING BY ID
	 */
	@DeleteMapping("/course-offering/{id}")
	public void removeCourseOffering(@PathVariable int id) {
		offeringService.removeCourseOffering(id);		
	}
	
	/*
	 * VIEW A TRAINER's DETAILS, COURSES AND RESPECTIVE OFFERINGS TAKEN BY THE TRAINER 
	 */
	@GetMapping("/trainer/{id}")
	public Map<String, Object> viewTrainerDetails(@PathVariable int id) {
		return offeringService.viewTrainerDetails(id);
	}
	
	/*
	 * VIEW A COURSE's DETAILS, ITS OFFERINGS AND AVERAGE RATING OF THE TRAINER
	 */
	@GetMapping("/trainer/{id}/course/{course_id}")
	public Map<String, Object> viewCourseDetails(@PathVariable int id, @PathVariable int course_id) {
		return offeringService.viewCourseDetailsByTrainerId(id, course_id);
	}
	
	/*
	 * DOWNLOAD THE EXCEL FORMAT FOR MULTIPLE ENROLMENT
	 */
	@GetMapping("/generate-excel-enrolment")
	public void downloadFileFromLocalEnrolment() throws IOException {
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		offeringService.generateExcelForEnrolment(file.toString());
		System.out.println(file);
	}	

	/*
	 * DOWNLOAD THE EXCEL FORMAT FOR UPDATING MULTIPLE SCORES
	 */
	@GetMapping("/generate-excel-score-update")
	public void downloadFileFromLocalScoreUpdate() throws IOException {
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		offeringService.generateExcelForScoreUpdate(file.toString());
		System.out.println(file);
	}
	
	/*
	 * VIEW ALL COURSES ATTENDED BY LEARNER
	 * RESPONSE BODY CONTAINS (LEARNER NAME,COURSE NAME,COURSE ID,LEARNER ID,PERCENTAGE AND STATUS)
	 */
	
	@GetMapping("/course-attended/{id}")
	public List<Map<String, Object>> viewCourseAttended(@PathVariable int id ){
		List<Map<String, Object>> cAttended = null;
		System.out.println(id);
		try {
			cAttended= offeringService.viewCourseOfferingsDetailsByLearnerId(id);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cAttended;
	}	


	
	/*
	 * UPDATE AN INDIVIDUAL TEST SCORE
	 * REQUEST BODY CONTENTS : {percentage}
	 */
	@PutMapping("/update-test-score")
	public ResponseEntity<String> updateTestScoreByCourseOfferingId(@RequestParam(value="tcId") int tcId, @RequestParam(value="learnerId") int learnerId, @RequestParam(value="percentage") int percentage) {
		try {
			int id = offeringService.findCourseOfferingIdByTcIdAndLearnerId(tcId, learnerId);
			offeringService.updateTestScore(id, percentage);
			return new ResponseEntity<>("Score updated successfully",HttpStatus.OK);
		}catch(NullPointerException e) {
			return new ResponseEntity<>("No such course offering exists.",HttpStatus.BAD_REQUEST);			
		}
	}

	/*
	 * VIEW DETAILS OF COURSE OFFERINGS
	 */	
	@GetMapping("/viewCourseOfferingsDetails")
	public List<Map<String, Object>> viewCourseOfferingsDetails() throws ParseException{
		return offeringService.viewCourseOfferingsDetails();
	}

	/*
	 * VIEW DETAILS OF COURSES ATTENDED
	 */	
	@GetMapping("/courses-attended/{id}")
	public List<Map<String, Object>> viewCoursesAttended(@PathVariable int id ) throws ParseException{
		return offeringService.viewCourseOfferingsDetailsByLearnerId(id);
	}	

	/*
	 * FIND TEACHER-COURSE MAPPINGS BY LEARNER ID
	 */		
	@GetMapping("/findTeacherCourseMappingsByLearnerId/{id}")
	public List<TeacherCourseMapping> findTeacherCourseMappingsByLearnerId(@PathVariable("id") int learnerId) throws ParseException{
		return offeringService.findTeacherCourseMappingsByLearnerId(learnerId);
	}

	/*
	 * FIND LEARNERS BY TCID
	 */	
	@GetMapping("/findLearnersByTcId/{id}")
	public List<Learner> findLearnersByTcId(@PathVariable("id") int tcId) throws ParseException{
		return offeringService.findLearnersByTcId(tcId);
	}	
}
