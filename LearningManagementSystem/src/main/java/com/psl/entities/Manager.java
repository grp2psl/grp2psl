package com.psl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager extends User {
	@Id
	@Column(name = "managerid")
	private int managerId;
	private String name;
	@Column(name = "phonenumber")
	private String phoneNumber;
	private String email;
	private String password;
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
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
		return "Manager [managerId=" + managerId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", password=" + password + "]";
	}
	public Manager(int managerId, String name, String phoneNumber, String email, String password) {
		super();
		this.managerId = managerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
}
