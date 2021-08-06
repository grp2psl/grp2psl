/*
 * Entity declaration of Course Offering Id
 */

// This shows that courseOfferingId is declared in com.psl.entities package
package com.psl.entities;

//Necessary imports for entity declaration
import java.io.Serializable;

/*
 * Sole purpose of given class is to store composite key of 
 * the CourseOffering class.
 */

//Definition of CourseOfferingId which implements Serializable interface
public class CourseOfferingId implements Serializable{
	
	//Part of Composite Primary key, the teacher course mapping key
	private int tcId;

	//Part of Composite Primary key, the learner primary key
	private int learnerId;
	
	//Public Getter and setter methods for the class
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

	//Default Constructor of the Class
	public CourseOfferingId() {
	}

	//Parameterized Constructor of the Class
	public CourseOfferingId(int tcId, int learnerId) {
		this.tcId = tcId;
		this.learnerId = learnerId;
	}
		
}
