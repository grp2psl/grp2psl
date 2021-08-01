package com.psl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Learner extends User {
	@Id
	private int learnerid;
	private String name;
	private long phonenumber;
	private String department;
	private String email;
	private String password;
	
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
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
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
	
	public Learner(int learnerid, String name, long phonenumber, String department, String email, String password) {
		this.learnerid = learnerid;
		this.name = name;
		this.phonenumber = phonenumber;
		this.department = department;
		this.email = email;
		this.password = password;
	}
	public Learner() {
	}	
}
