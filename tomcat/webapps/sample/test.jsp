<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ServletのPost</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/CallTest" method="post">
		登録したい図書を入力してください
		<br /> 
		<input type="text" name="fromJsp" value="たまごクラブ4月号" />
		<br />
		<input type="submit" value="実行" />
	</form>
</body>
</html>