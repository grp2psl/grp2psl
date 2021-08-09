package com.psl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ICourseDAO;
import com.psl.dao.ICourseOfferingDAO;
import com.psl.entities.Course;

@Service("courseService")
public class CourseService {
	
	@Autowired
	private ICourseDAO dao;

	
	@Autowired
	private ICourseOfferingDAO offeringDao;
		
	/*
	 *DISPLAY ALL COURSES
	 */

	public List<Course> findAll () {
        return (List<Course>) dao.findAll();
    }
	
	/*
	 *ADD A COURSE DETAILS
	 */
	public Course addCourse(Course course) {
	  return dao.save(course);		
	}
	
	/*
	 *VIEW A COURSE BY COURSEID
	 */
	public Course getCourse(int id) {
		return dao.findById(id).get();
	}

}
