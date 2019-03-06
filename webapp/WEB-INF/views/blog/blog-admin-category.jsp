<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/ejs/ejs.js"></script>
<script>
var id = ${id};
var isEnd = false;
var listItemTemplate = new EJS({url:"${pageContext.request.contextPath}/assets/js/ejs-template/guestbook-list-item.ejs"});
var listTemplate = new EJS({url:"${pageContext.request.contextPath}/assets/js/ejs-template/guestbook-list.ejs"});

var render = function(vo, mode){
	// var html = listItemTemplate.render(vo);
	
	var html = "<tr data-no='" + vo.no + "'><td>" + vo.no + "</td>" +
			   "<td>" + vo.name + "</td>" +
			   "<td>" + vo.countPost + "</td>" +
			   "<td>" + vo.description + "</td>" +
			   "<td><a href='' data-no='" + vo.no + "'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a></td></tr>";	
	if(mode){
		$("#table-header").after(html);
	}
	else{
		$("#list-category").append(html);
	}
}

var fetchList = function(){
	if(isEnd){
		return;
	}
	
	$.ajax({
		async:true,
		url:"/jblog/blog/" + id + "/admin/category/list",
		type:"get",
		dataType:"json",
		data:"",
		success:function(response){
			console.log(response);
			
			$.each(response.data, function(index, vo){
				console.log(vo);
				
				render(vo, false);
			});
			
			// var htmls = listTemplate.render(response);
			// $("#list-category").append(htmls);
		},
		error:function(xhr, status, e){
			console.log(status + " : " + e);
		}
	});
}


$(function(){
	fetchList();
	
	$("#add-category").submit(function(event){
		event.preventDefault();
		
		var name = $("#name").val();
		var desc = $("#desc").val();
		
		console.log(name);
		console.log(desc);
		
		if(name == ""){
			alert("카테고리명은 필수입력입니다.");
			$("#name").focus();
			
			return;
		}
		
		if(desc == ""){
			alert("설명은 필수입력입니다.");
			$("#desc").focus();
			
			return;
		}
		
		$.ajax({
			async:true,
			url:"/jblog/blog/" + id + "/admin/category/insert/" + $("#name").val() + "/" + $("#desc").val(),
			type:"get",
			dataType:"json",
			data:"",
			success:function(response){
				console.log(response);
			
				if(response.result == "fail"){
					alert("이미 존재하는 카테고리입니다.");
				}
				else{
					render(response.data, true);
				}
				
				$("#name").val("");
				$("#desc").val("");
			},
			error: function(xhr, status, e){
				console.log(status + " : " + e);
			}
		});
	});
	
	$(document).on("click", "#list-category tr td a", function(event){
		event.preventDefault();
		
		var no = $(this).data("no");
		console.log(no);
		
		var isDelete = confirm("삭제하시겠습니까?");
		
		if(isDelete){
			$.ajax({
				async:true,
				url:"/jblog/blog/" + id + "/admin/category/delete/" + no,
				type:"get",
				dataType:"json",
				data:"",
				success:function(response){
					console.log(response);
					
					if(response.data == true){
						console.log("카테고리 삭제");
						
						$("#list-category tr[data-no=" + no + "]").remove();
					}
				},
				error:function(xhr, status, e){
					console.log(status + " : " + e);
				}
			});
		}
	});
});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Spring 이야기</h1>
			<ul>
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/blog/admin?id=${id}">블로그 관리</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/blog/admin?id=${id}">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/blog/${id}/admin/write">글작성</a></li>
				</ul>
				
		      	<table class="admin-cat" id="list-category">
		      		<tr id="table-header">
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
				</table>

      			<form id="add-category" method="post" action="">
	      			<h4 class="n-c">새로운 카테고리 추가</h4>
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" id="name" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" id="desc" name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
      			</form>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>