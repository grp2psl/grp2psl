/*
 * Defines LearnerController which handles various url requests 
 * related to Learner.
 */
package com.psl.controller;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//Importing required imports for Learner Controller Definition.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.entities.Learner;
import com.psl.service.LearnerService;

/*Annotation to enable LearnerController to act as a RestController 
 * Which handles all requests on url's having "/learners" prefix
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
//Definition of the class
public class LearnerController {
	
	//Autowiring with LearnerService
	@Autowired
	private LearnerService service;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(LearnerController.class);
	private final String logPrefix = "Learner Controller - ";

	/*
	 * This part handles get requests from url's having /learners/id pattern
	 * Where id is learner Id
	 * It gets learner details with given learnerId.
	 * GET DETAILS OF LEARNER BY ID
	 */	
	@GetMapping({"/learners/{id}", "/managers/learners/{id}"})
	public Learner getLearner(@PathVariable int id) {
		LOGGER.info(logPrefix+"GET /{id} called to get details of a learner by ID");
		return service.getLearner(id);
	}

	/*
	 * GET DETAILS OF ALL LEARNERS
	 */
	@GetMapping("/managers/learners/")
	public List<Learner> getAllLearners(){
		LOGGER.info(logPrefix+"GET / called to view all learners");
		return service.getAllLearners();
	}
	
	/*
	 * This part handles post requests from url's ending with /learners/register pattern
	 * It registers a new Learner using addLearner function
	 * REGISTER LEARNER
	 */
	@PostMapping("/managers/learners/register")
	public ResponseEntity<String> addLearner(@RequestBody Learner learner) {
		LOGGER.info(logPrefix+"POST /register called to add a new learner");
		try {
			service.addLearner(learner);
			LOGGER.info("Learner registered successfully");
			return new ResponseEntity<>("Learner registered successfully", HttpStatus.OK);			
		}catch(DataIntegrityViolationException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage()+"\nPlease register with another email ID", HttpStatus.CONFLICT);	
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>("Server error. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * REGISTER MULTIPLE LEARNERS BY UPLOADING EXCEL FILE
	 */
	@PostMapping("/managers/learners/register-multiple")
	public ResponseEntity<String> addMultipleLearners(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		LOGGER.info(logPrefix+"POST /register-multiple called to add multiple learners");
		try {
			service.addMultipleLearners(csvFilePath);
			LOGGER.info("Learners registered successfully");
			return new ResponseEntity<>("Learners registered successfully", HttpStatus.OK);			
		}catch(DataIntegrityViolationException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage()+"\nPlease delete records till this email ID and upload the file again.", HttpStatus.CONFLICT);	
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<>("Server error. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * This part handles put requests from url's ending with /learners/update/id pattern
	 * Where id is learner Id.
	 * It updates credentials of Learner with given id.
	 */
	@PutMapping({"/learners/updatelearner/{id}/currentPassword/{currentPassword}/newPassword/{newPassword}", "/managers/learners/updatelearner/{id}/currentPassword/{currentPassword}/newPassword/{newPassword}"})
	public ResponseEntity<String> updateLearner(@PathVariable int id,  @PathVariable String currentPassword,@PathVariable String newPassword) {
		LOGGER.info(logPrefix+"PUT /updateadmin called to update admin password");
		System.out.println(newPassword);
		System.out.println(currentPassword);
		
		String pass=service.checkPassword(id);
		System.out.println(pass);
		if(pass.contentEquals(currentPassword)) {
			service.updateLearnerPassword(id, newPassword);
			LOGGER.info("Password updated successfully");
			return new ResponseEntity<>(" Password updated successfully", HttpStatus.OK);
		}
		else {
			LOGGER.error("Enter correct current password");
			return new ResponseEntity<>("Incorrect Password", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping({"/learners/update/{id}", "/managers/learners/update/{id}"})
	public void updateLearner(@PathVariable int id, @RequestBody Map<String, String> credentials) {
		LOGGER.info(logPrefix+"PUT /update/{id} called to update details of a learner by ID");
		service.updateLearner(id, credentials.get("email"), credentials.get("password"));
	}
	
	/*
	 * DELETE LEARNER BY ID
	 */
	@DeleteMapping("/managers/learners/{id}")
	public void removeLearner(@PathVariable int id) {
		LOGGER.info(logPrefix+"DELETE /{id} called to delete a learner by ID");
		service.removeLearner(id);
	}

	/*
	 * UPDATE LEARNER BY ID
	 */
	@PutMapping({"/learners/update", "/managers/learners/update"})
	public void updateLearner(@RequestBody Learner learner) {
		LOGGER.info(logPrefix+"PUT /update called to update details of a learner by ID");
		service.updateLearner(learner);
	}
	
	/*
	 * DOWNLOAD FORMAT OF EXCEL SHEET FOR UPLOADING MULTIPLE LEARNERS
	 */
	@GetMapping("/managers/learners/generate-excel")
	public void downloadFileFromLocal() throws IOException {
		LOGGER.info(logPrefix+"GET /generate-excel called to download excel format for multiple learner registration");
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		service.generateExcel(file.toString());
		System.out.println(file);
	}

	@GetMapping("/learners/login")
	public ResponseEntity<Learner> login(@RequestParam String email, @RequestParam String password){
		Learner result = service.login(email, password);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);	
		}
		return new ResponseEntity<>(result, HttpStatus.OK);	
	}
}
