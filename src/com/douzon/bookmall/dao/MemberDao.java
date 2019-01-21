package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.MemberVo;

public class MemberDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<MemberVo> selectList(){
		List<MemberVo> list = new ArrayList<MemberVo>();
		
		try {
			conn = getConnection();
			
			String sql = "select no, name, phone, mail from member";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String mail = rs.getString("mail");
				
				MemberVo vo = new MemberVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setMail(mail);
				
				list.add(vo);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	public int insert(String name, String phone, String mail, String password) {
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into member values (null, ?, ?, ?, password(?))";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3,  mail);
			pstmt.setString(4, password);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		return result;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/bookmall?useSSL=false";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
			
			System.out.println("DB 연결 성공");
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
}
