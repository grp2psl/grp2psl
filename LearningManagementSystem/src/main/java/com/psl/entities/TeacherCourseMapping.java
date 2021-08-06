/*
 * Entity declaration of TeacherCourseMapping
 */
// This shows that TeacherCourseMapping is declared in com.psl.entities package
package com.psl.entities;

//Necessary imports for entity declaration
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/*
 * TeacherCourseMapping class stores details of trainer and cpurse Mapping
 */
@Entity
//TeacherCourseMapping entity stored in table teachercoursemapping
@Table(name = "teachercoursemapping")
public class TeacherCourseMapping {
	/*
	 * stores trainer id, in the sql table it is stored in trainerid column
	 * Foreign key to the trainer table
	 */
	@Column(name = "trainerid")
	private int trainerId;
	
	/*
	 * stores course id, in the sql table it is stored in courseid column
	 * Foreign key to the course table
	 */
	@Column(name = "courseid")
	private int courseId;
	
	/*
	 * Primary key of the table hence can't be null. 
	 */
	@Id
	@Column(name = "tcid")
	@NotNull(message = "tcId field can't be empty")
	private int tcId;
	
	//Public getter and setter methods
	public int getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getTcId() {
		return tcId;
	}
	public void setTcId(int tcId) {
		this.tcId = tcId;
	}
	
	//Overrides toString function to print object in this format
	@Override
	public String toString() {
		return "TeacherCourseMapping [trainerId=" + trainerId + ", courseId=" + courseId + ", tcId=" + tcId + "]";
	}
	
	//Parameterized Constructor
	public TeacherCourseMapping(int trainerId, int courseId, int tcId) {
		super();
		this.trainerId = trainerId;
		this.courseId = courseId;
		this.tcId = tcId;
	}
	
	//Default Constructor
	public TeacherCourseMapping() {
		super();
	}
}
