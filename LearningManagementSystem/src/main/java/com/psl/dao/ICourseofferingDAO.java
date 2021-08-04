package com.psl.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entities.CourseOffering;

public interface ICourseofferingDAO extends CrudRepository<CourseOffering, Integer> {

	@Query(value="select max(courseofferingid) from courseoffering", nativeQuery=true)
	public Integer getMaxId();
	
}
