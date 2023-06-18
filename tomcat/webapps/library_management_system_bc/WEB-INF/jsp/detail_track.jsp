<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, library_management_class.Track, library_management_class.Due, library_management_class.Reservation" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Track Details</title>
    <%@ include file="head.jsp" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <link rel="stylesheet" type="text/css" href="bootstrap-datepicker-1.9.0-dist/css/bootstrap-datepicker.min.css">
    <script type="text/javascript" src="bootstrap-datepicker-1.9.0-dist/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="bootstrap-datepicker-1.9.0-dist/locales/bootstrap-datepicker.ja.min.js"></script>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js'></script>
    <script>
        var trackList = [
            <c:forEach var="trackData" items="${trackDataList}">
                {
                    title: "${trackData.title}",
                    start: "${trackData.start}",
                    color: "${trackData.color}",
                    end: "${trackData.end}",
                    textColor: "${trackData.textColor}",
                    url: "${trackData.url}",
                },
            </c:forEach>
        ];
        
    </script>
    <script>
        var disabledDateList = [
            <c:forEach var="disabledDate" items="${disabledDateList}">
            "${disabledDate}",
            </c:forEach>
        ];
        var occupiedDates = [
            <c:forEach var="disabledDate" items="${occupiedDates}">
            "${disabledDate}",
            </c:forEach>
        ];
    </script>
    <!-- <script src="js/detail_book_calendar.js"></script> -->
</head>
    <%@ include file="header.jsp" %>
    <body class="container mt-5">
    <jsp:useBean id="book" type="library_management_class.Book" scope="request" />
    <jsp:useBean id="track" type="library_management_class.Track" scope="request" />
    <jsp:useBean id="user" type="library_management_class.User" scope="request" />
        <h1 class="mb-4">選択手続き内容
        </h1>
        <table class="table table-striped">
            <tbody>
                <tr>
                    <th>手続き状態</th>
                    <td><%=track.getTrackStatus() %></td>
                </tr>
            </tbody>
            <tbody>
                <tr>
                    <th>書籍ID</th>
                    <td><%= book.getBookId() %></td>
                </tr>
            </tbody>
            <tbody>
                <tr>
                    <th>書籍名</th>
                    <td><%= book.getTitle() %></td>
                </tr>
            </tbody>
            <c:if test="${track.getTrackStatus() == '貸出'}">
                <tbody>
                    <tr>
                        <th>ユーザーID</th>
                        <td><%= track.getUserId() %></td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                        <th>ユーザー名</th>
                        <td><%= user.getUserName() %></td>
                    </tr>
                <tbody>
                    <tr>
                        <th>貸出日</th>
                        <td><%= ((Due)track).getBorrowDate() %></td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                        <th>返却予定日</th>
                        <td><%= ((Due)track).getReturnDueDate() %></td>
                    </tr>
                </tbody>
            </c:if>
        </table>
        <!-- もしtrackのステータスが貸出なら本を返却する -->
        <c:if test="${track.getTrackStatus() == '貸出'}">
            <form action="return_book" method="post">
                <input type="hidden" name="trackId" value="${track.getTrackId()}">
                <input type="hidden" name="bookId" value="${book.getBookId()}">
                <input type="submit" value="返却" class="btn btn-primary">
            </form>
        </c:if>
    

        <div class="input-daterange input-group">
            <input type="text" class="input-sm form-control" name="start" placeholder="貸出開始日"/>
            <span class="input-group-addon"></span>
            <input type="text" class="input-sm form-control" name="end" placeholder="返却予定日"/>
        </div>
    <h1 class="mb-4">書籍貸出状況</h1>
    <div id='calendar'></div>
</body>
</html>
<script src="js/detail_track.js"></script>
