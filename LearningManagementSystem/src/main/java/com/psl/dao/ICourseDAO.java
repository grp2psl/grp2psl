package com.psl.dao;

import org.springframework.data.repository.CrudRepository;

import com.psl.entities.Course;


public interface ICourseDAO extends CrudRepository<Course, Integer> {

}
