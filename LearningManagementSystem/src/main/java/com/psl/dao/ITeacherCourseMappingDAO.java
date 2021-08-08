package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entities.TeacherCourseMapping;



public interface ITeacherCourseMappingDAO extends CrudRepository<TeacherCourseMapping, Integer>{

	public List<TeacherCourseMapping> findByTrainerId(int id);
	public List<TeacherCourseMapping> findByCourseId(int id);
	public TeacherCourseMapping findByTrainerIdAndCourseId(int id, int courseid);
	@Query(value="select max(tcid) from teachercoursemapping", nativeQuery=true)
	public Integer getNextId();
	
}
