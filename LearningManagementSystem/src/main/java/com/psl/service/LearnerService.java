package com.psl.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ILearnerDAO;
import com.psl.entities.Learner;

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
	
	public void updateLearner(int id, String email, String password) {
	    Learner l = dao.findById(id).get();
	    l.setEmail(email);
	    l.setPassword(password);
	    dao.save(l);
	}
}
