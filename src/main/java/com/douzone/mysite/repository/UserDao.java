package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession; // xml로 myBatis 객체를 사용할 수 있음
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// email 중복 체크
	public UserVo get(String email){
		UserVo vo = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "select no, name from user where email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVo();
				
				vo.setNo(rs.getLong("no"));
				vo.setName(rs.getString("name"));
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
		
		return vo;
	}
	
	// 회원 정보 수정하기
	public int update(UserVo vo) {
		int result = 0;
		String sql = null;
		
		try {
			conn = dataSource.getConnection();
			
			if(!vo.getPassword().equals("") && !"".equals(vo.getName())) {
				sql = "update user set name = ?, password = ?, gender = ? where no = ?";
			
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getGender());
				pstmt.setLong(4, vo.getNo());
				
				result = pstmt.executeUpdate();
			}
			else if(vo.getPassword().equals("") && !"".equals(vo.getName())) {
				sql = "update user set name = ?, gender = ? where no = ?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setLong(3, vo.getNo());
				
				result = pstmt.executeUpdate();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
		
		return result;
	}
	
	// 회원 정보 가져오기
	public UserVo get(long no) {
		UserVo vo = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "select no, name, email, gender from user where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVo();
				
				vo.setNo(rs.getLong("no"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender"));
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
		
		return vo;
	}
	
	// 로그인
	public UserVo get(String email, String password) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("email", email);
		map.put("password", password);
		
		return sqlSession.selectOne("user.getByEmailAndPassword", map);
	}

	// 회원 등록
	public int insert(UserVo vo) { // Exception 전환
		int result = sqlSession.insert("user.insert", vo); // namespace.id, parameter
		
		return result;
	}
}
