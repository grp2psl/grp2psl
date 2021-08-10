package com.psl.controller;

/*
 * Defines TrainerController which handles various url requests 
 * related to Learner.
 */


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class TrainerController {
	@Autowired
	private TrainerService service;
	
	@GetMapping("/{id}")
	public Trainer getTrainer(@PathVariable int id) {
		return service.getTrainer(id);
	}
	
	@PostMapping("/register")
	public void addTrainer(@RequestBody Trainer t) {
		service.addTrainer(t);
	}

	@GetMapping("/{id}/coursestaughtbytrainer")
	public List<TeacherCoursesTaught> findCoursesTaughtByTrainer(@PathVariable int id) {

	/*
	 * Fetch Courses taught by trainer and feedback ratings merged and stored into TeacherCoursesTaught
	*/
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
		List<String> comments = service.findCommentsForACourse(tcid);
		float rating = getFeedbackRatings(tcid);
		RatingAndComment rac = new RatingAndComment(rating, comments);
		return rac;
	}
}
