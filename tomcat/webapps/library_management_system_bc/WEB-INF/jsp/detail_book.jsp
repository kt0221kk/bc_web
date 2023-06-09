<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="library_management_class.Book" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Details</title>
<%@ include file="head.jsp" %>
<!--jQuery JS-->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" /></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css"> -->


<link rel="stylesheet" type="text/css" href="bootstrap-datepicker-1.9.0-dist/css/bootstrap-datepicker.min.css">
<script type="text/javascript" src="bootstrap-datepicker-1.9.0-dist/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="bootstrap-datepicker-1.9.0-dist/locales/bootstrap-datepicker.ja.min.js"></script>

</head>
<body class="container mt-5">
    <%@ include file="header.jsp" %>
    <jsp:useBean id="book" type="library_management_class.Book" scope="request" />

    <h1 class="mb-4"><%= book.getTitle() %></h1>

    <table class="table table-striped">
        <tbody>
            <tr>
                <th>書籍ID</th>
                <td><%= book.getBookId() %></td>
            </tr>
            <tr>
                <th>書籍名</th>
                <td><%= book.getTitle() %></td>
            </tr>
            <tr>
                <th>作者</th>
                <td><%= book.getAuthor() %></td>
            </tr>
            <tr>
                <th>Publisher</th>
                <td><%= book.getPublisher() %></td>
            </tr>
            <tr>
                <th>書籍情報登録日</th>
                <td><%= book.getCreatedAt() %></td>
            </tr>
            <tr>
                <th>ISBN</th>
                <td><%= book.getIsbn() %></td>
            </tr>
            <tr>
                <th>ジャンル</th>
                <td><%= book.getGenre() %></td>
            </tr>
            <tr>
                <th>貸出状況</th>
                <td><%= book.getStatus() %></td>
            </tr>

            <!-- Add other book details as required -->
        </tbody>
    </table>

    <div class="mt-4">
        <a href="AccessLibraryData" class="btn btn-primary">書籍一覧に戻る</a>
        <!-- <a href="#" class="btn btn-warning" id="reservationButton">予約</a> -->
        <button id="reservationButton" class="btn btn-warning">予約</button>
        <a href="lend.jsp?bookId=<%= book.getBookId() %>" class="btn btn-success">貸出</a>
        <a href="return.jsp?bookId=<%= book.getBookId() %>" class="btn btn-info">返却</a>
    </div>
    

<div class="input-daterange input-group">
    <input type="text" class="input-sm form-control" name="start" />
    <span class="input-group-addon">to</span>
    <input type="text" class="input-sm form-control" name="end" />
</div>
<script>
    var invalidDate = new Date('2023-06-21');
    $('.input-daterange').datepicker({
    todayBtn: "linked",
    multidateSeparator: ",",
    orientation: "right bottom",
    // daysOfWeekHighlighted: "1,5",
    datesDisabled: ['06/10/2023', '06/21/2023'],
    toggleActive: true
}).on('changeDate', function (e) {
    var selectedDate = e.date;
    var startDate = $('.input-daterange').data('datepicker').dates[0];
    var endDate = $('.input-daterange').data('datepicker').dates[1];
    
    if (startDate && endDate) {
        // Convert selected dates to Date objects
        var startDateObj = new Date(startDate);
        var endDateObj = new Date(endDate);

        // Check if invalidDate is within selected range
        if ((invalidDate >= startDateObj && invalidDate <= endDateObj)) {
            setTimeout(function() {
                $('.input-daterange').datepicker('clearDates');
                alert('Invalid range selected. The date 2023-06-21 cannot be within the selected range.');
            }, 1);
        }
    }
});
</script>
</body>
</html>