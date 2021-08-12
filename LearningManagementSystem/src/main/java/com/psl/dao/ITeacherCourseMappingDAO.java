package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.psl.entities.TeacherCourseMapping;

public interface ITeacherCourseMappingDAO extends CrudRepository<TeacherCourseMapping, Integer>{
	@Query("SELECT a FROM TeacherCourseMapping a WHERE trainerId = :id")
	List<TeacherCourseMapping> findCoursesTaughtByTrainer(@Param("id") int id);
	public List<TeacherCourseMapping> findByTrainerId(int id);
	public List<TeacherCourseMapping> findByCourseId(int id);
	public TeacherCourseMapping findByTcId(int id);
	public TeacherCourseMapping findByTrainerIdAndCourseId(int id, int courseid);
	@Query(value="select max(tcid) from teachercoursemapping", nativeQuery=true)
	public Integer getNextId();
	
}
