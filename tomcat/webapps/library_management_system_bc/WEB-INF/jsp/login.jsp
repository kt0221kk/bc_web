<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.io.*" %>
<html>
<head>
<title>ログインページ</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>


<%
HttpSession mySession = request.getSession(true);

/* 認証失敗から呼び出されたのかどうか */
Object status = mySession.getAttribute("status");

if (status != null){
%>
  <p>認証に失敗しました</p>
  <p>再度ユーザー名とパスワードを入力して下さい</p>
<%
mySession.setAttribute("status", null);
}
%>

<!-- <form method="POST" action="/library_management_system_bc/LoginCheck" name="loginform">
<table>
<tr>
<td>ユーザー名</td>
<td><input type="text" name="user" size="32"></td>
</tr>
<tr>
<td>パスワード</td>
<td><input type="password" name="pass" size="32"></td>
</tr>
<tr>
<td><input type="submit" value="login"></td>
<td><input type="reset" value="reset"></td>
</tr>
</table>
</form> -->
<div id="login">
    <h3 class="text-center text-white pt-5">Login form</h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form method="POST" action="/library_management_system_bc/LoginCheck" name="loginform"> 
                        <h3 class="text-center text-info">Login</h3>
                        <div class="form-group">
                            <label for="username" class="text-info">Username:</label><br>
                            <input type="text" name="user" id="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Password:</label><br>
                            <input type="password" name="pass" id="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                        </div>
                        <div id="register-link" class="text-right">
                            <a href="RegisterUserUI" class="text-info">Register here</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
