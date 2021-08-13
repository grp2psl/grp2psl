package com.psl.controller;

/*
 * Defines TrainerController which handles various url requests 
 * related to Learner.
 */


import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.psl.entities.RatingAndComment;
import com.psl.entities.TeacherCourseMapping;
import com.psl.entities.TeacherCoursesTaught;
import com.psl.entities.Trainer;
import com.psl.service.TrainerService;

/*Annotation to enable LearnerController to act as a RestController 
 * Which handles all requests on url's having "/trainers" prefix
 */

@RestController
@RequestMapping("/trainers")
@CrossOrigin(origins="http://localhost:3000")
public class TrainerController {
	@Autowired
	private TrainerService service;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(TrainerController.class);
	private final String logPrefix = "Trainer Controller - ";

	/*
	 * GET DETAILS OF TRAINER BY ID
	 */
	@GetMapping("/{id}")
	public Trainer getTrainer(@PathVariable int id) {
		LOGGER.info(logPrefix+"GET /{id} called to get details of a trainer by ID");
		return service.getTrainer(id);
	}

	/*
	 * GET DETAILS OF ALL TRAINERS
	 */
	@GetMapping("/")
	public List<Trainer> getAllTrainers(){
		LOGGER.info(logPrefix+"GET / called to get details of all trainers");
		return service.getAllTrainers();
	}
	
	/*
	 * REGISTER TRAINER
	 */
	@PostMapping("/register")
	public ResponseEntity<String> addTrainer(@RequestBody Trainer trainer) {
		LOGGER.info(logPrefix+"POST /register called to add a trainer");
		try {
			service.addTrainer(trainer);
			LOGGER.info("Trainer registered successfully")
			return new ResponseEntity<>("Trainer registered successfully", HttpStatus.OK);			
		}catch(DataIntegrityViolationException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage()+"\nPlease register with another email ID", HttpStatus.CONFLICT);	
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>("Server error. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * REGISTER MULTIPLE TRAINERS BY UPLOADING EXCEL FILE
	 */
	@PostMapping("/register-multiple")
	public ResponseEntity<String> addMultipleTrainers(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		LOGGER.info(logPrefix+"POST /register-multiple called to add multiple trainers");
		try {
			service.addMultipleTrainers(csvFilePath);
			LOGGER.info("Trainer registered successfully");
			return new ResponseEntity<>("Trainer registered successfully", HttpStatus.OK);			
		}catch(DataIntegrityViolationException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage()+"\nPlease delete records till this email ID and upload the file again.", HttpStatus.CONFLICT);	
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>("Server error. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * DELETE TRAINER BY ID
	 */
	@DeleteMapping("/{id}")
	public void removeTrainer(@PathVariable int id) {
		LOGGER.info(logPrefix+"DELETE /{id} called to delete a trainer by ID");
		service.removeTrainer(id);
	}
	
	/*
	 * UPDATE TRAINER BY ID
	 */
	@PutMapping("/update")
	public void updateTrainer(@RequestBody Trainer trainer) {
		LOGGER.info(logPrefix+"PUT /update called to update details of a trainer");
		service.updateTrainer(trainer);
	}

	@GetMapping("/{id}/coursestaughtbytrainer")
	public List<TeacherCoursesTaught> findCoursesTaughtByTrainer(@PathVariable int id) {

	/*
	 * Fetch Courses taught by trainer and feedback ratings merged and stored into TeacherCoursesTaught
	*/
		LOGGER.info(logPrefix+"GET /{id}/coursestaughtbytrainer called to get courses taught by trainer and respective feedbacks and ratings");
		List<TeacherCourseMapping> l = service.findCoursesTaughtByTrainer(id);
		List<TeacherCoursesTaught> tct = new ArrayList<>();
		for (TeacherCourseMapping t: l) {
			float ratings = getFeedbackRatings(t.getTcId());
			TeacherCoursesTaught taught = new TeacherCoursesTaught(t.getTrainerId(), t.getCourseId(), t.getTcId(), ratings); 
			tct.add(taught);
		}
		return tct;
	}
	
	public float getFeedbackRatings(int tcid){		
		return service.getFeedbackResults(tcid);
	}
	
	@GetMapping("/{id}/{tcid}")
	public RatingAndComment getFeedbackResults(@PathVariable int id, @PathVariable int tcid){
		LOGGER.info(logPrefix+"GET /{id}/{tcid} called to get feedback results");
		List<String> comments = service.findCommentsForACourse(tcid);
		float rating = getFeedbackRatings(tcid);
		RatingAndComment rac = new RatingAndComment(rating, comments);
		return rac;
	}
	/*
	 * DOWNLOAD FORMAT OF EXCEL SHEET FOR UPLOADING MULTIPLE TRAINERS
	 */
	@GetMapping("/generate-excel")
	public void downloadFileFromLocal() throws IOException {
		LOGGER.info(logPrefix+"GET /generate-excel called to download excel format for uploading multiple trainers");
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		service.generateExcel(file.toString());
		System.out.println(file);
	}
	
}
