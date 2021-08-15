package com.psl.entities;
/*
 * Abstract entity from which entities like Learner, Admin, etc. inherit
 */
public abstract class User {
	/*
	 * Attributes of general user which store common details like name, contact number.
	 */
	private String name;
	private String phonenumber;
	private String email;
	private String password;
	
	/*
	 * Public Getter and Setter methods
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phonenumber;
	}
	public void setPhone(String phonenumber) {
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
		
}
