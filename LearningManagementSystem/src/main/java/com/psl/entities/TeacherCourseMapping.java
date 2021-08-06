package com.psl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teachercoursemapping")
public class TeacherCourseMapping {
	@Column(name = "trainerid")
	private int trainerId;
	@Column(name = "courseid")
	private int courseId;
	
	@Id
	@Column(name = "tcid")
	private int tcId;
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
	@Override
	public String toString() {
		return "TeacherCourseMapping [trainerId=" + trainerId + ", courseId=" + courseId + ", tcId=" + tcId + "]";
	}
	public TeacherCourseMapping(int trainerId, int courseId, int tcId) {
		super();
		this.trainerId = trainerId;
		this.courseId = courseId;
		this.tcId = tcId;
	}
	public TeacherCourseMapping() {
		super();
		// TODO Auto-generated constructor stub
	}
}
