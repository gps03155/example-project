package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao; // @Autowired 없으면 null
	
	public boolean existEmail(String email) {
		UserVo vo = userDao.get(email);
		
		if(vo != null) {
			return true;
		}
		
		return false;
	}
	
	public void join(UserVo userVo) {
		// 1. DB에 가입 회원 정도 insert 하기
		userDao.insert(userVo); // Spring에서는 다 RuntimeException
		
		// 2. email 주소 확인하는 메일 보내기
	}
	
	public UserVo getUserInfo(UserVo userVo) {
		return userDao.get(userVo.getNo());
	}
	
	public void modify(UserVo userVo) {
		userDao.update(userVo);
	}
	
	public UserVo login(UserVo userVo) {
		return userDao.get(userVo.getEmail(), userVo.getPassword());
	}
}
