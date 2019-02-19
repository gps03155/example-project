<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

<style>
#comment {
	border:1px solid #333333;
}

#comment tr td {
	border:1px solid #333333;
}

</style>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		
		<div id="content">
			<div id="board" class="board-form">				
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(vo.content, newline, "<br>")}
							</div>
						</td>
					</tr>
				</table>
				
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath}/board/list/${page}">글목록</a>
					
					<c:choose>
						<c:when test="${!empty authuser && authuser.no == vo.userNo}">
							<a href="${pageContext.servletContext.contextPath}/board/modify/${no}/${page}">글수정</a>
							<a href="${pageContext.servletContext.contextPath}/board/reply/${no}/${page}">답글</a>
						</c:when>
						
						<c:when test="${!empty authuser}">
							<a href="${pageContext.servletContext.contextPath}/board/reply/${no}/${page}">답글</a>
						</c:when>
					</c:choose>
				</div>
				
				<!-- 댓글 -->
						
				<form action="${pageContext.servletContext.contextPath}/board/comment/${no}/${page}" method="post">
					<input type="hidden" name="boardNo" value="${vo.no}" />
					<input type="hidden" name="authUser" value="${authuser.no}" />
					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">댓글</th>
						</tr>
						<c:if test="${!empty authuser}">
							<tr>
								<td colspan=4><textarea name="content" id="content" placeholder="댓글을 입력해주세요."></textarea></td>
							</tr>		
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE="등록"></td>
							</tr>
						</c:if>
					</table>
				</form>
				
				<ul>
					<c:set var="count" value="${fn:length(list)}" />
					
					<c:forEach items="${list}" var="commentVo" varStatus="status">
						<li>
							<table id="comment" class="tbl-ex">
								<tr>
									<td>[${count - status.index}]</td>
									<td>${commentVo.name}</td>
									<td>${commentVo.writeDate}</td>
									
									<c:if test="${!empty authuser &&  authuser.no == commentVo.userNo}">
										<td><a href="${pageContext.servletContext.contextPath}/board/deletecomment/${no}/${commentVo.no}/${page}">삭제</a></td>
									</c:if>
								</tr>
								<tr>
									<td colspan=4>${commentVo.content}</td>
								</tr>
							</table>
							<br>
						</li>
					</c:forEach>
				</ul>
				<!-- 댓글 -->
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>