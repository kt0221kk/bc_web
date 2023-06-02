<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>図書管理サイト</title>
  <!-- Bootstrap CSS -->
    <style>
      body {
        background: url('images/library.jpg') no-repeat center center fixed; 
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
        position: relative;  /* Add this */
      }

      .overlay {
        background-color: rgba(0, 0, 0, 0.5);  /* change this as per your need */
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
      }
  </style>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>
<body>
  <div class="overlay"></div>

  <%@ include file="header.jsp" %>


  <div class="container py-5">
    <div class="row">
      <div class="col-md-2">
        <img src="images/tomcat.gif" class="img-fluid" alt="Tomcat">
      </div>
      <div class="col-md-10 text-white">
        <h1>図書管理サイトへ</h1>
        <p>要件に応じて下記のリンクに遷移してください</p>
      </div>
    </div>
    <div class="row mt-4">
      <div class="col-md-12">
        <ul class="list-unstyled">
          <li><a href="hello" >図書の登録</a></li>
          <li><a href="AccessLibraryData" >書籍情報の検索</a></li>
          <li><a href="logout">ログアウト</a></li>

        </ul>
      </div>
    </div>
  </div>

</body>
</html>
