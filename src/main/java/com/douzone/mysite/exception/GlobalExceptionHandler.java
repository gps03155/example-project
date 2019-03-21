package com.douzone.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douzone.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice // 모든 Controller에 Exception이 발생하면 여기서 처리
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class) // Exception.class - 모든 Exception에서 처리를 동일하게 해줌
	public void handlerException(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws Exception {
		System.out.println("-----------> GlobalExceptionHandler");
		
		// 1. logging
		StringWriter errors = new StringWriter();

		e.printStackTrace(new PrintWriter(errors));
		// log.error(errors);

		// 2. 화면 전환 - ModelAndView
//		ModelAndView mav = new ModelAndView();
//		
//		mav.addObject("errors", errors.toString());
//		mav.setViewName("error/exception");
//		
//		return mav;

		String accept = request.getHeader("accept");

		// JSON 응답
		if (accept.matches(".*application/json.*")) { // 앞 뒤에 어떤 문자열이 붙어 있던 application/json 단어만 있으면 됨
			response.setStatus(HttpServletResponse.SC_OK);
			
			OutputStream out = response.getOutputStream();
			JSONResult jsonResult = JSONResult.fail(errors.toString());
			
			out.write(new ObjectMapper().writeValueAsString(jsonResult).getBytes("UTF-8"));
			out.flush(); // 강제로 버퍼에 뿌림
			
			out.close();
		} 
		else { // HTML 응답
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors.toString());

			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
	}
}
