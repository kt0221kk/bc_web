<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>
	<head>
	  <meta charset="UTF-8">
	  <title>図書の登録</title>
	</head>
	<body>
	  <form action="<%=request.getContextPath()%>/CallTest" method="post">
		登録したい図書の情報を入力してください
		<br /> 
	
		<label for="title">タイトル:</label>
		<input type="text" name="title" id="title" required />
	
		<br />
	
		<label for="author">著者:</label>
		<input type="text" name="author" id="author" required />
	
		<br />
	
		<label for="status">ステータス (貸出可能: true, 貸出不可: false):</label>
		<input type="text" name="status" id="status" required />
	
		<br />
	
		<label for="start_date">貸出開始日 (yyyy-mm-dd):</label>
		<input type="date" name="start_date" id="start_date" />
	
		<br />
	
		<label for="end_date">貸出終了日 (yyyy-mm-dd):</label>
		<input type="date" name="end_date" id="end_date" />
	
		<br />
	
		<input type="submit" value="登録" />
	  </form>
	</body>
	</html>
	