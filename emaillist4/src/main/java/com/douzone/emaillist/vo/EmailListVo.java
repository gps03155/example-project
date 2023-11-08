package com.douzone.emaillist.vo;

public class EmailListVo {
	private long no;
	private String firstName;
	private String lastName;
	private String email;
	
	public long getNo() {
		return no;
	}
	
	@Override
	public String toString() {
		return getNo() + " " + getFirstName() + " " + getLastName() + " " + getEmail();
	}

	public void setNo(long no) {
		this.no = no;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
