<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>書籍情報について検索してください</title>
    <%@ include file="head.jsp" %>
    
    <link rel="stylesheet" href="css/plot_book_data.css" />
    <%@ page import="java.util.ArrayList, library_management_class.Book" %>
    <script>
      $(document).ready(function(){
        $(".badge").hover(function(){
          // マウスカーソルが要素に重なったときの処理
          $(this).css("color", "gray");
          }, function(){
          // マウスカーソルが要素から離れたときの処理
          $(this).css("color", "white");
        });
      });
      </script>
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
            <% 
              String action_url = "DetailBook?book_id=" + book.getBookId();
              if (book.getStatus().equals("貸出可能")) { 
            %>
              
              <a href="<%= action_url %>" style="text-decoration: none;">
                <span class="badge bg-success">
                    貸出可能
                </span>
              </a>
            <% } else { %>
              <a href="<%= action_url %>" style="text-decoration: none;">

                <span class="badge bg-danger">貸出中</span>
              </a>
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
