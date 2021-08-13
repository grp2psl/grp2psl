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

import com.psl.entities.Trainer;
import com.psl.service.TrainerService;

import com.psl.entities.TeacherCourseMapping;
import com.psl.service.TeacherCourseMappingService;

import com.psl.entities.Course;

@RestController
@RequestMapping("/trainers")
@CrossOrigin(origins="http://localhost:3000")
public class TrainerController {

	@Autowired
	private TrainerService service;

	@Autowired
	private TeacherCourseMappingService service1;
	
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
	public void addTrainer(@RequestBody Trainer trainer) {
		service.addTrainer(trainer);
	}

	/*
	 * REGISTER MULTIPLE TRAINERS BY UPLOADING EXCEL FILE
	 */
	@PostMapping("/register-multiple")
	public void addMultipleTrainers(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		service.addMultipleTrainers(csvFilePath);
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

	@GetMapping("/{id}/coursestaughtbytrainer")
	public List<Course> getCoursesByTrainerId(@PathVariable int id) {
		return service1.getCoursesByTrainerId(id);
	}


}
