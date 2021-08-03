package com.psl.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.psl.entities.Trainer;
import com.psl.service.TrainerService;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
	@Autowired
	private TrainerService service;
	
	@GetMapping("/{id}")
	public Trainer getTrainer(@PathVariable int id) {
		return service.getTrainer(id);
	}
	
	@PostMapping("/register")
	public void addTrainer(@RequestBody Trainer trainer) {
		service.addTrainer(trainer);
	}

	@PostMapping("/register-multiple")
	public void addMultipleTrainers(@RequestParam("file") MultipartFile csvFilePath ) throws IOException {
		service.addMultipleTrainers(csvFilePath);
	}
<<<<<<< HEAD

=======
	
	@GetMapping("/")
	public List<Trainer> getAllTrainers(){
		return service.getAllTrainers();
	}
>>>>>>> 74f6f38d0b599692bd4d33a1a2a05ea702480fe9
}
