/*
 * Declaration of Leaner Service which completes various requests on learner class
 */
package com.psl.service;

//Required imports for the service declaration
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.psl.dao.ICourseDAO;
import com.psl.dao.ICourseOfferingDAO;
import com.psl.dao.ILearnerDAO;
import com.psl.dao.ITeacherCourseMappingDAO;
import com.psl.dao.ITrainerDAO;
import com.psl.entities.Course;

import com.psl.entities.CourseOffering;
import com.psl.entities.Learner;
import com.psl.entities.Manager;
import com.psl.entities.TeacherCourseMapping;
import com.psl.entities.Trainer;
import com.psl.utils.ExcelFields;
import com.psl.utils.ExcelHelper;


//This annotation enables use of this class as a service named learnerService.
@Service("learnerService")
public class LearnerService {

	//Autowires the learner service with learner dao interface.
	@Autowired
	private ILearnerDAO dao;

	@Autowired
	private EmailSenderService service;

	@Autowired
	private ICourseOfferingDAO COdao;

	@Autowired
	private ICourseDAO Coursedao;

	@Autowired
	private ITrainerDAO Tdao;

	@Autowired
	private ITeacherCourseMappingDAO TCdao;

	public static final Logger LOGGER = LoggerFactory.getLogger(LearnerService.class);
	private final String logPrefix = "Learner Service - ";

	/*
	 * ADD LEARNER
	 */
	public void addLearner(Learner learner) {
		LOGGER.info(logPrefix+"Adding a learner - "+learner);
		Integer id = dao.getNextId();
		id = (id==null ? 10000 : id);
		Random rand = new Random();
		String firstname = learner.getName();
		try {
			firstname = firstname.substring(0, learner.getName().indexOf(" "));
		}catch(StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		String password = firstname+id+"@"+rand.nextInt(9999);
		learner.setLearnerId(id);
		learner.setPassword(password);
		try {
			dao.saveNewEntry(learner.getLearnerId(), learner.getName(), learner.getDepartment(), learner.getPhoneNumber(), learner.getEmail(), learner.getPassword());
			service.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + firstname +", \nYour password is "+password+"\nChange your password once you are logged in.", "Learner registered successfully - learning management portal");
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Duplicate email ID found for learner with email ID: "+learner.getEmail());
		}
	}

	/*
	 * ADD MULTIPLE LEARNERS
	 */
	public void addMultipleLearners(MultipartFile csvFilePath) throws IOException {
		LOGGER.info(logPrefix+"Adding multiple learners using file - "+csvFilePath);
	    XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);

	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        Learner learner = new Learner();
	        DataFormatter formatter = new DataFormatter();
	        XSSFRow row = worksheet.getRow(i);
	        learner.setName(row.getCell(0).getStringCellValue());
	        learner.setDepartment(row.getCell(1).getStringCellValue());
	        learner.setPhoneNumber(formatter.formatCellValue(row.getCell(2)));
	        learner.setEmail(row.getCell(3).getStringCellValue());
	        addLearner(learner);
		}
	}

	/*
	 * GENERATES EXCEL SHEET OF SAMPLE DATA OF LEARNER DETAILS
	 */
	public void generateExcel(String path) throws IOException {
		LOGGER.info(logPrefix+"Generating excel format for adding multiple learners");
		ExcelHelper helper = new ExcelHelper();

		List<ExcelFields> fields = new ArrayList<>();
		fields.add(new ExcelFields("Name", "Firstname Lastname", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("Department", "DepartmentName", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("Phone Number", "9876543210", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("Email", "something@email.com", XSSFCell.CELL_TYPE_STRING));

		helper.generateExcel(path, "learners.xlsx", "Sample Data", fields);
	}

	/*
	 * GET DETAILS OF ALL LEARNERS
	 */
	public List<Learner> getAllLearners(){
		LOGGER.info(logPrefix+"Returning list of all learners");
		return dao.findAll();
	}

	/*
	 * GET DETAILS OF LEARNER BY ID
	 */
	public ILearnerDAO getDao() {
		return dao;
	}

	public void setDao(ILearnerDAO dao) {
		this.dao = dao;
	}
	//Function which retrieves learner from the table using learnerId.

	public Learner getLearner(int id) {
		LOGGER.info(logPrefix+"Returning a learner with ID - "+id);
		System.out.println(dao.findById(id).get());
		return dao.findById(id).get();
	}

	//Function which updates credentials of a learner with given learnerId.
	public Learner updateLearnerPassword(int id, String password) {
		Learner l = dao.findById(id).get();
	    l.setPassword(password);
	    return dao.save(l);
	}
	
	public String checkPassword(int id) {
		Learner learner = dao.findById(id).get();
		return learner.getPassword();
	}
	
	public Learner updateLearner(int id, String email, String password) {
		LOGGER.info(logPrefix+"Updating credentials of a learner with ID - "+id+" to email - "+email+" and password - "+password);
	    Learner l = dao.findById(id).get();
	    l.setPassword(password);
	    return dao.save(l);
	}

	
	/*
	 * REMOVE LEARNER BY ID
	 */
	public void removeLearner(int id) {
		LOGGER.info(logPrefix+"Deleting a learner with ID - "+id);
		dao.deleteById(id);
	}

	//Function which removes a learner
	public void deleteLearner(int id) {
		LOGGER.info(logPrefix+"Deleting a learner with ID - "+id);
		dao.deleteById(id);
	}

	/*
	 * UPDATE LEARNER BY ID
	 */
	public void updateLearner(Learner learner) {
		LOGGER.info(logPrefix+"Updating a learner details to - "+learner);
		dao.updateEntry(learner.getDepartment(), learner.getPhoneNumber(), learner.getLearnerId());
	}

	/*
	 * GET MAX ID OF LEARNER TABLE
	 */
	public int getNextId() {
		LOGGER.info(logPrefix+"Getting next id of learner");
		Integer id = dao.getNextId();
		id = (id==null ? 10000 : id);
		return id;
	}


	public Learner login(String email, String password) {
		Learner learner = dao.findByEmailAndPassword(email, password);
		return learner;
	}


}
