package com.psl.service;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ILearnerDAO;
import com.psl.entities.Learner;

@Service("learnerService")
public class LearnerService {
	@Autowired
	private ILearnerDAO dao;
	@Autowired
	private EmailSenderService service;
	
	public void addLearner(Learner learner) {
		Random rand = new Random();
		String firstname = learner.getName().substring(0, learner.getName().indexOf(" "));
		String password = firstname+learner.getLearnerid()+"@"+rand.nextInt(9999);
		learner.setPassword(password);
		dao.save(learner);
		service.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange once you are logged in.", "Registered successfully to learning management portal");		
	} 
	
	public Learner getLearner(int id) {
		return dao.findById(id).get();
	}
	
}
