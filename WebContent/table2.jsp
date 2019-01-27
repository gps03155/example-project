<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${empty param.row}">
		<c:set var="row" value="3"/>
	</c:when>
	
	<c:otherwise>
		<c:set var="row" value="${param.row}" />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${empty param.col}">
		<c:set var="col" value="3"/>
	</c:when>
	
	<c:otherwise>
		<c:set var="col" value="${param.col}" />
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1px" cellspacing="0" cellpadding="5px">
	
		<c:forEach begin="1" end="${row}" step="1" var="i">
			<tr>
				<c:forEach begin="1" end="${col}" step="1" var="j">
					<td>cell(${i-1}, ${j-1})</td>
				</c:forEach>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>