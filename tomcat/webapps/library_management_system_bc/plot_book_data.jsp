<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>書籍情報について検索してください</title>
</head>
<body>
    <h1>最近更新があったデータ</h1>
    <p>
        Book Name: <%= request.getAttribute("book_name") %>
    </p>
</body>
</html>