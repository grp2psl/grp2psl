package com.psl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ScoreStatus {
	@Column(name="name")
	private String name;
	
	@Id
	@Column(name="coursename")
	private String coursename;
	
	@Column(name="percentage")
	private int percentage;
	
	@Column(name="status")
	private String status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
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
	public ScoreStatus(String name, String coursename, int percentage, String status) {
		
		this.name = name;
		this.coursename = coursename;
		this.percentage = percentage;
		this.status = status;
	}
	public ScoreStatus() {
		
	}
	@Override
	public String toString() {
		return "ScoreStatus [name=" + name + ", coursename=" + coursename + ", percentage=" + percentage + ", status="
				+ status + "]";
	}
	
}
