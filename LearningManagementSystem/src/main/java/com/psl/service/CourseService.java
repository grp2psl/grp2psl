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
import com.psl.dao.ICourseofferingDAO;
import com.psl.entities.Course;
import com.psl.entities.CourseOffering;
import com.psl.entities.CourseOfferingStatus;

@Service("courseService")
public class CourseService {
	
	@Autowired
	private ICourseDAO dao;
	
	@Autowired
	private ICourseofferingDAO offeringDao;
	
	public List<Course> findAll () {
        return (List<Course>) dao.findAll();
    }
	
	public Course addCourse(Course course) {
	  return dao.save(course);		
	}

	public Course getCourse(int id) {
		return dao.findById(id).get();
	}
	
	public void enrollLearner(CourseOffering offering) throws ParseException {
		Integer maxId = offeringDao.getMaxId();
		maxId = maxId==null ? 0 : maxId;
		offering.setCourseofferingid(++maxId);
        offering.setStatus("In Progress");
        offeringDao.save(offering);
	}
	
	public void enrollMultipleLearners(MultipartFile csvFilePath) throws IOException, ParseException {
		Integer maxId = offeringDao.getMaxId();
		maxId = maxId==null ? 0 : maxId;
		XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        CourseOffering offering = new CourseOffering();
	        XSSFRow row = worksheet.getRow(i);
	        offering.setCourseofferingid(++maxId);
	        offering.setStartdate(formatter.parse(row.getCell(2).getStringCellValue()));
	        offering.setEnddate(formatter.parse(row.getCell(3).getStringCellValue()));
	        offering.setLearnerid((int)row.getCell(0).getNumericCellValue());
	        offering.setTcid((int)row.getCell(1).getNumericCellValue());
	        offering.setStatus("In Progress");
			offeringDao.save(offering);
		}				
	}
	
	public void updateTestScore(int id, float percentage) {
		CourseOffering offering = offeringDao.findById(id).get();
		offering.setPercentage((int)percentage);
		if(percentage >= 70) {
			offering.setStatus(CourseOfferingStatus.COMPLETE_PENDING.name());
		}else {
			offering.setStatus(CourseOfferingStatus.FAIL.name());
		}
		offeringDao.save(offering);
	}
	
	public void updateMultipleTestScores(MultipartFile csvFilePath) throws IOException, ParseException {
		XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        XSSFRow row = worksheet.getRow(i);
			CourseOffering offering = offeringDao.findById((int)row.getCell(0).getNumericCellValue()).get();
			offering.setPercentage((int)row.getCell(1).getNumericCellValue());
			if(offering.getPercentage() >= 70) {
				offering.setStatus(CourseOfferingStatus.COMPLETE_PENDING.name());
			}else {
				offering.setStatus(CourseOfferingStatus.FAIL.name());
			}
			offeringDao.save(offering);
		}		
		
	}
	
	public List<CourseOffering> viewCourseOfferings(){
		return (List<CourseOffering>) offeringDao.findAll();
	}
	
	public void removeCourseOffering(int id) {
		offeringDao.deleteById(id);
	}
}
