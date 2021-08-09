	package com.psl.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

import com.psl.entities.Learner;
import com.psl.entities.Trainer;
import com.psl.service.LearnerService;


@RestController
@RequestMapping("/learners")
@CrossOrigin(origins="http://localhost:3000")
public class LearnerController {
	@Autowired
	private LearnerService service;
	
	/*
	 * GET DETAILS OF LEARNER BY ID
	 */	
	@GetMapping("/{id}")
	public Learner getLearner(@PathVariable int id) {
		return service.getLearner(id);
	}

	/*
	 * GET DETAILS OF ALL LEARNERS
	 */
	@GetMapping("/")
	public List<Learner> getAllLearners(){
		return service.getAllLearners();
	}
	
	/*
	 * REGISTER LEARNER
	 */
	@PostMapping("/register")
	public ResponseEntity<String> addLearner(@RequestBody Learner learner) {
		try {
			service.addLearner(learner);
			return new ResponseEntity<>("Learner registered successfully", HttpStatus.OK);			
		}catch(DataIntegrityViolationException e) {
			return new ResponseEntity<>(e.getMessage()+"\nPlease register with another email ID", HttpStatus.CONFLICT);	
		}catch(Exception e) {
			return new ResponseEntity<>("Server error. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * REGISTER MULTIPLE LEARNERS BY UPLOADING EXCEL FILE
	 */
	@PostMapping("/register-multiple")
	public ResponseEntity<String> addMultipleLearners(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		try {
			service.addMultipleLearners(csvFilePath);
			return new ResponseEntity<>("Learner registered successfully", HttpStatus.OK);			
		}catch(DataIntegrityViolationException e) {
			return new ResponseEntity<>(e.getMessage()+"\nPlease delete records till this email ID and upload the file again.", HttpStatus.CONFLICT);	
		}catch(Exception e) {
			return new ResponseEntity<>("Server error. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * DELETE LEARNER BY ID
	 */
	@DeleteMapping("/{id}")
	public void removeLearner(@PathVariable int id) {
		service.removeLearner(id);
	}

	/*
	 * UPDATE LEARNER BY ID
	 */
	@PutMapping("/update")
	public void updateLearner(@RequestBody Learner learner) {
		service.updateLearner(learner);
	}
	
	/*
	 * DOWNLOAD FORMAT OF EXCEL SHEET FOR UPLOADING MULTIPLE LEARNERS
	 */
	@GetMapping("/generate-excel")
	public void downloadFileFromLocal() throws IOException {
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		service.generateExcel(file.toString());
		System.out.println(file);
	}

}
