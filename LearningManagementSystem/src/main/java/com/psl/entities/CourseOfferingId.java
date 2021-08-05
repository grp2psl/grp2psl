package com.psl.entities;

import java.io.Serializable;

public class CourseOfferingId implements Serializable{
	
	private int tcId;
	
	private int learnerId;
	
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
	
	public CourseOfferingId() {
	}

	public CourseOfferingId(int tcId, int learnerId) {
		this.tcId = tcId;
		this.learnerId = learnerId;
	}
		
}
