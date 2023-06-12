<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="library_management_class.Book" %>
<%@ page import="java.util.ArrayList, library_management_class.Track, library_management_class.Due, library_management_class.Reservation" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ReservationBook</title>
    <%@ include file="head.jsp" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <link rel="stylesheet" type="text/css" href="bootstrap-datepicker-1.9.0-dist/css/bootstrap-datepicker.min.css">
    <script type="text/javascript" src="bootstrap-datepicker-1.9.0-dist/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="bootstrap-datepicker-1.9.0-dist/locales/bootstrap-datepicker.ja.min.js"></script>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js'></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
</head>
<body class="container mt-5">
    <%@ include file="header.jsp" %>

    <h1 class="mb-4">以下の内容で手続き完了しました
    </h1>
    <jsp:useBean id="book" type="library_management_class.Book" scope="request" />
    <table class="table table-striped">
        <tbody>
            <tr>
                <th>書籍ID</th>
                <td><c:out value="${book.getBookId()}"></c:out></td>
            </tr>
        </tbody>
        <tbody>
            <tr>
                <th>書籍名</th>
                <td><c:out value="${book.getTitle()}"></c:out></td>
            </tr>
        </tbody>
        <tbody>
            <tr>
                <th>手続き内容</th>
                <td><c:out value="${book_status}"></c:out></td>
            </tr>
        </tbody>
        <tbody>
            <tr>
                <th>ユーザー名</th>
                <td><c:out value="${user.getUserName()}"></c:out></td>
            </tr>
        </tbody>
        <tbody>
            <tr>
                <th>開始日</th>
                <td><c:out value="${start}"></c:out></td>
            </tr>
        </tbody>
        <tbody>
            <tr>
                <th>終了日</th>
                <td><c:out value="${end}"></c:out></td>
            </tr>
        </tbody>
    </table>
    <!-- 前のページに戻る -->
    <div class="row">
        <div class="col-2">
            <a href="/library_management_system_bc/PlotBookData"><input type="submit" class="btn btn-primary" name="back" value="前のページに戻る"></a>
        </div>
        <div class="col-2">
            <input type="submit" class="btn btn-primary" name="top" value="トップページに戻る">
        </div>
    </div>
</html> 