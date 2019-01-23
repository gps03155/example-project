<%@page import="com.douzon.guestbook.dao.GuestBookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	request.setCharacterEncoding("UTF-8");

	String no = request.getParameter("no");
	String password = request.getParameter("password");
	
	GuestBookDao dao = new GuestBookDao();
	
	String resultPW = dao.comparePW(password);
	int resultNo = Integer.parseInt(no);
	
	System.out.println(resultNo + " " + resultPW);
	
	dao.delete(resultNo, resultPW);
	
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