package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Order;

import com.psl.entities.Learner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psl.entities.CourseAttended;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class LearnerServiceTest {
	@Autowired
	LearnerService service;
	
	@Autowired
	CourseOfferingService Cservice;
	

	
	
	
	/*
	 * TEST FOR VIEW COURSE ATTENDED BY LEARNER
	 */
	@Test
	@Order(1)
	public void viewCourseAttendedTest() {
		List<Map<String, Object>> response;
		try {
			response = Cservice.viewCourseOfferingsDetailsByLearnerId(10000);
			for(Map<String, Object> r : response) {
				assertThat(r.get("learners")).hasFieldOrPropertyWithValue("learnerId", 10000);
				assertThat(r).hasFieldOrProperty("courses");
				assertThat(r).hasFieldOrProperty("offerings");
				assertThat(r).hasFieldOrProperty("trainers");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	


	/*
	 * TEST ADD LEARNER
	 */
	@Test
	@Order(2)
	public void addLearnerTest() throws JsonMappingException, JsonProcessingException {
		int id = service.getNextId();
		String request = "{\"name\":\"John Radnor\",\"email\":\"group2.learning.management.system@gmail.com\","
				+ "\"department\":\"L&D\",\"phoneNumber\":\"9657892335\"}";
		ObjectMapper mapper = new ObjectMapper();
		Learner learner = mapper.readValue(request, Learner.class);		
		service.addLearner(learner);
		Learner createdLearner = service.getLearner(id);
		assertNotNull(createdLearner);
		assertThat(createdLearner.getName()).isEqualTo("John Radnor");
		assertThat(createdLearner.getEmail()).isEqualTo("group2.learning.management.system@gmail.com");
		assertThat(createdLearner.getDepartment()).isEqualTo("L&D");		
		assertThat(createdLearner.getPhoneNumber()).isEqualTo("9657892335");
		assertNotNull(createdLearner.getPassword());
	}
	
	/*
	 * TEST ADD LEARNER WITH DUPLICATE EMAIL ID
	 */
	@Test
	@Order(3)
	public void addLearnerDuplicateEmailTest() throws JsonMappingException, JsonProcessingException {
		String request = "{\"name\":\"John Radnor\",\"email\":\"group2.learning.management.system@gmail.com\","
				+ "\"department\":\"L&D\",\"phoneNumber\":\"9657892335\"}";
		ObjectMapper mapper = new ObjectMapper();
		Learner learner = mapper.readValue(request, Learner.class);		
		assertThrows(DataIntegrityViolationException.class, () -> service.addLearner(learner));
	}
	
	/*
	 * TEST GET DETAILS OF LEARNER BY ID
	 */
	@Test
	@Order(4)
	public void getLearnerTest() {
		int id = service.getNextId();
		Learner learner = service.getLearner(id - 1);
		assertNotNull(learner);
		assertThat(learner.getName()).isEqualTo("John Radnor");
		assertThat(learner.getEmail()).isEqualTo("group2.learning.management.system@gmail.com");
		assertThat(learner.getDepartment()).isEqualTo("L&D");		
		assertThat(learner.getPhoneNumber()).isEqualTo("9657892335");
		assertNotNull(learner.getPassword());	
	}

	/*
	 * TEST REMOVE LEARNER BY ID
	 */
	@Test
	@Order(5)
	public void removeLearnerTest() {
		int id = service.getNextId();
		service.removeLearner(id - 1);
		assertThrows(NoSuchElementException.class, () -> service.getLearner(id));
	}	
	
	/*
	 * TEST REMOVE LEARNER BY ID
	 */
	@Test
	@Order(6)
	public void removeLearnerFromEmptyResultSetTest() {
		int id = service.getNextId();
		assertThrows(EmptyResultDataAccessException.class, () -> service.removeLearner(id));
	}
	
	/*
	 * TEST ADD MULTIPLE LEARNERS 
	 */
	@Test
	@Order(7)
	public void addMultipleLearnersTest() throws IOException {
		String basePath = new File("").getAbsolutePath();
		basePath = new File(basePath).getParent();
		Path path = Paths.get(basePath + "\\learners.xlsx");
		String name = "learners.xlsx";
		String originalFileName = "learners.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile csvFilePath = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		
		int id = service.getNextId();
		
		service.addMultipleLearners(csvFilePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        Learner learner = service.getLearner(id++);	        
	        assertNotNull(learner);	
	        DataFormatter formatter = new DataFormatter();
	        XSSFRow row = worksheet.getRow(i);
	        assertThat(learner.getName()).isEqualTo(row.getCell(0).getStringCellValue());
	        assertThat(learner.getDepartment()).isEqualTo(row.getCell(1).getStringCellValue());
	        assertThat(learner.getPhoneNumber()).isEqualTo(formatter.formatCellValue(row.getCell(2)));
	        assertThat(learner.getEmail()).isEqualTo(row.getCell(3).getStringCellValue());
	        assertNotNull(learner.getPassword());
	    }
		
	}

	/*
	 * TEST GET DETAILS OF ALL LEARNERS
	 */
	@Test
	@Order(8)
	public void getAllLearnersTest(){
		List<Learner> learners = service.getAllLearners();
		 assertThat(learners).size().isGreaterThan(0);
		for(Learner learner: learners) {
			assertNotNull(learner);
		}
	}	
	
	/*
	 * TEST GENERATES EXCEL SHEET OF SAMPLE DATA OF LEARNER DETAILS
	 */
	@Test
	@Order(9)
	public void generateExcelTest() throws IOException {
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		service.generateExcel(file.toString());
		File readFile = new File(file+"\\learners.xlsx");
		String basePath = new File("").getAbsolutePath();
		basePath = new File(basePath).getParent();
		assertTrue(readFile.exists());
		assertThat(readFile.length()).isGreaterThan(0);
	}
		
}
