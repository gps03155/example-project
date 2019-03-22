<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	$("#id").change(function(){
		$("#btn-checkemail").show();
		$("#img-checkemail").hide();
	});
	
	$("#btn-checkemail").click(function(){
		var id = $("#id").val();
		
		if(id == ""){
			return;
		}
		
		$.ajax({
			async:true,
			url:"${pageContext.request.contextPath}/user/checkid",
			type:"post",
			dataType:"json",
			data:"id=" + id,
			success: function(response){
				console.log(response);
				
				if(response.data == true){
					alert("이미 사용중인 id 입니다. 다른 id를 사용해주세요");
					
					$("#blog-id").val("").focus();
					return;
				}
				
				$("#btn-checkemail").hide();
				$("#img-checkemail").show();
			},
			error: function(xhr, status, e){
				console.log(status + " : " + e);
			}
		});
	});
	
	$("#join-form").submit(function(){
		if(!$("#agree-prov").is(":checked")){
			alert("약관동의에 체크해주세요");
			
			return false;
		}
		
		if(!$("#img-checkemail").is(":visible")){
			alert("이메일 중복 체크를 해야합니다.");
			
			return false;
		}
		
		return true;
	});
});
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name"/><br>
			<form:errors path="name"/>
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id"/><br>
			<form:errors path="id"/>
			
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<form:input path="password"/><br>
			<form:errors path="password"/>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
