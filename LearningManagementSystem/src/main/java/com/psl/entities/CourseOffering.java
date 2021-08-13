/*
 * Entity declaration of Course Offering
 */

// This shows that course is declared in com.psl.entities package
package com.psl.entities;

//Necessary imports for entity declaration
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * CourseOffering class stores mapping of learners with course offerings.
 * learnerId and tcId is the composite primary key of course class
 * Other attributes are feedback, ratings, startDate, endDate , status and percentage.
 * Getter, setter and constructors both with and without parameters are declared.
 * toString method is overridden to display instance of the class appropriately. 
 */

//Annotation to recognize CourseOffering as an Entity
@Entity
//Annotation to map IdClass of CourseOffering which is stored as a separate class because primary key is composite
//@IdClass(CourseOfferingId.class)
//Name of the MySql table in which CourseOffering Entity records are stored
@Table(name = "courseoffering")
//Definition of CourseOffering Entity
public class CourseOffering {
	
	@Id
	@NotNull(message = "Course Offering id field can't be null")
	@Column(name = "courseofferingid")
	private int CourseOfferingId;
	
	public int getCourseOfferingId() {
		return CourseOfferingId;
	}
	public void setCourseOfferingId(int courseOfferingId) {
		CourseOfferingId = courseOfferingId;
	}

	/*
	 * feedback attribute stores feedback of course offering in a string format 
	 * with maximum limit on length of the string as 255.
	 */
	@Size(max = 255, message = "Length of feedback can't be more than 255 characheters!")
	private String feedback;
	
	/*
	 * ratings can't be null and are stored in integer format
	 */
	@NotNull(message = "ratings field can't be null")
	private int ratings;
	
	/*
	 * startDate stores the starting date of courseOffering in date format.
	 * It is stored in startDate column. 
	 */
	@Column(name = "startdate")
	private Date startDate;
	
	/*
	 * endDate stores the ending date of courseOffering in date format.
	 * It is stored in endDate column. 
	 */
	@Column(name = "enddate")
	private Date endDate;
	
	/*
	 * It is part of the composite key. 
	 * Maps enrolled learners in the given courseOffering with it.
	 * Stored in learnerid column
	 * It can't be null
	 */
//	@Id
	@Column(name = "learnerid")
	@NotNull(message = "learnerId field can't be null")
	private int learnerId;
	
	/*
	 * It is part of the composite key. 
	 * Maps given courseOffering with learners.
	 * Stored in tcid column
	 * It can't be null
	 */
//	@Id
	@Column(name = "tcid")
	@NotNull(message = "tcId field can't be null")
	private int tcId;
	
	/*
	 * Status stores current status of learner with the given course offering
	 * It can include strings like enrolled, approved, completed, pass , etc. 
	 * Maximum length of the status string is 255 characters
	 */
	@Size(max = 255, message = "Length of status can't be more than 255 characheters!")
	private String status;
	
	/*
	 * Percentage field stores percent score of learner on given course offering.
	 * It can't be null.
	 * By default it can be initialized with some negative value.
	 */
	@NotNull(message = "percentage field can't be null")	
	private int percentage;
		
	/*
	 * Public getter and setter methods of all attributes
	 */
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getLearnerId() {
		return learnerId;
	}
	public void setLearnerId(int learnerId) {
		this.learnerId = learnerId;
	}
	public int getTcId() {
		return tcId;
	}
	public void setTcId(int tcId) {
		this.tcId = tcId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	//Parameterized Constructor
	public CourseOffering(@NotNull(message = "Course Offering id field can't be null") int courseOfferingId,
			@Size(max = 255, message = "Length of feedback can't be more than 255 characheters!") String feedback,
			@NotNull(message = "ratings field can't be null") int ratings, Date startDate, Date endDate,
			@NotNull(message = "learnerId field can't be null") int learnerId,
			@NotNull(message = "tcId field can't be null") int tcId,
			@Size(max = 255, message = "Length of status can't be more than 255 characheters!") String status,
			@NotNull(message = "percentage field can't be null") int percentage) {
		super();
		this.CourseOfferingId = courseOfferingId;
		this.feedback = feedback;
		this.ratings = ratings;
		this.startDate = startDate;
		this.endDate = endDate;
		this.learnerId = learnerId;
		this.tcId = tcId;
		this.status = status;
		this.percentage = percentage;
	}
	//Default Constructor
	public CourseOffering() {
	}
	
	//Overrides equal function which compares equality of two course offering objects.
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
	    }
		
		if (!(o instanceof CourseOffering)) {
            return false;
        }
		
		CourseOffering Co = (CourseOffering)o;
		
		return ((feedback == null && Co.getFeedback() == null)||feedback.equals(Co.getFeedback())) &&
				ratings == Co.getRatings()&&
				(( startDate == null && Co.getStartDate() == null ) ||startDate.equals(Co.getStartDate()))&&
				(( endDate == null && Co.getEndDate() == null) || endDate.equals(Co.getEndDate())) &&
				learnerId == Co.getLearnerId() &&
				tcId == Co.getTcId() &&
				(( status == null && Co.getStatus() == null ) ||status.equals(Co.getStatus()))&&
				percentage == Co.getPercentage() &&
				CourseOfferingId == Co.getCourseOfferingId();
	}
	
	//Overrides toString function to change output of print function
	@Override
	public String toString() {
		return "CourseOffering [feedback=" + feedback + ", ratings=" + ratings + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", learnerId=" + learnerId + ", tcId=" + tcId + ", status=" + status
				+ ", percentage=" + percentage + "]";
	}
}
