package com.douzone.mysite.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	
	@NotEmpty
	private String gender;
	
	private String joinDate;
	private String role;
}
