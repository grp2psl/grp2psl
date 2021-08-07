package com.psl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

import com.psl.dao.ITrainerDAO;
import com.psl.entities.Trainer;
import com.psl.utils.ExcelFields;
import com.psl.utils.ExcelHelper;

@Service("trainerService")
public class TrainerService {
	@Autowired
	private ITrainerDAO dao;
	@Autowired
	private EmailSenderService service;
	
	/*
	 * ADD TRAINER
	 */
	public void addTrainer(Trainer trainer) {
		Integer id = dao.getNextId();
		id = (id==null ? 20000 : id);
		Random rand = new Random();
        String firstname = trainer.getName();
		try {
			firstname = firstname.substring(0, trainer.getName().indexOf(" "));
		}catch(StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		String password = firstname+id+"@"+rand.nextInt(9999);
		trainer.setTrainerid(id);
		trainer.setPassword(password);
		try {
			dao.saveNewEntry(trainer.getTrainerid(), trainer.getName(), trainer.getDepartment(), trainer.getPhonenumber(), trainer.getEmail(), trainer.getPassword());
			service.sendEmail("group2.learning.management.system@gmail.com", trainer.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Trainer registered successfully - learning management portal");	
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	} 

	/*
	 * ADD MULTIPLE TRAINERS
	 */
	public void addMultipleTrainers(MultipartFile csvFilePath) throws IOException {
		Integer id = dao.getNextId();
		id = (id==null ? 20000 : id);
	    XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
		Random rand = new Random();
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        Trainer trainer = new Trainer();
	            
	        XSSFRow row = worksheet.getRow(i);
	        trainer.setTrainerid(id++);   
	        trainer.setName(row.getCell(0).getStringCellValue());
	        trainer.setDepartment(row.getCell(1).getStringCellValue());
	        trainer.setPhonenumber(row.getCell(2).getStringCellValue());
	        trainer.setEmail(row.getCell(3).getStringCellValue());
	        String firstname = trainer.getName();
			try {
				firstname = firstname.substring(0, trainer.getName().indexOf(" "));
			}catch(StringIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
			String password = firstname+trainer.getTrainerid()+"@"+rand.nextInt(9999);
			trainer.setPassword(password);
			try {
				dao.saveNewEntry(trainer.getTrainerid(), trainer.getName(), trainer.getDepartment(), trainer.getPhonenumber(), trainer.getEmail(), trainer.getPassword());
				service.sendEmail("group2.learning.management.system@gmail.com", trainer.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Trainer registered successfully - learning management portal");	
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		}				
	}
	
	/*
	 * GENERATES EXCEL SHEET OF SAMPLE DATA OF TRAINER DETAILS
	 */
	public void generateExcel(String path) throws IOException {
		ExcelHelper helper = new ExcelHelper();
		
		List<ExcelFields> fields = new ArrayList<>();
		fields.add(new ExcelFields("Name", "Firstname Lastname", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("Department", "DepartmentName", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("Phone Number", "9876543210", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("Email", "something@email.com", XSSFCell.CELL_TYPE_STRING));
		
		helper.generateExcel(path, "trainers.xlsx", "Sample Data", fields);
	}
	
	/*
	 * GET DETAILS OF ALL TRAINERS
	 */
	public List<Trainer> getAllTrainers(){
		return dao.findAll();
	}
		
	/*
	 * GET DETAILS OF TRAINER
	 */
	public Trainer getTrainer(int id) {
		return dao.findById(id).get();
	}

	/*
	 * REMOVE TRAINER BY ID
	 */
	public void removeTrainer(int id) {
		dao.deleteById(id);
	}
	
	/*
	 * GET MAX ID OF TRAINER TABLE
	 */
	public int getNextId() {
		Integer id = dao.getNextId();
		id = (id==null ? 20000 : id);
		return id;
	}
}
