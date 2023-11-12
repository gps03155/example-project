package com.douzon.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {

	public static void main(String[] args) {
		boolean result = insert("마음이3", "또치", "dog", "f", "2018-11-11", "2019-01-01");
		
		System.out.println(result);
	}

//	public static void insert(VO pet) {
//
//	}
	
	public static boolean insert(String name, String owner, String species, String gender, String birth, String death) {
		Connection conn = null;
		Statement stmt = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("DB 연결 성공");
			
			stmt = conn.createStatement();
			String sql = "insert into pet values ('" + name + "', '" + owner + "', '" + species + "', '" + gender + "', '" + birth + "', '" + death + "')";
		
			int count = stmt.executeUpdate(sql);
			
			if(count == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
