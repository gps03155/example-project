<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String color = request.getParameter("color");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if("0".equals(color)){
	%>
		<h1 style="color:red">Hello World</h1>
	<%
		} else if("1".equals(color)){
	%>
		<h1 style="color:blue">Hello World</h1>
	<% 
		} else if("2".equals(color)){
	%>
		<h1 style="color:green">Hello World</h1>
	<%
		} else {
	%>
		<h1>Hello World</h1>
	<%
		}
	%>
</body>
</html>