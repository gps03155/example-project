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

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function(){
		$("#join-form").submit(function(){
			// 1. 이름 체크
			if($("#name").val() == ""){
				alert("이름은 필수 입력 항목입니다.");
				$("#name").focus();
				
				return false;
			}
			
			// 2-1. 이메일이 비어있는지 체크
			if($("#email").val() == ""){
				alert("이메일은 필수 입력 항목입니다.");
				$("#email").focus();
				
				return false;
			}
			
			// 2-2. 이메일 중복체크 유무
			
			// 3. 비밀번호 체크
			if($("input[type=password]").val() == ""){
				alert("비밀번호는 필수 입력 항목입니다.");
				$("input[type=password]").focus();
				
				return false;
			}
			
			// 4. 약관동의 체크 유무
			if(!$("#agree-prov").is(":checked")){
				alert("약관동의에 체크해주세요.");
				
				return false;
			}
		});
		
		$("#btn-checkemail").click(function(){
			var email = $("#email").val();
			
			if(email == ""){
				return;
			}
			
			$.ajax({
				async:true,
				url:"/mysite2/api/user",
				type:"post",
				dataType:"json",
				data:"action=ajax-checkemail&email=" + email,
				success: function(response){
					console.log(response);
					
					if(response.exist){
						alert("이미 존재하는 이메일입니다. 다른 이메일을 사용해주세요.");
						
						$("#email").val("").focus();
						return;
					}
					
					// 사용가능한 이메일
					$("#btn-checkemail").hide();
					$("#img-checkemail").show();
				},
				error: function(xhr, status, e){
					console.log(status + " : " + e);
				}
			});
		});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="POST" action="${pageContext.servletContext.contextPath}/user">
					<input type="hidden" name="action" value="join"/>
					
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<input id="btn-checkemail" type="button" value="id 중복체크">
					<img id="img-checkemail" src="${pageContext.servletContext.contextPath}/assets/images/check.png" alt="email 중복 체크 확인" style="width:25px; display:none">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>