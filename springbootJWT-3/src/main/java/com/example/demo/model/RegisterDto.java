package com.example.demo.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterDto {
	
	@NotEmpty
	private String firstname;
	
	@NotEmpty
	private String lirstname;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String email;
	
	private String phone;
	
	private String address;
	@NotEmpty
	@Size(min=6, message = "Minimum Password Lenth is 6 characters")
	private String password;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLirstname() {
		return lirstname;
	}
	public void setLirstname(String lirstname) {
		this.lirstname = lirstname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
