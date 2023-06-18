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

    <%@ include file="WEB-INF/jsp/header.jsp" %>
    <section class="jumbotron text-center">
      <div class="container">
        <h1 class="jumbotron-heading">学び大学図書 図書管理システム</h1>
        <p class="lead text-muted">知識の拠点、創造の源泉</p>

      </div>
    </section>
  <div class="container mt-5">
    <div class="row">
        <div class="col-lg-6">
            <a href="PlotBookData?page=1" class="no-link-style">
            <div class="card feature-card mb-4">
                <div class="feature-img-wrapper">
                    <img src="images/search.svg" alt="検索" class="feature-img">
                </div>
                <div class="card-body">
                  <h5 class="card-title">検索</h5>
                  <p class="card-text">本を素早く見つけることができます。</p>
              </div>
            </div>
            </a>
        </div>
        <div class="col-lg-6">
            <a href="RegisterBookUI" class="no-link-style">
              <div class="card feature-card mb-4">
                  <div class="feature-img-wrapper">
                      <img src="images/file-earmark-plus.svg" alt="登録" class="feature-img">
                  </div>
                  <div class="card-body">
                    <h5 class="card-title">書籍追加</h5>
                    <p class="card-text">新しい書籍を追加することができます</p>
                </div>
              </div>
            </a>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">
          <a href="RegisterUserUI" class="no-link-style">

            <div class="card feature-card">
                <div class="feature-img-wrapper">
                    <img src="images/person-plus.svg" alt="ユーザ登録" class="feature-img">
                </div>
                <div class="card-body">
                  <h5 class="card-title">ユーザー登録</h5>
                  <p class="card-text">ユーザ情報を登録することができます</p>
              </div>
            </div>
          </a>
        </div>
        <div class="col-lg-6">
            <div class="card feature-card">
                <div class="feature-img-wrapper">
                    <img src="images/person-circle.svg" alt="図書" class="feature-img">
                </div>
                <div class="card-body">
                  <!-- ログインしていれば -->
                  <h5 class="card-title">マイページ</h5>
                  <p class="card-text">ユーザ情報を確認できます</p>
              </div>
            </div>
        </div>
    </div>
  </div>
  


  </body>
</html>
