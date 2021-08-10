package com.psl.service;

/*
 * Declaration of Trainer Service which completes various requests on trainer class
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ICourseOfferingDAO;
import com.psl.dao.ITeacherCourseMappingDAO;
import com.psl.dao.ITrainerDAO;
import com.psl.entities.TeacherCourseMapping;
import com.psl.entities.Trainer;

@Service("trainerService")
public class TrainerService {
	@Autowired
	private ITrainerDAO dao;
	
	@Autowired
	private ITeacherCourseMappingDAO mappingDAO;
	
	@Autowired
	private ICourseOfferingDAO offeringDAO;
	
	public void addTrainer(Trainer t) {
		dao.save(t);
	}
	
	public Trainer getTrainer(int id) {
		return dao.findById(id).get();
	}
	
	public List<TeacherCourseMapping> findCoursesTaughtByTrainer(int id){
		List<TeacherCourseMapping> l = mappingDAO.findCoursesTaughtByTrainer(id);
		System.out.println(l.size()); // Awlays returing zero
		System.out.println("Trainer Query id: " + id + " ==>");
		return mappingDAO.findCoursesTaughtByTrainer(id);
	}
	
	public float getFeedbackResults(int tcid) {
		return offeringDAO.getFeedbackResults(tcid);
	}
	
	public List<Float> findAllCoursesTaughtRatings(int id){
		return offeringDAO.findAllCoursesTaughtRatings(id);
	}
	
	public List<String> findCommentsForACourse(int tcid){
		return offeringDAO.findCommentsForACourse(tcid);
	}
}
