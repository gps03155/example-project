<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
		/*
		// Validation
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
			if(!$("#img-checkemail").is(":visible")){
				alert("이메일 중복 체크를 해야합니다.");
				
				return false;
			}
			
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
			
			return true;
		});
		*/
		
		$("#email").change(function(){
			$("#btn-checkemail").show();
			$("#img-checkemail").hide();
		});
		
		$("#btn-checkemail").click(function(){
			var email = $("#email").val();
			
			if(email == ""){
				return;
			}
			
			$.ajax({
				async:true,
				url:"/user/api/checkemail",
				type:"post",
				dataType:"json",
				data:"email=" + email,
				success: function(response){
					console.log(response);
					console.log(response.result);
					
					if(response.result == "fail"){
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

				<form:form modelAttribute="userVo" id="join-form" name="joinForm" method="POST" action="${pageContext.servletContext.contextPath}/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name}">
					
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name')}">
							<p style="text-align:left; color: red">
								<strong>
									<spring:message code="${errors.getFieldError('name').codes[0]}" text="${errors.getFieldError('name').defaultMessage}" />
								</strong>
							</p>
						</c:if>
					</spring:hasBindErrors>
					
					<label class="block-label" for="email">이메일</label>
					<form:input path="email"/> <!-- id, name, value = email로 세팅 -->
					<!-- <form:input path="email" id="email" name="email" type="text" value="${userVo.email}"/> -->
					<p style="margin:0; padding:0; color:red; text-align: left">
						<form:errors path="email"/>
					</p>
					
					<input id="btn-checkemail" type="button" value="id 중복체크">
					<img id="img-checkemail" src="${pageContext.servletContext.contextPath}/assets/images/check.png" alt="email 중복 체크 확인" style="width:25px; display:none">
					
					<label class="block-label">패스워드</label>
					<form:input path="password"/>
					<!-- <form:input path="password" name="password" type="password" value="${userVo.password}" /> -->
					
					<p style="margin:0; padding:0; color:red; text-align: left">
						<form:errors path="password" />
					</p>				
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <form:radiobutton path="gender" value="female" checked="checked"/>
						<label>남</label> <form:radiobutton path="gender" value="male"/>
						
						<p style="margin:0; padding:0; color:red; text-align:left">
							<form:errors path="gender" />
						</p>
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<form:checkbox path="agree" value="y"/>
						<label>서비스 약관에 동의합니다.</label>
						<p style="margin:0; padding:0; color:red; text-align:left">
							<form:errors path="agree" />
						</p>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>