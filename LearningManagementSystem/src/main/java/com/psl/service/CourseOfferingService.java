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
	
	public ICourseOfferingDAO getDao() {
		return dao;
	}

	public void setDao(ICourseOfferingDAO dao) {
		this.dao = dao;
	}

	//Function to allow Learner to given feedback on given course Offering
	public CourseOffering AddFeedback(int courseOfferingId, String feedback) {
		CourseOffering co = dao.findById(courseOfferingId).get();
		co.setFeedback(feedback);
		return dao.save(co);
	}
	
	//Lists all Course Offerings of given learner which is identified by learnerId 
	public List<CourseOffering> getCourseOfferings(int learnerId) {
		return dao.findByLearnerId(learnerId);
	}
	
	//Returns course offering of given id
	public CourseOffering getCourseOffering(int id) {
		return dao.findById(id).get();
	}
}
