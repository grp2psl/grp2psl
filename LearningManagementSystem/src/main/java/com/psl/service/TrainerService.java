package com.psl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ITrainerDAO;
import com.psl.entities.Trainer;

@Service("trainerService")
public class TrainerService {
	@Autowired
	private ITrainerDAO dao;
	
	public void addTrainer(Trainer t) {
		dao.save(t);
	}
	
	public Trainer getTrainer(int id) {
		return dao.findById(id).get();
	}
}
