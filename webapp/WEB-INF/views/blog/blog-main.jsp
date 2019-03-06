<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title}</h1>
			<ul>
				<c:choose>
					<c:when test="${!empty authuser}">
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/blog/admin?id=${id}">블로그 관리</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${post.title}</h4>
					<p>
						${fn:replace(post.content, newLine, "<br>")}
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList}" var="postVo">
						<c:choose>
							<c:when test="${empty isCategory}">							
								<li>
									<a href="${pageContext.request.contextPath}/blog/${id}/${postVo.no}">${postVo.title}</a>
									<span>${postVo.regDate}</span>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a href="${pageContext.request.contextPath}/blog/${id}/${postVo.no}/${categoryNo}">${postVo.title}</a>
									<span>${postVo.regDate}</span>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo}" onerror="this.src='${pageContext.request.contextPath}/assets/images/default_profile.png'">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList}" var="categoryVo">
					<li><a href="${pageContext.request.contextPath}/blog/${id}/${post.no}/${categoryVo.no}">${categoryVo.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>