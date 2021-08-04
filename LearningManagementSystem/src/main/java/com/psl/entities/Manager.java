package com.psl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Manager extends User {
	@Id
	@Column(name="managerid")
	private int managerId;
	private String name;
	@Column(name="phonenumber")
	private String phoneNumber;
	private String email;
	private String password;
	
	public int getManagerid() {
		return managerId;
	}
	public void setManagerid(int managerid) {
		this.managerId = managerid;
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
	
	
	public Manager() {
		
	}
	public Manager(int managerid, String name, String phonenumber, String email, String password) {
		this.managerId = managerid;
		this.name = name;
		this.phoneNumber = phonenumber;
		this.email = email;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Manager [managerid=" + managerId + ", name=" + name + ", phonenumber=" + phoneNumber + ", email="
				+ email + ", password=" + password + "]";
	}
	
	
}
