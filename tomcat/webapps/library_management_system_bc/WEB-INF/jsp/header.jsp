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
                <a class="nav-link active" aria-current="page" href="AccessLibraryData">検索</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                登録
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown1">
                    <li><a class="dropdown-item" href="RegisterBookUI">書籍登録</a></li>
                    <li><a class="dropdown-item" href="RegisterUserUI">ユーザ登録</a></li>
                </ul>
            </li>
                
            </li>
            </ul>
            <!-- 右側に「ようこそユーザ名さん」と表示 -->
            <div class="d-flex">
                <%
                HttpSession mySession = request.getSession(false);
                if (mySession != null) {
                    String user = (String) mySession.getAttribute("user");
                    if (user != null){ %>
                        <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ようこそ <%= user %> さん
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown2">
                            <li><a class="dropdown-item" href="RegisterBookUI">マイページ</a></li>
                            <li><a class="dropdown-item" href="logout">ログアウト</a></li>
                        </ul>
                        <%
                    } else {
                %>
                    <a class="nav-link active" href="login.jsp" >ログイン</a>
                <%
                    }
                }
                %>
            </div>
            
        </div>
    </nav>
</header>