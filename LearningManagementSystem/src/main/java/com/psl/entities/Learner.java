package com.psl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Learner extends User {
	@Id
	@Column(name="learnerid")
	private int learnerId;
	private String name;
	@Column(name="phonenumber")
	private String phoneNumber;
	private String department;
	private String email;
	private String password;
	
	public int getLearnerid() {
		return learnerId;
	}
	public void setLearnerid(int learnerid) {
		this.learnerId = learnerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phoneNumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phoneNumber = phonenumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Learner(int learnerid, String name, String phonenumber, String department, String email, String password) {
		this.learnerId = learnerid;
		this.name = name;
		this.phoneNumber = phonenumber;
		this.department = department;
		this.email = email;
		this.password = password;
	}
	public Learner() {
	}	
}
