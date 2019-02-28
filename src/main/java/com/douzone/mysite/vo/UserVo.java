package com.douzone.mysite.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	private long no;
	
	@NotEmpty
	@Length(min=2, max=8)
	private String name; // name이 비어서는 안됨 Validation 조건
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String agree;
	
	private String gender;
	private String joinDate;
	private String role;
	
	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getNo() {
		return no;
	}
	
	@Override
	public String toString() {
		return getNo() + " " + getName() + " " + getEmail() + " " + getGender() + " " + getRole();
	}

	public void setNo(long no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	
}
