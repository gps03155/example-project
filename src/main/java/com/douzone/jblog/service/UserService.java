package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void insertUser(UserVo userVo) {
		userDao.insertUser(userVo);
	}
	
	public long lastInsert() {
		return userDao.lastInsert();
	}
	
	// id check
	public boolean checkId(String id) {
		String result = userDao.checkId(id);
		System.out.println(result);
		if(result != null) {
			return true;
		}
		
		return false;
	}
	
	public UserVo login(UserVo userVo) {
		return userDao.login(userVo);
	}
}
