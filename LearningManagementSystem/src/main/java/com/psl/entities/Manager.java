package com.psl.entities;

public class Manager extends User {
	private int id;
	private String name;
	private String phone;
	private String email;
	private String password;
	public Manager() {
		super();
	}
	public Manager(int id, String name, String phone, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password="
				+ password + "]";
	}
		
}
