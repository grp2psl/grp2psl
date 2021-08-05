package com.psl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public void updateTestScore(int id, double percentage) {
		CourseOffering offering = dao.findById(id).get();
		offering.setPercentage(percentage);
		dao.save(updateCourseOfferingStatus(offering));
	}
	
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
	
	public void generateExcelForEnrolment(String path) throws IOException {
		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Sample Data");
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 4000);
		
		
		Row header = sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setBold(true);
		headerStyle.setFont(font);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("Learner ID");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(1);
		headerCell.setCellValue("TCID");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(2);
		headerCell.setCellValue("Start Date");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(3);
		headerCell.setCellValue("End Date");
		headerCell.setCellStyle(headerStyle);

		Row data = sheet.createRow(1);
		Cell dataCell = data.createCell(0);
		dataCell.setCellValue(1);
		dataCell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);

		dataCell = data.createCell(1);
		dataCell.setCellValue(2);
		dataCell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);

		dataCell = data.createCell(2);
		dataCell.setCellValue("YYYY-MM-DD");
		dataCell.setCellType(XSSFCell.CELL_TYPE_STRING);

		dataCell = data.createCell(3);
		dataCell.setCellValue("YYYY-MM-DD");
		dataCell.setCellType(XSSFCell.CELL_TYPE_STRING);
		
		String fileName = "enrollLearners.xlsx";
		
		File file = new File(path, fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		System.out.println(file.getPath());
	}

	
	public void generateExcelForScoreUpdate(String path) throws IOException {
		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Sample Data");
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 4000);
		
		
		Row header = sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setBold(true);
		headerStyle.setFont(font);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("Course Offering ID");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(1);
		headerCell.setCellValue("Percentage");
		headerCell.setCellStyle(headerStyle);
		
		Row data = sheet.createRow(1);
		Cell dataCell = data.createCell(0);
		dataCell.setCellValue(1);
		dataCell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);

		dataCell = data.createCell(1);
		dataCell.setCellValue(70);
		dataCell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);

		String fileName = "updateScores.xlsx";
		
		File file = new File(path, fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		System.out.println(file.getPath());
	}

	public List<CourseOffering> viewCourseOfferings(){
		return (List<CourseOffering>) dao.findAll();
	}
	
	public void removeCourseOffering(int id) {
		dao.deleteById(id);
	}
	
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
	
	public Map<String, Object> viewCourseDetails(int id) {
		Map<String, Object> response = new HashMap<>();
		Map<Integer, List<CourseOffering>> offerings = new HashMap<>();
		Map<Integer, Double> avgRating = new HashMap<>();
		Course course = courseService.getCourse(id);
		List<TeacherCourseMapping> tcMappings = tcService.getByCourseId(id);
		for(TeacherCourseMapping tc: tcMappings) {
			int sum = 0;
			List<CourseOffering> co = dao.findByTcId(tc.getTcId());
			for(CourseOffering c : co) {
				sum = sum + c.getRatings();
			}
			avgRating.put(tc.getTcId(), (double)sum/co.size());
			offerings.put(tc.getTcId(), co);
		}
		response.put("courseDetails", course);
		response.put("offerings", offerings);
		response.put("avgRating", avgRating);
		return response;
	}
}
