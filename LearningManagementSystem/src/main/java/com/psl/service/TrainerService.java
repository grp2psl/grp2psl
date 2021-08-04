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

import com.psl.dao.ITrainerDAO;
import com.psl.entities.Trainer;

@Service("trainerService")
public class TrainerService {
	@Autowired
	private ITrainerDAO dao;
	@Autowired
	private EmailSenderService service;
	
	public void addTrainer(Trainer trainer) {
		Integer id = dao.getNextId();
		id = (id==null ? 0 : id);
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
		dao.save(trainer);
		service.sendEmail("group2.learning.management.system@gmail.com", trainer.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Trainer registered successfully - learning management portal");		
	} 

	public void addMultipleTrainers(MultipartFile csvFilePath) throws IOException {
		Integer id = dao.getNextId();
		id = (id==null ? 0 : id);
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
			dao.save(trainer);
			service.sendEmail("group2.learning.management.system@gmail.com", trainer.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Trainer registered successfully - learning management portal");		
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
		
		String fileName = "trainers.xlsx";
		
		File file = new File(path, fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		System.out.println(file.getPath());
	}
	
	public List<Trainer> getAllTrainers(){
		return dao.findAll();
	}
		
	public Trainer getTrainer(int id) {
		return dao.findById(id).get();
	}

	public void removeTrainer(int id) {
		dao.deleteById(id);
	}
}
