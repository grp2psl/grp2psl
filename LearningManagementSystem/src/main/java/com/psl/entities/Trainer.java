package com.psl.entities;

/*
 * Entity declaration of Trainer
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/*
 * Stores details of trainer table from DBs
 */

@Entity
public class Trainer extends User {
	
	@Id
	@Column(name = "trainerid")
	private int trainerId;	

	private String name;

	private String department;

	@Column(name = "phonenumber")
	private String phoneNumber;

	private String email;

	private String password;

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
		return "Trainer [trainerId=" + trainerId + ", name=" + name + ", department=" + department + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", password=" + password + "]";
	}

	public Trainer(int trainerId, String name, String department, String phoneNumber, String email, String password) {
		this.trainerId = trainerId;
		this.name = name;
		this.department = department;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}

	public Trainer() {
	}
}
