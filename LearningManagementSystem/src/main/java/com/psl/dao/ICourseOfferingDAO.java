package com.psl.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.psl.entities.CourseOffering;
import com.psl.entities.CourseOfferingId;

public interface ICourseOfferingDAO extends CrudRepository<CourseOffering, CourseOfferingId>{

	CourseOffering findByTcIdAndLearnerId(int tcId, int learnerId);

	List<CourseOffering> findByLearnerId(int learnerId);
	
}
