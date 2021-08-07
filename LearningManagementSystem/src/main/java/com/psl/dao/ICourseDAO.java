package com.psl.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entities.Course;


public interface ICourseDAO extends CrudRepository<Course, Integer> {
	/*
	 * AUTO-INCREMENT ID
	 */
	@Query(value="select max(courseid) from course", nativeQuery=true)
	public Integer getNextId();
}
