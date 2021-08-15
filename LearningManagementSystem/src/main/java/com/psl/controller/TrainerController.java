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
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
	@GetMapping("/{id}")
	public Trainer getTrainer(@PathVariable int id) {
		LOGGER.info(logPrefix+"GET /{id} called to get details of a trainer by ID");
		return service.getTrainer(id);
	}

	/*
	 * GET DETAILS OF ALL TRAINERS
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/")
	public List<Trainer> getAllTrainers(){
		LOGGER.info(logPrefix+"GET / called to get details of all trainers");
		return service.getAllTrainers();
	}
	
	/*
	 * REGISTER TRAINER
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<String> addTrainer(@RequestBody Trainer trainer) {
		LOGGER.info(logPrefix+"POST /register called to add a trainer");
		try {
			service.addTrainer(trainer);
			LOGGER.info("Trainer registered successfully");
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public void removeTrainer(@PathVariable int id) {
		LOGGER.info(logPrefix+"DELETE /{id} called to delete a trainer by ID");
		service.removeTrainer(id);
	}
	
	/*
	 * UPDATE TRAINER BY ID
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
	@PutMapping("/update")
	public void updateTrainer(@RequestBody Trainer trainer) {
		LOGGER.info(logPrefix+"PUT /update called to update details of a trainer");
		service.updateTrainer(trainer);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
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

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
	public float getFeedbackRatings(int tcid){		
		return service.getFeedbackResults(tcid);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
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
	@PreAuthorize("permitAll")
	@GetMapping("/generate-excel")
	public void downloadFileFromLocal() throws IOException {
		LOGGER.info(logPrefix+"GET /generate-excel called to download excel format for uploading multiple trainers");
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		service.generateExcel(file.toString());
		System.out.println(file);
	}

	@PreAuthorize("hasRole('ROLE_TRAINER')")
	@GetMapping("/login")
	public ResponseEntity<Trainer> login(@RequestParam String email, @RequestParam String password){
		Trainer result = service.login(email, password);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);	
		}
		return new ResponseEntity<>(result, HttpStatus.OK);	
	}
}
