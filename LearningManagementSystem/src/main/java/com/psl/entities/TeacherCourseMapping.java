package com.psl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeacherCourseMapping {
	@Id
	private int trainerId;
	private int courseId;
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
	public TeacherCourseMapping(int trainerId, int courseId, int tcId) {
		this.trainerId = trainerId;
		this.courseId = courseId;
		this.tcId = tcId;
	}
	public TeacherCourseMapping() {
		
	}
	@Override
	public String toString() {
		return "TeacherCourseMapping [trainerId=" + trainerId + ", courseId=" + courseId + ", tcId=" + tcId + "]";
	}
	
}
