package com.psl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	@Id
	private int courseId;
	private String coursename;
	private String prerequisite;
	private String syllabus;
	private String duration;
	public int getCourseid() {
		return courseId;
	}
	public void setCourseid(int courseid) {
		this.courseId = courseid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
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
		this.coursename = coursename;
		this.prerequisite = prerequisite;
		this.syllabus = syllabus;
		this.duration = duration;
	}
	public Course() {
		
	}
	@Override
	public String toString() {
		return "Course [courseid=" + courseId + ", coursename=" + coursename + ", prerequisite=" + prerequisite
				+ ", syllabus=" + syllabus + ", duration=" + duration + "]";
	}
	
}
