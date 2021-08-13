package com.psl.service;

/*
 * Declaration of Trainer Service which completes various requests on trainer class
 */

import java.util.List;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.ICourseOfferingDAO;
import com.psl.dao.ITeacherCourseMappingDAO;
import com.psl.dao.ITrainerDAO;
import com.psl.entities.TeacherCourseMapping;
import com.psl.entities.Trainer;
import com.psl.utils.ExcelFields;
import com.psl.utils.ExcelHelper;

@Service("trainerService")
public class TrainerService {
	@Autowired
	private ITrainerDAO dao;
	@Autowired
	private EmailSenderService service;

	public static final Logger LOGGER = LoggerFactory.getLogger(TrainerService.class);
	private final String logPrefix = "Trainer Service - ";

	/*
	 * ADD TRAINER
	 */
	public void addTrainer(Trainer trainer) {
		LOGGER.info(logPrefix+"Adding a trainer - "+trainer);
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
		trainer.setTrainerId(id);
		trainer.setPassword(password);
		try {
			dao.saveNewEntry(trainer.getTrainerId(), trainer.getName(), trainer.getDepartment(), trainer.getPhoneNumber(), trainer.getEmail(), trainer.getPassword());
			service.sendEmail("group2.learning.management.system@gmail.com", trainer.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Trainer registered successfully - learning management portal");
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Duplicate email ID found for trainer with email ID: "+trainer.getEmail());
		}
	}

	/*
	 * ADD MULTIPLE TRAINERS
	 */
	public void addMultipleTrainers(MultipartFile csvFilePath) throws IOException {
		LOGGER.info(logPrefix+"Adding multiple trainers using file - "+csvFilePath);
	    XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);

	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        Trainer trainer = new Trainer();

	        XSSFRow row = worksheet.getRow(i);
	        trainer.setName(row.getCell(0).getStringCellValue());
	        trainer.setDepartment(row.getCell(1).getStringCellValue());
	        trainer.setPhoneNumber(row.getCell(2).getStringCellValue());
	        trainer.setEmail(row.getCell(3).getStringCellValue());
	        addTrainer(trainer);

		}
	}

	@Autowired
	private ITeacherCourseMappingDAO mappingDAO;

	@Autowired
	private ICourseOfferingDAO offeringDAO;

	/*
	 * GENERATES EXCEL SHEET OF SAMPLE DATA OF TRAINER DETAILS
	 */
	public void generateExcel(String path) throws IOException {
		LOGGER.info(logPrefix+"Generating excel format for adding multiple trainers");
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
		LOGGER.info(logPrefix+"Returning list of all the trainers");
		return dao.findAll();
	}

	/*
	 * GET DETAILS OF TRAINER
	 */
	public Trainer getTrainer(int id) {
		LOGGER.info(logPrefix+"Returning details of trainer with ID - "+id);
		return dao.findById(id).get();
	}

	public List<TeacherCourseMapping> findCoursesTaughtByTrainer(int id){
		LOGGER.info(logPrefix+"Returning List of Trainer-Course Mappings for trainer with ID - "+id);
		List<TeacherCourseMapping> l = mappingDAO.findCoursesTaughtByTrainer(id);
		System.out.println(l.size()); // Awlays returing zero
		System.out.println("Trainer Query id: " + id + " ==>");
		return mappingDAO.findCoursesTaughtByTrainer(id);
	}

	public float getFeedbackResults(int tcid) {
		LOGGER.info(logPrefix+"Returning feedback results for Trainer-Course Mapping with ID - "+tcid);
		return offeringDAO.getFeedbackResults(tcid);
	}

	public List<Float> findAllCoursesTaughtRatings(int id){
		LOGGER.info(logPrefix+"Returning list of ratings for trainer with ID - "+id);
		return offeringDAO.findAllCoursesTaughtRatings(id);
	}

	public List<String> findCommentsForACourse(int tcid){
		LOGGER.info(logPrefix+"Returning lsit of feedbacks for a Trainer-Course Mapping with ID - "+tcid);
		return offeringDAO.findCommentsForACourse(tcid);
	}

	/*
	 * REMOVE TRAINER BY ID
	 */
	public void removeTrainer(int id) {
		LOGGER.info(logPrefix+"Deleting a trainer with ID - "+id);
		dao.deleteById(id);
	}

	/*
	 * Update TRAINER BY ID
	 */
	public void updateTrainer(Trainer trainer) {
		LOGGER.info(logPrefix+"Updating details of a trainer to - "+trainer);
		dao.updateEntry(trainer.getDepartment(), trainer.getPhoneNumber(), trainer.getTrainerId());
	}

	/*
	 * GET MAX ID OF TRAINER TABLE
	 */
	public int getNextId() {
		LOGGER.info(logPrefix+"Getting next ID of trainer");
		Integer id = dao.getNextId();
		id = (id==null ? 20000 : id);
		return id;
	}
}
