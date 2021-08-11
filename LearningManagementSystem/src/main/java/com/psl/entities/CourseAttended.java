package com.psl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CourseAttended {
	@Id
	private int learnerid;
	private String name;
	private int courseid;
	private String coursename;
	private String trainername;
	private int percentage;
	private String status;
	public int getLearnerid() {
		return learnerid;
	}
	public void setLearnerid(int learnerid) {
		this.learnerid = learnerid;
	}
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
	public String getTrainername() {
		return trainername;
	}
	public void setTrainername(String trainername) {
		this.trainername = trainername;
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
	public CourseAttended(int learnerid, String name, int courseid, String coursename, String trainername,
			int percentage, String status) {
		this.learnerid = learnerid;
		this.name = name;
		this.courseid = courseid;
		this.coursename = coursename;
		this.trainername = trainername;
		this.percentage = percentage;
		this.status = status;
	}
	public CourseAttended() {
		
	}
	@Override
	public String toString() {
		return "CourseAttended [learnerid=" + learnerid + ", name=" + name + ", courseid=" + courseid + ", coursename="
				+ coursename + ", trainername=" + trainername + ", percentage=" + percentage + ", status=" + status
				+ "]";
	}

	
	

}
