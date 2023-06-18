<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>予約のキャンセル</title>
        <%@ include file="head.jsp" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <h1>以下の内容の予約をキャンセルしました</h1>
        <!-- 予約内容のテーブル -->
        <table class="table table-striped">
            <tbody>
                <tr>
                    <th>予約ID</th>
                    <td>${track.trackId}</td>
                </tr>
                <tr>
                    <th>書籍ID</th>
                    <td>${track.bookId}</td>
                </tr>
                <tr>
                    <th>予約者ID</th>
                    <td>${track.userId}</td>
                </tr>
                <tr>
                    <th>予約日</th>
                    <td>${track.reservationStartDate}</td>
                </tr>
                <tr>
                    <th>予約期限</th>
                    <td>${track.reservationEndDate}</td>
                </tr>
            </tbody>
    </body>
</html>