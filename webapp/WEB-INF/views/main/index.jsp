<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="${pageContext.servletContext.contextPath}/assets/images/profile.png" style="width:120px; height:120px;">
					<h2>${site.welcome}</h2> <br>
					<h3>https://github.com/gps03155</h3>
					<p>
						${site.desc}<br><br>
						<a href="${pageContext.servletContext.contextPath}/guestbook/list">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main" />
		</c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>