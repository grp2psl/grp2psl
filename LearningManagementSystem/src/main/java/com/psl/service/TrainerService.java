package com.psl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
