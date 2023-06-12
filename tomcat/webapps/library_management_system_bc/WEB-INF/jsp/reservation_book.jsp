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
</head>
<body class="container mt-5">
    <%@ include file="header.jsp" %>

    <h1 class="mb-4">書籍の貸出または予約
    </h1>
    <jsp:useBean id="book" type="library_management_class.Book" scope="request" />
    <form action="ReservationBook" method="post">
    <table class="table table-striped">
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
        
        <tbody>
            <tr>
                <th>書籍貸出期間
                </th>
                <td><div class="input-daterange input-group">
                    <input type="text" class="input-sm form-control" id="start_date" name="start" placeholder="貸出開始日"required/>
                    <span class="input-group-addon"></span>
                    <input type="text" id="end_date" class="input-sm form-control" name="end" placeholder="返却予定日" required/>
                </div></td>
            </tr>
        </tbody>
        <tbody>
            <tr>
                <th>貸出の種類
                </th>
                <td>
                    <!-- <input type="text" class="input-sm text" name="book_status" value=""readonly/> -->
                    <input type="text" class="input-sm text" id="book_status" name="book_status" value="" readonly/>
                </td>
            </tr>
        </tbody>
        <tbody>
            <tr>
                <th>ユーザID
                </th>
                <td>
                    <input type="number" class="input-sm text" name="user_id" value="" required/>
                </td>
            </tr>
        </tbody>
        
    </table>
    <input type="hidden" name="book_id" value="<%= book.getBookId() %>">
    <input type="hidden" name="title" value="<%= book.getTitle() %>">

    <input type="submit" class="btn btn-primary" value="貸出または予約">
    </form>
    <h2 class="mb-4">書籍の貸出状況</h2>
    <div id='calendar'></div>
</body>
</html>
<script src="js/reservation_book.js"></script>
