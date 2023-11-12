package com.douzon.jdbc.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzon.jdbc.hr.vo.EmployeeVo;

public class EmployeeDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public List<EmployeeVo> getList(String name){
		List<EmployeeVo> list = new ArrayList<EmployeeVo>();
		
		try {
		conn = getConnection();
		
		String sql = "select emp_no, concat(first_name, ' ', last_name), hire_date from employees where first_name Like ? or last_name Like ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, "%" + name + "%");
		pstmt.setString(2, "%" + name + "%");
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String emp_no = rs.getString("emp_no");
			String fullName = rs.getString("concat(first_name, ' ', last_name)");
			String hire_date = rs.getString("hire_date");
			
			EmployeeVo vo = new EmployeeVo();
			
			vo.setEmp_no(emp_no);
			vo.setName(fullName);
			vo.setHire_date(hire_date);
			
			list.add(vo);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/employees?useSSL=false";
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			System.out.println(e);
		}

		return conn;
	}
}
