package com.psl.entities;

import java.util.Date;

public class CourseOffering {
	private String Feedback;
	private int ratings;
	private Date startDate;
	private Date endDate;
	private int learnerId;
	private int tcId;
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
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getLearnerId() {
		return learnerId;
	}
	public void setLearnerId(int learnerId) {
		this.learnerId = learnerId;
	}
	public int getTcId() {
		return tcId;
	}
	public void setTcId(int tcId) {
		this.tcId = tcId;
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
		this.startDate = startDate;
		this.endDate = endDate;
		this.learnerId = learnerId;
		this.tcId = tcId;
		this.status = status;
		this.percentage = percentage;
	}
	public CourseOffering() {

	}
	@Override
	public String toString() {
		return "CourseOffering [Feedback=" + Feedback + ", ratings=" + ratings + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", learnerId=" + learnerId + ", tcId=" + tcId + ", status=" + status
				+ ", percentage=" + percentage + "]";
	}
	
}
