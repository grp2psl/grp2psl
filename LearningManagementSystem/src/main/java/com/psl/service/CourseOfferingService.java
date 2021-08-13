package com.psl.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.psl.dao.ICourseOfferingDAO;
import com.psl.entities.Course;
import com.psl.entities.CourseOffering;
import com.psl.entities.CourseOfferingStatus;
import com.psl.entities.Learner;
import com.psl.entities.TeacherCourseMapping;
import com.psl.entities.Trainer;
import com.psl.utils.ExcelFields;
import com.psl.utils.ExcelHelper;
/*
 * Declaration of Course Offering Service which completes various requests on CourseOffering class.
 */
//Required imports for the service declaration.



//This annotation enables use of this class as a service named courseOfferingService.


@Service("courseOfferingService")
public class CourseOfferingService {

	@Autowired
	private ICourseOfferingDAO dao;
	
	@Autowired
	private LearnerService learnerService;
	
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private EmailSenderService emailService;
	
	@Autowired
	private TeacherCourseMappingService tcService;

	public static final Logger LOGGER = LoggerFactory.getLogger(CourseOfferingService.class);
	private final String logPrefix = "Course Offering Service - ";
	
	/*
	 * ENROLL LEARNER
	 */
	public ICourseOfferingDAO getDao() {
		return dao;
	}

	public void setDao(ICourseOfferingDAO dao) {
		this.dao = dao;
	}

	//Function to allow Learner to given feedback on given course Offering
	public CourseOffering AddFeedback(int learnerId, int tcId, String feedback) {
		LOGGER.info(logPrefix+"Adding feedback - "+feedback+" for Trainer-course mapping with ID - "+tcId+" by learner with ID - "+learnerId);
		CourseOffering co = dao.findByTcIdAndLearnerId(tcId, learnerId);
		co.setFeedback(feedback);
		return dao.save(co);
	}
	
