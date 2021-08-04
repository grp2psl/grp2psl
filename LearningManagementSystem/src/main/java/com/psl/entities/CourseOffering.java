package com.psl.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courseoffering")
public class CourseOffering {
	@Id
	@Column(name="courseofferingid")
	private int courseOfferingId;
	private String feedback;
	private int ratings;
	@Column(name="startdate")
	private Date startDate;
	@Column(name="enddate")
	private Date endDate;
	@Column(name="learnerid")
	private int learnerId;
	@Column(name="tcid")
	private int tcId;
	private String status;
	private double percentage;
	public String getFeedback() {
		return feedback;
	}
	public int getCourseofferingid() {
		return courseOfferingId;
	}
	public void setCourseofferingid(int courseofferingid) {
		this.courseOfferingId = courseofferingid;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public Date getStartdate() {
		return startDate;
	}
	public void setStartdate(Date startdate) {
		this.startDate = startdate;
	}
	public Date getEnddate() {
		return endDate;
	}
	public void setEnddate(Date enddate) {
		this.endDate = enddate;
	}
	public int getLearnerid() {
		return learnerId;
	}
	public void setLearnerid(int learnerid) {
		this.learnerId = learnerid;
	}
	public int getTcid() {
		return tcId;
	}
	public void setTcid(int tcid) {
		this.tcId = tcid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public CourseOffering(int courseofferingid, String feedback, int ratings, Date startdate, Date enddate,
			int learnerid, int tcid, String status, double percentage) {
		this.courseOfferingId = courseofferingid;
		this.feedback = feedback;
		this.ratings = ratings;
		this.startDate = startdate;
		this.endDate = enddate;
		this.learnerId = learnerid;
		this.tcId = tcid;
		this.status = status;
		this.percentage = percentage;
	}
	public CourseOffering() {
	}
	@Override
	public String toString() {
		return "Courseoffering [courseofferingid=" + courseOfferingId + ", feedback=" + feedback + ", ratings="
				+ ratings + ", startdate=" + startDate + ", enddate=" + endDate + ", learnerid=" + learnerId + ", tcid="
				+ tcId + ", status=" + status + ", percentage=" + percentage + "]";
	}	
}
