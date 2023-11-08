package com.douzone.mysite.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class GuestBookVo {
	private int no;
	
	@NotEmpty
	@Length(min=2, max=8)
	private String name;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String message;
	
	private String msgDate;
	
	@Override
	public String toString() {
		return getNo() + " " + getName() + " " + getMessage() + " " + getMsgDate();
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMsgDate() {
		return msgDate;
	}
	
	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}
	
}
