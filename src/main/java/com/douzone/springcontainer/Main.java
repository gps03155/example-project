package com.douzone.springcontainer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {

	public static void main(String[] args) {
		// testBeanFactory();
		testApplicationContext();
	}

	public static void testBeanFactory() {
		// Annotation 설정일 경우 id가 자동으로 만들어 진다. User1 -> user1
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("config/applicationContext2.xml")); // classpath를 fullpath로 바꿔줌
		
		User1 user1 = (User1) bf.getBean("user1");
		System.out.println(user1.getName());
		
		// XML bean 설정인 경우에는 id를 주지 않으면 에러
		BeanFactory bf2 = new XmlBeanFactory(new ClassPathResource("config/applicationContext.xml"));
		
		User1 user2 = bf2.getBean(User1.class); // id 대신에 타입으로 bean을 가져올 수 있다.
		
		System.out.println(user2.getName());
	}
	
	public static void testApplicationContext() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		
		User1 user1 = ac.getBean(User1.class);
		System.out.println(user1.getName());
		
		// 오류 : bean 설정은 id나 name을 지정해야함
		// User user = (User) ac.getBean("user");
		// System.out.println(user.getName());
		
		// name으로 가져오기
		User user = (User) ac.getBean("user");
		System.out.println(user.getName());
		
		// id로 가져오기
		user = (User) ac.getBean("usr");
		System.out.println(user.getName());
		
		// 오류 : 같은 타입의 bean이 2개이상 존재하면 타입으로 bean을 가져올 수 없다. - 모호함
		// ac.getBean(User.class);
		
		User user3 = (User) ac.getBean("usr2");
		System.out.println(user3.toString());
		
		user3 = (User) ac.getBean("usr3");
		System.out.println(user3.toString());

		Friend friend = (Friend) ac.getBean("friend");
		System.out.println(friend.toString());
		
		user3 = (User) ac.getBean("usr4");
		System.out.println(user3.toString());
	}
}
