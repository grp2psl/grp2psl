package com.psl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trainer extends User {
	@Id
	@Column(name="trainerid")
	private int trainerId;	
	private String name;
	private String department;
	@Column(name="phonenumber")
	private String phoneNumber;
	private String email;
	private String password;
	
	
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
	public String getPhonenumber() {
		return phoneNumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phoneNumber = phonenumber;
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
	
	public int getTrainerid() {
		return trainerId;
	}
	public void setTrainerid(int trainerid) {
		this.trainerId = trainerid;
	}
	
	public Trainer() {
		
	}
	
	public Trainer(int trainerid, String name, String department, String phonenumber, String email, String password) {
		super();
		this.trainerId = trainerid;
		this.name = name;
		this.department = department;
		this.phoneNumber = phonenumber;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Trainer [trainerid=" + trainerId + ", name=" + name + ", department=" + department + ", phonenumber="
				+ phoneNumber + ", email=" + email + ", password=" + password + "]";
	}
			
}
