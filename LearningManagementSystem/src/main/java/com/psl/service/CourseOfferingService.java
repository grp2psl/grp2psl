package com.psl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ICourseOfferingDAO;
import com.psl.entities.CourseOffering;

@Service("courseOfferingService")
public class CourseOfferingService {
	
	@Autowired
	private ICourseOfferingDAO dao;
	
	public void AddFeedback(int learnerId, int tcId, String feedback) {
		CourseOffering co = dao.findByTcidAndLearnerid(tcId, learnerId);
		co.setFeedback(feedback);
		dao.save(co);
	}
	
	public List<CourseOffering> getCourseOfferings(int learnerId) {
		return dao.findByLearnerid(learnerId);
	}
}
