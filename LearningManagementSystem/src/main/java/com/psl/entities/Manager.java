package com.psl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Manager")
public class Manager extends User {
	@Id
	private int managerid;
	private String name;
	private String phonenumber;
	private String email;
	private String password;
	
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
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
		this.managerid = managerid;
		this.name = name;
		this.phonenumber = phonenumber;
		this.email = email;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Manager [managerid=" + managerid + ", name=" + name + ", phonenumber=" + phonenumber + ", email="
				+ email + ", password=" + password + "]";
	}
	
	
}
