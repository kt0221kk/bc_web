<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>書籍情報について検索してください</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <link rel="stylesheet" href="css/plot_book_data.css" />
    <%@ page import="java.util.ArrayList, library_management_class.Book" %>
  </head>
  <body>
    <%@ include file="header.jsp" %>

    <h1>書籍情報</h1>
    <!-- 書籍情報のテーブル -->
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">貸し出し状態</th>
          <th scope="col">書籍ID</th>
          <th scope="col">書籍名</th>
          <th scope="col">著者名</th>
          <th scope="col">出版社</th>
          <th scope="col">出版日</th>
          <th scope="col">ISBN</th>
          <th scope="col">ジャンル</th>
        </tr>
      </thead>
      <tbody>
        <!-- ArrayList<Book>から表を作る -->
        <%
          ArrayList<Book> bookList = (ArrayList<Book>)request.getAttribute("bookList");
          for (Book book : bookList) {
        %>
        <tr>
          <!-- Availableなら貸出可能,Resevedなら予約と表示し先頭に色付きの丸で表現する-->
          <td>
            <% if (book.getStatus().equals("貸出可能")) { %>
              <span class="badge bg-success">貸出可能</span>
            <% } else { %>
              <span class="badge bg-danger">貸出中</span>
            <% } %>
          <td><%= book.getBookId() %></td>
          <td><%= book.getTitle() %></td>
          <td><%= book.getAuthor() %></td>
          <td><%= book.getPublisher() %></td>
          <td><%= book.getPublicationYear() %></td>
          <td><%= book.getIsbn() %></td>
          <td><%= book.getGenre() %></td>

        </tr>
        <%
          }
        %>
      </tbody>
  </body>
</html>
