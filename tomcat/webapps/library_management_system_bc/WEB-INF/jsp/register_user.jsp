<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <title>ユーザー登録</title>
        <%@ include file="head.jsp" %>
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="container mt-5">
            <h2 class="mb-4">ユーザー登録</h2>

            <form action="/library_management_system_bc/RegisterUser" method="POST">
                <div class="mb-3">
                    <label for="name" class="form-label">ユーザ名</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">パスワード</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">登録</button>
            </form>
        </div>
    </body>
</html>