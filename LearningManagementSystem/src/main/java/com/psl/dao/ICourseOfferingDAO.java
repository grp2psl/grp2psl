package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entities.CourseOffering;

public interface ICourseOfferingDAO extends CrudRepository<CourseOffering, Integer> {

	/*
	 * AUTO-INCREMENT ID
	 */
	@Query(value="select max(courseofferingid) from courseoffering", nativeQuery=true)
	public Integer getMaxId();
	public List<CourseOffering> findByTcId(int id);
	public CourseOffering findByTcIdAndLearnerId(int tcId, int learnerId);
	public List<CourseOffering> findByLearnerId(int learnerId);
	
	
}
