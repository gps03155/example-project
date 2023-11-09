package com.douzon.mvc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
//@WebFilter("/") // "/"하면 모든 경로
public class EncodingFilter implements Filter {
	private String encoding;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// request 처리
		request.setCharacterEncoding(encoding); // Servlet의 공통적으로 처리할 부분들을 수행할 수 있음
		
		chain.doFilter(request, response); // 지우면 안됨
		
		// response 처리
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Encoding Filter Initialized");
		
		encoding = fConfig.getInitParameter("encoding");
		
		if(encoding == null) {
			encoding = "UTF-8";
		}
	}

}
