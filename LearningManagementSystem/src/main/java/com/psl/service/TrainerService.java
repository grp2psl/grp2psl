package com.psl.service;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.ITrainerDAO;
import com.psl.entities.Trainer;

@Service("trainerService")
public class TrainerService {
	@Autowired
	private ITrainerDAO dao;
	@Autowired
	private EmailSenderService service;
	
	public void addTrainer(Trainer trainer) {
		Random rand = new Random();
		String firstname = trainer.getName().substring(0, trainer.getName().indexOf(" "));
		String password = firstname+trainer.getTrainerid()+"@"+rand.nextInt(9999);
		trainer.setPassword(password);
		dao.save(trainer);
		service.sendEmail("group2.learning.management.system@gmail.com", trainer.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Trainer registered successfully - learning management portal");		
	} 

	public void addMultipleTrainers(MultipartFile csvFilePath) throws IOException {
	    XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
		Random rand = new Random();
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        Trainer trainer = new Trainer();
	            
	        XSSFRow row = worksheet.getRow(i);
	        trainer.setTrainerid((int)row.getCell(0).getNumericCellValue());   
	        trainer.setName(row.getCell(1).getStringCellValue());
	        trainer.setDepartment(row.getCell(2).getStringCellValue());
	        trainer.setPhonenumber(row.getCell(3).getStringCellValue());
	        trainer.setEmail(row.getCell(4).getStringCellValue());
			String firstname = trainer.getName().substring(0, trainer.getName().indexOf(" "));
			String password = firstname+trainer.getTrainerid()+"@"+rand.nextInt(9999);
			trainer.setPassword(password);
			dao.save(trainer);
			service.sendEmail("group2.learning.management.system@gmail.com", trainer.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Learner registered successfully - learning management portal");		
	    }
				
	}
	
	public Trainer getTrainer(int id) {
		return dao.findById(id).get();
	}
}
