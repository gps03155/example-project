<%@page import="com.douzon.guestbook.dao.GuestBookDao"%>
<%@page import="com.douzon.guestbook.vo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	request.setCharacterEncoding("UTF-8");

	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String message = request.getParameter("message");
	
	GuestBookVo vo = new GuestBookVo();
	
	vo.setName(name);
	vo.setPassword(password);
	vo.setMessage(message);
	
	new GuestBookDao().insert(vo);
	
	response.sendRedirect("index.jsp");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>