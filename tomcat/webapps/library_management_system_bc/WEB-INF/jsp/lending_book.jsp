<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>LendingBook</title>
        <%@ include file="head.jsp" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <h1 class="mb-4">以下の内容で貸出処理が完了しました</h1>    
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
                    <td><c:out value="${book.status}"></c:out></td>
                </tr>
            </tbody>
            <tbody>
                <tr>
                    <th>手続きID</th>
                    <td><c:out value="${due.trackId}"></c:out></td>
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
                    <td><c:out value="${due.getBorrowDate()}"></c:out></td>
                </tr>
            </tbody>
            <tbody>
                <tr>
                    <th>終了日</th>
                    <td><c:out value="${due.getReturnDueDate()}"></c:out></td>
                </tr>
            </tbody>
        </table>
        <!-- 前のページに戻る -->
        <div class="row">
            <div class="col-2">
                <a href="/library_management_system_bc/PlotBookData"><input type="submit" class="btn btn-primary" name="back" value="前のページに戻る"></a>
            </div>
            <div class="col-2">
                <a href="/library_management_system_bc"><input type="submit" class="btn btn-primary" name="top" value="トップページに戻る"></a>
            </div>
        </div>
    </body>
</html> 