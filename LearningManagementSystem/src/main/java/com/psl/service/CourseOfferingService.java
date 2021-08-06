/*
 * Declaration of Course Offering Service which completes various requests on CourseOffering class.
 */
package com.psl.service;

//Required imports for the service declaration.
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ICourseOfferingDAO;
import com.psl.entities.CourseOffering;

//This annotation enables use of this class as a service named courseOfferingService.
@Service("courseOfferingService")
public class CourseOfferingService {
	
	//Autowires the courseOffering Service with courseOffering DAO interface.
	@Autowired
	private ICourseOfferingDAO dao;
	
	//Function to allow Learner to given feedback on given course Offering
	public void AddFeedback(int learnerId, int tcId, String feedback) {
		CourseOffering co = dao.findByTcIdAndLearnerId(tcId, learnerId);
		co.setFeedback(feedback);
		dao.save(co);
	}
	
	//Lists all Course Offerings of given learner which is identified by learnerId 
	public List<CourseOffering> getCourseOfferings(int learnerId) {
		return dao.findByLearnerId(learnerId);
	}
}
