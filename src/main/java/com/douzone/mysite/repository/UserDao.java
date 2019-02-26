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
import org.springframework.util.StopWatch;

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
		return sqlSession.selectOne("user.getByEmail", email);
	}
	
	// 회원 정보 수정하기
	public int update(UserVo vo) {
		return sqlSession.update("user.update", vo);
	}
	
	// 회원 정보 가져오기
	public UserVo get(long no) {
		return sqlSession.selectOne("user.getByNo", no);
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
