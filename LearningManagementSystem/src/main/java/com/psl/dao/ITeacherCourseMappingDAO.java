package com.psl.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.psl.entities.TeacherCourseMapping;



public interface ITeacherCourseMappingDAO extends CrudRepository<TeacherCourseMapping, Integer>{

	public List<TeacherCourseMapping> findByTrainerId(int id);
	public List<TeacherCourseMapping> findByCourseId(int id);
	public TeacherCourseMapping findByTrainerIdAndCourseId(int id, int courseid);
	
}