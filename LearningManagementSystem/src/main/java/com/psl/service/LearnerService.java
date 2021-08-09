package com.psl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.Random;

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

import com.psl.dao.ICourseAttended;
import com.psl.dao.ILearnerDAO;
import com.psl.entities.CourseAttended;
import com.psl.entities.Learner;
import com.psl.utils.ExcelFields;
import com.psl.utils.ExcelHelper;


@Service("learnerService")
public class LearnerService {
	@Autowired
	private ILearnerDAO dao;
	
	@Autowired
	private ICourseAttended Cdao;
	
	@Autowired
	private EmailSenderService service;
	
	/*
	 * ADD LEARNER
	 */
	public void addLearner(Learner learner) {
		Integer id = dao.getNextId();
		id = (id==null ? 0 : id + 1);
		Random rand = new Random();
		String firstname = learner.getName();
		try {
			firstname = firstname.substring(0, learner.getName().indexOf(" "));
		}catch(StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		String password = firstname+id+"@"+rand.nextInt(9999);
		learner.setLearnerid(id);
		learner.setPassword(password);
		try {
			dao.saveNewEntry(learner.getLearnerid(), learner.getName(), learner.getDepartment(), learner.getPhonenumber(), learner.getEmail(), learner.getPassword());
			service.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Learner registered successfully - learning management portal");		
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	} 
	
	/*
	 * ADD MULTIPLE LEARNERS
	 */
	public void addMultipleLearners(MultipartFile csvFilePath) throws IOException {
		Integer id = dao.getNextId();
		id = (id==null ? 0 : id + 1);
	    XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
		Random rand = new Random();
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        Learner learner = new Learner();
	            
	        XSSFRow row = worksheet.getRow(i);
	        learner.setLearnerid(id++);   
	        learner.setName(row.getCell(0).getStringCellValue());
	        learner.setDepartment(row.getCell(1).getStringCellValue());
	        learner.setPhonenumber(row.getCell(2).getStringCellValue());
	        learner.setEmail(row.getCell(3).getStringCellValue());
	        String firstname = learner.getName();
			try {
				firstname = firstname.substring(0, learner.getName().indexOf(" "));
			}catch(StringIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
			String password = firstname+learner.getLearnerid()+"@"+rand.nextInt(9999);
			learner.setPassword(password);

			try {
				dao.saveNewEntry(learner.getLearnerid(), learner.getName(), learner.getDepartment(), learner.getPhonenumber(), learner.getEmail(), learner.getPassword());
				service.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Learner registered successfully - learning management portal");		
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		}				
	}
	
	/*
	 * GENERATES EXCEL SHEET OF SAMPLE DATA OF LEARNER DETAILS
	 */
	public void generateExcel(String path) throws IOException {
		ExcelHelper helper = new ExcelHelper();
		
		List<ExcelFields> fields = new ArrayList<>();
		fields.add(new ExcelFields("Name", "Firstname Lastname", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("Department", "DepartmentName", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("Phone Number", "9876543210", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("Email", "something@email.com", XSSFCell.CELL_TYPE_STRING));
		
		helper.generateExcel(path, "learners.xlsx", "Sample Data", fields);
	}

	/*
	 * GET DETAILS OF ALL LEARNERS
	 */
	public List<Learner> getAllLearners(){
		return dao.findAll();
	}
	
	/*
	 * GET DETAILS OF LEARNER BY ID
	 */
	public Learner getLearner(int id) {
		System.out.println(dao.findById(id).get());
		return dao.findById(id).get();
	}
	
	/*
	 * REMOVE LEARNER BY ID
	 */
	public void removeLearner(int id) {
		dao.deleteById(id);
	}
	
	/*
	 * VIEW ALL COURSES ATTENDED BY A LEARNER
	 * FOR LOOP FOR GETTING SPECIFIC DETAILS OF LEARNERID
	 */
	public List<CourseAttended> viewCourseAttended(int id) {
		List<CourseAttended> cAttended=new ArrayList<>();
		List<CourseAttended> courseAttendedByLearners = Cdao.courseAttended();
		for(CourseAttended cl:courseAttendedByLearners) {
			if(cl.getLearnerid()==id){
				cAttended.add(cl);
				
			}
		}
		return cAttended;
	}
	
	/*
	 * VIEW SCORE AND STATUS OF A COURSES 
	 * FOR LOOP FOR GETTING SPECIFIC DETAILS OF LEARNERID
	 */
	public CourseAttended viewScoreAndStatus(int id,int courseId) {
		List<CourseAttended> cAttended= viewCourseAttended(id);
		CourseAttended course= new CourseAttended();
	
		for(CourseAttended cl:cAttended) {
			if(cl.getCourseid()==courseId){
				course=cl;
				
			}
		}
		return course;
}
}
