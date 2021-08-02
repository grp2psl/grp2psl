package com.psl.service;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.ITeacherCourseMappingDAO;
import com.psl.entities.TeacherCourseMapping;

@Service("teacherCourseMappingService")
public class TeacherCourseMappingService {
	
	@Autowired
	private ITeacherCourseMappingDAO dao;
	
	public void addTeacherCourseMapping(TeacherCourseMapping teacherCourseMapping) {
		
		dao.save(teacherCourseMapping);
		
	}
	
	public void addMultipleTeacherCourseMapping(MultipartFile csvFilePath) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        TeacherCourseMapping teacherCourseMapping = new TeacherCourseMapping();
	            
	        XSSFRow row = worksheet.getRow(i);
	        teacherCourseMapping.setTcId((int)row.getCell(0).getNumericCellValue());
	        teacherCourseMapping.setTrainerId((int)row.getCell(1).getNumericCellValue());
	        teacherCourseMapping.setCourseId((int)row.getCell(2).getNumericCellValue());
	        
			dao.save(teacherCourseMapping);
					
	    }
		
		
		
	}

}
