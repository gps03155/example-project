package com.douzone.springcontainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.douzone.springcontainer.soundsystem.CDPlayer;
import com.douzone.springcontainer.soundsystem.CompactDisc;
import com.douzone.springcontainer.user.User;

import config.user.AppConfig01;

public class JavaConfigTest {

	public static void main(String[] args) {
		// testJavaConfigTest01();
		// testJavaConfigTest02();
		testJavaConfigTest03();
	}

	// Java Config 01
	// 직접 설정 자바 클래스를 전달하는 경우
	// 설정 자바 클래스에 @Configuration 필요 없음 - 보통은 붙임
	public static void testJavaConfigTest01() {
		ApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfig01.class);
		
		User user = appCtx.getBean(User.class);
		System.out.println(user);
		
		((ConfigurableApplicationContext) appCtx).close();
	}

	// Java Config 02
	// 설정 자바 클래스가 있는 베이스 패키지를 지정하는 방법
	// 설정 자바 클래스에 @Configuration 반드시 필요
	public static void testJavaConfigTest02() {
		ApplicationContext appCtx = new AnnotationConfigApplicationContext("config.user");
		
		User user = appCtx.getBean(User.class);
		System.out.println(user);
	}
	
	// Java Config 03
	// Component Scanning (@Component - bean 클래스, @Autowired - 주입)
	public static void testJavaConfigTest03() {
		// 해당 패키지에서 @Configuration 어노테이션을 가지는 클래스 찾음
		ApplicationContext appCtx = new AnnotationConfigApplicationContext("config.soundsystem");
		
		CompactDisc cb = appCtx.getBean(CompactDisc.class);
		System.out.println(cb);
		
		CDPlayer cdPlayer = appCtx.getBean(CDPlayer.class);
		cdPlayer.play();
		
		((ConfigurableApplicationContext)appCtx).close();
	}
}
