package com.douzon.emaillist.dao.test;

import java.util.List;

import com.douzon.emaillist.dao.EmailListDao;
import com.douzon.emaillist.vo.EmailListVo;

public class EmailListDaoTest {

	public static void main(String[] args) {
		getListTest();
	}

	public static void getListTest() {
		List<EmailListVo> list = new EmailListDao().getList();
		
		for(EmailListVo vo : list) {
			System.out.println(vo.toString());
		}
	}
}
