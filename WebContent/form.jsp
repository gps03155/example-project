<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/helloweb/join" method="POST">
		이메일 : <input type="text" name="mail"/>
		<br/><br/>
		
		비밀번호 : <input type="password" name="password"/>
		<br/><br/>
		
		성별 : 
		<input type="radio" name="gender" value="female" checked="checked"/> 여
		<input type="radio" name="gender" value="male"/> 남
		<br/><br/>
		
		생년:
		<select name="birth-year">
			<option value="1998">1998</option>
			<option value="1999">1999</option>
			<option value="2000">2000</option>
			<option value="2001">2001</option>
		</select>
		<br/><br/>
		
		취미:
		<input type="checkbox" name="hobby" value="reading"/> 독서
		<input type="checkbox" name="hobby" value="swimming"/> 수영
		<input type="checkbox" name="hobby" value="coding"/> 코딩
		<br/><br/>
		
		자기소개:
		<textarea name="self-info"></textarea> <!--  개행이 들어갈수도 있기때문에 태그 사이의 빈줄 제거해야함 -->
		<br/><br/>
		
		<input type="submit" value="가입"/>
	</form>
</body>
</html>