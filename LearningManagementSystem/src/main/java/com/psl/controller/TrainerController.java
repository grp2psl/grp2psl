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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.entities.Trainer;
import com.psl.service.TrainerService;

@RestController
@RequestMapping("/trainers")
@CrossOrigin(origins="http://localhost:3000")
public class TrainerController {
	@Autowired
	private TrainerService service;
	/*
	 * GET DETAILS OF TRAINER BY ID
	 */
	@GetMapping("/{id}")
	public Trainer getTrainer(@PathVariable int id) {
		return service.getTrainer(id);
	}

	/*
	 * GET DETAILS OF ALL TRAINERS
	 */
	@GetMapping("/")
	public List<Trainer> getAllTrainers(){
		return service.getAllTrainers();
	}
	
	/*
	 * REGISTER TRAINER
	 */
	@PostMapping("/register")
	public ResponseEntity<String> addTrainer(@RequestBody Trainer trainer) {
		try {
			service.addTrainer(trainer);
			return new ResponseEntity<>("Trainer registered successfully", HttpStatus.OK);			
		}catch(DataIntegrityViolationException e) {
			return new ResponseEntity<>(e.getMessage()+"\nPlease register with another email ID", HttpStatus.CONFLICT);	
		}catch(Exception e) {
			return new ResponseEntity<>("Server error. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * REGISTER MULTIPLE TRAINERS BY UPLOADING EXCEL FILE
	 */
	@PostMapping("/register-multiple")
	public ResponseEntity<String> addMultipleTrainers(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		try {
			service.addMultipleTrainers(csvFilePath);
			return new ResponseEntity<>("Trainer registered successfully", HttpStatus.OK);			
		}catch(DataIntegrityViolationException e) {
			return new ResponseEntity<>(e.getMessage()+"\nPlease delete records till this email ID and upload the file again.", HttpStatus.CONFLICT);	
		}catch(Exception e) {
			return new ResponseEntity<>("Server error. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * DELETE TRAINER BY ID
	 */
	@DeleteMapping("/{id}")
	public void removeTrainer(@PathVariable int id) {
		service.removeTrainer(id);
	}

	/*
	 * DOWNLOAD FORMAT OF EXCEL SHEET FOR UPLOADING MULTIPLE TRAINERS
	 */
	@GetMapping("/generate-excel")
	public void downloadFileFromLocal() throws IOException {
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		service.generateExcel(file.toString());
		System.out.println(file);
	}
}
