package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginCheck extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    String user = request.getParameter("user");
    String pass = request.getParameter("pass");

    HttpSession session = request.getSession(true);

    boolean check = authUser(user, pass);
    if (check) {
        /* 認証済みにセット */
        session.setAttribute("login", "OK");
        session.setAttribute("user", user);

        /* 本来のアクセス先へ飛ばす */
        String target = (String) session.getAttribute("target");
        
        /* ログインページへのリダイレクトをチェック */
        if (target != null && target.equals("/library_management_system_bc/login.jsp")) {
            response.sendRedirect("/library_management_system_bc/");
        } else if (target != null && target.equals("library_management_system_bc/LoginCheck")) {
            response.sendRedirect("/library_management_system_bc/");
        } else {
            response.sendRedirect(target);
        }
    } else {
        /* 認証に失敗したら、ログイン画面に戻す */
        session.setAttribute("status", "Not Auth");
        response.sendRedirect("/library_management_system_bc/login.jsp");
    }
}


  protected boolean authUser(String user, String pass){
    /* 取りあえずユーザー名とパスワードが入力されていれば認証する */
    if (user == null || user.length() == 0 || pass == null || pass.length() == 0){
      return false;
    }

    return true;
  }
}