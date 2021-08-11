package com.psl.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.psl.entities.Course;
import com.psl.entities.TeacherCourseMapping;


public interface ICourseDAO extends CrudRepository<Course, Integer> {
	
	public Course findByCourseId(int id);

	/*
	 * AUTO-INCREMENT ID
	 */
	@Query(value="select max(courseid) from course", nativeQuery=true)
	public Integer getNextId();
	

	/*
	 * CUSTOM QUERY FOR NEW RECORD INSERTION  
	 */
	@Modifying
	@Transactional
	@Query(value="insert into course(courseid, coursename, prerequisite, syllabus, duration) values(?1, ?2, ?3, ?4, ?5)", nativeQuery=true)
	public void saveNewEntry(@Param("id") int id, @Param("coursename") String coursename, @Param("prerequisite") String prerequisite, 
			@Param("syllabus") String syllabus, @Param("duration") String duration);

	/*
	 * CUSTOM QUERY TO UPDATE RECORD  
	 */
	@Transactional
	@Modifying
	@Query(value="update course set prerequisite = ?1, syllabus = ?2, duration = ?3 where courseid = ?4", nativeQuery=true)
	public void updateEntry(@Param("prerequisite") String prerequisite, 
			@Param("syllabus") String syllabus,@Param("duration") String duration, @Param("id") int id);
}
