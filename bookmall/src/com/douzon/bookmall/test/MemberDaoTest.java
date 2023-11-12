package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.MemberDao;
import com.douzon.bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		// 회원리스트  2개
		//insertMember("둘리", "010-2222-2222", "aaaa@gmail.com", "4567");
		//insertMember("또치", "010-3333-3333", "bbbb@hanmail.net", "7890");
		getList();
	}

	public static void getList() {
		List<MemberVo> list = new MemberDao().selectList();
		
		for(MemberVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getName() + " " + vo.getPhone() + " " + vo.getMail() + " " + vo.getPassword());
		}
	}
	
	public static void insertMember(String name, String phone, String mail, String password) {
		int result = new MemberDao().insert(name, phone, mail, password);
		
		if(result == 1) {
			System.out.println("고객이 등록되었습니다.");
		}
	}
}
