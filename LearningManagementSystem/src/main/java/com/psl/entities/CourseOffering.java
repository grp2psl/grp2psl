package com.psl.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(CourseOfferingId.class)
@Table(name = "courseoffering")
public class CourseOffering {
	private String Feedback;
	private int ratings;
	private Date startdate;
	private Date enddate;

	@Id
	private int learnerid;
	
	@Id
	private int tcid;
	private String status;
	private int percentage;
	public String getFeedback() {
		return Feedback;
	}
	public void setFeedback(String feedback) {
		Feedback = feedback;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public Date getStartDate() {
		return startdate;
	}
	public void setStartDate(Date startDate) {
		this.startdate = startDate;
	}
	public Date getEndDate() {
		return enddate;
	}
	public void setEndDate(Date endDate) {
		this.enddate = endDate;
	}
	public int getLearnerId() {
		return learnerid;
	}
	public void setLearnerId(int learnerId) {
		this.learnerid = learnerId;
	}
	public int getTcId() {
		return tcid;
	}
	public void setTcId(int tcId) {
		this.tcid = tcId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public CourseOffering(String feedback, int ratings, Date startDate, Date endDate, int learnerId, int tcId,
			String status, int percentage) {
	
		Feedback = feedback;
		this.ratings = ratings;
		this.startdate = startDate;
		this.enddate = endDate;
		this.learnerid = learnerId;
		this.tcid = tcId;
		this.status = status;
		this.percentage = percentage;
	}
	public CourseOffering() {

	}
	@Override
	public String toString() {
		return "CourseOffering [Feedback=" + Feedback + ", ratings=" + ratings + ", startDate=" + startdate
				+ ", endDate=" + enddate + ", learnerId=" + learnerid + ", tcId=" + tcid + ", status=" + status
				+ ", percentage=" + percentage + "]";
	}
	
}
