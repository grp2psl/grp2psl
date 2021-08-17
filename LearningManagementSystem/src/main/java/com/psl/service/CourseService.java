package com.psl.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.ICourseDAO;
import com.psl.entities.Course;

@Service("courseService")
public class CourseService {
	
	@Autowired
	private ICourseDAO dao;

	public static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);
	private final String logPrefix = "Course Service - ";
	
	/*
	 *DISPLAY ALL COURSES
	 */

	public List<Course> findAll () {
		LOGGER.info(logPrefix+"Returning list of all courses");
        return (List<Course>) dao.findAll();
    }
	
	/*
	 *ADD A COURSE DETAILS
	 */
	public Course addCourse(Course course) {
		LOGGER.info(logPrefix+"Adding a course - "+course);
		Integer id = dao.getNextId();
		id = (id==null ? 1 : id + 1);
		course.setCourseId(id);
	    return dao.save(course);		
	}
	
	/*
	 *VIEW A COURSE BY COURSEID
	 */
	public Course getCourse(int id) {
		LOGGER.info(logPrefix+"Returning details of a course with ID - "+id);
		return dao.findByCourseId(id);
	}
	
	/*
	 * REMOVE COURSE BY ID
	 */
	public void removeCourse(int id) {
		LOGGER.info(logPrefix+"Deleting a course with ID - "+id);
		dao.deleteById(id);
	}

	/*
	 * Update COURSE BY ID
	 */
	public void updateCourse(Course course) {
		LOGGER.info(logPrefix+"Updating details of a course to - "+course);
		dao.updateEntry(course.getPrerequisite(),course.getSyllabus(),course.getDuration(),course.getCourseId());
	}
	

}
