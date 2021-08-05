package com.psl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.psl.dao.IScoreStatus;
import com.psl.entities.CourseAttended;
import com.psl.entities.Learner;
import com.psl.entities.ScoreStatus;

@Service("learnerService")
public class LearnerService {
	@Autowired
	private ILearnerDAO dao;
	
	@Autowired
	private IScoreStatus Idao;
	
	@Autowired
	private ICourseAttended Cdao;
	
	@Autowired
	private EmailSenderService service;
	
	public void addLearner(Learner learner) {
		Integer id = dao.getNextId();
		id = (id==null ? 0 : id);
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
		dao.save(learner);
		service.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Learner registered successfully - learning management portal");		
	} 
	
	public void addMultipleLearners(MultipartFile csvFilePath) throws IOException {
		Integer id = dao.getNextId();
		id = (id==null ? 0 : id);
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
			dao.save(learner);
			service.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Learner registered successfully - learning management portal");		
	    }
				
	}
	
	public void generateExcel(String path) throws IOException {
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
		headerCell.setCellValue("Name");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(1);
		headerCell.setCellValue("Department");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(2);
		headerCell.setCellValue("Phone Number");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(3);
		headerCell.setCellValue("Email");
		headerCell.setCellStyle(headerStyle);

		Row data = sheet.createRow(1);
		Cell dataCell = data.createCell(0);
		dataCell.setCellValue("Firstname Lastname");
		dataCell.setCellType(XSSFCell.CELL_TYPE_STRING);

		dataCell = data.createCell(1);
		dataCell.setCellValue("Department");
		dataCell.setCellType(XSSFCell.CELL_TYPE_STRING);

		dataCell = data.createCell(2);
		dataCell.setCellValue("9876543210");
		dataCell.setCellType(XSSFCell.CELL_TYPE_STRING);

		dataCell = data.createCell(3);
		dataCell.setCellValue("something@email.com");
		dataCell.setCellType(XSSFCell.CELL_TYPE_STRING);
		
		String fileName = "learners.xlsx";
		
		File file = new File(path, fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		System.out.println(file.getPath());
	}

	public List<Learner> getAllLearners(){
		return dao.findAll();
	}
	
	public Learner getLearner(int id) {
		System.out.println(dao.findById(id).get());
		return dao.findById(id).get();
	}
	
	public void removeLearner(int id) {
		dao.deleteById(id);
	}

	public CourseAttended viewCourseAttended(int id) {
		CourseAttended cAttended=new CourseAttended();
		List<CourseAttended> courseAttendedByLearners = Cdao.courseAttended();
		for(CourseAttended cl:courseAttendedByLearners) {
			if(cl.getLearnerid()==id){
				cAttended=cl;
				
			}
		}
		return cAttended;
	}
	
	public ScoreStatus viewScoreAndStatus(int id) {
		ScoreStatus sStatus=new ScoreStatus();
		List<ScoreStatus> scoreOfLearner = Idao.scoreAndStatus();
		for(ScoreStatus cl:scoreOfLearner) {
			if(cl.getLearnerid()==id){
				sStatus=cl;
			}
		}
		return sStatus;
}
}
