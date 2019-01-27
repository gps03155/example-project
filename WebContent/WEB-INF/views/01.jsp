<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>데이터 타입</h1>
	${ iVal } <br> <!-- 내부적으로 코드 실행 : reqeust.getAttribute("iVal") -->
	${ lVal } <br>
	${ fVal } <br>
	${ bVal } <br>
	--${ obj }-- <br> <!--  null은 아무것도 안찍힘 -->
	
	<h1>연산</h1>
	${ iVal+20*lVal/2-10 } <br>
	${ iVal > lVal && fVal < 5 } <br>
	${ empty obj } <br> <!--  null은 자바의 키워드이므로 사용하면 안됨 -->
	${not empty obj } <br>
	
	<h1>요청 파라미터 가져오기</h1>
	<%= request.getParameter("a") + 10 %> <br>
	
	${ param.a+10 } <br>
	
	<h1>객체 접근</h1>
	${ vo.no } <br>
	${ vo.name } <br>
	${ vo } <br>
</body>
</html>