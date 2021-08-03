package com.psl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ICourseDAO;
import com.psl.entities.Course;

@Service("courseService")
public class CourseService {
	
	@Autowired
	private ICourseDAO dao;
	
	public List<Course> findAll () {
        return (List<Course>) dao.findAll();
    }
	
	public Course addCourse(Course course) {
	  return dao.save(course);		
	}

	public Course getCourse(int id) {
		return dao.findById(id).get();
	}
}
