package com.psl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.ICourseOfferingDAO;
import com.psl.entities.Course;
import com.psl.entities.CourseOffering;
import com.psl.entities.CourseOfferingStatus;
import com.psl.entities.Learner;
import com.psl.entities.TeacherCourseMapping;
import com.psl.entities.Trainer;
import com.psl.utils.ExcelFields;
import com.psl.utils.ExcelHelper;

@Service("courseOfferingService")
public class CourseOfferingService {

	@Autowired
	private ICourseOfferingDAO dao;
	
	@Autowired
	private LearnerService learnerService;
	
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private EmailSenderService emailService;
	
	@Autowired
	private TeacherCourseMappingService tcService;
	
	/*
	 * ENROLL LEARNER
	 */
	public void enrollLearner(CourseOffering offering) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Integer maxId = dao.getMaxId();
		maxId = maxId==null ? 0 : maxId;
		offering.setCourseofferingid(++maxId);
		System.out.println(offering.getStatus());
        dao.save(updateCourseOfferingStatus(offering));
        Learner learner = learnerService.getLearner(offering.getLearnerid());
        Course course = tcService.getCourse(offering.getTcid());
        emailService.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + learner.getName() +", \nYou have been enrolled to the Course - "+course.getCoursename()+".\nDuration of the course is - "+course.getDuration()+" hours, starting from "+formatter.format(offering.getStartdate()), "Learner enrolled to "+course.getCoursename()+" successfully - learning management portal");
	}
	
	/*
	 * ENROLL MULTIPLE LEARNERS
	 */
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
	        Learner learner = learnerService.getLearner(offering.getLearnerid());
	        Course course = tcService.getCourse(offering.getTcid());
	        dao.save(updateCourseOfferingStatus(offering));
	        emailService.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + learner.getName() +", \nYou have been enrolled to the Course - "+course.getCoursename()+".\nDuration of the course is - "+course.getDuration()+" hours, starting from "+formatter.format(offering.getStartdate()), "Learner enrolled to "+course.getCoursename()+" successfully - learning management portal");
		}
				
	}
	
	/*
	 * UPDATE STATUS OF COURSE OFFERING
	 * STATUS CAN BE - IN_PROGRESS, FAILFEEDBACK_PENDING, PASSFEEDBACK_PENDING, FAILFEEDBACK_GIVEN, COMPLETED
	 */
	public CourseOffering updateCourseOfferingStatus(CourseOffering offering) {
		double percentage = offering.getPercentage();
		String feedback = offering.getFeedback();
		String status = offering.getStatus();
		if(percentage == 0.0 && status == null) {
			offering.setStatus(CourseOfferingStatus.IN_PROGRESS.name());
		} else if((percentage < 70.0) && feedback == null) {
			offering.setStatus(CourseOfferingStatus.FAIL.name()+","+CourseOfferingStatus.FEEDBACK_PENDING.name());
		} else if((percentage >= 70.0) && feedback == null) {
			offering.setStatus(CourseOfferingStatus.PASS.name()+","+CourseOfferingStatus.FEEDBACK_PENDING.name());
		} else if((percentage < 70.0) && feedback != null) {
			offering.setStatus(CourseOfferingStatus.FAIL.name()+","+CourseOfferingStatus.FEEDBACK_GIVEN.name());
		} else {
			offering.setStatus(CourseOfferingStatus.COMPLETED.name());
		}
		return offering;
	}
	
	/*
	 * UPDATE TEST SCORE OF AN INDIVIDUAL
	 */
	public void updateTestScore(int id, double percentage) {
		CourseOffering offering = dao.findById(id).get();
		offering.setPercentage(percentage);
		dao.save(updateCourseOfferingStatus(offering));
	}
	
	/*
	 * UPDATE TEST SCORE OF MULTIPLE LEARNERS
	 */
	public void updateMultipleTestScores(MultipartFile csvFilePath) throws IOException, ParseException {
		XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        XSSFRow row = worksheet.getRow(i);
			CourseOffering offering = dao.findById((int)row.getCell(0).getNumericCellValue()).get();
			offering.setPercentage((double)row.getCell(1).getNumericCellValue());
			dao.save(updateCourseOfferingStatus(offering));
		}		
		
	}
	
	/*
	 * GENERATES EXCEL SHEET OF SAMPLE DATA FOR ENROLMENT
	 */
	public void generateExcelForEnrolment(String path) throws IOException {
		ExcelHelper helper = new ExcelHelper();
		
		List<ExcelFields> fields = new ArrayList<>();
		fields.add(new ExcelFields("Learner ID", "1", XSSFCell.CELL_TYPE_NUMERIC));
		fields.add(new ExcelFields("TCID", "2", XSSFCell.CELL_TYPE_NUMERIC));
		fields.add(new ExcelFields("Start Date", "YYYY-MM-DD", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("End Date", "YYYY-MM-DD", XSSFCell.CELL_TYPE_STRING));
		
		helper.generateExcel(path, "enrollLearners.xlsx", "Sample Data", fields);
		
	}

	/*
	 * GENERATES EXCEL SHEET OF SAMPLE DATA FOR TEST SCORE UPDATE 
	 */
	public void generateExcelForScoreUpdate(String path) throws IOException {
		ExcelHelper helper = new ExcelHelper();
		
		List<ExcelFields> fields = new ArrayList<>();
		fields.add(new ExcelFields("Course Offering ID", "1", XSSFCell.CELL_TYPE_NUMERIC));
		fields.add(new ExcelFields("Percentage", "70", XSSFCell.CELL_TYPE_NUMERIC));
		
		helper.generateExcel(path, "updateScores.xlsx", "Sample Data", fields);
	}

	/*
	 * VIEW COURSE OFFERING
	 */
	public List<CourseOffering> viewCourseOfferings(){
		return (List<CourseOffering>) dao.findAll();
	}
	
	/*
	 * GET MAX ID OF COURSE OFFERING TABLE
	 */
	public int getMaxId() {
		return dao.getMaxId();
	}
	
	/*
	 * GET COURSE OFFERING BY ID
	 */
	public CourseOffering getCourseOffering(int id) {
		return dao.findById(id).get();
	}
	
	/*
	 * REMOVE COURSE OFFERING
	 */
	public void removeCourseOffering(int id) {
		dao.deleteById(id);
	}
	
	/*
	 * VIEW DETAILS OF TRAINER BY ID
	 */
	public Map<String, Object> viewTrainerDetails(int id) {
		Map<String, Object> response = new HashMap<>();
		Map<Integer, List<CourseOffering>> offerings = new HashMap<>();
		Trainer trainer = trainerService.getTrainer(id);
		List<Course> courses = tcService.getCoursesByTrainerId(id);
		List<TeacherCourseMapping> tcMappings = tcService.getByTrainerId(id); 
		for(TeacherCourseMapping tc: tcMappings) {
			List<CourseOffering> co = dao.findByTcId(tc.getTcId());
			Course course = tcService.getCourse(tc.getTcId());
			offerings.put(course.getCourseid(), co);
		}
		response.put("trainerDetails", trainer);
		response.put("courses", courses);
		response.put("offerings", offerings);
		return response;
	}
	
	/*
	 * VIEW DETAILS OF COURSE BY TRAINER ID
	 */
	public Map<String, Object> viewCourseDetailsByTrainerId(int id, int course_id) {
		Map<String, Object> response = new HashMap<>();
		double avgRating;
		Course course = courseService.getCourse(course_id);
		TeacherCourseMapping tc = tcService.getByTrainerIdAndCourseId(id, course_id);
		int sum = 0;
		List<CourseOffering> offerings = dao.findByTcId(tc.getTcId());
		for(CourseOffering c : offerings) {
			sum = sum + c.getRatings();
		}
		avgRating = (double)sum/offerings.size();
		response.put("courseDetails", course);
		response.put("offerings", offerings);
		response.put("avgRating", avgRating);
		return response;
	}
}
