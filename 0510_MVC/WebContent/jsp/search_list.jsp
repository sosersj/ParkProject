<%@page import="mybatis.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#wrap{
		text-align: center;
		
	}
	#wrap table{
		width:600px;
		margin-left: 10px;
		border: 1px solid black;
		border-collapse: collapse;;
		font-size: 11px;
	}
	#wrap table caption{
		font-size: 20px;
		font-weight: bold;
		margin-bottom: 10px;
	}
	
	th,td{
		text-align: center;
		border: 1px solid black;
		padding: 4px 10px;
	}
	
	.title{
		background-color: #ababab;
	}
	
</style>
</head>
<body>
<%
	MemberVO[] ar = (MemberVO[]) request.getAttribute("ar");
%>
	<div id="wrap">
		<table summary="이름검색결과목록" cellspacing="0" cellpadding="4">
			<caption>::전체사원::</caption>
			<thead>
				<tr class="title">
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
				</tr>
			</thead>
<%
			if(ar != null && ar.length > 0){
				for(MemberVO vo : ar){
%>
			<tbody>
				<tr>
					<td><%=vo.getId() %></td>
					<td><%=vo.getName() %></td>
					<td><%=vo.getEmail() %></td>
				</tr>
<%
				}// for문의 끝
			}else{ 
				// 내용이 없는 경우
%>
			<tr>
				<td colspan="3" style="height: 60px; text-align: center">
					등록된 정보가 없습니다.
				</td>
			</tr>
<%
			}// if문의 끝
%>				
			</tbody>
		</table>
	</div>
</body>
</html>