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
    <section class="jumbotron text-center">
      <div class="container">
        <h1 class="jumbotron-heading">学び大学図書館</h1>
        <p class="lead text-muted">知識の拠点、創造の源泉</p>
      </div>
    </section>
    機能一覧的なリンク集（仮）
  <h2>
    News
  </h2>
    <div class="album py-5 bg-light">
      <div class="container">
  
        <div class="row">
          <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
              <!-- {% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %} -->
              <img src="images/tora.png" width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" />
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
              <img src="images/book_rental.png" width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" />
              
              <div class="card-body">
                <p class="card-text">本の貸し借りページを開設しました。本を借りたい場合はこちらのサイトから貸し借りをお願いします</p>
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
    </div>
  </body>
</html>
