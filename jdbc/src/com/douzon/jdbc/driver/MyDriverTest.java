package com.douzon.jdbc.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			// 1. JDBC Driver 로딩
			// Class.forName("com.mysql.jdbc.Driver"); // JVM에 메모리가 올라감
			Class.forName("com.douzon.jdbc.driver.MyDriver");
			
			// 2. Connection
			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("DB 연결 성공");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
