<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
	function exe(f){
		var v1 = f.name.value.trim();
		var v2 = f.id.value.trim();
		var v3 = f.pwd.value.trim();
		var v4 = f.email.value.trim();
		
		if(v1.length < 1){
			alert("검색할 이름을 입력하시오");
			f.name.focus();
			return;
		}
		
		if(v2.length < 1){
			alert("추가할 아이디를 입력하시오");
			f.id.focus();
			return;
		}
		
		if(v3.length < 1){
			alert("추가할 비밀번호를 입력하시오");
			f.pwd.focus();
			return;
		}
		
		if(v4.length < 1){
			alert("추가할 이메일을 입력하시오");
			f.email.focus();
			return;
		}
		
		f.submit();
		
	}
</script>
</head>
<body>
	<form action="../MyControl" method="post">
		<fieldset>
			<label for="name">이름:</label>
			<input type="text" name="name" id="name"/><br/>
			<label for="id">아이디:</label>
			<input type="text" name="id" id="id"/><br/>
			<label for="pwd">비밀번호:</label>
			<input type="password" name="pwd" id="pwd"/><br/>
			<label for="email">이메일:</label>
			<input type="text" name="email" id="email"/><br/>
			<input type="button" value="저장" onclick="exe(this.form)"/>
			<input type="reset" value="취소"/>
			<input type="hidden" name="type" value="add"/>
		</fieldset>
	</form>
</body>
</html>