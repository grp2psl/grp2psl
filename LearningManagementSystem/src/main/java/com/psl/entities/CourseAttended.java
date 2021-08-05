package com.psl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CourseAttended {
	private String name;
	
	@Id
	private int courseid;
	
	private String coursename;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCourseid() {
		return courseid;
	}

	@Override
	public String toString() {
		return "CourseAttended [name=" + name + ", courseid=" + courseid + ", coursename=" + coursename + "]";
	}

	public CourseAttended() {
		super();
	}

	public CourseAttended(String name, int courseid, String coursename) {
		super();
		this.name = name;
		this.courseid = courseid;
		this.coursename = coursename;
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
}
