<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>返却</title>
        <%@ include file="head.jsp" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <h1>返却</h1>
        <p>返却しました。</p>
        <a href="PlotBookData"><input type="submit" class="btn btn-primary" name="back" value="貸出一覧に戻る"></a>
    </body>
</html>