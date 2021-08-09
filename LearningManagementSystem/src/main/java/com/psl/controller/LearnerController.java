	package com.psl.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.psl.entities.Learner;
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
	public void addLearner(@RequestBody Learner learner) {
		service.addLearner(learner);
	}
	
	/*
	 * REGISTER MULTIPLE LEARNERS BY UPLOADING EXCEL FILE
	 */
	@PostMapping("/register-multiple")
	public void addMultipleLearners(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		service.addMultipleLearners(csvFilePath);
	}
	
	/*
	 * DELETE LEARNER BY ID
	 */
	@DeleteMapping("/{id}")
	public void removeLearner(@PathVariable int id) {
		service.removeLearner(id);
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
