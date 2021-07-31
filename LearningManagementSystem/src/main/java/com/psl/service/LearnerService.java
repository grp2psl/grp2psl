package com.psl.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ILearnerDAO;
import com.psl.entity.Learner;

@Service("learnerService")
public class LearnerService {
	@Autowired
	private ILearnerDAO dao;
	
	public void addLearner(Learner l) {
		dao.save(l);
	} 
	
	public Learner getLearner(int id) {
		return dao.findById(id).get();
	}
	
}
