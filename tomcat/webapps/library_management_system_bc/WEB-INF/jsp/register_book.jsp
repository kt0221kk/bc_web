<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <title>書籍登録</title>
        <%@ include file="head.jsp" %>
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="container mt-5">
            <h2 class="mb-4">書籍登録</h2>

            <form action="/library_management_system_bc/RegisterBook" method="POST">
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
                    <input type="text" class="form-control" id="genre" name="genre" required>
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
                    <label for="ISBN" class="form-label">ISBN</label>
                    <input type="text" class="form-control" id="ISBN" name="ISBN" required>
                </div>

                <div class="mb-3">
                    <label for="publisher" class="form-label">出版社</label>
                    <input type="text" class="form-control" id="publisher" name="publisher" required>
                </div>

                <button type="submit" class="btn btn-primary">登録</button>
            </form>
        </div>
    </body>
</html>
