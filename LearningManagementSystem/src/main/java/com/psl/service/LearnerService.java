package com.psl.service;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.ILearnerDAO;
import com.psl.entities.Learner;

@Service("learnerService")
public class LearnerService {
	@Autowired
	private ILearnerDAO dao;
	@Autowired
	private EmailSenderService service;
	
	public void addLearner(Learner learner) {
		Random rand = new Random();
		String firstname = learner.getName().substring(0, learner.getName().indexOf(" "));
		String password = firstname+learner.getLearnerid()+"@"+rand.nextInt(9999);
		learner.setPassword(password);
		dao.save(learner);
		service.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Learner registered successfully - learning management portal");		
	} 
	
	public void addMultipleLearners(MultipartFile csvFilePath) throws IOException {
	    XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
		Random rand = new Random();
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        Learner learner = new Learner();
	            
	        XSSFRow row = worksheet.getRow(i);
	        learner.setLearnerid((int)row.getCell(0).getNumericCellValue());   
	        learner.setName(row.getCell(1).getStringCellValue());
	        learner.setDepartment(row.getCell(2).getStringCellValue());
	        learner.setPhonenumber((long)row.getCell(3).getNumericCellValue());
	        learner.setEmail(row.getCell(4).getStringCellValue());
			String firstname = learner.getName().substring(0, learner.getName().indexOf(" "));
			String password = firstname+learner.getLearnerid()+"@"+rand.nextInt(9999);
			learner.setPassword(password);
			dao.save(learner);
			service.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Learner registered successfully - learning management portal");		
	    }
				
	}
	
	public Learner getLearner(int id) {
		return dao.findById(id).get();
	}
	
}
