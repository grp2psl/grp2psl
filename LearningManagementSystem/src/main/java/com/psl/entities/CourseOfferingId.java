package com.psl.entities;

import java.io.Serializable;

public class CourseOfferingId implements Serializable{
	
	private int tcid;
	private int learnerid;
	
	public CourseOfferingId() {
	}

	public CourseOfferingId(int tcid, int learnerid) {
		this.tcid = tcid;
		this.learnerid = learnerid;
	}
		
}
