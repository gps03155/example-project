<%@page import="com.douzon.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="header">
	<h1>MySite</h1>
	<ul>
		<%
			UserVo authUser = (UserVo)session.getAttribute("authuser");
		
			// 로그인 실패
			if(authUser == null){
		%>
			<li><a
				href="<%=request.getContextPath()%>/user?action=loginform">로그인</a>
			<li>
			<li><a
				href="<%=request.getContextPath()%>/user?action=joinform">회원가입</a>
			<li>
		<%
			// 로그인 성공
			} else {
		%>
			<li><a
				href="<%=request.getContextPath()%>/user?action=modifyform">회원정보수정</a>
			<li>
			<li><a href="<%=request.getContextPath()%>/user?action=logout">로그아웃</a>
			<li>
			<li><%= authUser.getName() %>님 안녕하세요 ^^;</li>
		<%
			}
		%>
	</ul>
</div>