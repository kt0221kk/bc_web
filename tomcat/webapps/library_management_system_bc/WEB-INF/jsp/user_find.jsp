<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="library_management_class.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>ユーザー情報の確認</title>
        <%@ include file="head.jsp" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    </head>
    <body>
        <!-- User name ,id  のテーブル userごと-->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="'col">ユーザID</th>
                    <th scope="'col">ユーザ名</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                </tr>
                </c:forEach>
        </table>

    </body>
</html>