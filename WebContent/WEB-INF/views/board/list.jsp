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
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath}/board" method="post">
					<input type="hidden" name="action" value="search" />
					<input type="hidden" name="page" value="${page}" />
					
					<select name="search">
						<option value="full">전체</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="name">작성자</option>
					</select>
					
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					
					<c:set var="count" value="${fn:length(list)}" />
					
					<c:forEach items="${list}" var="vo" varStatus="status">
						<tr>
							<td>${count - status.index}</td>
						
							<c:choose>
								<c:when test="${vo.depth > 0}">
									<td style="padding-left:${20 * vo.depth}px"><img src="${pageContext.servletContext.contextPath}/assets/images/reply.png"/>
										<a href="${pageContext.servletContext.contextPath}/board?action=viewform&no=${vo.no}&page=${page}">${vo.title}</a>
									</td>
								</c:when>
								
								<c:otherwise>
									<td><a href="${pageContext.servletContext.contextPath}/board?action=viewform&no=${vo.no}&page=${page}">${vo.title}</a></td>	
								</c:otherwise>
							</c:choose>
							
							<td>${vo.name}</td>
							<td>${vo.hit}</td>
							<td>${vo.writeDate}</td>
							
							<c:choose>
								<c:when test="${!empty authuser && authuser.no == vo.userNo}">
									<td><a href="${pageContext.servletContext.contextPath}/board?action=delete&no=${vo.no}&page=${page}" class="del">삭제</a></td>
								</c:when>
								
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>			
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${page > startPage}">
							<li><a href="${pageContext.servletContext.contextPath}/board?action=boardform&page=${page-1}">◀</a></li>
						</c:if>
						
						<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" varStatus="status">
							<c:choose>
								<c:when test="${page == i}">
									<li class="selected">${i}</li>
								</c:when>
								
								<c:otherwise>
									<li><a href="${pageContext.servletContext.contextPath}/board?action=boardform&page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:if test="${endPage > page}">
							<li><a href="${pageContext.servletContext.contextPath}/board?action=boardform&page=${page+1}">▶</a></li>
						</c:if>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<c:if test="${!empty authuser}">
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath}/board?action=writeform&page=${page}" id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>