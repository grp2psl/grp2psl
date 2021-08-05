package com.psl.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.ICourseDAO;
import com.psl.dao.ICourseOfferingDAO;
import com.psl.entities.Course;
import com.psl.entities.CourseOffering;
import com.psl.entities.CourseOfferingStatus;

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
