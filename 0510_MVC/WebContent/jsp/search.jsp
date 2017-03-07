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
		
		if(v1.length > 0){
			f.submit();
		}else{
			alert("검색할 이름을 입력하시오");
			f.name.focus();
		}
	}
</script>
</head>
<body>
	<form action="../MyControl" method="post">
		<fieldset>
			<label for="name">이름:</label>
			<input type="text" name="name" id="name"/>
			<input type="button" value="검색" onclick="exe(this.form)"/>
			<input type="hidden" name="type" value="search"/>
		</fieldset>
	</form>
</body>
</html>