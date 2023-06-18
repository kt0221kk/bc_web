<%@ page contentType="text/html; charset=UTF-8" %>
<meta name="viewport" content="width=device-width, initial-scale=1">

<html>
    <head>
        <title>書籍登録</title>
        <%@ include file="head.jsp" %>
        <link rel="stylesheet" href="css/register_book.css">
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="container mt-5">
            <h2 class="mb-4">書籍登録</h2>
            書籍登録を行います。ISBNを入力してください。<br>
            ISBNを入力し書籍情報を取得を押す書籍情報が表示されます。<br>
            <div class="card" style="width: 18rem; display:none;" id="card_book">
                <img class="card-img-top" src="" id="thumbnail" alt="Book cover">
                <div class="card-body">
                  <p class="card-text" id="description">Book description</p>
                </div>
              </div>
         <div>


            <form action="/library_management_system_bc/RegisterBook" method="POST">
                <div class="mb-3">
                    <label type="number" for="isbn" class="form-label">ISBN</label>
                    <input type="text" class="form-control" id="isbn" name="ISBN" required>
                    <button id="getBookInfo" class="btn btn-primary">書籍情報を取得</button>

                </div>
                <div id="inputFields" style="display: none;">
                    <div class="mb-3">
                        <label for="title" class="form-label">タイトル</label>
                        <input type="text" class="form-control" id="title" name="title" required>
                    </div>

                    <div class="mb-3">
                        <label for="author" class="form-label">著者</label>
                        <input type="text" class="form-control" id="author" name="author" required>
                    </div>

                    <div class="mb-3">
                        <label for="genre" class="form-label">ジャンル</label>
                        <!-- <input type="text" class="form-control" id="genre" name="genre" required> -->
                        <!-- ジャンルはセレクトボタンで選択、手入力も可能 -->
                        <select class="form-control" id="genre" name="genre" required>
                            <option value="文学・評論">文学・評論</option>
                            <option value="ノンフィクション">ノンフィクション</option>
                            <option value="ビジネス・経済">ビジネス・経済</option>
                            <option value="歴史・地理">歴史・地理</option>
                            <option value="芸能・エンターテイメント">芸能・エンターテイメント</option>
                            <option value="アート・建築・デザイン">アート・建築・デザイン</option>
                            <option value="人文・思想・宗教">人文・思想・宗教</option>
                            <option value="暮らし・健康・料理">暮らし・健康・料理</option>
                            <option value="サイエンス・テクノロジー">サイエンス・テクノロジー</option>
                            <option value="趣味・実用">趣味・実用</option>
                            <option value="教育・自己啓発">教育・自己啓発</option>
                            <option value="スポーツ・アウトドア">スポーツ・アウトドア</option>
                            <option value="辞典・年鑑・本・ことば">辞典・年鑑・本・ことば</option>
                            <option value="音楽">音楽</option>
                            <option value="旅行・紀行">旅行・紀行</option>
                            <option value="絵本・児童書">絵本・児童書</option>
                            <option value="コミックス">コミックス</option>
                            <option value="ライトノベル">ライトノベル</option>
                            <option value="ボーイズラブ">ボーイズラブ</option>
                            <option value="その他">その他</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="status" class="form-label">状態</label>
                        <select class="form-control" id="status" name="status" required>
                            <option value="貸出可能">貸出可能</option>
                            <option value="貸出中">貸出中</option>
                            <option value="閲覧のみ可能">閲覧のみ可能</option>
                            <option value="その他">その他</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="publication_year" class="form-label">出版年</label>
                        <input type="number" class="form-control" id="publication_year" name="publication_year" required>
                    </div>

                    

                    <div class="mb-3">
                        <label for="publisher" class="form-label">出版社</label>
                        <input type="text" class="form-control" id="publisher" name="publisher" required>
                    </div>
                    

                    <button type="submit" class="btn btn-primary">登録</button>
                </div>
            </form>
        </div>
          
    </body>
</html>
<!-- js -->
<script type="text/javascript" src="js/register_book.js"></script>
