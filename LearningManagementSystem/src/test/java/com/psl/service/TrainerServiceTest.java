package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psl.entities.CourseOffering;
import com.psl.entities.Learner;
import com.psl.entities.Trainer;
import com.psl.utils.ExcelFields;
import com.psl.utils.ExcelHelper;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TrainerServiceTest {
	@Autowired
	private TrainerService service;
	
	/*
	 * TEST ADD TRAINER
	 */
	@Test
	@Order(1)
	public void addTrainerTest() throws JsonMappingException, JsonProcessingException {
		int id = service.getNextId();
		System.out.println(id);
		String request = "{\"name\":\"John Radnor\",\"email\":\"group2.learning.management.system@gmail.com\","
				+ "\"department\":\"L&D\",\"phonenumber\":\"9657892335\"}";
		ObjectMapper mapper = new ObjectMapper();
		Trainer trainer = mapper.readValue(request, Trainer.class);		
		service.addTrainer(trainer);
		Trainer createdTrainer = service.getTrainer(id);
		assertNotNull(createdTrainer);
		assertThat(createdTrainer.getName()).isEqualTo("John Radnor");
		assertThat(createdTrainer.getEmail()).isEqualTo("group2.learning.management.system@gmail.com");
		assertThat(createdTrainer.getDepartment()).isEqualTo("L&D");		
		assertThat(createdTrainer.getPhonenumber()).isEqualTo("9657892335");
		assertNotNull(createdTrainer.getPassword());
	} 

	/*
	 * TEST GET DETAILS OF TRAINER
	 */
	@Test
	@Order(2)
	public void getTrainerTest() {
		int id = service.getNextId();
		Trainer trainer = service.getTrainer(id - 1);
		assertNotNull(trainer);
		assertThat(trainer.getName()).isEqualTo("John Radnor");
		assertThat(trainer.getEmail()).isEqualTo("group2.learning.management.system@gmail.com");
		assertThat(trainer.getDepartment()).isEqualTo("L&D");		
		assertThat(trainer.getPhonenumber()).isEqualTo("9657892335");
		assertNotNull(trainer.getPassword());		
	}

	/*
	 * TEST ADD MULTIPLE TRAINERS
	 */
	@Test
	@Order(3)
	public void addMultipleTrainersTest() throws IOException {
		String basePath = new File("").getAbsolutePath();
		basePath = new File(basePath).getParent();
		Path path = Paths.get(basePath + "\\trainers.xlsx");
		System.out.println(path);
		String name = "trainers.xlsx";
		String originalFileName = "trainers.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		try {
		    content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		MultipartFile csvFilePath = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		
		int id = service.getNextId();
		
		service.addMultipleTrainers(csvFilePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        Trainer trainer = service.getTrainer(id++);	        
	        assertNotNull(trainer);
	        XSSFRow row = worksheet.getRow(i);
	        assertThat(trainer.getName()).isEqualTo(row.getCell(0).getStringCellValue());
	        assertThat(trainer.getDepartment()).isEqualTo(row.getCell(1).getStringCellValue());
	        assertThat(trainer.getPhonenumber()).isEqualTo(row.getCell(2).getStringCellValue());
	        assertThat(trainer.getEmail()).isEqualTo(row.getCell(3).getStringCellValue());
	        assertNotNull(trainer.getPassword());
	    }
	}
		
	/*
	 * TEST GET DETAILS OF ALL TRAINERS
	 */
	@Test
	@Order(4)
	public void getAllTrainersTest(){
		List<Trainer> trainers = service.getAllTrainers();
		 assertThat(trainers).size().isGreaterThan(0);
		for(Trainer trainer: trainers) {
			assertNotNull(trainer);
		}
	}

	/*
	 * TEST REMOVE TRAINER BY ID
	 */
	@Test
	@Order(5)
	public void removeTrainer() {
		int id = service.getNextId();
		service.removeTrainer(id - 1);
		assertThrows(NoSuchElementException.class, () -> service.getTrainer(id));
	}
	
	/*
	 * TEST GENERATES EXCEL SHEET OF SAMPLE DATA OF TRAINER DETAILS
	 */
	@Test
	@Order(6)
	public void generateExcelTest() throws IOException {
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		service.generateExcel(file.toString());
		File readFile = new File(file+"\\trainers.xlsx");
		String basePath = new File("").getAbsolutePath();
		basePath = new File(basePath).getParent();
		assertTrue(readFile.exists());
		assertThat(readFile.length()).isGreaterThan(0);
	}
}
