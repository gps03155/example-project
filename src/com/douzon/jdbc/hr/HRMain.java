package com.douzon.jdbc.hr;

import java.util.List;
import java.util.Scanner;

import com.douzon.jdbc.hr.dao.EmployeeDao;
import com.douzon.jdbc.hr.vo.EmployeeVo;

public class HRMain {

	public static void main(String[] args) {
		System.out.print("사원 이름을 입력해주세요 : ");
		Scanner sc = new Scanner(System.in);
		
		String name = sc.nextLine();
		
		System.out.println();
		search(name);
	}

	public static void search(String name) {
		List<EmployeeVo> list = new EmployeeDao().getList(name);
		
		for(EmployeeVo vo : list) {
			System.out.println(vo.getEmp_no() + " " + vo.getName() + " " + vo.getHire_date());
		}
	}
}
