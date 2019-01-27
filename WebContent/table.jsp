<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String row = request.getParameter("row"); // 숫자라도 String
	String col = request.getParameter("col");
	
	int nRow = 1;
	
	if(row != null){
		nRow = Integer.parseInt(row);
	}
		
	int nCol = 1;
	
	if(col != null){
		nCol = Integer.parseInt(col);
	}
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1px" cellspacing="0" cellpadding="5px">
	
		<%
			for(int i=0;i<nRow;i++){
		%>
			<tr>
			
			<%
				for(int j=0;j<nCol;j++){
			%>
					<td>cell(<%= i %>, <%= j %>)</td>
			<%
				}
			%>
			</tr>
		<%
			}
		%>
		
		<tr>
			<td>cell(1, 0)</td>
			<td>cell(1, 1)</td>
			<td>cell(1, 2)</td>
		</tr>
		
		<tr>
			<td>cell(2, 0)</td>
			<td>cell(2, 1)</td>
			<td>cell(2, 2)</td>
		</tr>
	</table>
</body>
</html>