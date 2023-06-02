<%@ page contentType="text/html; charset=UTF-8" %>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
        <a class="navbar-brand" href="/library_management_system_bc">Library</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">検索</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="#">登録</a>
            </li>
            <li class="nav-item">
                <%
                    HttpSession mySession = request.getSession(false);
                    String user = mySession.getAttribute("user")
                    if (mySession != null && mySession.getAttribute("user") != null){
                %>
                    <a class="nav-link active" href="logoutServlet" >ログアウト</a>
                <%
                    } else {
                %>
                    <a class="nav-link active" href="login.jsp" >ログイン</a>
                <%
                    }
                %>
            
                
            </li>
            </ul>
            <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
        </div>
    </nav>
</header>