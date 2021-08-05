package com.psl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "learner")
public class Learner extends User {
	@Id
	@Column(name = "learnerid")
	private int learnerId;
	private String name;
	
	@Column(name = "phonenumber")
	private String phoneNumber;
	private String department;
	private String email;
	private String password;
	public int getLearnerId() {
		return learnerId;
	}
	public void setLearnerId(int learnerId) {
		this.learnerId = learnerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	@Override
	public String toString() {
		return "Learner [learnerId=" + learnerId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", department="
				+ department + ", email=" + email + ", password=" + password + "]";
	}
	public Learner(int learnerId, String name, String phoneNumber, String department, String email, String password) {
		super();
		this.learnerId = learnerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.department = department;
		this.email = email;
		this.password = password;
	}
	public Learner() {
		super();
		// TODO Auto-generated constructor stub
	}
}
