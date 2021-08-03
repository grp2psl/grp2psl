package com.psl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "learner")
public class Learner extends User {
	@Id
	private int learnerid;
	private String name;
	private String phonenumber;
	private String department;

	@NaturalId
	@Column(name="email")
	private String email;

	private String password;
	private String role;
	private int enabled;
	
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getEnable() {
		return enabled;
	}
	public void setEnable(int enabled) {
		this.enabled = enabled;
	}

	
	public Learner(int learnerid, String name, String phonenumber, String department, String email, String password) {
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
