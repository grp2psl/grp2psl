package com.psl.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Courseoffering {
	@Id
	private int courseofferingid;
	private String feedback;
	private int ratings;
	private Date startdate;
	private Date enddate;
	private int learnerid;
	private int tcid;
	private String status;
	private int percentage;
	public String getFeedback() {
		return feedback;
	}
	public int getCourseofferingid() {
		return courseofferingid;
	}
	public void setCourseofferingid(int courseofferingid) {
		this.courseofferingid = courseofferingid;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public int getLearnerid() {
		return learnerid;
	}
	public void setLearnerid(int learnerid) {
		this.learnerid = learnerid;
	}
	public int getTcid() {
		return tcid;
	}
	public void setTcid(int tcid) {
		this.tcid = tcid;
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
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Courseoffering(int courseofferingid, String feedback, int ratings, Date startdate, Date enddate,
			int learnerid, int tcid, String status, int percentage) {
		this.courseofferingid = courseofferingid;
		this.feedback = feedback;
		this.ratings = ratings;
		this.startdate = startdate;
		this.enddate = enddate;
		this.learnerid = learnerid;
		this.tcid = tcid;
		this.status = status;
		this.percentage = percentage;
	}
	public Courseoffering() {
	}
	@Override
	public String toString() {
		return "Courseoffering [courseofferingid=" + courseofferingid + ", feedback=" + feedback + ", ratings="
				+ ratings + ", startdate=" + startdate + ", enddate=" + enddate + ", learnerid=" + learnerid + ", tcid="
				+ tcid + ", status=" + status + ", percentage=" + percentage + "]";
	}	
}
