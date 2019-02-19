package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice // 모든 Controller에 Exception이 발생하면 여기서 처리
public class GlobalExceptionHandler { 

	@ExceptionHandler(Exception.class) // Exception.class - 모든 Exception에서 처리를 동일하게 해줌
	public ModelAndView handlerException(HttpServletRequest request, Exception e) {
		// 1. logging
		StringWriter errors = new StringWriter();

		e.printStackTrace(new PrintWriter(errors));
		// log.error(errors);
		
		// 2. 화면 전환 - ModelAndView
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("errors", errors.toString());
		mav.setViewName("error/exception");
		
		return mav;
	}
}
