package com.psl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.ITeacherCourseMappingDAO;
import com.psl.entities.Course;
import com.psl.entities.TeacherCourseMapping;

@Service("teacherCourseMappingService")
public class TeacherCourseMappingService {
	
	@Autowired
	private ITeacherCourseMappingDAO dao;
	
	@Autowired
	private CourseService courseService;
	
	/*
	 *ADDING TEACHER-COURSE MAPPING DETAILS
	 */
	public void addTeacherCourseMapping(TeacherCourseMapping teacherCourseMapping) {
		
		dao.save(teacherCourseMapping);
		
	}
	
	/*
	 *ADDING MULTIPLE TEACHER-COURSE MAPPING DETAILS FROM CSV FILE
	 */
	public void addMultipleTeacherCourseMapping(MultipartFile csvFilePath) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        TeacherCourseMapping teacherCourseMapping = new TeacherCourseMapping();
	            
	        XSSFRow row = worksheet.getRow(i);
	        teacherCourseMapping.setTcId((int)row.getCell(0).getNumericCellValue());
	        teacherCourseMapping.setTrainerId((int)row.getCell(1).getNumericCellValue());
	        teacherCourseMapping.setCourseId((int)row.getCell(2).getNumericCellValue());
	        
			dao.save(teacherCourseMapping);
					
	    }
	}

	/*
	 * GET COURSE BY TCID (TRAINER-COURSE MAPPING ID)
	 */
	public Course getCourse(int tcid) {
		TeacherCourseMapping tcMapping = dao.findById(tcid).get();
		return courseService.getCourse(tcMapping.getCourseId());
	}
	
	/*
	 * GET TRAINER-COURSE MAPPINGS BY TRAINER ID
	 */
	public List<TeacherCourseMapping> getByTrainerId(int trainerid) {
		return dao.findByTrainerId(trainerid);
	}
	
	/*
	 * GET TRAINER-COURSE MAPPINGS BY COURSE ID
	 */
	public List<TeacherCourseMapping> getByCourseId(int courseid) {
		return dao.findByCourseId(courseid);
	}
	
	/*
	 * GET TRAINER-COURSE MAPPING BY TRAINER ID AND COURSE ID
	 */
	public TeacherCourseMapping getByTrainerIdAndCourseId(int id, int courseid) {
		return dao.findByTrainerIdAndCourseId(id, courseid);
	}
	
	/*
	 * GET COURSES BY TRAINER ID
	 */
	public List<Course> getCoursesByTrainerId(int trainerid) {
		List<TeacherCourseMapping> tcMapping = dao.findByTrainerId(trainerid);
		List<Course> courses = new ArrayList<>();
		for(TeacherCourseMapping i: tcMapping) {
			courses.add(courseService.getCourse(i.getCourseId()));
		}
		return courses;
	}
}
