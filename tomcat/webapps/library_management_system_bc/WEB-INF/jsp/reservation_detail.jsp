<!-- 予約情報の確認画面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="library_management_class.Book" %>
<%@ page import="java.util.ArrayList, library_management_class.Track, library_management_class.Due, library_management_class.Reservation" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>ReservationBook</title>
        <%@ include file="head.jsp" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <!-- BootstrapのCSSを読み込む -->
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <h1 class="my-4">予約情報の詳細について</h1>
            <!-- テーブル -->
            <table class="table table-striped">
                <tbody>
                    <tr>
                        <th>予約ID</th>
                        <td>${track.trackId}</td>
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
                        <th>予約者ID</th>
                        <td>${user.userId}</td>
                    </tr>
                    <tr>
                        <th>予約者名</th>
                        <td>${user.userName}</td>
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
            </table>
            <a href="ReservationBook?book_id=${track.bookId}" class="btn btn-primary">前のページに戻る</a>
            <!-- 今日が予約期間内の日付であれば貸出フォームの作成 -->
            <c:if test="${IsDue == true}">
                <form action="ReserveToLend" method="post">
                    <input type="hidden" name="book_id" value="${track.bookId}">
                    <input type="hidden" name="user_id" value="${track.userId}">
                    <input type="hidden" name="track_id" value="${track.trackId}">
                    <input type="submit" value="本を貸出する" class="btn btn-success mt-2">
                </form>
            </c:if>
            <form action="CancelReservation" method="post">
                <input type="hidden" name="book_id" value="${track.bookId}">
                <input type="hidden" name="user_id" value="${track.userId}">
                <input type="hidden" name="track_id" value="${track.trackId}">
                <input type="submit" value="予約をキャンセルする" class="btn btn-success mt-2">
            </form>
        </div>
    </body>
    <!-- 前のページに戻る -->
</html>
