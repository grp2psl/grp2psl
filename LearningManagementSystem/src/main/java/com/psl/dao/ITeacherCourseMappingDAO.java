package com.psl.dao;

import org.springframework.data.repository.CrudRepository;

import com.psl.entities.TeacherCourseMapping;



public interface ITeacherCourseMappingDAO extends CrudRepository<TeacherCourseMapping, Integer>{

}
