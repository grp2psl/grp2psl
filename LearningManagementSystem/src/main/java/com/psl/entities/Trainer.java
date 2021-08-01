package com.psl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trainer extends User {
	@Id
	private int trainerid;
	
	private String name;
	private String department;
	private long phonenumber;
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
	
	public int getTrainerid() {
		return trainerid;
	}
	public void setTrainerid(int trainerid) {
		this.trainerid = trainerid;
	}
	
	public Trainer() {
		
	}
	
	public Trainer(int trainerid, String name, String department, long phonenumber, String email, String password) {
		super();
		this.trainerid = trainerid;
		this.name = name;
		this.department = department;
		this.phonenumber = phonenumber;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Trainer [trainerid=" + trainerid + ", name=" + name + ", department=" + department + ", phonenumber="
				+ phonenumber + ", email=" + email + ", password=" + password + "]";
	}
			
}
