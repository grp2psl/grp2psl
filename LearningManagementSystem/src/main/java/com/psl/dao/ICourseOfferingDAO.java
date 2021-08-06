/*
 * Data Access Object Interface of Course Offering Entity
 * Present in com.psl.dao package
 */
package com.psl.dao;

//Necessary imports for DAO declaration
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.psl.entities.CourseOffering;
import com.psl.entities.CourseOfferingId;

/*
 * Course Offering DAO interface extends CRUD (Create, Read ,Update, Delete) Repository
 * CrudRepository has two parameters CourseOffering and CourseOfferingId
 * CourseOffering is Entity Class on which CRUD operations are to be performed
 * CourseOfferingId is primary key Class of CourseOffering Class
 */
public interface ICourseOfferingDAO extends CrudRepository<CourseOffering, CourseOfferingId>{
	
	//This function finds unique Course Offering and Learner Mapping using bpth tcId and learnerId
	CourseOffering findByTcIdAndLearnerId(int tcId, int learnerId);
	
	//This function finds all Course Offerings in which given learner is enrolled by learnerId.
	List<CourseOffering> findByLearnerId(int learnerId);
	
	@Query("select avg(ratings) from CourseOffering where tcid = :tcid")
	float getFeedbackResults(@Param("tcid") int tcid);
	
	@Query("select ratings from CourseOffering where tcid = :tcid")
	List<Float> findAllCoursesTaughtRatings(@Param("tcid") int tcid);
	
	@Query("SELECT feedback from CourseOffering where tcid = :tcid")
	List<String> findCommentsForACourse(@Param("tcid") int tcid);
}