	//Lists all Course Offerings of given learner which is identified by learnerId 
	public List<CourseOffering> getCourseOfferings(int learnerId) {
		LOGGER.info(logPrefix+"Returning list of all course offerings of the learner with ID - "+learnerId);
		return dao.findByLearnerId(learnerId);
	}
	public void enrollLearner(CourseOffering offering) throws ParseException {
		LOGGER.info(logPrefix+"Enrolling a learner with ID - "+offering.getLearnerId()+" to Trainer-Course mapping with ID - "+offering.getTcId());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Integer maxId = dao.getMaxId();
		maxId = maxId==null ? 0 : maxId;
		offering.setCourseOfferingId(++maxId);
		System.out.println(offering.getStatus());
        dao.save(updateCourseOfferingStatus(offering));
        Learner learner = learnerService.getLearner(offering.getLearnerId());
        Course course = tcService.getCourse(offering.getTcId());
        emailService.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + learner.getName() +", \nYou have been enrolled to the Course - "+course.getCourseName()+".\nDuration of the course is - "+course.getDuration()+" hours, starting from "+formatter.format(offering.getStartDate()), "Learner enrolled to "+course.getCourseName()+" successfully - learning management portal");		
	}
	
	/*
	 * ENROLL MULTIPLE LEARNERS
	 */
	public void enrollMultipleLearners(MultipartFile csvFilePath) throws IOException, ParseException {
		LOGGER.info(logPrefix+"Enrolling multiple learners using file - "+csvFilePath);
		Integer maxId = dao.getMaxId();
		maxId = maxId==null ? 0 : maxId;
		XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        CourseOffering offering = new CourseOffering();
	        XSSFRow row = worksheet.getRow(i);
	        offering.setCourseOfferingId(++maxId);
	        offering.setStartDate(formatter.parse(row.getCell(2).getStringCellValue()));
	        offering.setEndDate(formatter.parse(row.getCell(3).getStringCellValue()));
	        offering.setLearnerId((int)row.getCell(0).getNumericCellValue());
	        offering.setTcId((int)row.getCell(1).getNumericCellValue());
	        Learner learner = learnerService.getLearner(offering.getLearnerId());
	        Course course = tcService.getCourse(offering.getTcId());
	        dao.save(updateCourseOfferingStatus(offering));
	        emailService.sendEmail("group2.learning.management.system@gmail.com", learner.getEmail(), "Hi " + learner.getName() +", \nYou have been enrolled to the Course - "+course.getCourseName()+".\nDuration of the course is - "+course.getDuration()+" hours, starting from "+formatter.format(offering.getStartDate()), "Learner enrolled to "+course.getCourseName()+" successfully - learning management portal");
		}
				
	}
	
	/*
	 * UPDATE STATUS OF COURSE OFFERING
	 * STATUS CAN BE - IN_PROGRESS, FAILFEEDBACK_PENDING, PASSFEEDBACK_PENDING, FAILFEEDBACK_GIVEN, COMPLETED
	 */
	public CourseOffering updateCourseOfferingStatus(CourseOffering offering) {
		LOGGER.info(logPrefix+"Updating Course Offering Status for ID - "+offering.getCourseOfferingId());
		double percentage = offering.getPercentage();
		String feedback = offering.getFeedback();
		String status = offering.getStatus();
		if(percentage == 0.0 && status == null) {
			offering.setStatus(CourseOfferingStatus.IN_PROGRESS.name());
		} else if((percentage < 70.0) && feedback == null) {
			offering.setStatus(CourseOfferingStatus.FAIL.name()+","+CourseOfferingStatus.FEEDBACK_PENDING.name());
		} else if((percentage >= 70.0) && feedback == null) {
			offering.setStatus(CourseOfferingStatus.PASS.name()+","+CourseOfferingStatus.FEEDBACK_PENDING.name());
		} else if((percentage < 70.0) && feedback != null) {
			offering.setStatus(CourseOfferingStatus.FAIL.name()+","+CourseOfferingStatus.FEEDBACK_GIVEN.name());
		} else {
			offering.setStatus(CourseOfferingStatus.COMPLETED.name());
		}
		return offering;
	}
	
	/*
	 * UPDATE TEST SCORE OF AN INDIVIDUAL
	 */
	public void updateTestScore(int id, double percentage) {
		LOGGER.info(logPrefix+"Updating Test Score Percentage - "+percentage+" for offering with ID - "+id);
		CourseOffering offering = dao.findById(id).get();
		offering.setPercentage(percentage);
		dao.save(updateCourseOfferingStatus(offering));
	}
	
	/*
	 * UPDATE TEST SCORE OF MULTIPLE LEARNERS
	 */
	public void updateMultipleTestScores(MultipartFile csvFilePath) throws IOException, ParseException {
		LOGGER.info(logPrefix+"Updating multiple test scores using file - "+csvFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(csvFilePath.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        XSSFRow row = worksheet.getRow(i);
			CourseOffering offering = dao.findById((int)row.getCell(0).getNumericCellValue()).get();
			offering.setPercentage((double)row.getCell(1).getNumericCellValue());
			dao.save(updateCourseOfferingStatus(offering));
		}		
		
	}
	
	/*
	 * GENERATES EXCEL SHEET OF SAMPLE DATA FOR ENROLMENT
	 */
	public void generateExcelForEnrolment(String path) throws IOException {
		LOGGER.info(logPrefix+"Generating excel format for multiple learner enrolment");
		ExcelHelper helper = new ExcelHelper();
		
		List<ExcelFields> fields = new ArrayList<>();
		fields.add(new ExcelFields("Learner ID", "1", XSSFCell.CELL_TYPE_NUMERIC));
		fields.add(new ExcelFields("TCID", "2", XSSFCell.CELL_TYPE_NUMERIC));
		fields.add(new ExcelFields("Start Date", "YYYY-MM-DD", XSSFCell.CELL_TYPE_STRING));
		fields.add(new ExcelFields("End Date", "YYYY-MM-DD", XSSFCell.CELL_TYPE_STRING));
		
		helper.generateExcel(path, "enrollLearners.xlsx", "Sample Data", fields);
		
	}

	/*
	 * GENERATES EXCEL SHEET OF SAMPLE DATA FOR TEST SCORE UPDATE 
	 */
	public void generateExcelForScoreUpdate(String path) throws IOException {
		LOGGER.info(logPrefix+"Generating excel format for updating multiple test scores");
		ExcelHelper helper = new ExcelHelper();
		
		List<ExcelFields> fields = new ArrayList<>();
		fields.add(new ExcelFields("Course Offering ID", "1", XSSFCell.CELL_TYPE_NUMERIC));
		fields.add(new ExcelFields("Percentage", "70", XSSFCell.CELL_TYPE_NUMERIC));
		
		helper.generateExcel(path, "updateScores.xlsx", "Sample Data", fields);
	}

	/*
	 * VIEW COURSE OFFERING
	 */
	public List<CourseOffering> viewCourseOfferings(){
		LOGGER.info(logPrefix+"Returning list of all course offerings");
		return (List<CourseOffering>) dao.findAll();
	}
	
	/*
	 * GET MAX ID OF COURSE OFFERING TABLE
	 */
	public int getMaxId() {
		LOGGER.info(logPrefix+"Getting max(id) of course offering");
		return dao.getMaxId();
	}
	
	/*
	 * GET COURSE OFFERING BY ID
	 */
	public CourseOffering getCourseOffering(int id) {
		LOGGER.info(logPrefix+"Returning course offering with ID - "+id);
		return dao.findById(id).get();
	}
	
	/*
	 * REMOVE COURSE OFFERING
	 */
	public void removeCourseOffering(int id) {
		LOGGER.info(logPrefix+"Deleting course offering with ID - "+id);
		dao.deleteById(id);
	}
	
	/*
	 * VIEW DETAILS OF TRAINER BY ID
	 */
	public Map<String, Object> viewTrainerDetails(int id) {
		LOGGER.info(logPrefix+"Returning details of a trainer with ID - "+id+" with courses offered");
		Map<String, Object> response = new HashMap<>();
		Map<Integer, List<CourseOffering>> offerings = new HashMap<>();
		Trainer trainer = trainerService.getTrainer(id);
		List<Course> courses = tcService.getCoursesByTrainerId(id);
		List<TeacherCourseMapping> tcMappings = tcService.getByTrainerId(id); 
		for(TeacherCourseMapping tc: tcMappings) {
			List<CourseOffering> co = dao.findByTcId(tc.getTcId());
			Course course = tcService.getCourse(tc.getTcId());
			offerings.put(course.getCourseId(), co);
		}
		response.put("trainerDetails", trainer);
		response.put("courses", courses);
		response.put("offerings", offerings);
		return response;
	}
	
	/*
	 * VIEW DETAILS OF COURSE BY TRAINER ID
	 */
	public Map<String, Object> viewCourseDetailsByTrainerId(int id, int course_id) {
		LOGGER.info(logPrefix+"Returning details of a course with ID - "+course_id+" with feedback and ratings for trainer with ID - "+id);
		Map<String, Object> response = new HashMap<>();
		double avgRating;
		Course course = courseService.getCourse(course_id);
		TeacherCourseMapping tc = tcService.getByTrainerIdAndCourseId(id, course_id);
		int sum = 0;
		List<CourseOffering> offerings = dao.findByTcId(tc.getTcId());
		for(CourseOffering c : offerings) {
			sum = sum + c.getRatings();
		}
		avgRating = (double)sum/offerings.size();
		response.put("courseDetails", course);
		response.put("offerings", offerings);
		response.put("avgRating", avgRating);
		return response;
	}
	
	public int findCourseOfferingIdByTcIdAndLearnerId(int tcId, int learnerId) {
		LOGGER.info(logPrefix+"Returning course offering id for Trainer-Course mapping with ID - "+tcId+" and learner with ID - "+learnerId);
		return dao.findByTcIdAndLearnerId(tcId, learnerId).getCourseOfferingId();
	}

	/*
	 * VIEW DETAILS OF COURSE OFFERINGS
	 */	
	public List<Map<String, Object>> viewCourseOfferingsDetails() throws ParseException {
		LOGGER.info(logPrefix+"Returning details of all Course Offerings");
		List<Map<String, Object>> response = new ArrayList<Map<String,Object>>();
		Map<String, Object> element;
		List<CourseOffering> courseOfferingList = viewCourseOfferings();
		for(CourseOffering co : courseOfferingList) {
			element = new HashMap<String, Object>();
			element.put("offering", co);
			element.put("learner", learnerService.getLearner(co.getLearnerId()));
			element.put("trainer", trainerService.getTrainer(tcService.getById(co.getTcId()).getTrainerId()));
			element.put("course", courseService.getCourse(tcService.getById(co.getTcId()).getCourseId()));
			response.add(element);
		}
		return response;
	}

	/*
	 * VIEW DETAILS OF COURSE OFFERINGS BY LEARNER ID
	 */	
	public List<Map<String, Object>> viewCourseOfferingsDetailsByLearnerId(int learnerid) throws ParseException {
		LOGGER.info(logPrefix+"Returning details of Course Offerings for learner with ID - "+learnerid);
		List<Map<String, Object>> response = new ArrayList<Map<String,Object>>();
		Map<String, Object> element;
		List<CourseOffering> courseOfferingList = viewCourseOfferings();
		for(CourseOffering co : courseOfferingList) {
			if(co.getLearnerId() == learnerid) {
				element = new HashMap<String, Object>();
				element.put("offerings", co);
				element.put("learners", learnerService.getLearner(co.getLearnerId()));
				element.put("trainers", trainerService.getTrainer(tcService.getById(co.getTcId()).getTrainerId()));
				element.put("courses", courseService.getCourse(tcService.getById(co.getTcId()).getCourseId()));
				response.add(element);
			}
		}
		return response;
	}

	/*
	 * FIND TEACHER-COURSE MAPPINGS BY LEARNER ID
	 */	
	public List<TeacherCourseMapping> findTeacherCourseMappingsByLearnerId(int learnerId) {
		LOGGER.info(logPrefix+"Returning trainer-course mappings for learner with ID - "+learnerId);
		List<CourseOffering> courseOfferingList = dao.findByLearnerId(learnerId);
		List<TeacherCourseMapping> list = new ArrayList<>();
		for(CourseOffering co : courseOfferingList) {
			list.add(tcService.getById(co.getTcId()));
		}
		return list;
	}

	/*
	 * FIND LEARNERS BY TCID
	 */	
	public List<Learner> findLearnersByTcId(int tcId) {
		LOGGER.info(logPrefix+"Returning Learners for Trainer-Course mapping with TcId - "+tcId);
		List<CourseOffering> courseOfferingList = dao.findByTcId(tcId);
		List<Learner> learners = new ArrayList<>();
		for(CourseOffering co : courseOfferingList) {
			learners.add(learnerService.getLearner(co.getLearnerId()));
		}
		return learners;
	}
}
