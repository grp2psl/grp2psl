package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psl.entities.CourseOffering;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CourseOfferingServiceTest {

	@Autowired
	CourseOfferingService service;
	
	@Test
	@Order(1)
	/*
	 * TEST ENROLL LEARNER
	 */
	public void enrollLearnerTest() throws ParseException, JsonProcessingException, JsonMappingException{
		int id = service.getMaxId(); //implicit testing of getMaxId()
		String request = "{\"learnerid\":12,\"tcid\":1,\"startdate\":\"2021-09-01\",\"enddate\":\"2021-09-05\"}";
		ObjectMapper mapper = new ObjectMapper();
		CourseOffering offering = mapper.readValue(request, CourseOffering.class);
		service.enrollLearner(offering);
		CourseOffering result = service.getCourseOffering(service.getMaxId()); //implicit testing of getCourseOffering(int id)
		assertThat(result.getLearnerid()).isEqualTo(12);
		assertThat(result.getTcid()).isEqualTo(1);
		assertThat(result.getCourseofferingid()).isEqualTo(id+1);
	}
	
	@Test
	@Order(2)
	/*
	 * TEST UPDATE TEST SCORE OF AN INDIVIDUAL
	 */
	public void updateTestScoreTest() {
		service.updateTestScore(service.getMaxId(), 67);
		CourseOffering result = service.getCourseOffering(service.getMaxId());
		assertThat(result.getLearnerid()).isEqualTo(12);
		assertThat(result.getPercentage()).isEqualTo(67);
		assertThat(result.getFeedback()).isEqualTo(null);
		assertThat(result.getStatus()).isEqualTo("FAIL,FEEDBACK_PENDING"); //implicit testing of updateCourseOfferingStatus(CourseOffering offering)
	}
	
	@Test
	@Order(3)
	/*
	 * TEST VIEW COURSE OFFERING
	 */
	public void viewCourseOfferingsTest() {
		List<CourseOffering> offerings = service.viewCourseOfferings();
		assertThat(offerings).size().isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	/*
	 * TEST REMOVE COURSE OFFERING
	 */
	public void removeCourseOfferingTest() {
		int id = service.getMaxId();
		service.removeCourseOffering(id);
		CourseOffering offering = service.getCourseOffering(service.getMaxId());
		assertThat(offering.getCourseofferingid()).isEqualTo(id-1);
	}	

	@Test
	@Order(5)
	/*
	 * TEST ENROLL MULTIPLE LEARNERS
	 */
	public void enrollMultipleLearnersTest() throws IOException, ParseException {
		int id = service.getMaxId();
		Path path = Paths.get("EnrollMultipleLearners.xlsx");
		String name = "EnrollMultipleLearners.xlsx";
		String originalFileName = "EnrollMultipleLearners.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile file = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		service.enrollMultipleLearners(file);
		assertThat(service.getMaxId() > id);
	}
	
	@Test
	@Order(6)
	/*
	 * TEST UPDATE TEST SCORE OF MULTIPLE LEARNERS
	 */
	public void updateMultipleTestScoresTest() throws IOException, ParseException {
		Path path = Paths.get("update-score.xlsx");
		String name = "update-score.xlsx";
		String originalFileName = "update-score.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile file = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		service.updateMultipleTestScores(file);
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    XSSFRow row = worksheet.getRow(worksheet.getPhysicalNumberOfRows()-1);
		CourseOffering offering = service.getCourseOffering((int)row.getCell(0).getNumericCellValue());
		double percentage = (double)row.getCell(1).getNumericCellValue();
	    assertThat(offering.getPercentage()).isEqualTo(percentage);
	}
	
	@Test
	@Order(7)
	/*
	 * TEST VIEW DETAILS OF TRAINER BY ID
	 */
	public void viewTrainerDetailsTest() {
		Map<String, Object> response = service.viewTrainerDetails(1);
		assertThat(response.get("trainerDetails")).hasFieldOrPropertyWithValue("trainerid", 1);
		assertThat(response).hasFieldOrProperty("courses");
		assertThat(response).hasFieldOrProperty("offerings");
	}
	
	@Test
	@Order(8)
	/*
	 * TEST VIEW DETAILS OF COURSE BY TRAINER ID
	 */
	public void viewCourseDetailsByTrainerIdTest() {
		Map<String, Object> response = service.viewCourseDetailsByTrainerId(1, 2);
		assertThat(response.get("courseDetails")).hasFieldOrPropertyWithValue("courseid", 2);
		assertThat(response).hasFieldOrProperty("avgRating");
		assertThat(response).hasFieldOrProperty("offerings");
	}
	
	@Test
	@Order(9)
	/*
	 * TEST GENERATE EXCEL SHEET OF SAMPLE DATA FOR ENROLMENT
	 */
	public void generateExcelForEnrolmentTest() throws IOException {
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		service.generateExcelForEnrolment(file.toString());
		File readFile = new File(file+"\\enrollLearners.xlsx");
		String basePath = new File("").getAbsolutePath();
		basePath = new File(basePath).getParent();
		assertTrue(readFile.exists());
		assertThat(readFile.length()).isGreaterThan(0);
	}
	
	@Test
	@Order(10)
	/*
	 * TEST GENERATE EXCEL SHEET OF SAMPLE DATA FOR TEST SCORE UPDATE 
	 */
	public void generateExcelForScoreUpdateTest() throws IOException {
		Path file = Paths.get(System.getProperty("user.home"), "Downloads");
		service.generateExcelForScoreUpdate(file.toString());
		File readFile = new File(file+"\\updateScores.xlsx");
		String basePath = new File("").getAbsolutePath();
		basePath = new File(basePath).getParent();
		assertTrue(readFile.exists());
		assertThat(readFile.length()).isGreaterThan(0);
	}
	
	/*
	 * TEST VIEW DETAILS OF COURSE OFFERINGS
	 */
	@Test
	@Order(11)
	public void viewCourseOfferingsDetailsTest() throws ParseException {
		List<Map<String, Object>> list = service.viewCourseOfferingsDetails();
		assertNotNull(list);
		assertThat(list.size()).isGreaterThan(0);		
		for(Map<String, Object> response : list) {
			assertThat(response).hasFieldOrProperty("offerings");
			assertThat(response).hasFieldOrProperty("trainers");
			assertThat(response).hasFieldOrProperty("learners");
			assertThat(response).hasFieldOrProperty("courses");
		}
	}
}
