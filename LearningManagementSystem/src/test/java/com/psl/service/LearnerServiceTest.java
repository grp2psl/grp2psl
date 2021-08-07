package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.multipart.MultipartFile;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.psl.entities.Learner;
import com.psl.utils.ExcelFields;
import com.psl.utils.ExcelHelper;
import com.psl.dao.ICourseAttended;
import com.psl.entities.CourseAttended;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class LearnerServiceTest {
	@Autowired
	LearnerService service;

	/*
	 * TEST ADD LEARNER
	 */
	@Test
	@Order(3)
	public void addLearnerTest() {
		Learner learner = new Learner();
		learner.setLearnerid(123);
		learner.setName("Vaishnavi");
		learner.setEmail("vaishnavivp18.it@coep.ac.in");
		learner.setDepartment("L&D");
		learner.setPhonenumber("9657892335");
		service.addLearner(learner);
		assertNotNull(service.getLearner(123));
	}

	/*
	 * TEST ADD MULTIPLE LEARNERS
	 */
	@Test
	@Order(4)
	public void addMultipleLearnersTest(MultipartFile csvFilePath) throws IOException {
		
	}
	
	/*
	 * TEST GENERATES EXCEL SHEET OF SAMPLE DATA OF LEARNER DETAILS
	 */
	@Test
	@Order(8)
	public void generateExcelTest(String path) throws IOException {
		
	}

	/*
	 * TEST GET DETAILS OF ALL LEARNERS
	 */
	@Test
	@Order(6)
	public void getAllLearnersTest(){
	}
	
	/*
	 * TEST GET DETAILS OF LEARNER BY ID
	 */
	@Test
	@Order(5)
	public void getLearnerTest(int id) {
	}
	
	/*
	 * TEST REMOVE LEARNER BY ID
	 */
	@Test
	@Order(7)
	public void removeLearnerTest(int id) {
	}
	

	
	@Test
	@Order(1)
	public void courseAttendedTest() {
		 List<CourseAttended> courses = (List<CourseAttended>) service.viewCourseAttended(2);
		 assertThat(courses).size().isGreaterThan(0);
		 for(CourseAttended co:courses) {
			 assertThat(co.getLearnerid()).isEqualTo(2);
		 }
	}
	
	@Test
	@Order(2)
	public void ScoreStatusTest() {
		 CourseAttended course = (CourseAttended) service.viewScoreAndStatus(2,2);
		 assertThat(course.getLearnerid()).isEqualTo(2);
		 assertThat(course.getCourseid()).isEqualTo(2);
		 
	}
}
