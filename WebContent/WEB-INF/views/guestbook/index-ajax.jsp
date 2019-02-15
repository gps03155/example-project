<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	var page = 0;
	var isEnd = false;
	var render = function(vo, mode){
		// 현업에 가면 이렇게 안한다. -> js template Libarary 사용
		// ex) ejs, underscore, mustache
		
		var htmls = "<li data-no='" + vo.no + "'>" +
					"<strong>" + vo.name + "</strong>" +
					"<p>" + vo.message.replace(/\n/g, "<br>") + "</p>" +
					"<strong></strong>" +
					"<a href=' ' data-no= '" + vo.no + "'>삭제</a>" +
					"</li>";
		
		if(mode){
			$("#list-guestbook").prepend(htmls);
		} else{
			$("#list-guestbook").append(htmls);
		}			
	}
	
	var fetchList = function(){
		if(isEnd){
			return;
		}
		
		++page;
				
		$.ajax({
			async:true,
			url:"/mysite2/api/guestbook?action=ajax-list&page=" + page,
			type:"get",
			dateType:"json",
			data:"",
			success:function(response){
				console.log(response);
						
				if(response.result == "fail"){
					console.warn(response.data);
							
					return;
				}
						
				console.log(response.data);
						
				// 페이지 끝을 검출
				if(response.data.length < 5){
					isEnd = true;
							
					$("#btn-next").prop("disabled", true);
				}
						
				// rendering
				$.each(response.data, function(index, vo){
					console.log(vo);
							
					render(vo, false);
				});
			},
			error:function(xhr, status, e){
				console.error(status + " : " + e);
			}
		});	
	};
	
	$(function(){
		// 최초 리스트 가져오기
		$("#btn-next").click(function(){
			fetchList();
		});
		
		/*
		$(window).scroll(function(){
			var $window = $(this);
			var scrollTop = $window.scrollTop();
			var windowHeight = $window.height();
			var documentHeight = $(document).height();
			
			console.log(scrollTop);
			console.log(windowHeight);
			console.log(documentHeight);
			
			// 끝까지 scroll 했을 경우
			if(scrollTop + windowHeight + 10 > documentHeight){
				console.log("fetch ajax start");
			}
		});	
		*/
		
		fetchList();
	});
	
/*
	$(function(){
		$(window).scroll(function(){
			var $window = $(this);
			var scrollTop = $window.scrollTop();
			var windowHeight = $window.height();
			var documentHeight = $(document).height();
			
			console.log(scrollTop);
			console.log(windowHeight);
			console.log(documentHeight);
			
			// 끝까지 scroll 했을 경우
			if(scrollTop + windowHeight + 10 > documentHeight){
				console.log("fetch ajax start");
			}
		});	
	});
*/
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름"> <input
						type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>

				<ul id="list-guestbook">
				</ul>

				<button id="btn-next">다음</button>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
				<p class="validateTips error" style="display: none">비밀번호가 틀립니다.</p>
				<form>
					<input type="password" id="password-delete" value=""
						class="text ui-widget-content ui-corner-all"> <input
						type="hidden" id="hidden-no" value=""> <input
						type="submit" tabindex="-1"
						style="position: absolute; top: -1000px">
				</form>
			</div>
			<div id="dialog-message" title="" style="display: none">
				<p></p>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>