package com.psl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.ICourseOfferingDAO;
import com.psl.dao.ITeacherCourseMappingDAO;
import com.psl.entities.Course;
import com.psl.entities.CourseOffering;
import com.psl.entities.TeacherCourseMapping;
import com.psl.utils.ExcelFields;
import com.psl.utils.ExcelHelper;
import com.psl.entities.Trainer;

@Service("teacherCourseMappingService")
public class TeacherCourseMappingService {
	
	@Autowired
	private ITeacherCourseMappingDAO dao;
	
	@Autowired
	private ICourseOfferingDAO COdao;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TrainerService trainerService; 
	
	/*
	 *ADDING TEACHER-COURSE MAPPING DETAILS
	 */
	public void addTeacherCourseMapping(TeacherCourseMapping teacherCourseMapping) {
		Integer id = dao.getNextId();
		id = (id==null ? 0 : id + 1);
		teacherCourseMapping.setTcId(id);
		dao.save(teacherCourseMapping);
		
	}
	
	/*
	 *ADDING MULTIPLE TEACHER-COURSE MAPPING DETAILS FROM CSV FILE
	 */
	public void addMultipleTeacherCourseMapping(MultipartFile csvFilePath) throws IOException {
		Integer id = dao.getNextId();
		id = id==null ? 0 : id;
		XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        TeacherCourseMapping teacherCourseMapping = new TeacherCourseMapping();
	            
	        XSSFRow row = worksheet.getRow(i);
	        teacherCourseMapping.setTcId(++id);
	        teacherCourseMapping.setTrainerId((int)row.getCell(0).getNumericCellValue());
	        teacherCourseMapping.setCourseId((int)row.getCell(1).getNumericCellValue());
	       // addTeacherCourseMapping(teacherCourseMapping);
			dao.save(teacherCourseMapping);
					
	    }
	}
	
	/*
	 * GENERATES EXCEL SHEET OF SAMPLE DATA OF LEARNER DETAILS
	 */
	public void generateExcel(String path) throws IOException {
		ExcelHelper helper = new ExcelHelper();
		
		List<ExcelFields> fields = new ArrayList<>();
		fields.add(new ExcelFields("trainerid", "2", XSSFCell.CELL_TYPE_NUMERIC));
		fields.add(new ExcelFields("courseid", "126", XSSFCell.CELL_TYPE_NUMERIC));
		
		
		helper.generateExcel(path, "teachercoursemapping.xlsx", "Sample Data", fields);
	}
	
	/*
	 * GET MAX ID OF TeacherCourseMapping TABLE
	 */
	public int getNextId() {
		Integer id = dao.getNextId();
		id = (id==null ? 10000 : id);
		return id;
	}

	/*
	 * GET COURSE BY TCID (TRAINER-COURSE MAPPING ID)
	 */
	public Course getCourse(int tcid) {
		TeacherCourseMapping tcMapping = dao.findById(tcid).get();
		return courseService.getCourse(tcMapping.getCourseId());
	}
	
	/*
	 * GET TRAINER AND COURSE NAMES FOR ALL TRAINER-COURSE MAPPINGS
	 */
	public List<Object> getAllTrainerCourseNames() {
		List<TeacherCourseMapping> tcMappings = (List<TeacherCourseMapping>) dao.findAll();
		List<Object> tcNames = new ArrayList<>();
		for(TeacherCourseMapping tc: tcMappings) {
			Map<String, Object> tcName = new HashMap<>();
			Course course = courseService.getCourse(tc.getCourseId());
			Trainer trainer = trainerService.getTrainer(tc.getTrainerId());
			tcName.put("tcid", tc.getTcId());
			tcName.put("trainerid", tc.getTrainerId());
			tcName.put("trainerName", trainer.getName());
			tcName.put("courseid", tc.getCourseId());
			tcName.put("courseName", course.getCoursename());
			tcNames.add(tcName);
		}
		return tcNames;
	}
	
	/*
	 * GET TRAINER-COURSE MAPPINGS BY TRAINER ID
	 */
	public List<TeacherCourseMapping> getByTrainerId(int trainerid) {
		return dao.findByTrainerId(trainerid);
	}
	
	/*
	 * GET TRAINER-COURSE MAPPINGS BY COURSE ID
	 */
	public List<TeacherCourseMapping> getByCourseId(int courseid) {
		return dao.findByCourseId(courseid);
	}
	
	/*
	 * GET TRAINER-COURSE MAPPING BY TRAINER ID AND COURSE ID
	 */
	public TeacherCourseMapping getByTrainerIdAndCourseId(int id, int courseid) {
		return dao.findByTrainerIdAndCourseId(id, courseid);
	}
	
	public List<CourseOffering> getCourseOffering(int id, int courseid) {
		List<CourseOffering> courseOffering = new ArrayList<>();
		CourseOffering cOffering= new CourseOffering();
		List<TeacherCourseMapping> list = dao.findByCourseId(courseid);
		for(TeacherCourseMapping tc: list) {
			cOffering = COdao.findByTcIdAndLearnerId(tc.getTcId(),id);
			if(cOffering!= null) {
				courseOffering.add(cOffering);
			}
			
		}
		return courseOffering;
	}
	
	/*
	 * GET COURSES BY TRAINER ID
	 */
	public List<Course> getCoursesByTrainerId(int trainerid) {
		List<TeacherCourseMapping> tcMapping = dao.findByTrainerId(trainerid);
		List<Course> courses = new ArrayList<>();
		for(TeacherCourseMapping i: tcMapping) {
			courses.add(courseService.getCourse(i.getCourseId()));
		}
		return courses;
	}
	
	public TeacherCourseMapping getById(int id) {
		return dao.findById(id).get();
	}
}
