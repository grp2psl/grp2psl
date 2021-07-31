package com.psl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Learner {

	@Id
	private int learnerid;
    
	private String name;
    
	private String department;
    
	private long phonenumber;
    
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
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
	
	public Learner(int learnerid, String name, String department, long phonenumber, String email, String password) {
		this.learnerid = learnerid;
		this.name = name;
		this.department = department;
		this.phonenumber = phonenumber;
		this.email = email;
		this.password = password;
	}
	public Learner() {
	}
	
	@Override
	public String toString() {
		return "learner [learnerid=" + learnerid + ", name=" + name + ", department=" + department + ", phonenumber="
				+ phonenumber + ", email=" + email + ", password=" + password + "]";
	}
}
