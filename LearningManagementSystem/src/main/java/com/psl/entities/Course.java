/*
 * Entity declaration of Course
 */

// This shows that course is declared in com.psl.entities package
package com.psl.entities;

//Necessary imports for entity declaration
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * Course class stores course details in attribute
 * courseId is the primary key of course class
 * Other attributes are courseName, prerequisite, syllabus and duration.
 * Getter, setter and constructors both with and without parameters are declared.
 * toString method is overridden to display instance of the class appropriately. 
 */

//Annotation to recognize Course as an Entity
@Entity
//Name of the MySql table in which Course Entity records are stored
@Table(name = "course")
//Definition of Course Entity
public class Course {
	/*courseId attribute is the primary key. 
	 *stored in courseid column in the table.
	 *Compulsory for courseId to be non-empty.
	 *Minimun value is 1 because it can not be 0 or negative.  
	*/
	@NotNull(message = "courseId field can't be empty")
	@Id
	@Column(name="courseid")
	@Min(value = 1, message = "courseId field should be non zerp")
	private int courseId;
	
	/*
	 * courseName attribute stores name of course in a string format 
	 * with maximum limit on length of the string as 100.
	 */
	@Size(max = 100, message = "Length of courseName can't be more than 100 characheters!")
	@Column(name="coursename")
	private String courseName;
	
	/*
	 * prerequisite attribute stores prerquisites of the course in a string format 
	 * with maximum limit on length of the string as 300.
	 */
	@Size(max = 300, message = "Length of prerequisite can't be more than 300 characheters!")	
	private String prerequisite;
	
	/*
	 * syllabus attribute stores syllabus of the course in a string format 
	 * with maximum limit on length of the string as 300.
	 */
	@Size(max = 300, message = "Length of syllabus can't be more than 300 characheters!")		
	private String syllabus;
	
	/*
	 * duration stores the time required to complete the course in string format
	 */
	private String duration;
	
	
	/*
	 * Public getter and setter methods of all attributes
	 */
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public String getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Course(int courseid, String coursename, String prerequisite, String syllabus, String duration) {
		this.courseId = courseid;
		this.courseName = coursename;
		this.prerequisite = prerequisite;
		this.syllabus = syllabus;
		this.duration = duration;
	}
	public Course() {
		
	//Overrides toString function to change output of print function
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", prerequisite=" + prerequisite
				+ ", syllabus=" + syllabus + ", duration=" + duration + "]";
	}

	//Parameterized Constructor
	public Course(int courseId, String courseName, String prerequisite, String syllabus, String duration) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.prerequisite = prerequisite;
		this.syllabus = syllabus;
		this.duration = duration;
	}
	
	//Default Constructor
	public Course() {
	}
}
