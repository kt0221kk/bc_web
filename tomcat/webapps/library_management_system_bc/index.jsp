<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>図書管理サイト</title>
    <%@ include file="WEB-INF/jsp/head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />

  </head>
  <body>
    <div class="overlay"></div>

    <%@ include file="WEB-INF/jsp/header.jsp" %>

    <div class="container py-5">
      <div class="row">
        <div class="col-md-2">
          <img src="images/tomcat.gif" class="img-fluid" alt="Tomcat" />
        </div>
        <div class="col-md-10 text-white">
          <h1>図書管理サイトへ</h1>
          <p>要件に応じて下記のリンクに遷移してください</p>
        </div>
      </div>
      <div class="row mt-4">
        <div class="col-md-12">
          <ul class="list-unstyled">
            <li><a href="RegisterBookUI">図書の登録</a></li>
            <li><a href="AccessLibraryData">書籍情報の検索</a></li>
          </ul>
        </div>
      </div>
    </div>
  </body>
</html>
