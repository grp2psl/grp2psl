package com.psl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CourseAttended {
	private String name;
	
	private int courseid;
	
	private String coursename;
	
	@Id
	private int learnerid;

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

	public CourseAttended(String name, int courseid, String coursename, int learnerid) {
		
		this.name = name;
		this.courseid = courseid;
		this.coursename = coursename;
		this.learnerid = learnerid;
	}

	public CourseAttended() {
		
	}

	@Override
	public String toString() {
		return "CourseAttended [name=" + name + ", courseid=" + courseid + ", coursename=" + coursename + ", learnerid="
				+ learnerid + "]";
	}
	

}
