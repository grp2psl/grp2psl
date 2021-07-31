package com.psl.entities;

public class Trainer extends User {
	private int id;
	private String name;
	private String phone;
	private String department;
	private String specialization; 
	private String email;
	private String password;
	public Trainer() {
		super();
	}
	public Trainer(int id, String name, String phone, String department, String specialization, String email,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.department = department;
		this.specialization = specialization;
		this.email = email;
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	@Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", phone=" + phone + ", department=" + department
				+ ", specialization=" + specialization + ", email=" + email + ", password=" + password + "]";
	}
			
}
