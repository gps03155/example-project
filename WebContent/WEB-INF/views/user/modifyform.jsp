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
<link href="${pageContext.servletContext.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="POST" action="${pageContext.servletContext.contextPath}/user">
					<input type="hidden" name="action" value="modify"/> <!-- 보안처리 후 session에 있는 no를 받아야함  -->
					<input type="hidden" name="no" value="${vo.no}"/>
					
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${vo.name}">

					<label class="block-label" for="email">이메일</label>
					<h3>${vo.email}</h3>
					
					<label class="block-label">패스워드</label> <!-- 패스워드는 비워두는 것이 좋음 -->
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend> <!-- 성별에 따라 checked 표시 -->
						 <!-- radio 그룹으로 생각 - if문으로 구분 -->
						<c:choose>
							<c:when test="${vo.gender eq 'female'}">
								<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="male">
							</c:when>
							
							<c:when test="${vo.gender eq 'male'}">
								<label>여</label> <input type="radio" name="gender" value="female">
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:when>
						</c:choose>
						
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>