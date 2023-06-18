<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, library_management_class.Track, library_management_class.Due, library_management_class.Reservation" %>

<!DOCTYPE html>
<html>
    <head>
        <title>貸出詳細</title>
        <%@ include file="head.jsp" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    </head>
    <body class="container mt-5">
        <%@ include file="header.jsp" %>
        <h1 class="mb-4">貸出詳細</h1>
        <!-- ユーザ、貸出、書籍情報一覧の表示 -->
        <table class="table table-striped">
            <tbody>
                <tr>
                    <th>ユーザID</th>
                    <td>${user.userId}</td>
                </tr>
                <tr>
                    <th>ユーザ名</th>
                    <td>${user.userName}</td>
                </tr>
                <tr>
                    <th>書籍ID</th>
                    <td>${book.bookId}</td>
                </tr>
                <tr>
                    <th>書籍名</th>
                    <td>${book.title}</td>
                </tr>
                <tr>
                    <th>貸出日</th>
                    <td>${due.getBorrowDate()}</td>
                </tr>
                <tr>
                    <th>返却予定日</th>
                    <td>${due.getReturnDueDate()}</td>
                </tr>
            </tbody>
        </table>
        <!-- 返却ボタン -->
        <form action="ReturnBook" method="post">
            <input type="hidden" name="book_id" value="${book.bookId}">
            <input type="hidden" name="user_id" value="${user.userId}">
            <input type="hidden" name="track_id" value="${due.trackId}">
            <input type="submit" value="返却" class="btn btn-primary">
    </body>
</html>