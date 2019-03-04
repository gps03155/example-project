package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insertUser(UserVo userVo) {
		sqlSession.insert("user.insert", userVo);
	}
	
	public long lastInsert() {
		return sqlSession.selectOne("user.lastInsert");
	}
	
	// id check
	public String checkId(String id) {
		return sqlSession.selectOne("user.checkId", id);
	}
	
	public UserVo login(UserVo userVo) {
		return sqlSession.selectOne("user.login", userVo);
	}
}
