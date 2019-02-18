<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page import="com.douzone.guestbook.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	List<GuestBookVo> list = (List)request.getAttribute("list");
	int count = list.size() + 1;
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/add" method="post">
		<input type="hidden" name="action" value="add"/>
		<table border=1 width=500>
			<tr>
				<td>이름</td><td><input type="text" name="name"></td>
				<td>비밀번호</td><td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE="확인"></td>
			</tr>
		</table>
	</form>
	<br>
	
	<%
		for(GuestBookVo vo : list){
			count--;
	%>
		<table width=510 border=1>
			<tr>	
				<td><%= count %></td> <!-- no가 아님 : 글의 순서 -total count -->
				<td><%= vo.getName() %></td>
				<td><%= vo.getMsgDate() %></td>
				<td><a href="${pageContext.request.contextPath}/deleteform?no=<%= vo.getNo() %>">삭제</a></td>
			</tr>
			<tr>
				<td colspan=4><%= (vo.getMessage()).replace("\r\n", "<br/>") %></td>
			</tr>
		</table>
		<br/>
	<%
		}
	%>
</body>
</html>