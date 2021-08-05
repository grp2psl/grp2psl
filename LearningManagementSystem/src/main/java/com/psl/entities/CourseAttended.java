package com.psl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CourseAttended {
	private String name;
	
	private int courseid;
	
	private String coursename;
	
	@Id
	private int learnerid;


	private int percentage;
	
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public int getLearnerid() {
		return learnerid;
	}

	public void setLearnerid(int learnerid) {
		this.learnerid = learnerid;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CourseAttended(String name, int courseid, String coursename, int learnerid, int percentage, String status) {
		
		this.name = name;
		this.courseid = courseid;
		this.coursename = coursename;
		this.learnerid = learnerid;
		this.percentage = percentage;
		this.status = status;
	}

	public CourseAttended() {
		
	}

	@Override
	public String toString() {
		return "CourseAttended [name=" + name + ", courseid=" + courseid + ", coursename=" + coursename + ", learnerid="
				+ learnerid + ", percentage=" + percentage + ", status=" + status + "]";
	}

	
	
	

}
