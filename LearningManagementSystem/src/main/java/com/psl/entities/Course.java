package com.psl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@Column(name="courseid")
	private int courseId;
	
	@Column(name="coursename")
	private String courseName;
	
	private String prerequisite;
	private String syllabus;
	private String duration;
	
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
	
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", prerequisite=" + prerequisite
				+ ", syllabus=" + syllabus + ", duration=" + duration + "]";
	}

	public Course(int courseId, String courseName, String prerequisite, String syllabus, String duration) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.prerequisite = prerequisite;
		this.syllabus = syllabus;
		this.duration = duration;
	}
	public Course() {
	}
}
