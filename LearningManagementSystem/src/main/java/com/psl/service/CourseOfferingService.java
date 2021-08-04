package com.psl.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.ICourseOfferingDAO;
import com.psl.entities.Course;
import com.psl.entities.CourseOffering;
import com.psl.entities.Learner;

@Service("courseOfferingService")
public class CourseOfferingService {

	@Autowired
	private ICourseOfferingDAO dao;
	
	@Autowired
	private LearnerService learnerService;
	
	@Autowired
	private EmailSenderService emailService;
	
	@Autowired
	private TeacherCourseMappingService tcService;
	
	public void enrollLearner(CourseOffering offering) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Integer maxId = dao.getMaxId();
		maxId = maxId==null ? 0 : maxId;
		offering.setCourseofferingid(++maxId);
        offering.setStatus("In Progress");
        dao.save(offering);
        Learner learner = learnerService.getLearner(offering.getLearnerid());
        Course course = tcService.getCourse(offering.getTcid());
        emailService.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + learner.getName() +", \nYou have been enrolled to the Course - "+course.getCoursename()+".\nDuration of the course is - "+course.getDuration()+" hours, starting from "+formatter.format(offering.getStartdate()), "Learner enrolled to "+course.getCoursename()+" successfully - learning management portal");
	}
	
	public void enrollMultipleLearners(MultipartFile csvFilePath) throws IOException, ParseException {
		Integer maxId = dao.getMaxId();
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
	        Learner learner = learnerService.getLearner(offering.getLearnerid());
	        Course course = tcService.getCourse(offering.getTcid());
	        dao.save(offering);
	        emailService.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + learner.getName() +", \nYou have been enrolled to the Course - "+course.getCoursename()+".\nDuration of the course is - "+course.getDuration()+" hours, starting from "+formatter.format(offering.getStartdate()), "Learner enrolled to "+course.getCoursename()+" successfully - learning management portal");
		}
				
	}
	
}
