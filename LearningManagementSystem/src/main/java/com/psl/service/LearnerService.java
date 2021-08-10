/*
 * Declaration of Leaner Service which completes various requests on learner class
 */
package com.psl.service;

//Required imports for the service declaration
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ILearnerDAO;
import com.psl.entities.Learner;

//This annotation enables use of this class as a service named learnerService.
@Service("learnerService")
public class LearnerService {
	
	//Autowires the learner service with learner dao interface.
	@Autowired
	private ILearnerDAO dao;
	
	public ILearnerDAO getDao() {
		return dao;
	}

	public void setDao(ILearnerDAO dao) {
		this.dao = dao;
	}

	//Function which adds the learner to the table.
	public Learner addLearner(Learner l) {
		return dao.save(l);
	} 
	
	//Function which retrieves learner from the table using learnerId.
	public Learner getLearner(int id) {
		return dao.findById(id).get();
	}
	
	//Function which updates credentials of a learner with given learnerId.
	public Learner updateLearner(int id, String email, String password) {
	    Learner l = dao.findById(id).get();
	    l.setEmail(email);
	    l.setPassword(password);
	    return dao.save(l);
	}
	
	//Function which removes a learner
	public void deleterLearner(int id) {
		dao.deleteById(id);
	}
}
