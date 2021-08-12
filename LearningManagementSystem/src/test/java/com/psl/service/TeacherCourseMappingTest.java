package com.psl.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.psl.entities.CourseOffering;
import com.psl.entities.TeacherCourseMapping;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TeacherCourseMappingTest {
	@Autowired 
	TeacherCourseMappingService service;
	
	@Test
	@Order(1)
	public void addTeacherCourseMappingTest() {
		TeacherCourseMapping c=new TeacherCourseMapping(4,2,6);
	    service.addTeacherCourseMapping(c);
	     
	    TeacherCourseMapping tcmapping=service.getByTrainerIdAndCourseId(2,2);
	    assertThat(tcmapping.getCourseId()).isEqualTo(2);
	    
	}
	
	/*
	 * TEST ADD MULTIPLE LEARNERS
	 */
	@Test
	@Order(2)
	public void addMultipleTeacherCourseMappingTest() throws IOException, ParseException {
		int id = service.getNextId();
		Path path = Paths.get("teachercoursemapping.xlsx");
		String name = "teachercoursemapping.xlsx";
		String originalFileName = "teachercoursemapping.xlsx";
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile file = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		service.addMultipleTeacherCourseMapping(file);
		assertThat(service.getNextId() > id);
	}
	
	/*
	 * TEST GET TRAINER AND COURSE NAMES FOR ALL TC MAPPINGS
	 */
	@Test
	@Order(3)
	public void getAllTrainerCourseNamesTest() {
		List<Object> response = service.getAllTrainerCourseNames();
		assertThat(response).size().isGreaterThan(0);
		for(Object iterator: response) {
			assertThat(iterator).hasFieldOrProperty("trainerName");
			assertThat(iterator).hasFieldOrProperty("courseName");
			assertThat(iterator).hasFieldOrProperty("trainerId");
			assertThat(iterator).hasFieldOrProperty("courseId");
			assertThat(iterator).hasFieldOrProperty("tcid");
		}
	}
	

	/*
	 * TEST FOR SCORE AND STATUS
	 */
	@Test
	@Order(4)
	public void getScoreAndStatus() {
		List<CourseOffering> cOffering = service.getCourseOffering(1,2);
		for(CourseOffering co : cOffering) {
			 assertThat(co.getLearnerid()).isEqualTo(1);
		}
	}
}
