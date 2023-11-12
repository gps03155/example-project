package com.douzon.jdbc.hr.vo;

public class EmployeeVo {
	private String name;
	private String hire_date;
	private String emp_no;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHire_date() {
		return hire_date;
	}
	
	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}
	
	public String getEmp_no() {
		return emp_no;
	}
	
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
