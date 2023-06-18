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

    <!-- <div class="container py-5">
      <div class="row">
        <div class="col-md-10 text-black">
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
    </div> -->
    <!-- <section class="jumbotron text-center">
      <div class="container">
        <h1 class="jumbotron-heading">学び大学図書館</h1>
        <p class="lead text-muted">知識の拠点、創造の源泉</p>
      </div>
    </section> -->
    <!-- 通常の画像表示 -->
<!-- <img src="images/search.png" class="rounded-circle"> -->

<!-- レスポンシブ -->
<!-- <img src="images/tora.png" class="img-responsive"> -->
<!-- <p class="bg-info text-dark rounded-circle position-relative" style="width:6rem;height:6rem;">
  <span class="position-absolute top-50 start-50 translate-middle text-nowrap">
  </span>
</p> -->

  <h2>
  機能一覧
  </h2>
  <div class="container my-5">
    <div class="row">
      <div class="col-lg-6 col-md-6 col-sm-12">
        <div class="card border-0 shadow">
          <img src="images/search.svg" class="card-img-top rounded" alt="検索" width="64" height="64">
        </div>
      </div>
      <div class="col-lg-6 col-md-6 col-sm-12">
        <div class="card border-0 shadow">
          <img src="images/file-earmark-plus.svg" class="card-img-top rounded" alt="登録" width="64" height="64">
        </div>
      </div>
    </div>
    <div class="row mt-4">
      <div class="col-lg-6 col-md-6 col-sm-12">
        <div class="card border-0 shadow">
          <img src="images/person-plus.svg" class="card-img-top rounded" alt="ユーザ追加" width="64" height="64">
        </div>
      </div>
      <div class="col-lg-6 col-md-6 col-sm-12">
        <div class="card border-0 shadow">
          <img src="images/book.svg" class="card-img-top rounded" alt="図書" width="64" height="64">
        </div>
      </div>
    </div>
  </div>
  
    <!-- <div class="album py-5 bg-light">
      <div class="container">
  
        <div class="row">
          <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
              {% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %} 
              <svg xmlns="http://www.w3.org/2000/svg" width="255" height="255" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"></path>
              </svg>
              <div class="card-body">
                <p class="card-text">学び大学図書館を設立しました。<br>
                  施設情報や営業日などの詳細情報についてはこちらからご確認ください。</p>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">詳細</button>
                  </div>
                  <small class="text-muted">9 mins</small>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
              <img src="images/search.png" width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" />
            </div>
          </div>
          <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
              <img src="images/tomcat.gif" width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" />
              <div class="card-body">
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                  </div>
                  <small class="text-muted">9 mins</small>
                </div>
              </div>
            </div>
          </div>
  
          <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
              {% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %}
              <div class="card-body">
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                  </div>
                  <small class="text-muted">9 mins</small>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
              {% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %}
              <div class="card-body">
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                  </div>
                  <small class="text-muted">9 mins</small>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
              {% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %}
              <div class="card-body">
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                  </div>
                  <small class="text-muted">9 mins</small>
                </div>
              </div>
            </div>
          </div>
  
          <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
              {% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %}
              <div class="card-body">
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                  </div>
                  <small class="text-muted">9 mins</small>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
              {% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %}
              <div class="card-body">
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                  </div>
                  <small class="text-muted">9 mins</small>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
              {% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %}
              <div class="card-body">
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                  </div>
                  <small class="text-muted">9 mins</small>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div> -->
  </body>
</html>
